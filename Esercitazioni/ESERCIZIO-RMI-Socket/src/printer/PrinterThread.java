package printer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class PrinterThread extends Thread {
	private Socket s;
	private PrinterSkel ske;
	
	public PrinterThread(Socket s, PrinterSkel ske) {
		this.s=s;
		this.ske=ske;
	}
	
	public void run() {
		try {
			DataInputStream fromClient=new DataInputStream(s.getInputStream());
			DataOutputStream toClient = new DataOutputStream(s.getOutputStream());

			String req=fromClient.readUTF();
			if(ske.print(req)) {
				toClient.writeBoolean(true);
			}else {
				toClient.writeBoolean(false);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
