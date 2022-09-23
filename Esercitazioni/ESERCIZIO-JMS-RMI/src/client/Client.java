package client;

import java.util.Hashtable;
import java.util.Random;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class Client {
	public static void main(String[] args) {
		Hashtable<String, String> prop= new Hashtable<String, String>();

		prop.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		prop.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
		prop.put("queue.PrintRequest", "PrintRequest");

		Context context;
		try {
			context = new InitialContext(prop);
			QueueConnectionFactory connf= (QueueConnectionFactory) context.lookup("QueueConnectionFactory");
			Queue coda = (Queue) context.lookup("PrintRequest");
			QueueConnection conn= connf.createQueueConnection();
			QueueSession session= conn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			QueueSender sender=session.createSender(coda);
			MapMessage m=session.createMapMessage();
			for(int i=0;i<5;i++) {
				Random rand=new Random();
				int numero= rand.nextInt(41);
				String nomeDoc="doc"+numero;
				m.setString("nomeDocumento", nomeDoc);
				m.setString("nomePrinter", args[0]);
				sender.send(m);
				System.out.println("[CLIENT] Ho inviato il documento "+ nomeDoc+ " alla printer "+ args[0]);
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
