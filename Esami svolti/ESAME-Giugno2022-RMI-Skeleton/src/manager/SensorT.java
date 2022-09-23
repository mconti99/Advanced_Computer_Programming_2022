package manager;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;

import service.IController;

public class SensorT extends Thread {
	public void run() {
		try {
			Registry rmi=LocateRegistry.getRegistry();
			IController controller =(IController) rmi.lookup("mycontroller");
			String type;
			if(Math.random()<0.5) {
				type="temperature";
			}else {
				type="pressure";
			}
			Random rand=new Random();
			int data=rand.nextInt(50)+1;
			Reading r=new Reading(data, type);
			controller.sensorRead(r);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
