package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.rmi.UnknownHostException;

import service.IDispatcher;

public class DispatcherProxy implements IDispatcher{
	private String addr;
	private int port;
	
	public DispatcherProxy(String a, int p) {
		addr= new String (a);
		port = p;
	}
	
	public void sendCmd(int cmd) {
		try {
			Socket s= new Socket(addr, port);
			DataOutputStream dataOut = new DataOutputStream (s.getOutputStream());
			DataInputStream dataIn = new DataInputStream(s.getInputStream());
			
			dataOut.writeUTF("sendCmd");
			dataOut.writeInt(cmd);
			
			dataIn.readUTF();//forza il proxy ad attendere una risposta dal server
			s.close();
		}catch(UnknownHostException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getCmd() {
		int x=0;
		try {
			Socket s= new Socket(addr, port);
			DataOutputStream dataOut = new DataOutputStream (s.getOutputStream());
			DataInputStream dataIn = new DataInputStream(s.getInputStream());
			
			dataOut.writeUTF("getCmd");
			x=dataIn.readInt();
			
			s.close();
		}catch(UnknownHostException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return x;
	}
}
