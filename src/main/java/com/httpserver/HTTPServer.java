package com.httpserver;

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
    }
}
