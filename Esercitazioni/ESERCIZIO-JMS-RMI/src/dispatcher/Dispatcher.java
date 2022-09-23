package dispatcher;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Dispatcher {
	public static void main(String[] args) {
		Hashtable<String, String> prop= new Hashtable<String, String>();
		
		prop.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		prop.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
		prop.put("queue.PrintRequest", "PrintRequest");
		
		try {
			Context context=new InitialContext(prop);
			QueueConnectionFactory connf= (QueueConnectionFactory) context.lookup("QueueConnectionFactory");
			Queue coda = (Queue) context.lookup("PrintRequest");
			QueueConnection conn= connf.createQueueConnection();
			conn.start();
			QueueSession session= conn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			QueueReceiver receiver=session.createReceiver(coda);
			DispatcherListener listener=new DispatcherListener();
			receiver.setMessageListener(listener);
			System.out.println("[DISPATCHER] Dispatcher settato correttamente");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
