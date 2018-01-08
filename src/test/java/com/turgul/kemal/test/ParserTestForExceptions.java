package com.turgul.kemal.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.turgul.kemal.Constants;
import com.turgul.kemal.exceptions.DurationException;
import com.turgul.kemal.exceptions.ExtraArgumentException;
import com.turgul.kemal.exceptions.FilePathException;
import com.turgul.kemal.exceptions.MissingArgumentException;
import com.turgul.kemal.exceptions.StartDateException;
import com.turgul.kemal.exceptions.ThresholdException;
import com.turgul.kemal.params.ParseInputParameters;

public class ParserTestForExceptions {

	public ParserTestForExceptions() {
	}

	@Test(expected = MissingArgumentException.class)
	public void throwsMissingArgumentException() throws MissingArgumentException, FilePathException, StartDateException,
			DurationException, ThresholdException, ExtraArgumentException {
		String[] inputs = { "--startDate=2017-01-01.15:00:00", "--duration=hourly" };
		ParseInputParameters parseInputParameters = new ParseInputParameters();
		parseInputParameters.parseInputParams(inputs);
	}

	@Test(expected = ExtraArgumentException.class)
	public void throwsExtraArgumentException() throws MissingArgumentException, FilePathException, StartDateException,
			DurationException, ThresholdException, ExtraArgumentException {
		// --accesslog=/Users/kemalturgul/Desktop/Java_MySQL_Test/testData.txt
		// --startDate=2017-01-01.15:00:00 --duration=hourly --threshold=200
		String[] inputs = { "--accesslog=/path", "--startDate=2017-01-01.15:00:00", "--duration=hourly",
				"--threshold=200", "--extraparam=" };
		ParseInputParameters parseInputParameters = new ParseInputParameters();
		parseInputParameters.parseInputParams(inputs);
	}

	@Test(expected = FilePathException.class)
	public void throwsFilePathException() throws MissingArgumentException, FilePathException, StartDateException,
			DurationException, ThresholdException, ExtraArgumentException {
		ParseInputParameters parseInputParameters = new ParseInputParameters();
		parseInputParameters.parseInputParams("--accesslog", "--startDate=2017-01-01.15:00:00", "--duration=hourly",
				"--threshold=200");
	}

	@Test
	public void throwsStartDateException() throws MissingArgumentException, FilePathException, StartDateException,
			DurationException, ThresholdException, ExtraArgumentException {
		ParseInputParameters parseInputParameters = new ParseInputParameters();
		try {
			parseInputParameters.parseInputParams("--startDate", "--duration=hourly", "--threshold=200");
			fail("StartDateException was not thrown  for empty param value!!");
		} catch (StartDateException e) {
			assertEquals("Exception message did not match!!",
					Constants.getEmptyParamValueExceptionMessage(Constants.INPUT_PARAMETER_START_DATE_TXT),
					e.getMessage());
		}
		try {
			parseInputParameters.parseInputParams("--startDate=2017-01-01 15:00:00.123", "--duration=hourly",
					"--threshold=200");
			fail("StartDateException was not thrown for invalid input!!");
		} catch (StartDateException e) {
			assertEquals("Exception message did not match!!",
					Constants.getInvalidParamValueExceptionMessage(Constants.INPUT_PARAMETER_START_DATE_TXT),
					e.getMessage());
		}
	}

	@Test
	public void throwsDurationException() throws MissingArgumentException, FilePathException, StartDateException,
			DurationException, ThresholdException, ExtraArgumentException {
		ParseInputParameters parseInputParameters = new ParseInputParameters();
		try {
			parseInputParameters.parseInputParams("--startDate=2017-01-01.15:00:00", "--duration", "--threshold=200");
			fail("DurationException was not thrown for empty param value!!");
		} catch (DurationException e) {
			assertEquals("Exception message did not match!!",
					Constants.getEmptyParamValueExceptionMessage(Constants.INPUT_PARAMETER_DURATION_TXT),
					e.getMessage());
		}
		try {
			parseInputParameters.parseInputParams("--startDate=2017-01-01.15:00:00", "--duration=weekly",
					"--threshold=200");
			fail("DurationException was not thrown for invalid input!!");
		} catch (DurationException e) {
			assertEquals("Exception message did not match!!",
					Constants.getInvalidParamValueExceptionMessage(Constants.INPUT_PARAMETER_DURATION_TXT),
					e.getMessage());
		}
	}

	@Test
	public void throwsThresholdException() throws MissingArgumentException, FilePathException, StartDateException,
			DurationException, ThresholdException, ExtraArgumentException {
		ParseInputParameters parseInputParameters = new ParseInputParameters();
		try {
			parseInputParameters.parseInputParams("--startDate=2017-01-01.15:00:00", "--duration=hourly", "--threshold");
			fail("ThresholdException was not thrown for empty param value!!");
		} catch (ThresholdException e) {
			assertEquals("Exception message did not match!!",
					Constants.getEmptyParamValueExceptionMessage(Constants.INPUT_PARAMETER_THRESHOLD_TXT),
					e.getMessage());
		}
		try {
			parseInputParameters.parseInputParams("--startDate=2017-01-01.15:00:00", "--duration=hourly",
					"--threshold=0");
			fail("ThresholdException was not thrown for invalid input of 0 !!");
		} catch (ThresholdException e) {
			assertEquals("Exception message did not match!!",
					Constants.getInvalidParamValueExceptionMessage(Constants.INPUT_PARAMETER_THRESHOLD_TXT),
					e.getMessage());
		}
		
		try {
			parseInputParameters.parseInputParams("--startDate=2017-01-01.15:00:00", "--duration=hourly",
					"--threshold=abc");
			fail("ThresholdException was not thrown for invalid input of 'abc'!!");
		} catch (ThresholdException e) {
			assertEquals("Exception message did not match!!",
					Constants.getInvalidParamValueExceptionMessage(Constants.INPUT_PARAMETER_THRESHOLD_TXT),
					e.getMessage());
		}
	}

}
