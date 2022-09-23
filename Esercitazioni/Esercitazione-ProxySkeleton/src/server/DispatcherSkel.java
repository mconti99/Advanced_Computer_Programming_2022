package server;
import service.*;


import java.net.*;
import java.io.*;

public abstract class DispatcherSkel implements IDispatcher {

	private int port;
	public DispatcherSkel(int p) {
		port=p;
	}
	public void runSkeleton() {
		
		try {
			
			@SuppressWarnings("resource")
			ServerSocket server = new ServerSocket(port); //socket server
			System.out.println("Server in ascolto sulla porta "+port);
			
			while (true){
				
				Socket socket = server.accept();
				ServerThread st = new ServerThread(socket, this);
				st.start();
			}
			
		} catch (IOException e) {
			// Eccezione dovuta alle socket
			e.printStackTrace();
		}
	}

}
		