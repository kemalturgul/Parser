package com.turgul.kemal.params;

import java.io.File;
import java.text.ParseException;
import java.util.Date;

import com.turgul.kemal.Constants;
import com.turgul.kemal.enums.Duration;
import com.turgul.kemal.exceptions.DurationException;
import com.turgul.kemal.exceptions.FilePathException;
import com.turgul.kemal.exceptions.StartDateException;
import com.turgul.kemal.exceptions.ThresholdException;
import com.turgul.kemal.util.DateUtil;

/**
 * This class validate input parameters and throws defined Exception in case
 * 
 * @author kemalturgul
 * @date Jan 6, 2018
 */
public class InputParametersValidation {

	private InputParameters inputParameters;

	public InputParametersValidation() {
		inputParameters = new InputParameters();
	}

	public InputParametersValidation(InputParameters inputParameters) {
		this.inputParameters = inputParameters;
	}

	/**
	 * @param filePath
	 *            file path to parse data
	 * @throws FilePathException
	 *             if null, empty or invalid file path entered
	 */
	public void validatePathToFile(String filePath) throws FilePathException {
		if (filePath == null || filePath.trim().length() == 0) {
			throw new FilePathException(
					Constants.getEmptyParamValueExceptionMessage(Constants.INPUT_PARAMETER_ACCESS_LOG_TXT));
		}

		File file = new File(filePath);
		if (!file.isFile()) {
			throw new FilePathException(
					Constants.getInvalidParamValueExceptionMessage(Constants.INPUT_PARAMETER_ACCESS_LOG_TXT));
		}
		inputParameters.setPathToLogFile(file);
	}

	/**
	 * 
	 * @param startDate
	 *            server access date-time of the client
	 * @throws StartDateException
	 *             if null, empty or invalid date entered
	 */
	public void validateStartDate(String startDate) throws StartDateException {
		if (startDate == null || startDate.trim().length() == 0) {
			throw new StartDateException(
					Constants.getEmptyParamValueExceptionMessage(Constants.INPUT_PARAMETER_START_DATE_TXT));
		}

		Date parsedStartDate;
		try {
			parsedStartDate = DateUtil.getFormattedDate(startDate, Constants.INPUT_PARAMETER_START_DATE_FORMAT);

			if (parsedStartDate == null) {
				throw new StartDateException(
						Constants.getInvalidParamValueExceptionMessage(Constants.INPUT_PARAMETER_START_DATE_TXT));
			}
			inputParameters.setStartDate(parsedStartDate);

		} catch (ParseException e) {
			throw new StartDateException(
					Constants.getInvalidParamValueExceptionMessage(Constants.INPUT_PARAMETER_START_DATE_TXT));
		}

	}

	/**
	 * 
	 * @param durationString
	 *            server access time interval to add @startDate when searching, it
	 *            can be only 'hourly' or 'daily'
	 * @throws DurationException
	 *             if null, empty or invalid duration entered
	 */
	public void validateDuration(String durationString) throws DurationException {
		if (durationString == null || durationString.trim().length() == 0) {
			throw new DurationException(
					Constants.getEmptyParamValueExceptionMessage(Constants.INPUT_PARAMETER_DURATION_TXT));
		}
		Duration duration = Duration.getDuration(durationString);
		if (duration == null) {
			throw new DurationException(
					Constants.getInvalidParamValueExceptionMessage(Constants.INPUT_PARAMETER_DURATION_TXT));
		}
		inputParameters.setDuration(duration);
	}

	/**
	 * 
	 * @param threshold
	 *            minimum access count number of an IP address in the interval of
	 *            the startDate and duration
	 * @throws ThresholdException
	 *             if null, empty or non-integer value entered
	 */
	public void validateThreshold(String threshold) throws ThresholdException {
		if (threshold == null || threshold.trim().length() == 0) {
			throw new ThresholdException(
					Constants.getEmptyParamValueExceptionMessage(Constants.INPUT_PARAMETER_THRESHOLD_TXT));
		}
		try {
			int thresholdParsed = Integer.parseInt(threshold);
			if (thresholdParsed <= 0) {
				throw new ThresholdException(
						Constants.getInvalidParamValueExceptionMessage(Constants.INPUT_PARAMETER_THRESHOLD_TXT));
			}
			inputParameters.setThreshold(thresholdParsed);
		} catch (Exception e) {
			throw new ThresholdException(
					Constants.getInvalidParamValueExceptionMessage(Constants.INPUT_PARAMETER_THRESHOLD_TXT));
		}

	}

	public InputParameters getInputParameters() {
		return inputParameters;
	}

	public void setInputParameters(InputParameters inputParameters) {
		this.inputParameters = inputParameters;
	}

}
