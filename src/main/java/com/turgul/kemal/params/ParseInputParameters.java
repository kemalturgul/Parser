
package com.turgul.kemal.params;

import java.util.HashMap;
import java.util.Map;

import com.turgul.kemal.Constants;
import com.turgul.kemal.exceptions.DurationException;
import com.turgul.kemal.exceptions.ExtraArgumentException;
import com.turgul.kemal.exceptions.FilePathException;
import com.turgul.kemal.exceptions.MissingArgumentException;
import com.turgul.kemal.exceptions.StartDateException;
import com.turgul.kemal.exceptions.ThresholdException;

/**
 * @author kemalturgul
 * @date Jan 7, 2018
 */
public class ParseInputParameters {

	private Map<String, String> inputParamsAndValues = new HashMap<>();
	private InputParametersValidation inputParametersValidation;

	public ParseInputParameters() {
		inputParametersValidation = new InputParametersValidation();
	}

	/**
	 * 
	 * @param inputs
	 *            command arguments
	 * @return InputParameters class instance
	 * @throws MissingArgumentException
	 * @throws FilePathException
	 * @throws StartDateException
	 * @throws DurationException
	 * @throws ThresholdException
	 * @throws ExtraArgumentException
	 */
	public InputParameters parseInputParams(String... inputs) throws MissingArgumentException, FilePathException,
			StartDateException, DurationException, ThresholdException, ExtraArgumentException {

		//Split by = sign and prepare key-value pair
		splitInputParamsAsKeyValue(inputs);
		if (inputs.length == 4) {

			checkInputParametersNames(true);
			parseLogFilePath();
			parseSearchParameters();

		} else if (inputs.length == 3) {

			checkInputParametersNames(false);
			parseSearchParameters();
			
		} else if (inputs.length == 1) {
			
			checkForHelp();
			
		} else if (inputs.length > 4) {
			throw new ExtraArgumentException("Extra arguments entered, please check usage!!");
		} else {
			throw new MissingArgumentException("Missing arguments, please check usage!!");
		}

		return inputParametersValidation.getInputParameters();
	}

	private void splitInputParamsAsKeyValue(String[] inputs) {
		for (String input : inputs) {
			String[] inputParamSplitted = input.split(Constants.EQUAL_SIGN);
			if (inputParamSplitted.length > 1) {
				inputParamsAndValues.put(inputParamSplitted[0], inputParamSplitted[1]);
			} else {
				inputParamsAndValues.put(inputParamSplitted[0], null);
			}
		}
	}

	private void checkForHelp() throws MissingArgumentException {
		if (inputParamsAndValues.containsKey(Constants.INPUT_PARAMETER_HELP_TXT)) {
			inputParametersValidation.getInputParameters().setHelp(true);
		} else {
			throw new MissingArgumentException("Missing arguments, please check usage!!");
		}
	}
	
	private void checkInputParametersNames(boolean checkLogFilePath)
			throws FilePathException, StartDateException, DurationException, ThresholdException {

		if (checkLogFilePath && !inputParamsAndValues.containsKey(Constants.INPUT_PARAMETER_ACCESS_LOG_TXT)) {
			throw new FilePathException(
					Constants.getMissingParamExceptionMessage(Constants.INPUT_PARAMETER_ACCESS_LOG_TXT));

		} else if (!inputParamsAndValues.containsKey(Constants.INPUT_PARAMETER_START_DATE_TXT)) {
			throw new StartDateException(
					Constants.getMissingParamExceptionMessage(Constants.INPUT_PARAMETER_START_DATE_TXT));

		} else if (!inputParamsAndValues.containsKey(Constants.INPUT_PARAMETER_DURATION_TXT)) {
			throw new DurationException(
					Constants.getMissingParamExceptionMessage(Constants.INPUT_PARAMETER_DURATION_TXT));

		} else if (!inputParamsAndValues.containsKey(Constants.INPUT_PARAMETER_THRESHOLD_TXT)) {
			throw new ThresholdException(
					Constants.getMissingParamExceptionMessage(Constants.INPUT_PARAMETER_THRESHOLD_TXT));
		}

	}

	private void parseLogFilePath() throws FilePathException {
		inputParametersValidation.validatePathToFile(inputParamsAndValues.get(Constants.INPUT_PARAMETER_ACCESS_LOG_TXT));
	}

	private void parseSearchParameters() throws StartDateException, DurationException, ThresholdException {
		inputParametersValidation.validateStartDate(inputParamsAndValues.get(Constants.INPUT_PARAMETER_START_DATE_TXT));
		inputParametersValidation.validateDuration(inputParamsAndValues.get(Constants.INPUT_PARAMETER_DURATION_TXT));
		inputParametersValidation.validateThreshold(inputParamsAndValues.get(Constants.INPUT_PARAMETER_THRESHOLD_TXT));
	}

}
