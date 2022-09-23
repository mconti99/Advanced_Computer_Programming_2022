package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import service.IDispatcher;

public class ServerThread extends Thread{
		
		private Socket s;
		private IDispatcher dispatcher;
		
		public ServerThread (Socket s, IDispatcher dispatcher){
			
			this.s = s;
			this.dispatcher = dispatcher;
		}
		
		public void run() {
			
			System.out.println("   [ServerThread] run thread! ");
			
			try {
				
				DataOutputStream ostream = new DataOutputStream(s.getOutputStream());
			    DataInputStream istream = new DataInputStream(s.getInputStream());
		 
			    // lettura dell'operazione, ovvero il metodo, prima parola
				String operation = istream.readUTF();
			    System.out.println("Servo l'operazione: " + operation);
	    		   
			    //unmarshalling e invocazione sul servizio reale 
			    if (operation.equals("getCmd")) {
			    	
			    	ostream.writeInt(dispatcher.getCmd());
			    		
			    } else if (operation.equals("sendCmd")) {
			    	
			    	int value = istream.readInt();
			    	dispatcher.sendCmd(value);
			    	ostream.writeUTF("ack");
			    }
	    	
			} catch (IOException e) {
				// Eccezione dovuta alle socket
				e.printStackTrace();
			}
		}
}