
package com.turgul.kemal.exceptions;

/**
 * @author kemalturgul
 * @date Jan 8, 2018
 */
public class ExtraArgumentException extends Exception {

	private static final long serialVersionUID = 6643680039352514798L;

	public ExtraArgumentException() {
	}

	/**
	 * @param message
	 */
	public ExtraArgumentException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ExtraArgumentException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ExtraArgumentException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ExtraArgumentException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
