package com.httpserver.http;

public class HTTPParsingException extends Exception {

	private static final long serialVersionUID = 2064331010845070040L;
	private HTTPStatusCode httpStatusCode;
	
	public HTTPParsingException(HTTPStatusCode httpStatusCode) {
		super(httpStatusCode.STATUS_MESSAGE);
		this.httpStatusCode = httpStatusCode;
	}

	public HTTPStatusCode getHttpStatusCode() {
		return httpStatusCode;
	}
	
}
