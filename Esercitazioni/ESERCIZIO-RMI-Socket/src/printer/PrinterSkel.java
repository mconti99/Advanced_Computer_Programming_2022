package printer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.IDispatcher;
import service.IPrinter;

public class PrinterSkel implements IPrinter{
	private IPrinter printer;
	private int port;
	
	public PrinterSkel(int port, String filename){
		this.port=port;
		this.printer=new PrinterImpl(filename);
	}
	
	public void start() {
		Registry rmi;
		try {
			rmi = LocateRegistry.getRegistry();
			IDispatcher disp= (IDispatcher)rmi.lookup("dispatcher");
			
			@SuppressWarnings("resource")
			ServerSocket server=new ServerSocket(port);
			disp.addPrinter("127.0.0.1", port);
			
			while(true) {
				Socket s=server.accept();
				Thread worker=new PrinterThread(s, this);
				worker.start();
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean print(String docName) {
		return printer.print(docName);
	}
}
