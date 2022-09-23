package disk;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Disk {
	public static void main(String[] args) {
		Hashtable<String,String > prop= new Hashtable<String, String>();
		prop.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		prop.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
		prop.put("queue.Storage", "Storage");
		
		try {
			InitialContext context=new InitialContext(prop);
			QueueConnectionFactory connf= (QueueConnectionFactory) context.lookup("QueueConnectionFactory");
			Queue queue= (Queue) context.lookup("Storage");
			
			QueueConnection conn= connf.createQueueConnection();
			conn.start();
			QueueSession session=conn.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);
			QueueReceiver receiver=session.createReceiver(queue);
			
			DiskListener listener=new DiskListener();
			receiver.setMessageListener(listener);
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
