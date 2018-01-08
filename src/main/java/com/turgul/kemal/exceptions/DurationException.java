package com.turgul.kemal.exceptions;

/**
 * @author kemalturgul
 * @date Jan 6, 2018
 */
public class DurationException extends Exception {

	private static final long serialVersionUID = 3739222113727456294L;

	public DurationException() {
	}

	public DurationException(String message) {
		super(message);
	}

	public DurationException(Throwable cause) {
		super(cause);
	}

	public DurationException(String message, Throwable cause) {
		super(message, cause);
	}

	public DurationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
