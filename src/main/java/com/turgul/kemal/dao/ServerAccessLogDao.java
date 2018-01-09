package com.turgul.kemal.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import com.turgul.kemal.Constants;
import com.turgul.kemal.model.ServerAccessLog;
import com.turgul.kemal.util.DateUtil;

/**
 * @author kemalturgul
 * @date Jan 6, 2018
 */
public class ServerAccessLogDao extends RootDao {

	/**
	 * Search in parsed log data by startDate, endDate and threshold
	 * 
	 * @param startDate
	 *            access date of the client, starting search from this time in
	 *            parsed data
	 * @param endDate
	 *            access date of the client, ending search at this time in parsed
	 *            data
	 * @param threshold
	 *            minimum number of the access count of the ip address
	 * @return list of ip addresses and their access count value
	 */
	public List<Object[]> searchByDateAndThreshold(Date startDate, Date endDate, Integer threshold) {
		Query query = em.createQuery(
				"select s.ip, count(s.id) from ServerAccessLog s where s.serverAccessTime between :startDate and :endDate group by s.ip having count(s.id) > :threshold");
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		query.setParameter("threshold", new Long(threshold));

		List<Object[]> result = query.getResultList();
		return result;
	}

	/**
	 * Search in parsed log data by startDate, endDate and threshold
	 * 
	 * @param startDate
	 *            access date of the client, starting search from this time in
	 *            parsed data
	 * @param endDate
	 *            access date of the client, ending search at this time in parsed
	 *            data
	 * @param ip
	 *            ip address of the client's device
	 * @return list of ServerAccessLog class
	 */
	public List<ServerAccessLog> searchByDateAndIp(Date startDate, Date endDate, String ip) {
		Query query = em.createQuery(
				"select s from ServerAccessLog s where s.serverAccessTime between :startDate and :endDate and s.ip=:ip order by s.serverAccessTime desc");
		query.setParameter("startDate", startDate);
		query.setParameter("endDateDate", startDate);
		query.setParameter("ip", ip);

		List<ServerAccessLog> result = query.getResultList();
		return result;
	}

	/**
	 * 
	 * @param ip
	 *            ip address of the client's device
	 * @return list of ServerAccessLog class
	 */
	public List<ServerAccessLog> searchByIp(String ip) {
		Query query = em.createQuery("select s from ServerAccessLog s where s.ip=:ip order by s.serverAccessTime desc");
		query.setParameter("ip", ip);

		List<ServerAccessLog> result = query.getResultList();
		return result;
	}

}
