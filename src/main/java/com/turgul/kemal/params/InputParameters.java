package com.turgul.kemal.params;

import java.io.File;
import java.util.Date;

import com.turgul.kemal.enums.Duration;

/**
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
		StringBuilder builder = new StringBuilder();
		builder.append("Since number of requests exceeded threshold=");
		builder.append(threshold);
		builder.append(" value in duration of the ");
		builder.append(duration.getDurationTxt());
		builder.append(" starting at ");
		builder.append(startDate);

		return builder.toString();
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
