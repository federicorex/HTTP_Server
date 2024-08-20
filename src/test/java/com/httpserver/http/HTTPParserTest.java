package com.httpserver.http;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HTTPParserTest {

	private HTTPParser httpParser;
	
	@BeforeEach
	void setUp() {
		httpParser = new HTTPParser();
	}
	
	@Test
	void testParseHTTPRequestBadMethodName() {
		
		try {
			httpParser.parseHTTPRequest(generateNotValidGETTestCase());
			Assertions.fail();
		} catch (HTTPParsingException httpParsingException) {
			Assertions.assertEquals(httpParsingException.getHttpStatusCode(), HTTPStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);
		}
		
	}
	
	@Test
	void testParseHTTPRequestBadMethodName2() {
		
		try {
			httpParser.parseHTTPRequest(generateNotValidGETTestCase2());
			Assertions.fail();
		} catch (HTTPParsingException httpParsingException) {
			Assertions.assertEquals(httpParsingException.getHttpStatusCode(), HTTPStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);
		}
		
	}
	
	@Test
	void testParseHTTPRequestInvalidNumberItems() {
		
		try {
			httpParser.parseHTTPRequest(generateNotValidGETTestCaseRequestLineInvalidNumberItems());
			Assertions.fail();
		} catch (HTTPParsingException httpParsingException) {
			Assertions.assertEquals(httpParsingException.getHttpStatusCode(), HTTPStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
		}
		
	}
	
	@Test
	void testParseHTTPEmptyRequestLine() {
		
		try {
			httpParser.parseHTTPRequest(generateNotValidGETTestCaseEmptyRequestLine());
			Assertions.fail();
		} catch (HTTPParsingException httpParsingException) {
			Assertions.assertEquals(httpParsingException.getHttpStatusCode(), HTTPStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
		}
		
	}
	
	@Test
	void testParseHTTPRequestLineCRnoLF() {
		
		try {
			httpParser.parseHTTPRequest(generateNotValidGETTestCaseRequestLineOnlyCRnoLF());
			Assertions.fail();
		} catch (HTTPParsingException httpParsingException) {
			Assertions.assertEquals(httpParsingException.getHttpStatusCode(), HTTPStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
		}
		
	}
	
	@Test
	void testParseHTTPRequest() {
		HTTPRequest httpRequest = null;
		
		try {
			httpRequest = httpParser.parseHTTPRequest(generateValidGETTestCase());
		} catch (HTTPParsingException httpParsingException) {
			Assertions.fail(httpParsingException);
		}
		
		Assertions.assertNotNull(httpRequest);
		Assertions.assertEquals(httpRequest.getHttpMethod(), HTTPMethod.GET);
		Assertions.assertEquals(httpRequest.getHttpRequestTarget(), "/");
	}
	
	private InputStream generateValidGETTestCase() {
		String request = "GET / HTTP/1.1\r\n"
				+ "Host: localhost:8080\r\n"
				+ "Connection: keep-alive\r\n"
				+ "Cache-Control: max-age=0\r\n"
				+ "sec-ch-ua: \"Not)A;Brand\";v=\"99\", \"Google Chrome\";v=\"127\", \"Chromium\";v=\"127\"\r\n"
				+ "sec-ch-ua-mobile: ?0\r\n"
				+ "sec-ch-ua-platform: \"Windows\"\r\n"
				+ "Upgrade-Insecure-Requests: 1\r\n"
				+ "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36\r\n"
				+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7\r\n"
				+ "Sec-Fetch-Site: none\r\n"
				+ "Sec-Fetch-Mode: navigate\r\n"
				+ "Sec-Fetch-User: ?1\r\n"
				+ "Sec-Fetch-Dest: document\r\n"
				+ "Accept-Encoding: gzip, deflate, br, zstd\r\n"
				+ "Accept-Language: en-US,en;q=0.9\r\n"
				+ "\r\n";
		
		InputStream inputStream = new ByteArrayInputStream(request.getBytes(StandardCharsets.US_ASCII));
		
		return inputStream;
	}
	
	private InputStream generateNotValidGETTestCase() {
		String request = "Get / HTTP/1.1\r\n"
				+ "Host: localhost:8080\r\n"
				+ "Connection: keep-alive\r\n"
				+ "Cache-Control: max-age=0\r\n"
				+ "sec-ch-ua: \"Not)A;Brand\";v=\"99\", \"Google Chrome\";v=\"127\", \"Chromium\";v=\"127\"\r\n"
				+ "sec-ch-ua-mobile: ?0\r\n"
				+ "sec-ch-ua-platform: \"Windows\"\r\n"
				+ "Upgrade-Insecure-Requests: 1\r\n"
				+ "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36\r\n"
				+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7\r\n"
				+ "Sec-Fetch-Site: none\r\n"
				+ "Sec-Fetch-Mode: navigate\r\n"
				+ "Sec-Fetch-User: ?1\r\n"
				+ "Sec-Fetch-Dest: document\r\n"
				+ "Accept-Encoding: gzip, deflate, br, zstd\r\n"
				+ "Accept-Language: en-US,en;q=0.9\r\n"
				+ "\r\n";
		
		InputStream inputStream = new ByteArrayInputStream(request.getBytes(StandardCharsets.US_ASCII));
		
		return inputStream;
	}
	
	private InputStream generateNotValidGETTestCase2() {
		String request = "GETTTTTT / HTTP/1.1\r\n"
				+ "Host: localhost:8080\r\n"
				+ "Connection: keep-alive\r\n"
				+ "Cache-Control: max-age=0\r\n"
				+ "sec-ch-ua: \"Not)A;Brand\";v=\"99\", \"Google Chrome\";v=\"127\", \"Chromium\";v=\"127\"\r\n"
				+ "sec-ch-ua-mobile: ?0\r\n"
				+ "sec-ch-ua-platform: \"Windows\"\r\n"
				+ "Upgrade-Insecure-Requests: 1\r\n"
				+ "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36\r\n"
				+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7\r\n"
				+ "Sec-Fetch-Site: none\r\n"
				+ "Sec-Fetch-Mode: navigate\r\n"
				+ "Sec-Fetch-User: ?1\r\n"
				+ "Sec-Fetch-Dest: document\r\n"
				+ "Accept-Encoding: gzip, deflate, br, zstd\r\n"
				+ "Accept-Language: en-US,en;q=0.9\r\n"
				+ "\r\n";
		
		InputStream inputStream = new ByteArrayInputStream(request.getBytes(StandardCharsets.US_ASCII));
		
		return inputStream;
	}
	
	private InputStream generateNotValidGETTestCaseRequestLineInvalidNumberItems() {
		String request = "GET / ABABABAB HTTP/1.1\r\n"
				+ "Host: localhost:8080\r\n"
				+ "Connection: keep-alive\r\n"
				+ "Cache-Control: max-age=0\r\n"
				+ "sec-ch-ua: \"Not)A;Brand\";v=\"99\", \"Google Chrome\";v=\"127\", \"Chromium\";v=\"127\"\r\n"
				+ "sec-ch-ua-mobile: ?0\r\n"
				+ "sec-ch-ua-platform: \"Windows\"\r\n"
				+ "Upgrade-Insecure-Requests: 1\r\n"
				+ "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36\r\n"
				+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7\r\n"
				+ "Sec-Fetch-Site: none\r\n"
				+ "Sec-Fetch-Mode: navigate\r\n"
				+ "Sec-Fetch-User: ?1\r\n"
				+ "Sec-Fetch-Dest: document\r\n"
				+ "Accept-Encoding: gzip, deflate, br, zstd\r\n"
				+ "Accept-Language: en-US,en;q=0.9\r\n"
				+ "\r\n";
		
		InputStream inputStream = new ByteArrayInputStream(request.getBytes(StandardCharsets.US_ASCII));
		
		return inputStream;
	}
	
	private InputStream generateNotValidGETTestCaseEmptyRequestLine() {
		String request = "\r\n"
				+ "Host: localhost:8080\r\n"
				+ "Connection: keep-alive\r\n"
				+ "Cache-Control: max-age=0\r\n"
				+ "sec-ch-ua: \"Not)A;Brand\";v=\"99\", \"Google Chrome\";v=\"127\", \"Chromium\";v=\"127\"\r\n"
				+ "sec-ch-ua-mobile: ?0\r\n"
				+ "sec-ch-ua-platform: \"Windows\"\r\n"
				+ "Upgrade-Insecure-Requests: 1\r\n"
				+ "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36\r\n"
				+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7\r\n"
				+ "Sec-Fetch-Site: none\r\n"
				+ "Sec-Fetch-Mode: navigate\r\n"
				+ "Sec-Fetch-User: ?1\r\n"
				+ "Sec-Fetch-Dest: document\r\n"
				+ "Accept-Encoding: gzip, deflate, br, zstd\r\n"
				+ "Accept-Language: en-US,en;q=0.9\r\n"
				+ "\r\n";
		
		InputStream inputStream = new ByteArrayInputStream(request.getBytes(StandardCharsets.US_ASCII));
		
		return inputStream;
	}
	
	private InputStream generateNotValidGETTestCaseRequestLineOnlyCRnoLF() {
		String request = "GET / HTTP/1.1\r" // there's no line feed here, just for the test
				+ "Host: localhost:8080\r\n"
				+ "Connection: keep-alive\r\n"
				+ "Cache-Control: max-age=0\r\n"
				+ "sec-ch-ua: \"Not)A;Brand\";v=\"99\", \"Google Chrome\";v=\"127\", \"Chromium\";v=\"127\"\r\n"
				+ "sec-ch-ua-mobile: ?0\r\n"
				+ "sec-ch-ua-platform: \"Windows\"\r\n"
				+ "Upgrade-Insecure-Requests: 1\r\n"
				+ "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36\r\n"
				+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7\r\n"
				+ "Sec-Fetch-Site: none\r\n"
				+ "Sec-Fetch-Mode: navigate\r\n"
				+ "Sec-Fetch-User: ?1\r\n"
				+ "Sec-Fetch-Dest: document\r\n"
				+ "Accept-Encoding: gzip, deflate, br, zstd\r\n"
				+ "Accept-Language: en-US,en;q=0.9\r\n"
				+ "\r\n";
		
		InputStream inputStream = new ByteArrayInputStream(request.getBytes(StandardCharsets.US_ASCII));
		
		return inputStream;
	}
	
}
