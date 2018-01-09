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

	/**
	 * Creates a new instance of the class represented by RootDao object
	 * 
	 * @param clazz
	 *            RootDao class or its subclasses
	 * @return a newly allocated instance of RootDao
	 * 
	 * @throws RuntimeException
	 *             if any Exception occurs
	 */
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

	/**
	 * Close entityManager if it is not null and open
	 */
	public void closeContext() {
		if (em != null && em.isOpen()) {
			em.close();
		}
	}

	/**
	 * Creates a new instance of RootDao class if defined instance is null
	 * 
	 * @return RootDao instance
	 */
	public RootDao getRootDao() {
		if (rootDao == null) {
			rootDao = instantiateDao(ROOT_DAO);
		}
		return rootDao;
	}

	/**
	 * Creates a new instance of ServerAccessLogDao class if defined instance is
	 * null
	 * 
	 * @return ServerAccessLogDao instance
	 */
	public ServerAccessLogDao getServerAccessLogDao() {
		if (serverAccessLogDao == null) {
			serverAccessLogDao = (ServerAccessLogDao) instantiateDao(SERVER_ACCCESS_LOG_DAO);
		}
		return serverAccessLogDao;
	}

	/**
	 * Creates a new instance of ServerAccessBlockedDao class if defined instance is
	 * null
	 * 
	 * @return ServerAccessBlockedDao instance
	 */
	public ServerAccessBlockedDao getServerAccessBlockedDao() {
		if (serverAccessBlockedDao == null) {
			serverAccessBlockedDao = (ServerAccessBlockedDao) instantiateDao(SERVER_ACCCESS_BLOCKED_DAO);
		}
		return serverAccessBlockedDao;
	}

}
