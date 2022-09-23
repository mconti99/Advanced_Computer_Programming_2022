package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import service.IMagazzino;

public class MagazzinoProxy implements IMagazzino{
	private int port;
	
	public MagazzinoProxy(int p) {
		port =p;
	}
	
	public void deposita(String articolo, int id) {
		try {
			Socket s= new Socket("127.0.0.1", port);
			DataOutputStream dataOut= new DataOutputStream(s.getOutputStream());
			
			
			dataOut.writeUTF("deposita");
			dataOut.writeUTF(articolo);
			dataOut.writeInt(id);
			
			s.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int preleva(String articolo) {
		int x=0;
		try {
			Socket s= new Socket("127.0.0.1", port);
			DataOutputStream dataOut= new DataOutputStream(s.getOutputStream());
			DataInputStream dataIn = new DataInputStream(s.getInputStream());
			
			
			dataOut.writeUTF("preleva");
			dataOut.writeUTF(articolo);
			x=dataIn.readInt();
			
			s.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return x;
	}
}
