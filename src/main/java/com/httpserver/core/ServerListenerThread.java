package com.httpserver.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// This class is responsible for setting up the server socket, listening for incoming connections on a specific port and delegating the actual handling of each connection to a separate thread --that is, the class HTTPConnectionWorkerThread.
public class ServerListenerThread extends Thread{
	
	private final static Logger LOGGER = LoggerFactory.getLogger(ServerListenerThread.class);

	private int port;
	private String webRoot;
	// This server socket listens for incoming client connections.
	private ServerSocket serverSocket;
	
	public ServerListenerThread(int port, String webRoot) throws IOException {
		super();
		this.port = port;
		this.webRoot = webRoot;
		this.serverSocket = new ServerSocket(this.port);
	}

	/*
	 * 1) Continuously listens for client connections using serverSocket.accept();
	 * 2) When a client connects, a Socket object is created representing the connection;
	 * 3) A new HTTPConnectionWorkerThread is created and started to handle this connection.
	 */
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
