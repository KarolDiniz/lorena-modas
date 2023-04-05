package br.edu.ifpb.mt.dac.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.mt.dac.entities.Compra;

public class CompraDAO extends DAO {

    public void salvar(Compra compra) throws PersistenciaDacException{
    	EntityManager entityManager = getEntityManager();
    	EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
    	try {
			 entityManager.persist(compra);
			 transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar salvar esta compra.", pe);
		} finally {
			entityManager.close();
		}
       
    }
    public void atualizar(Compra compra) {
    	EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(compra);
        entityManager.getTransaction().commit();
    }

    public void remove(Compra compra)  throws PersistenciaDacException{
    	EntityManager entityManager = getEntityManager();
    	EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		try {
			compra = entityManager.find(Compra.class, compra.getId());
			entityManager.remove(compra);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar salvar a compra.", pe);
		} finally {
			entityManager.close();
		}
        
    }

    public Compra obterID(Long id) {
    	EntityManager entityManager = getEntityManager();
        return entityManager.find(Compra.class, id);
    }

    public List<Compra> getCompras() throws PersistenciaDacException {    
    	EntityManager entityManager = getEntityManager();
        List<Compra> resultado = null;
  		try {
  			TypedQuery<Compra> query = entityManager.createQuery("SELECT u FROM Compra u", Compra.class);
  			resultado = query.getResultList();
  		} catch (PersistenceException pe) {
  			pe.printStackTrace();
  			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar todos os usuários.", pe);
  		} finally {
  			entityManager.close();
  		}
  		return resultado;
  	}
}

