package dispatcher;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

public class DispatcherListener implements MessageListener{
	public void onMessage(Message m) {
		MapMessage messaggio=(MapMessage) m;
		try {
			String nomePrinter=messaggio.getString("nomePrinter");
			String nomeDocumento=messaggio.getString("nomeDocumento");
			DispatcherThread thread=new DispatcherThread(nomePrinter, nomeDocumento);
			thread.start();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
