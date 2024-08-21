package com.httpserver.http;

public class BadHTTPVersionException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8598996247802479051L;

	public BadHTTPVersionException() {
		super();
	}

	public BadHTTPVersionException(String message, Throwable cause) {
		super(message, cause);
	}

	public BadHTTPVersionException(String message) {
		super(message);
	}

	public BadHTTPVersionException(Throwable cause) {
		super(cause);
	}
	
}
