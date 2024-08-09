package com.httpserver.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Json {

	private static ObjectMapper objectMapper = defaultObjectMapper();
	
	private static ObjectMapper defaultObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return objectMapper;
	}
	
	// From JSON string (initial step) to JSON Node (intermediate step)
	public static JsonNode parse(String jsonSource) throws IOException {
		return objectMapper.readTree(jsonSource);
	}
	
	// From JSON Node (intermediate step) to Object/Generic (final step)
	public static <T> T fromJson(JsonNode jsonNode, Class<T> clazz) throws JsonProcessingException {
		return objectMapper.treeToValue(jsonNode, clazz);
	}
	
	// Object/Generic (initial step) to from JSON Node (intermediate step) 
	public static JsonNode toJson(Object object) {
		return objectMapper.valueToTree(object);
	}
	
	//  From JSON Node (intermediate step) to JSON string --not indented (final step)
	public static String stringify(JsonNode jsonNode) throws JsonProcessingException {
		return generateJson(jsonNode, Boolean.FALSE);
	}
	
	//  From JSON Node (intermediate step) to JSON string --indented (final step)
	public static String stringifyPretty(JsonNode jsonNode) throws JsonProcessingException {
		return generateJson(jsonNode, Boolean.TRUE);
	}
	
	private static String generateJson(Object object, Boolean pretty) throws JsonProcessingException {
		ObjectWriter objectWriter = objectMapper.writer();
		if(Boolean.valueOf(pretty.booleanValue()) == true) {
			objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);
		}
		return objectWriter.writeValueAsString(object);
	}
	
}
