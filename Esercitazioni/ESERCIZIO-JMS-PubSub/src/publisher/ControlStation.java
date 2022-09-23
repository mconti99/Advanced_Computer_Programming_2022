package publisher;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ControlStation {
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
			TopicSession session=conn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			TopicPublisher pub= session.createPublisher(topic);
			TextMessage text= session.createTextMessage();
			
			for(int i=0;i<20;i++) {
				if(i%3==0) {
					text.setText("startSensor");
					pub.publish(text);
				}else if(i%3==1) {
					text.setText("stopSensor");
					pub.publish(text);
				}else {
					text.setText("read");
					pub.publish(text);
				}
				System.out.println("Ho pubblicato "+ text.getText());
				Thread.sleep(1000);
			}
			
			pub.close();
			session.close();
			conn.close();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
