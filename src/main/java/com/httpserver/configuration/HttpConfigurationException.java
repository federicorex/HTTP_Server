package com.httpserver.configuration;

public class HttpConfigurationException extends RuntimeException {

	private static final long serialVersionUID = 5210746982712664867L;

	public HttpConfigurationException() {
		super();
	}

	public HttpConfigurationException(String message, Throwable cause) {
		super(message, cause);
	}

	public HttpConfigurationException(String message) {
		super(message);
	}

	public HttpConfigurationException(Throwable cause) {
		super(cause);
	}
	
}
