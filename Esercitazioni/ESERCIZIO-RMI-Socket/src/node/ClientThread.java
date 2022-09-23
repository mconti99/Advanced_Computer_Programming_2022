package node;

import java.rmi.RemoteException;
import java.util.Random;

import service.IDispatcher;

public class ClientThread extends Thread{
	private IDispatcher dispatcher;
	public ClientThread(IDispatcher dispatcher) {
		this.dispatcher=dispatcher;
	}
	
	public void run() {
		for(int i=0;i<3;i++) {
			
			try {
				Random rand=new Random();
				int numero=rand.nextInt(50)+1;
				String docName="doc"+numero;
				if(dispatcher.printRequest(docName))
					System.out.println("[CLIENT] La stampa del documento "+ docName+" è avvenuta con successo");
				else 
					System.out.println("[CLIENT] La stampa del documento "+ docName+" è fallita");
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
