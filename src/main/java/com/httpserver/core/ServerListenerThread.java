package com.httpserver.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerListenerThread extends Thread{
	
	private final static Logger LOGGER = LoggerFactory.getLogger(ServerListenerThread.class);

	private int port;
	private String webRoot;
	private ServerSocket serverSocket;
	
	public ServerListenerThread(int port, String webRoot) throws IOException {
		super();
		this.port = port;
		this.webRoot = webRoot;
		this.serverSocket = new ServerSocket(this.port);
	}

	@Override
	public void run() {
		try {
			
        	while (serverSocket.isBound() && !serverSocket.isClosed()) {
        		
				Socket socket = serverSocket.accept();
				LOGGER.info(" ** CONNECTION ACCEPTED ** " + socket.getInetAddress());
				HTTPConnectionWorkerThread httpConnectionWorkerThread = new HTTPConnectionWorkerThread(socket);
				httpConnectionWorkerThread.start();
			}
        	
        } catch(IOException ioException) {
        	LOGGER.error(" !! Problem with setting socket !!", ioException);
        } finally {
			if(serverSocket != null) {
				try {
					serverSocket.close();
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
			}
		}
	}
	
}
