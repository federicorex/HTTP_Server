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
		} catch (FileNotFoundException e) {
			throw new HttpConfigurationException(e);
		}
		
		StringBuffer stringBuffer = new StringBuffer();
		int i;
		
		try {
			while((i = fileReader.read()) != -1 ) {
				stringBuffer.append((char) i);
			}
		} catch (IOException e) {
			throw new HttpConfigurationException(e);
		}
		JsonNode jsonNode = null;
		
		try {
			jsonNode = Json.parse(stringBuffer.toString());
			fileReader.close();
		} catch (IOException e) {
			throw new HttpConfigurationException("Error parsing the configuration file", e);
		}
		
		try {
			configuration = Json.fromJson(jsonNode, Configuration.class);
		} catch (JsonProcessingException e) {
			throw new HttpConfigurationException("Error parsing the configuration file, internal", e);
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
