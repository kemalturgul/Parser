package com.turgul.kemal.exceptions;

/**
 * @author kemalturgul
 * @date Jan 7, 2018
 */
public class MissingArgumentException extends Exception {

	private static final long serialVersionUID = -8932861804070362798L;

	public MissingArgumentException() {
	}

	/**
	 * @param message
	 */
	public MissingArgumentException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public MissingArgumentException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public MissingArgumentException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public MissingArgumentException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
