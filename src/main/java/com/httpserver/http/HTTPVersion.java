package com.httpserver.http;

import java.util.regex.Matcher;
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
	
	public static HTTPVersion getBestCompatibleVersion(String literalversion) throws BadHTTPVersionException {
		Matcher matcher = httpVersionRegexPattern.matcher(literalversion);
		if(!matcher.find() || matcher.groupCount() != 2) {
			throw new BadHTTPVersionException();
		}
		int major = Integer.parseInt(matcher.group("major"));
		int minor = Integer.parseInt(matcher.group("minor"));
		HTTPVersion tempBestCompatibleHTTPVersion = null;
		
		for(HTTPVersion httpVersion : HTTPVersion.values()) {
			if(httpVersion.LITERAL.equals(literalversion)) {
				return httpVersion;
			} else {
				if(httpVersion.MAJOR == major) {
					if(httpVersion.MINOR < minor) {
						tempBestCompatibleHTTPVersion = httpVersion;
					}
				}
			}
		}
		return tempBestCompatibleHTTPVersion;
	}
	
}
