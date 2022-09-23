package printer;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Printer  {
	
	public static void main(String[] args) {
	try {
		Registry rmi= LocateRegistry.getRegistry();
		PrinterImpl printer=new PrinterImpl();
		
		rmi.rebind(args[0], printer);
		System.out.println("[PRINTER] Avviato servizio remoto");
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	

}
