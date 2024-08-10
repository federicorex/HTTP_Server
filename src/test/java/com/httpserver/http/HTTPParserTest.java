package com.httpserver.http;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HTTPParserTest {

	private HTTPParser httpParser;
	
	@BeforeEach
	void setUp() {
		httpParser = new HTTPParser();
	}
	
	@Test
	void testParseHTTPRequest() {
		httpParser.parseHTTPRequest(generateValidTestCase());
	}
	
	private InputStream generateValidTestCase() {
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
	
}
