package actuator;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import service.IActuator;

public abstract class ActuatorSkeleton implements IActuator{
	private int p;
	public ActuatorSkeleton(int port) {
		p=port;
	}
	public void runSkeleton() {
		try {
			@SuppressWarnings("resource")
			ServerSocket server= new ServerSocket(p);
			while(true) {
				Socket s= server.accept();
				ActuatorThread thread=new ActuatorThread(s, this);
				thread.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
