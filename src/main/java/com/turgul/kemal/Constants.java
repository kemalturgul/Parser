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
	public static final String INPUT_PARAMETER_HELP_TXT = "--help";
	public static final String INPUT_PARAMETER_START_DATE_FORMAT = "yyyy-MM-dd.HH:mm:ss";
	public static final String ACCESS_LOG_FILE_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String MY_SQL_TIMESTAMP_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final String BLOCKING_COMMENT = "Since number of requests exceeded threshold={0} value in duration of the {1} starting at {3}";

	public static String getCommandLineUsage() {
		StringBuilder usage = new StringBuilder("\nUSAGE:\n\n");
		usage.append("$ java -cp parser-jar-with-dependencies.jar com.turgul.kemal.main.Parser ");
		usage.append("[--accesslog=<pathToLogFile>] --startDate=<dateTime> --duration=(hourly | daily) --threshold=<number>\n");
		usage.append("$ java -cp parser-jar-with-dependencies.jar com.turgul.kemal.main.Parser --help\n\n");
		usage.append("Command List:\n\n");
		usage.append("--accesslog    file        The input to process data (Optional)\n");
		usage.append("--startDate    date        '" + INPUT_PARAMETER_START_DATE_FORMAT + "' format\n");
		usage.append("--duration     string      It can be 'hourly' or 'daily'\n");
		usage.append("--threshold    number      an integer number bigger than 0\n");
		usage.append("--help         Display this usage guide\n");

		return usage.toString();
	}

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
