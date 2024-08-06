package com.httpserver.configuration;

public class Configuration {

	private int port;
	private String webRoot;
	
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getWebRoot() {
		return webRoot;
	}
	public void setWebRoot(String webRoot) {
		this.webRoot = webRoot;
	}
	
}
