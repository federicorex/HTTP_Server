package com.httpserver.configuration;

public class HTTPConfigurationException extends RuntimeException {

	private static final long serialVersionUID = 5210746982712664867L;

	public HTTPConfigurationException() {
		super();
	}

	public HTTPConfigurationException(String message, Throwable cause) {
		super(message, cause);
	}

	public HTTPConfigurationException(String message) {
		super(message);
	}

	public HTTPConfigurationException(Throwable cause) {
		super(cause);
	}
	
}
