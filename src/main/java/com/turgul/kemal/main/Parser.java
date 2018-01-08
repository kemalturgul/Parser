package com.turgul.kemal.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import com.turgul.kemal.Constants;
import com.turgul.kemal.exceptions.DurationException;
import com.turgul.kemal.exceptions.ExtraArgumentException;
import com.turgul.kemal.exceptions.FilePathException;
import com.turgul.kemal.exceptions.MissingArgumentException;
import com.turgul.kemal.exceptions.StartDateException;
import com.turgul.kemal.exceptions.ThresholdException;
import com.turgul.kemal.model.ServerAccessLog;
import com.turgul.kemal.params.InputParameters;
import com.turgul.kemal.params.ParseInputParameters;
import com.turgul.kemal.process.ProcessLogFile;
import com.turgul.kemal.search.SearchServerAccessLog;
import com.turgul.kemal.util.DateUtil;

/**
 * @author kemalturgul
 * @date Jan 5, 2018
 */
public class Parser {
	// private static Logger logger = LogManager.getLogger(Parser.class);

	public Parser() {

	}

	public static void main(String[] args) {
		// logger.info("****************************Parser Started******************");
		System.out.println("****************************Parser Started******************");

		ParseInputParameters parseInputParameters = new ParseInputParameters();
		InputParameters inputParameters = null;
		try {
			inputParameters = parseInputParameters.parseInputParams(args);
			if (inputParameters.getPathToLogFile() != null) {
				ProcessLogFile processLogFile = new ProcessLogFile(inputParameters.getPathToLogFile());
				processLogFile.startProcessing();
				System.out.println("Access Log file parsed and added to DB successfully");
			}

			SearchServerAccessLog searchServerAccessLog = new SearchServerAccessLog(inputParameters);
			searchServerAccessLog.searchFromTable();
		} catch (ExtraArgumentException e) {
			System.out.println(e.getMessage());
		} catch (MissingArgumentException e) {
			System.out.println(e.getMessage());
		} catch (FilePathException e) {
			System.out.println(e.getMessage());
		} catch (StartDateException e) {
			System.out.println(e.getMessage());
		} catch (DurationException e) {
			System.out.println(e.getMessage());
		} catch (ThresholdException e) {
			System.out.println(e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println("File:" + inputParameters.getPathToLogFile() + " could not found");
		} catch (IOException e) {
			System.out.println(
					"An error occurred for File:" + inputParameters.getPathToLogFile() + " message:" + e.getMessage());
		}

		System.exit(0);

	}

}
