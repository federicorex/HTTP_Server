package com.httpserver.http;

public enum HTTPStatusCode {

	// Client errors
	CLIENT_ERROR_400_BAD_REQUEST(400, "Bad Request"),
	CLIENT_ERROR_401_METHOD_NOT_ALLOWED(401, "Method Not Allowed"),
	CLIENT_ERROR_414_BAD_REQUEST(414, "URI Too Long"),
	//Server errors
	SERVER_ERROR_500_INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
	SERVER_ERROR_501_NOT_IMPLEMENTED(501, "Not Implemented"), SERVER_ERROR_505_HTTP_VERSION_NOT_SUPPORTED(505, "HTTP Version Not Supported");
	
	public final int STATUS_CODE;
	public final String STATUS_MESSAGE;
	
	HTTPStatusCode(int STATUS_CODE, String STATUS_MESSAGE) {
		this.STATUS_CODE = STATUS_CODE;
		this.STATUS_MESSAGE = STATUS_MESSAGE;
	}
	
}
