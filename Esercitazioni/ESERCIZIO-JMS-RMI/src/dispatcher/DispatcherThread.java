package dispatcher;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.IPrinter;

public class DispatcherThread extends Thread{
	String documento;
	String printer;
	
	public DispatcherThread(String p, String d){
		documento=d;
		printer=p;
	}
	
	public void run() {
		System.out.println("[DISPATCHER] Il nome documento è "+documento);
		try {
			Registry rmi=LocateRegistry.getRegistry();
			IPrinter service=(IPrinter) rmi.lookup(printer);
			service.printDoc(documento);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
