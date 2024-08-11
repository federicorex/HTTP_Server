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

	void setHttpMethod(String httpMethodName) throws HTTPParsingException {
		for(HTTPMethod httpMethod: HTTPMethod.values()) {
			if(httpMethodName.equals(httpMethod.name())) {
				this.httpMethod = HTTPMethod.valueOf(httpMethodName);
				return;
			}
		}
		throw new HTTPParsingException(HTTPStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);
	}
	
}
