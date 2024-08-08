package com.httpserver;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.httpserver.configuration.Configuration;
import com.httpserver.configuration.ConfigurationManager;
import com.httpserver.core.ServerListenerThread;

/**
 * Drive class for the http server
 *
 */
public class HTTPServer {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(HTTPServer.class);
	
    public static void main( String[] args ) {
        LOGGER.info("The server is starting...");
        
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration configuration = ConfigurationManager.getInstance().getCurrentConfiguration();
        
        LOGGER.info("Port: " + configuration.getPort());
        LOGGER.info("WebRoot: " + configuration.getWebRoot());
        
        try {
			ServerListenerThread serverListenerThread = new ServerListenerThread(configuration.getPort(), configuration.getWebRoot());
			serverListenerThread.start();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
    }
}
