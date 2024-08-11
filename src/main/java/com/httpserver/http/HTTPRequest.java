package com.httpserver.http;

public class HTTPRequest extends HTTPMessage {

	private HTTPMethod httpMethod;
	private String httpRequestTarget;
	private String httpVersion;
	
	HTTPRequest() {
	
	}

	public HTTPMethod getHttpMethod() {
		return httpMethod;
	}

	void setHttpMethod(HTTPMethod httpMethod) {
		this.httpMethod = httpMethod;
	}
	
}
