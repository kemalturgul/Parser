package com.turgul.kemal.exceptions;

/**
 * @author kemalturgul
 * @date Jan 6, 2018
 */
public class FilePathException extends Exception {

	private static final long serialVersionUID = 8276628348683437525L;

	public FilePathException() {
	}

	public FilePathException(String message) {
		super(message);
	}

	public FilePathException(Throwable cause) {
		super(cause);
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public FilePathException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public FilePathException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
