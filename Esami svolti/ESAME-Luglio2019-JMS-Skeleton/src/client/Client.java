package client;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class Client {
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
			QueueSession session=conn.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);
			QueueSender sender=session.createSender(queue);
			MapMessage m=session.createMapMessage();
			
			m.setInt("dato", Integer.parseInt(args[0]));
			m.setInt("porta", Integer.parseInt(args[1]));
			
			sender.send(m);
			System.out.println("[CLIENT] Messaggio inviato");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
