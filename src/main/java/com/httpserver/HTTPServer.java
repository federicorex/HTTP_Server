package com.httpserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.httpserver.configuration.Configuration;
import com.httpserver.configuration.ConfigurationManager;

/**
 * Hello world!
 *
 */
public class HTTPServer 
{
    public static void main( String[] args ) {
        System.out.println( "The server is starting..." );
        
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration configuration = ConfigurationManager.getInstance().getCurrentConfiguration();
        
        System.out.println("Port: " + configuration.getPort());
        System.out.println("WebRoot: " + configuration.getWebRoot());
        
        try {
        	ServerSocket serverSocket = new ServerSocket(configuration.getPort());
        	Socket socket = serverSocket.accept();
        	
        	InputStream inputStream = socket.getInputStream();
        	OutputStream outputStream = socket.getOutputStream();
        	
        	String html = "<!DOCTYPE html>\r\n"
        			+ "<html lang=\"en\">\r\n"
        			+ "<head>\r\n"
        			+ "    <meta charset=\"UTF-8\">\r\n"
        			+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
        			+ "    <title>Document</title>\r\n"
        			+ "</head>\r\n"
        			+ "<body>\r\n"
        			+ "   <h1>This page is served using the HTTP Server</h1> \r\n"
        			+ "</body>\r\n"
        			+ "</html>";
        	
        	final String CRLF = "\n\r";
        	
        	String response = "HTTP/1.1 200 OK" + CRLF + // Status Line: HTTP/VERSION, HTTP_RESPONSE_CODE, RESPONSE_MESSAGE
        		"Content-Length: " + html.getBytes().length + CRLF + // Header
        		CRLF +
        		html +
        		CRLF + CRLF;
        	
        	outputStream.write(response.getBytes());
        	
        	inputStream.close();
        	outputStream.close();
        	socket.close();
        	serverSocket.close();
        	
        } catch(IOException ioException) {
        	ioException.printStackTrace();
        }
    }
}
