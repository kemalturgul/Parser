package com.turgul.kemal.main;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.turgul.kemal.Constants;
import com.turgul.kemal.exceptions.DurationException;
import com.turgul.kemal.exceptions.ExtraArgumentException;
import com.turgul.kemal.exceptions.FilePathException;
import com.turgul.kemal.exceptions.MissingArgumentException;
import com.turgul.kemal.exceptions.StartDateException;
import com.turgul.kemal.exceptions.ThresholdException;
import com.turgul.kemal.params.InputParameters;
import com.turgul.kemal.params.ParseInputParameters;
import com.turgul.kemal.process.ProcessLogFile;
import com.turgul.kemal.search.SearchServerAccessLog;

/**
 * @author kemalturgul
 * @date Jan 5, 2018
 */
public class Parser {
	public Parser() {

	}

	public static void main(String[] args) {
		System.out.println("****************************Parser Started******************");

		ParseInputParameters parseInputParameters = new ParseInputParameters();
		InputParameters inputParameters = null;
		boolean showUsage = false;
		try {
			inputParameters = parseInputParameters.parseInputParams(args);
			if (inputParameters.isHelp()) {
				showUsage = true;
			} else {
				System.out.println("parsed input arguments:" + inputParameters);
				if (inputParameters.getPathToLogFile() != null) {
					System.out.println("Processing Access Log file:" + inputParameters.getPathToLogFile());
					ProcessLogFile processLogFile = new ProcessLogFile(inputParameters.getPathToLogFile());
					processLogFile.startProcessing();
					System.out.println("Access Log file parsed and added to DB successfully");
				}

				SearchServerAccessLog searchServerAccessLog = new SearchServerAccessLog(inputParameters);
				searchServerAccessLog.searchFromTable();
			}
		} catch (ExtraArgumentException e) {
			showUsage = true;
			System.out.println(e.getMessage());
		} catch (MissingArgumentException e) {
			showUsage = true;
			System.out.println(e.getMessage());
		} catch (FilePathException e) {
			showUsage = true;
			System.out.println(e.getMessage());
		} catch (StartDateException e) {
			showUsage = true;
			System.out.println(e.getMessage());
		} catch (DurationException e) {
			showUsage = true;
			System.out.println(e.getMessage());
		} catch (ThresholdException e) {
			showUsage = true;
			System.out.println(e.getMessage());
		} catch (FileNotFoundException e) {
			showUsage = true;
			System.out.println("File:" + inputParameters.getPathToLogFile() + " could not be found");
		} catch (IOException e) {
			showUsage = true;
			System.out.println("An error occurred for File:" + inputParameters.getPathToLogFile() + " message:" + e.getMessage());
		}

		if (showUsage) {
			System.out.println(Constants.getCommandLineUsage());
		}
		System.exit(0);
	}

}
