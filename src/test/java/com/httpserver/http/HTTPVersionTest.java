package com.httpserver.http;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HTTPVersionTest {
	
	@Test
	void getBestCompatibleVersionExactMatch() {
		HTTPVersion httpVersion = null;
		try {
			httpVersion = HTTPVersion.getBestCompatibleVersion("HTTP/1.1");
		} catch (BadHTTPVersionException e) {
			Assertions.fail();
		}
		
		Assertions.assertNotNull(httpVersion);
		Assertions.assertEquals(httpVersion, HTTPVersion.HTTP_1_1);
	}
	
	@Test
	void getBestCompatibleVersionBadFormat() throws BadHTTPVersionException {
		HTTPVersion httpVersion = null;
		try {
			httpVersion = HTTPVersion.getBestCompatibleVersion("hTTP/1.1");
			Assertions.fail();
		} catch (BadHTTPVersionException e) {
			
		}
		
	}
	
	@Test
	void getBestCompatibleVersionHigherVersion() throws BadHTTPVersionException {
		HTTPVersion httpVersion = null;
		try {
			httpVersion = HTTPVersion.getBestCompatibleVersion("HTTP/1.2");
		} catch (BadHTTPVersionException e) {
			Assertions.fail();
		}
		
		Assertions.assertNotNull(httpVersion);
		Assertions.assertEquals(httpVersion, HTTPVersion.HTTP_1_1);
	}

}
