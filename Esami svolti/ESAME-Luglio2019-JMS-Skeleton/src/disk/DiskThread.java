package disk;

import javax.jms.JMSException;
import javax.jms.MapMessage;

import service.ILogger;

public class DiskThread extends Thread{

	MapMessage message;
	public DiskThread(MapMessage message) {
		// TODO Auto-generated constructor stub
		this.message=message;
	}
	
	public void run() {
		try {
			int dato=message.getInt("dato");
			int port=message.getInt("porta");
			System.out.println("[THREAD] Il dato è "+dato);
			ILogger proxy=new DiskProxy(port);
			proxy.registraDato(dato);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
