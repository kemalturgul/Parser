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

	public List<Object[]> searchByDateAndThreshold(Date startDate, Date endDate, Integer threshold) {
		System.out.println("startDate:" + DateUtil.getFormattedDate(startDate, Constants.ACCESS_LOG_FILE_DATE_FORMAT));
		System.out.println("endDate:" + DateUtil.getFormattedDate(endDate, Constants.ACCESS_LOG_FILE_DATE_FORMAT));
		System.out.println("threshold:" + threshold);
		
		Query query = em.createQuery(
				"select s.ip, count(s.id) from ServerAccessLog s where s.serverAccessTime between :startDate and :endDate group by s.ip having count(s.id) > :threshold");
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		query.setParameter("threshold", new Long(threshold));

		List<Object[]> result = query.getResultList();
		return result;
	}

	public List<ServerAccessLog> searchByDateAndIp(Date startDate, Date endDate, String ip) {
		Query query = em.createQuery(
				"select s from ServerAccessLog s where s.serverAccessTime between :startDate and :endDate and s.ip=:ip order by s.serverAccessTime desc");
		query.setParameter("startDate", startDate);
		query.setParameter("endDateDate", startDate);
		query.setParameter("ip", ip);

		List<ServerAccessLog> result = query.getResultList();
		return result;
	}

	public List<ServerAccessLog> searchByIp(String ip) {
		Query query = em.createQuery("select s from ServerAccessLog s where s.ip=:ip order by s.serverAccessTime desc");
		query.setParameter("ip", ip);

		List<ServerAccessLog> result = query.getResultList();
		return result;
	}

}
