package disk;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

public class DiskListener implements MessageListener {

	@Override
	public void onMessage(Message arg0) {
		// TODO Auto-generated method stub
		MapMessage message=(MapMessage) arg0;
		DiskThread thread=new DiskThread(message);
		thread.start();
	}

}
