package br.edu.ifpb.mt.dac.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.edu.ifpb.mt.dac.entities.Cliente;
import br.edu.ifpb.mt.dac.entities.Produto;

public class ProdutoDAO extends DAO{
	
	    public void salvar(Produto produto) throws PersistenciaDacException{
	    	EntityManager entityManager = getEntityManager();
	    	EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
	    	try {
				 entityManager.persist(produto);
				 transaction.commit();
			} catch (PersistenceException pe) {
				pe.printStackTrace();
				if (transaction.isActive()) {
					transaction.rollback();
				}
				throw new PersistenciaDacException("Ocorreu algum erro ao tentar salvar o produto.", pe);
			} finally {
				entityManager.close();
			}
	       
	    }

	    public void atualizar(Produto produto)  throws PersistenciaDacException{
	    	EntityManager entityManager = getEntityManager();
	    	EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
	        entityManager.merge(produto);
	        entityManager.getTransaction().commit();
	    }

	    public void remove(Produto produto)  throws PersistenciaDacException{
	    	EntityManager entityManager = getEntityManager();
	    	EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			try {
				produto = entityManager.find(Produto.class, produto.getId());
				entityManager.remove(produto);
				transaction.commit();
			} catch (PersistenceException pe) {
				pe.printStackTrace();
				if (transaction.isActive()) {
					transaction.rollback();
				}
				throw new PersistenciaDacException("Ocorreu algum erro ao tentar remover um produto.", pe);
			} finally {
				entityManager.close();
			}
	        
	    }

	    public Produto obterID(Long id)  throws PersistenciaDacException{
	    	EntityManager entityManager = getEntityManager();
	        return entityManager.find(Produto.class, id);
	    }

	    public List<Produto> getProdutos() throws PersistenciaDacException { 
	      EntityManager entityManager = getEntityManager();
	      List<Produto> resultado = null;
			try {
				TypedQuery<Produto> query = entityManager.createQuery("SELECT u FROM Produto u", Produto.class);
				resultado = query.getResultList();
			} catch (PersistenceException pe) {
				pe.printStackTrace();
				throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar todos os produtos.", pe);
			} finally {
				entityManager.close();
			}
			return resultado;
		}

}
