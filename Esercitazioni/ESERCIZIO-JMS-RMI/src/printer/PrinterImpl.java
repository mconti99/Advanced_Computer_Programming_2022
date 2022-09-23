package printer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import service.IPrinter;

public class PrinterImpl extends UnicastRemoteObject implements IPrinter{

	private static final long serialVersionUID = 8857220398015618696L;

	protected PrinterImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public synchronized void printDoc(String s) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("[PRINTER] Il nome del documento è "+ s);
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("./fileDocumenti.txt", true);
			PrintStream outStream = new PrintStream(fileOut);
			outStream.println(s);
			outStream.close();
			Thread.sleep(5000);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
