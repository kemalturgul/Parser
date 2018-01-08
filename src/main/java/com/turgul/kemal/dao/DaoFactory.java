package com.turgul.kemal.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;

import com.turgul.kemal.Constants;

/**
 * @author kemalturgul
 * @date Jan 6, 2018
 */
public class DaoFactory {

	public static Class<RootDao> ROOT_DAO = RootDao.class;
	public static Class<ServerAccessLogDao> SERVER_ACCCESS_LOG_DAO = ServerAccessLogDao.class;
	public static Class<ServerAccessBlockedDao> SERVER_ACCCESS_BLOCKED_DAO = ServerAccessBlockedDao.class;

	private RootDao rootDao;
	private ServerAccessLogDao serverAccessLogDao;
	private ServerAccessBlockedDao serverAccessBlockedDao;

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(Constants.PERSISTENCE_UNIT_NAME);

	private EntityManager em;

	private DaoFactory() {
	}

	public static DaoFactory getInstance() {
		DaoFactory f = new DaoFactory();
		f.em = emf.createEntityManager();
		f.em.setFlushMode(FlushModeType.COMMIT);
		return f;
	}

	private RootDao instantiateDao(Class<? extends RootDao> clazz) {
		RootDao dao;
		try {
			dao = (RootDao) clazz.newInstance();
			dao.setEntityManager(em);

			return dao;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void closeContext() {
		if (em != null && em.isOpen()) {
			em.close();
		}
	}

	public void closeEntityManager() {
		if (emf != null && emf.isOpen()) {
			emf.close();
		}
	}

	public RootDao getRootDao() {
		if (rootDao == null) {
			rootDao = instantiateDao(ROOT_DAO);
		}
		return rootDao;
	}

	public ServerAccessLogDao getServerAccessLogDao() {
		if (serverAccessLogDao == null) {
			serverAccessLogDao = (ServerAccessLogDao) instantiateDao(SERVER_ACCCESS_LOG_DAO);
		}
		return serverAccessLogDao;
	}

	public ServerAccessBlockedDao getServerAccessBlockedDao() {
		if (serverAccessBlockedDao == null) {
			serverAccessBlockedDao = (ServerAccessBlockedDao) instantiateDao(SERVER_ACCCESS_BLOCKED_DAO);
		}
		return serverAccessBlockedDao;
	}

}
