package subscriber;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class SensorReceiver implements MessageListener{
	private CodaCircolare coda;
	
	public SensorReceiver(CodaCircolare c) {
		coda=c;
	}
	public void onMessage(Message m) {
		TextMessage message=(TextMessage) m;
		try {
			String comando=message.getText();
			System.out.println("[LISTENER] Ho ricevuto il comando "+comando);
			TManager thread =new TManager(comando,coda);
			thread.start();

		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
