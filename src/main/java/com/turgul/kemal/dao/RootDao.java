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

	public <T> void save(T persistentObject) {
		try {
			beginTransaction();
			em.persist(persistentObject);
			commit();
		} finally {
			rollbackIfTransactionActive();
		}
	}

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

	public <T> void delete(T persistentObject) {
		try {
			beginTransaction();
			em.remove(em.merge(persistentObject));
			commit();
		} finally {
			rollbackIfTransactionActive();
		}
	}

	public <T> void saveForBatch(T persistentObject) {
		em.persist(persistentObject);
	}

	public <T> T find(Class<T> classT, Object primaryKey) {
		return em.find(classT, primaryKey);
	}

	public void beginTransaction() {
		em.getTransaction().begin();
	}

	public void commit() {
		em.getTransaction().commit();
	}

	public void rollbackIfTransactionActive() {
		if (em != null && em.getTransaction().isActive()) {
			em.getTransaction().rollback();
		}
	}

}
