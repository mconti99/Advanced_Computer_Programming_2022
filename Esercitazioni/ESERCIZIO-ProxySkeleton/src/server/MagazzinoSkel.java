package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import service.IMagazzino;

public abstract class MagazzinoSkel implements IMagazzino{
	private int port;
	public MagazzinoSkel(int p) {
		port = p;
	}
	
	public void runSkeleton() {
		ServerSocket server;
		try {
			server = new ServerSocket(port);
			System.out.println("Server in ascolto sulla porta "+port);
			
			while(true) {
				Socket socket=server.accept();
				MagazzinoThread mt= new MagazzinoThread(socket, this);
				mt.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
