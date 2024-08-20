package com.httpserver.http;

import java.util.regex.Pattern;

public enum HTTPVersion {

	HTTP_1_1("HTTP/1.1", 1, 1);
	
	public final String LITERAL;
	public final int MAJOR;
	public final int MINOR;
	
	HTTPVersion(String LITERAL, int MAJOR, int MINOR) {
		this.LITERAL = LITERAL;
		this.MAJOR = MAJOR;
		this.MINOR = MINOR;
	}
	
	private static final Pattern httpVersionRegexPattern = Pattern.compile("^HTTP/(?<major>\\d+).(?<minor>\\d+)");
	
	public static HTTPVersion getBestCompatibleVersion(String literalversion) {
		return null; // !!! TO COMPLETE !!!
	}
}
