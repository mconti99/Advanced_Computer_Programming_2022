package dispatcher;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import service.IPrinter;

public class DispatcherProxy implements IPrinter{
	private String addr;
	private int port;
	
	DispatcherProxy(String addr, int port){
		this.addr=addr;
		this.port=port;
	}
	
	public boolean print(String docName) {
		Socket s;
		try {
			s = new Socket(addr, port);
			DataInputStream fromServer=new DataInputStream(s.getInputStream());
			DataOutputStream toServer=new DataOutputStream(s.getOutputStream());
			toServer.writeUTF(docName);
			return fromServer.readBoolean();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
		
	}
}
