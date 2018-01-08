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
