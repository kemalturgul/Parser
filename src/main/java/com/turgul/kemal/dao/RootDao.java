package com.turgul.kemal.dao;

import javax.persistence.EntityManager;

/**
 * @author kemalturgul
 * @date Jan 6, 2018
 */
public class RootDao {

	protected EntityManager em;

	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	public EntityManager getEntityManager() {
		return em;
	}

	/**
	 * Save entity instance in DB
	 * 
	 * @param persistentObject
	 *            entity instance to save in DB
	 */
	public <T> void save(T persistentObject) {
		try {
			beginTransaction();
			em.persist(persistentObject);
			commit();
		} finally {
			rollbackIfTransactionActive();
		}
	}

	/**
	 * 
	 * @param persistentObject
	 *            entity instance to update in DB
	 * @return the managed instance that the state was merged to
	 */
	public <T> T update(T persistentObject) {
		T persistentObjectLocal = null;
		try {
			beginTransaction();
			persistentObjectLocal = em.merge(persistentObject);
			commit();
		} finally {
			rollbackIfTransactionActive();
		}
		return persistentObjectLocal;
	}

	/**
	 * 
	 * @param persistentObject
	 *            entity instance to delete from DB
	 */
	public <T> void delete(T persistentObject) {
		try {
			beginTransaction();
			em.remove(em.merge(persistentObject));
			commit();
		} finally {
			rollbackIfTransactionActive();
		}
	}

	/**
	 * 
	 * @param persistentObject
	 *            entity instance to add in batchList for saving in DB
	 */
	public <T> void saveForBatch(T persistentObject) {
		em.persist(persistentObject);
	}

	/**
	 * 
	 * @param classT
	 *            entity class
	 * @param primaryKey
	 *            primary key to search
	 * @return the found entity instance or null if the entity does not exist
	 */
	public <T> T find(Class<T> classT, Object primaryKey) {
		return em.find(classT, primaryKey);
	}

	/**
	 * Starting transaction
	 */
	public void beginTransaction() {
		em.getTransaction().begin();
	}

	/**
	 * Saving changes and ending transaction
	 */
	public void commit() {
		em.getTransaction().commit();
	}

	/**
	 * Rolling back transaction is active after commit
	 */
	public void rollbackIfTransactionActive() {
		if (em != null && em.getTransaction().isActive()) {
			em.getTransaction().rollback();
		}
	}

}
