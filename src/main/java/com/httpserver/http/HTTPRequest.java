package com.httpserver.http;

public class HTTPRequest extends HTTPMessage {

	private HTTPMethod httpMethod;
	private String httpRequestTarget;
	private String originalHTTPVersion;
	private HTTPVersion bestCompatibleHTTPVersion;
	
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

	public String getHttpRequestTarget() {
		return httpRequestTarget;
	}

	void setHttpRequestTarget(String httpRequestTarget) throws HTTPParsingException {
		if(httpRequestTarget == null || httpRequestTarget.isEmpty()) {
			throw new HTTPParsingException(HTTPStatusCode.SERVER_ERROR_500_INTERNAL_SERVER_ERROR);
		}
		this.httpRequestTarget = httpRequestTarget;
	}

	public String getOriginalHTTPVersion() {
		return originalHTTPVersion;
	}

	public void setOriginalHTTPVersion(String originalHTTPVersion) throws BadHTTPVersionException, HTTPParsingException {
		this.originalHTTPVersion = originalHTTPVersion;
		this.bestCompatibleHTTPVersion = HTTPVersion.getBestCompatibleVersion(originalHTTPVersion);
		
		if(this.bestCompatibleHTTPVersion == null) {
			throw new HTTPParsingException(HTTPStatusCode.SERVER_ERROR_505_HTTP_VERSION_NOT_SUPPORTED);
		}
	}

	public HTTPVersion getBestCompatibleHTTPVersion() {
		return bestCompatibleHTTPVersion;
	}

	public void setBestCompatibleHTTPVersion(HTTPVersion bestCompatibleHTTPVersion) {
		this.bestCompatibleHTTPVersion = bestCompatibleHTTPVersion;
	}
	
}
