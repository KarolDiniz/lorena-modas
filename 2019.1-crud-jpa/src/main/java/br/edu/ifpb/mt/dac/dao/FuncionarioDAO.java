package br.edu.ifpb.mt.dac.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.mt.dac.entities.Cliente;
import br.edu.ifpb.mt.dac.entities.Funcionario;

public class FuncionarioDAO extends DAO{

    public void salvar(Funcionario funcionario) throws PersistenciaDacException{
    	EntityManager entityManager = getEntityManager();
    	EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
    	try {
			 entityManager.persist(funcionario);
			 transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar salvar o funcionario.", pe);
		} finally {
			entityManager.close();
		}
       
    }

    public void atualizar(Funcionario funcionario) throws PersistenciaDacException{
    	EntityManager entityManager = getEntityManager();
    	EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
        entityManager.merge(funcionario);
        entityManager.getTransaction().commit();
    }

    public void remove(Funcionario funcionario)  throws PersistenciaDacException{
    	EntityManager entityManager = getEntityManager();
    	EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		try {
			funcionario = entityManager.find(Funcionario.class, funcionario.getId());
			entityManager.remove(funcionario);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar remover o funcionario.", pe);
		} finally {
			entityManager.close();
		}
        
    }

    public Funcionario getObterID(Long id) throws PersistenciaDacException{
    	EntityManager entityManager = getEntityManager();
        return entityManager.find(Funcionario.class, id);
    }

    public List<Funcionario> getFuncionarios() throws PersistenciaDacException {      
    	EntityManager entityManager = getEntityManager();
    	List<Funcionario> resultado = null;
		try {
			TypedQuery<Funcionario> query = entityManager.createQuery("SELECT u FROM Funcionario u", Funcionario.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar todos os funcionarios.", pe);
		} finally {
			entityManager.close();
		}
		return resultado;
	}
}
