package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import service.IActuator;

public class ControllerProxy implements IActuator{
	private int port;
	
	public ControllerProxy(int p) {
		port=p;
	}
	
	public boolean execute(String stringa) {
		try {
			Socket s=new Socket("127.0.0.1", port);
			DataInputStream in=new DataInputStream(s.getInputStream());
			DataOutputStream out= new DataOutputStream(s.getOutputStream());
			out.writeUTF(stringa);
			System.out.println("[CONTROLLER] ho inviato la stringa "+ stringa);
			boolean result= in.readBoolean();
			s.close();
			return result;
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}

}
