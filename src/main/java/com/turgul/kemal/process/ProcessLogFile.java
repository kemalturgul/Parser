package com.turgul.kemal.process;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import com.turgul.kemal.Constants;
import com.turgul.kemal.dao.DaoFactory;
import com.turgul.kemal.dao.ServerAccessLogDao;
import com.turgul.kemal.model.ServerAccessLog;
import com.turgul.kemal.util.DateUtil;

/**
 * @author kemalturgul
 * @date Jan 6, 2018
 */
public class ProcessLogFile {
	File pathToLogFile;

	public ProcessLogFile(File pathToLogFile) {
		this.pathToLogFile = pathToLogFile;
	}

	public void startProcessing() throws FileNotFoundException, IOException {
		DaoFactory daoFactory = null;
		ServerAccessLogDao serverAccessLogDao = null;
		int batchCounter = 0;
		String line;

		try (BufferedReader reader = new BufferedReader(new FileReader(pathToLogFile))) {
			daoFactory = DaoFactory.getInstance();
			serverAccessLogDao = daoFactory.getServerAccessLogDao();
			serverAccessLogDao.beginTransaction();

			while ((line = reader.readLine()) != null) {
				ServerAccessLog serverAccessLog = prepareEntityData(line);
				if (serverAccessLog != null) {
					serverAccessLogDao.saveForBatch(serverAccessLog);
					batchCounter++;
				}
				if (batchCounter > 0 && batchCounter % Constants.BATCH_SIZE == 0) {
					serverAccessLogDao.getEntityManager().flush();
					serverAccessLogDao.getEntityManager().clear();

					serverAccessLogDao.commit();
					serverAccessLogDao.beginTransaction();
				}
			}
			// Commmit last data (if any)
			serverAccessLogDao.commit();

		} finally {
			if (daoFactory != null) {
				daoFactory.closeContext();
			}
		}
	}

	public ServerAccessLog prepareEntityData(String line) {
		ServerAccessLog serverAccessLog = null;
		try {
			if (line != null && line.trim().length() > 0) {
				String[] accessData = line.split(Constants.REGEX_PIPE);

				String dateString = accessData[0];
				String ip = accessData[1];
				String request = accessData[2];
				String statusString = accessData[3];
				String userAgent = accessData[4];

				dateString = dateString.substring(0, dateString.indexOf("."));
				Date serverAccessTime = DateUtil.getFormattedDate(dateString, Constants.MY_SQL_TIMESTAMP_DATE_FORMAT);
//				Date serverAccessTime = DateUtil.getFormattedDate(dateString, Constants.ACCESS_LOG_FILE_DATE_FORMAT);
				Integer status = new Integer(statusString);

				serverAccessLog = new ServerAccessLog(serverAccessTime, ip, removeQuoteFromText(request), status,
						removeQuoteFromText(userAgent));
			}

		} catch (Exception e) {
			System.out.println("Could not parse line:" + line);
		}
		return serverAccessLog;
	}

	private String removeQuoteFromText(String text) {
		if (text != null) {
			return text.replaceAll(Constants.QUOTE_SIGN, "");
		}
		return "";
	}

}
