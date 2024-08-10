package com.httpserver;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.httpserver.configuration.Configuration;
import com.httpserver.configuration.ConfigurationManager;
import com.httpserver.core.ServerListenerThread;

/**
 * Drive class for the HTTP server: the class is the main entry point for starting the HTTP server. It brings together different components such as configuration management and server setup, and it initiates the server thread that will listen for incoming connections.
 */
public class HTTPServer {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(HTTPServer.class);
	
    /*
     * 1) The server loads its configuration from a JSON file located in the directory;
     * 2) Once the configuration file is loaded, the current configuration is retrieved;
     * 3) A ServerListenerThread object is created using the port and web root directory from the configuration. This thread will be responsible for listening to incoming connections;
     * 4) The server listener thread is started, which begins the process of accepting and handling client connections.
     */
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
