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
	
	public HTTPRequest parseHTTPRequest(InputStream inputStream) throws HTTPParsingException {
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.US_ASCII);
		HTTPRequest httpRequest = new HTTPRequest();
		
		try {
			parseRequestLine(inputStreamReader, httpRequest);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
		parseRequestHeader(inputStreamReader, httpRequest);
		parseRequestBody(inputStreamReader, httpRequest);
		
		return httpRequest;
	}

	private void parseRequestLine(InputStreamReader inputStreamReader, HTTPRequest httpRequest) throws IOException, HTTPParsingException {
		StringBuilder processingDataBuffer = new StringBuilder();
		int _byte; 
		boolean httpMethodParsed = false;
		boolean httpRequestTargetParsed = false;
		
		while((_byte = inputStreamReader.read()) >= 0) {
			if(_byte == CR) {
				_byte = inputStreamReader.read();
				if(_byte == LF) {
					LOGGER.debug("Request line Version to process: {}", processingDataBuffer.toString());
					if(!httpMethodParsed || !httpRequestTargetParsed) {
						throw new HTTPParsingException(HTTPStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
					}
					try {
						httpRequest.setOriginalHTTPVersion(processingDataBuffer.toString());
					} catch (BadHTTPVersionException e) {
						throw new HTTPParsingException(HTTPStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
					}
					
					return;
				} else {
					throw new HTTPParsingException(HTTPStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
				}
			}
			
			if(_byte == SP) {
				if(!httpMethodParsed) {
					LOGGER.debug("Request line Method to process: {}", processingDataBuffer.toString());
					
					httpRequest.setHttpMethod(processingDataBuffer.toString());
					httpMethodParsed = true;
				} else if(!httpRequestTargetParsed) {
					LOGGER.debug("Request line Request Target to process: {}", processingDataBuffer.toString());
					httpRequest.setHttpRequestTarget(processingDataBuffer.toString());
					
					httpRequestTargetParsed = true;
				} else {
					throw new HTTPParsingException(HTTPStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
				}
				processingDataBuffer.delete(0, processingDataBuffer.length());
			} else {
				processingDataBuffer.append((char) _byte);
				if(!httpMethodParsed) {
					if(processingDataBuffer.length() > HTTPMethod.MAX_LENGTH) {
						throw new HTTPParsingException(HTTPStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);
					}
				}
			}
		}
	}
	
	private void parseRequestHeader(InputStreamReader inputStreamReader, HTTPRequest httpRequest) {
		
	}
	
	private void parseRequestBody(InputStreamReader inputStreamReader, HTTPRequest httpRequest) {
		
	}

}
