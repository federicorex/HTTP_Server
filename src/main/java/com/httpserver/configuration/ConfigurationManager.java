package com.httpserver.configuration;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.httpserver.util.Json;

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
	public void loadConfigurationFile(String filePath) {
		FileReader fileReader = null;
		
		try {
			fileReader = new FileReader(filePath);
		} catch (FileNotFoundException fileNotFoundException) {
			throw new HttpConfigurationException(fileNotFoundException);
		}
		
		StringBuffer stringBuffer = new StringBuffer();
		int i;
		
		try {
			while((i = fileReader.read()) != -1 ) {
				stringBuffer.append((char) i);
			}
		} catch (IOException ioException) {
			throw new HttpConfigurationException(ioException);
		}
		JsonNode jsonNode = null;
		
		try {
			jsonNode = Json.parse(stringBuffer.toString());
			fileReader.close();
		} catch (IOException ioException) {
			throw new HttpConfigurationException("Error parsing the configuration file", ioException);
		}
		
		try {
			configuration = Json.fromJson(jsonNode, Configuration.class);
		} catch (JsonProcessingException jsonProcessingException) {
			throw new HttpConfigurationException("Error parsing the configuration file, internal", jsonProcessingException);
		}
	}
	
	/*
	 * Returns the loaded configuration
	 */
	public Configuration getCurrentConfiguration() {
		if(configuration == null) {
			throw new HttpConfigurationException("No configuaration set");
		}
		return configuration;
	}
	
}
