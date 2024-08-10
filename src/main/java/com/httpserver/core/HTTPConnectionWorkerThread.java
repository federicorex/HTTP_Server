package com.httpserver.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// This class handles individual client connections, processes the request and sends back a simple HTTP response.
public class HTTPConnectionWorkerThread extends Thread{
	
	private final static Logger LOGGER = LoggerFactory.getLogger(HTTPConnectionWorkerThread.class);

	// The socket object represents the client connection.
	private Socket socket;
	
	public HTTPConnectionWorkerThread(Socket socket) {
		super();
		this.socket = socket;
	}

	/*
	 * 1) Opens InputStream and OutputStream for reading from and writing to the client.
	 * 2) Constructs a simple HTML page as a String, prepares a basic HTTP response with a status line, headers and the HTML content.
	 * 3) Sends the response back to the client through the OutputStream.
	 * 4) Closes the streams and the socket to free resources.
	 */
	@Override
	public void run() {
		
		InputStream inputStream = null;
		OutputStream outputStream = null;
		
		try {
			inputStream = socket.getInputStream();
			outputStream = socket.getOutputStream();
			
			String html = "<!DOCTYPE html>\r\n" + "<html lang=\"en\">\r\n" + "<head>\r\n"
					+ "    <meta charset=\"UTF-8\">\r\n"
					+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
					+ "    <title>Document</title>\r\n" + "</head>\r\n" + "<body>\r\n"
					+ "   <h1>This page is served using the HTTP Server</h1> \r\n" + "</body>\r\n" + "</html>";
			final String CRLF = "\n\r";
			String response = "HTTP/1.1 200 OK" + CRLF + // Status Line: HTTP/VERSION, HTTP_RESPONSE_CODE, RESPONSE_MESSAGE
					"Content-Length: " + html.getBytes().length + CRLF + // Header
					CRLF + html + CRLF + CRLF;
			outputStream.write(response.getBytes());

			LOGGER.info(" ** CONNECTION PROCESSING FINISHED ** ");
		} catch (IOException ioException) {
			LOGGER.error(" !! Problem with the connection !! ", ioException);
		} finally {
			if(inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
			}
			if(outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
			}
			if(socket != null) {
				try {
					socket.close();
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
			}
		}
	}
	
}
