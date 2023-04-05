package br.edu.ifpb.mt.dac.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.mt.dac.entities.Cliente;
import java.util.List;

public class ClienteDAO extends DAO {

    public void salvar(Cliente cliente) throws PersistenciaDacException{
    	EntityManager entityManager = getEntityManager();
    	EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
    	try {
			 entityManager.persist(cliente);
			 transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar salvar este cliente.", pe);
		} finally {
			entityManager.close();
		}
       
    }

    public void atualizar(Cliente cliente)  throws PersistenciaDacException{
    	EntityManager entityManager = getEntityManager();
    	EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
        entityManager.merge(cliente);
        entityManager.getTransaction().commit();
    }

    public void remove(Cliente cliente)  throws PersistenciaDacException{
    	EntityManager entityManager = getEntityManager();
    	EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		try {
			cliente = entityManager.find(Cliente.class, cliente.getId());
			entityManager.remove(cliente);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar remover este cliente.", pe);
		} finally {
			entityManager.close();
		}
        
    }

    public Cliente getObterID(Long id)  throws PersistenciaDacException{
    	EntityManager entityManager = getEntityManager();
        return entityManager.find(Cliente.class, id);
    }

    public List<Cliente> getClientes()throws PersistenciaDacException {  
      EntityManager entityManager = getEntityManager();
      List<Cliente> resultado = null;
		try {
			TypedQuery<Cliente> query = entityManager.createQuery("SELECT u FROM Cliente u", Cliente.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar todos os clientes.", pe);
		} finally {
			entityManager.close();
		}
		return resultado;
	}
}

