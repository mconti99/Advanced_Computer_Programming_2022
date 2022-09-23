package node;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.IDispatcher;

public class Client {
	public static void main(String[] args) {
		try {
			Registry rmi=LocateRegistry.getRegistry();
			IDispatcher dispatcher= (IDispatcher) rmi.lookup("dispatcher");
			ClientThread[] threads=new ClientThread[5];
			System.out.println("[CLIENT] Avviato");
			for(int i=0;i<5;i++) {
				threads[i]=new ClientThread(dispatcher);
				threads[i].start();
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
