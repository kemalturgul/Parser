package com.turgul.kemal;

/**
 * @author kemalturgul
 * @date Jan 5, 2018
 */
public class Constants {

	public static final String PERSISTENCE_UNIT_NAME = "parserPU";
	public static final int BATCH_SIZE = 25;

	public static final String REGEX_PIPE = "[|]";
	public static final String EQUAL_SIGN = "=";
	public static final String QUOTE_SIGN = "\"";
	public static final String DOT = ".";
	public static final String INPUT_PARAMETER_ACCESS_LOG_TXT = "--accesslog";
	public static final String INPUT_PARAMETER_START_DATE_TXT = "--startDate";
	public static final String INPUT_PARAMETER_DURATION_TXT = "--duration";
	public static final String INPUT_PARAMETER_THRESHOLD_TXT = "--threshold";
	public static final String INPUT_PARAMETER_START_DATE_FORMAT = "yyyy-MM-dd.HH:mm:ss";
	public static final String ACCESS_LOG_FILE_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String MY_SQL_TIMESTAMP_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final String BLOCKING_COMMENT = "Since number of requests exceeded threshold={0} value in duration of the {1} starting at {3}";

	public static final String USAGE_TXT = "java -cp parser.jar com.turgul.kemal.main.Parser --accesslog=/path/to/file --startDate="
			+ INPUT_PARAMETER_START_DATE_FORMAT + " --duration=(hourly | daily) --threshold=<number>";

	public static String getMissingParamExceptionMessage(String parameterName) {
		return "'" + parameterName + "' parameter name and value is not entered !!";
	}

	public static String getEmptyParamValueExceptionMessage(String parameterName) {
		return "'" + parameterName + "' parameter value is empty!!";
	}

	public static String getInvalidParamValueExceptionMessage(String parameterName) {
		return "'" + parameterName + "' parameter value is invalid !!, please see usage";
	}
}
