package observer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import service.IDispatcher;
import service.IObserver;

public class ObserverImpl extends UnicastRemoteObject implements IObserver{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1904493906146814870L;
	private String filename;
	IDispatcher dispatcher;
	public ObserverImpl(String f, IDispatcher dispatcher) throws RemoteException{
		filename=f;
		this.dispatcher=dispatcher;
	}
	@Override
	public void notifyReading() throws RemoteException{
		// TODO Auto-generated method stub
		int x=dispatcher.getReading().valore;
		try {
			FileOutputStream fileOut=new FileOutputStream(filename, true);
			PrintStream outStream= new PrintStream(fileOut);
			outStream.println("Il valore della reading è "+ x);
			outStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
