package com.httpserver.http;

public enum HTTPMethod {

	GET,
	HEAD;
	
	public static final int MAX_LENGTH;
	
	static {
		int tempMaxLength = -1;
		for(HTTPMethod httpMethod : values()) {
			if(httpMethod.name().length() > tempMaxLength) {
				tempMaxLength = httpMethod.name().length();
			}
		}
		MAX_LENGTH = tempMaxLength;
	}
	
}
