package com.httpserver.configuration;

public class ConfigurationManager {

	private static ConfigurationManager configurationManager;
	private static Configuration configuration;
	
	private ConfigurationManager() {
	
	}
	
	public static ConfigurationManager getInstance() {
		if(configurationManager == null) {
			configurationManager = new ConfigurationManager();
		}
		return configurationManager;
	}
	
	/*
	 * Used to load a configuration file by the path provided
	 */
	public static void loadConfigurationFile(String filePath) {
		
	}
	
	/*
	 * Returns the loaded configuration
	 */
	public static void getCurrentConfiguration() {
		
	}
	
}
