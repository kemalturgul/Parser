package com.turgul.kemal.params;

import java.io.File;
import java.text.MessageFormat;
import java.util.Date;

import com.turgul.kemal.Constants;
import com.turgul.kemal.enums.Duration;

/**
 * This class saves parsed input parameters
 * 
 * @author kemalturgul
 * @date Jan 6, 2018
 */
public class InputParameters {

	File pathToLogFile;
	Date startDate;
	Date endDate;
	Duration duration;
	int threshold;

	public InputParameters() {
	}

	/**
	 * 
	 * @param pathToLogFile
	 *            a file path to load CSV file and parse it
	 * @param startDate
	 *            server access date-time of the client
	 * @param duration
	 *            server access time interval to add @startDate, it can be DAILY or
	 *            HOURLY
	 * @param threshold
	 *            minimum access count number of an IP address in the interval of
	 *            the startDate and duration
	 */
	public InputParameters(File pathToLogFile, Date startDate, Duration duration, int threshold) {
		this(startDate, duration, threshold);
		this.pathToLogFile = pathToLogFile;
	}

	public InputParameters(Date startDate, Duration duration, int threshold) {
		this.startDate = startDate;
		this.duration = duration;
		this.threshold = threshold;
	}

	public File getPathToLogFile() {
		return pathToLogFile;
	}

	public void setPathToLogFile(File pathToLogFile) {
		this.pathToLogFile = pathToLogFile;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public String getBlockingComment() {
		return MessageFormat.format(Constants.BLOCKING_COMMENT, threshold, duration.getDurationTxt(), startDate);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InputParameters [");
		if (pathToLogFile != null) {
			builder.append("pathToLogFile=");
			builder.append(pathToLogFile);
			builder.append(", ");
		}
		builder.append("startDate=");
		builder.append(startDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", duration=");
		builder.append(duration.getDurationTxt());
		builder.append(", threshold=");
		builder.append(threshold);
		builder.append("]");
		return builder.toString();
	}

}
