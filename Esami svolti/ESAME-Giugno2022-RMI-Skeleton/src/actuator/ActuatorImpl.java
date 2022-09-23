package actuator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import service.IController;

public class ActuatorImpl extends ActuatorSkeleton{
	private String filename;
	Lock lock;
	
	public ActuatorImpl(int port, String file) {
		super(port);
		lock=new ReentrantLock();
		filename=file;
		try {
			Registry rmi=LocateRegistry.getRegistry();
			IController controller = (IController) rmi.lookup("mycontroller");
			controller.addActuator(port);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean execute(String stringa) {
		if(!lock.tryLock()) {
			return false;
		}else {
			StringTokenizer messageTokens=new StringTokenizer(stringa, "#");
			String type= messageTokens.nextToken();
			int data=Integer.valueOf(messageTokens.nextToken()).intValue();
			System.out.println("[ACTUATOR] Ho estratto il tipo "+ type +" e il dato "+ data);
			try {
				FileOutputStream fileOut=new FileOutputStream(filename, true);
				PrintStream outStream = new PrintStream(fileOut);
				outStream.println("Tipo: "+ type+"; Data: "+ data+".");
				outStream.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Random rand= new Random();
			try {
				Thread.sleep(1+rand.nextInt(5));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lock.unlock();
			return true;
		}
	}

}
