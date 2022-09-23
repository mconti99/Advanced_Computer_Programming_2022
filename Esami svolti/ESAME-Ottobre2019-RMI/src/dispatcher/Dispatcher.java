package dispatcher;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.IDispatcher;

public class Dispatcher {
	public static void main(String[] args) {
		try {
			Registry rmi=LocateRegistry.getRegistry();
			IDispatcher dispatcher=new DispatcherImpl();
			rmi.rebind("mydispatcher", dispatcher);
			System.out.println("[DISPATCHER] Registrato sul RMI Registry");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
