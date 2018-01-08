package com.turgul.kemal.search;

import java.util.Date;
import java.util.List;

import com.turgul.kemal.Constants;
import com.turgul.kemal.dao.DaoFactory;
import com.turgul.kemal.dao.ServerAccessBlockedDao;
import com.turgul.kemal.dao.ServerAccessLogDao;
import com.turgul.kemal.enums.Duration;
import com.turgul.kemal.model.ServerAccessBlocked;
import com.turgul.kemal.params.InputParameters;
import com.turgul.kemal.util.DateUtil;

/**
 * @author kemalturgul
 * @date Jan 6, 2018
 */
public class SearchServerAccessLog {

	private InputParameters inputParameters;

	public SearchServerAccessLog(InputParameters inputParameters) {
		this.inputParameters = inputParameters;
	}

	public void searchFromTable() {
		DaoFactory daoFactory = null;
		ServerAccessLogDao serverAccessLogDao = null;
		try {
			daoFactory = DaoFactory.getInstance();
			serverAccessLogDao = daoFactory.getServerAccessLogDao();

			prepareEndDate();
			
			List<Object[]> searchResultList = serverAccessLogDao.searchByDateAndThreshold(
					inputParameters.getStartDate(), inputParameters.getEndDate(), inputParameters.getThreshold());

			if (searchResultList != null && !searchResultList.isEmpty()) {
				addToBlockedTable(searchResultList);
				printToConsole(searchResultList);

			} else {
				System.out.println("There is not any result for given parameters:" + inputParameters.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (daoFactory != null) {
				daoFactory.closeContext();
			}
		}
	}

	// Prepare for endDate according to duration and startDate
	public void prepareEndDate() {
		Date endDate = null;
		if (Duration.HOURLY == inputParameters.getDuration()) {
			endDate = DateUtil.dateAddHours(inputParameters.getStartDate(), 1);
			// } else if (Duration.DAILY == inputParameters.getDuration()) {
			// endDate = DateUtil.dateAddOneDay(inputParameters.getStartDate());
		} else {
			endDate = DateUtil.dateAddOneDay(inputParameters.getStartDate());
		}
		inputParameters.setEndDate(endDate);
	}

	private void printToConsole(List<Object[]> serverAccessData) {
		for (Object[] accessData : serverAccessData) {
			System.out.println(String.format("Ip:%s requests:%s", accessData[0], accessData[1]));
		}
	}

	private void addToBlockedTable(List<Object[]> serverAccessData) {
		DaoFactory daoFactory = null;
		try {
			daoFactory = DaoFactory.getInstance();
			ServerAccessBlockedDao serverAccessBlockedDao = daoFactory.getServerAccessBlockedDao();
			
			for (Object[] accessData : serverAccessData) {
				ServerAccessBlocked serverAccessBlocked = new ServerAccessBlocked((String) accessData[0],
						inputParameters.getStartDate(), inputParameters.getDuration(), inputParameters.getThreshold(),
						new Date(), inputParameters.getBlockingComment());

				serverAccessBlockedDao.save(serverAccessBlocked);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (daoFactory != null) {
				daoFactory.closeContext();
			}
		}
	}

}
