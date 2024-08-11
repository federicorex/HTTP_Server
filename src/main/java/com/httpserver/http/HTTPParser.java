package com.httpserver.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HTTPParser {

	private final static Logger LOGGER = LoggerFactory.getLogger(HTTPParser.class);
	private final static int SP = 0x20; // 32
	private final static int CR = 0x0D; // 13
	private final static int LF = 0x0A; // 10
	
	public HTTPRequest parseHTTPRequest(InputStream inputStream) throws IOException {
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.US_ASCII);
		HTTPRequest httpRequest = new HTTPRequest();
		
		parseRequestLine(inputStreamReader, httpRequest);
		parseRequestHeader(inputStreamReader, httpRequest);
		parseRequestBody(inputStreamReader, httpRequest);
		
		return httpRequest;
	}

	private void parseRequestLine(InputStreamReader inputStreamReader, HTTPRequest httpRequest) throws IOException {
		int _byte;
		
		while((_byte = inputStreamReader.read()) >= 0) {
			if(_byte == CR) {
				inputStreamReader.read();
				if(_byte == LF) {
					inputStreamReader.read();
					return;
				}
			}
		}
	}
	
	private void parseRequestHeader(InputStreamReader inputStreamReader, HTTPRequest httpRequest) {
		
	}
	
	private void parseRequestBody(InputStreamReader inputStreamReader, HTTPRequest httpRequest) {
		
	}

}
