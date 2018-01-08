package com.turgul.kemal.exceptions;

/**
 * @author kemalturgul
 * @date Jan 6, 2018
 */
public class ThresholdException extends Exception {

	private static final long serialVersionUID = -1797285912812448483L;

	public ThresholdException() {
	}

	public ThresholdException(String message) {
		super(message);
	}

	public ThresholdException(Throwable cause) {
		super(cause);
	}

	public ThresholdException(String message, Throwable cause) {
		super(message, cause);
	}

	public ThresholdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
