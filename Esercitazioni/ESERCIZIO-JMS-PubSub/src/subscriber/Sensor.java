package subscriber;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Sensor {
	public static void main(String[] args) {
		Hashtable<String,String> prop= new Hashtable<String,String>();

		prop.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		prop.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
		prop.put("topic.command", "command");
		try {
			Context context= new InitialContext(prop);
			TopicConnectionFactory connf=(TopicConnectionFactory) context.lookup("TopicConnectionFactory");
			Topic topic=(Topic) context.lookup("command");

			TopicConnection conn=connf.createTopicConnection();
			conn.start();
			TopicSession session=conn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			TopicSubscriber sub= session.createSubscriber(topic);
			CodaCircolare coda= new CodaCircolare();
			SensorReceiver listener=new SensorReceiver(coda);
			sub.setMessageListener(listener);
			
			System.out.println("[SENSOR] Startato");
			
			TExecutor thread = new TExecutor(coda);
			thread.start();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
