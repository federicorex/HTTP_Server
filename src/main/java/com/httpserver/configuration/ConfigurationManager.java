package com.httpserver.configuration;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.httpserver.util.Json;

// This class is projected to create and manage the Configuration object.
public class ConfigurationManager {

	private static ConfigurationManager configurationManager;
	private static Configuration configuration;
	
	private ConfigurationManager() {
	
	}
	
	// Singleton creation.
	public static ConfigurationManager getInstance() {
		if(configurationManager == null) {
			configurationManager = new ConfigurationManager();
		}
		return configurationManager;
	}
	
	/*
	 * Used to load a configuration file by the path provided:
	 *   1) The file path is read by a File Reader, a new String is created with the same characters of the file from the file path;
	 *   2) Once the new String (Json) is created it's converted from its status (String) to Json Node and then to an Object (Configuration).
	 */
	public void loadConfigurationFile(String filePath) {
		FileReader fileReader = null;
		
		try {
			fileReader = new FileReader(filePath);
		} catch (FileNotFoundException fileNotFoundException) {
			throw new HTTPConfigurationException(fileNotFoundException);
		}
		
		StringBuffer stringBuffer = new StringBuffer();
		int i;
		
		try {
			while((i = fileReader.read()) != -1 ) {
				stringBuffer.append((char) i);
			}
		} catch (IOException ioException) {
			throw new HTTPConfigurationException(ioException);
		}
		
		JsonNode jsonNode = null;
		
		try {
			jsonNode = Json.parse(stringBuffer.toString());
			fileReader.close();
		} catch (IOException ioException) {
			throw new HTTPConfigurationException("Error parsing the configuration file", ioException);
		}
		
		try {
			configuration = Json.fromJson(jsonNode, Configuration.class);
		} catch (JsonProcessingException jsonProcessingException) {
			throw new HTTPConfigurationException("Error parsing the configuration file, internal", jsonProcessingException);
		}
	}
	
	// Returns the loaded configuration, once it's created by the method above.
	public Configuration getCurrentConfiguration() {
		if(configuration == null) {
			throw new HTTPConfigurationException("No configuration set, please set one");
		}
		return configuration;
	}
	
}
