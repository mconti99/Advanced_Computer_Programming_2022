package dispatcher;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import service.IDispatcher;

public class Dispatcher extends UnicastRemoteObject implements IDispatcher{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8251608179302163490L;
	private Vector<DispatcherProxy> printers;
	protected Dispatcher() throws RemoteException{
		super();
		printers=new Vector<DispatcherProxy>();
	}
	
	public void addPrinter(String host, int port) throws RemoteException {
		DispatcherProxy p=new DispatcherProxy(host, port);
		printers.add(p);
		System.out.println("[DISPATCHER] Printer aggiunta");
	}
	
	public boolean printRequest(String docName) throws RemoteException{
		for(int i=0;i<printers.size();i++) {
			if(printers.get(i).print(docName)==true) {
				return true;
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		try {
			IDispatcher disp=new Dispatcher();
			Registry rmi;
			rmi = LocateRegistry.getRegistry();
			rmi.rebind("dispatcher", disp);
			System.out.println("[DISPATCHER] Avviato e registrato su RMI");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
