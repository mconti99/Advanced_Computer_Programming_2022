package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import service.IMagazzino;

public class MagazzinoThread extends Thread{
	private Socket s;
	private IMagazzino magazzino;
	
	public MagazzinoThread(Socket s, IMagazzino magazzino) {
		this.s=s;
		this.magazzino=magazzino;
	}
	
	public void run() {
		
		try {
			DataOutputStream ostream = new DataOutputStream(s.getOutputStream());
			DataInputStream istream = new DataInputStream(s.getInputStream());
			
			String operation;
			operation = istream.readUTF();
			System.out.println("[MAGAZZINO] servo l'operazione: "+operation);
			if(operation.equals("deposita")) {
				String coda=istream.readUTF();
				int id=istream.readInt();
				magazzino.deposita(coda, id);
			}else {
				String coda=istream.readUTF();
				int id=magazzino.preleva(coda);
				ostream.writeInt(id);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
