package actuator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import service.IActuator;

public class ActuatorThread extends Thread{
	private Socket s;
	private IActuator actuator;
	
	public ActuatorThread(Socket socket, IActuator act) {
		s=socket;
		actuator=act;
	}
	
	public void run() {
		try {
			DataInputStream in=new DataInputStream(s.getInputStream());
			DataOutputStream out=new DataOutputStream(s.getOutputStream());
			
			String stringa = in.readUTF();
			if(actuator.execute(stringa)) {
				out.writeBoolean(true);
			}else {
				out.writeBoolean(false);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
