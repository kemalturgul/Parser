package com.turgul.kemal.exceptions;

/**
 * @author kemalturgul
 * @date Jan 6, 2018
 */
public class StartDateException extends Exception {

	private static final long serialVersionUID = -714840084281916964L;

	public StartDateException() {
	}

	public StartDateException(String message) {
		super(message);
	}

	public StartDateException(Throwable cause) {
		super(cause);
	}

	public StartDateException(String message, Throwable cause) {
		super(message, cause);
	}

	public StartDateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
