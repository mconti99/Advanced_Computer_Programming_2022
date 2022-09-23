package observer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.IDispatcher;
import service.IObserver;

public class Observer {
	public static void main(String[] args) {
		try {
			Registry rmi=LocateRegistry.getRegistry();
			IDispatcher dispatcher=(IDispatcher) rmi.lookup("mydispatcher");
			System.out.println("[OBSERVER] Riferimento remoto al dispatcher creato" );
			IObserver observer=new ObserverImpl(args[1], dispatcher);
			dispatcher.attach(observer, args[0]);
			System.out.println("[OBSERVER] Attach effettuata con successo alla lista "+ args[0]);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
