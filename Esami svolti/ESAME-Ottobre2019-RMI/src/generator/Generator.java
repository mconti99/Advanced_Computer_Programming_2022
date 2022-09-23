package generator;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.IDispatcher;

public class Generator {
	public static void main(String[] args) {
		GeneratorThread[] threads= new GeneratorThread[3];
		try {
			Registry rmi=LocateRegistry.getRegistry();
			IDispatcher dispatcher=(IDispatcher) rmi.lookup("mydispatcher");
			for(int i=0;i<3;i++) {
				threads[i]=new GeneratorThread(dispatcher);
				System.out.println("[GENERATOR] Thread "+i+" in esecuzione");
				threads[i].start();
			}
			
			for(int i=0;i<3;i++) {
				try {
					threads[i].join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}
}
