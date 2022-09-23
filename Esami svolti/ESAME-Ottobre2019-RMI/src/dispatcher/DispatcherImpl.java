package dispatcher;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import generator.Reading;
import service.IDispatcher;
import service.IObserver;

public class DispatcherImpl extends UnicastRemoteObject implements IDispatcher{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3630814127894238628L;
	private Reading r;
	Vector<IObserver> listaObserverTemp;
	Vector<IObserver> listaObserverPress;
	
	public DispatcherImpl() throws RemoteException {
		r=new Reading();
		listaObserverTemp=new Vector<IObserver>();
		listaObserverPress=new Vector<IObserver>();
	}
	@Override
	public synchronized void setReading(Reading reading) throws RemoteException {
		// TODO Auto-generated method stub
		r=reading;
		String tipo= reading.tipo;
		if(tipo.compareTo("temperatura")==0) {
			for(int i=0;i<listaObserverTemp.size();i++) {
				listaObserverTemp.get(i).notifyReading();
			}
		}else {
			for(int i=0;i<listaObserverPress.size();i++) {
				listaObserverPress.get(i).notifyReading();
			}
		}
		
		try {
			Thread.sleep((long) ((1+Math.random()*4)*1000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Reading getReading() throws RemoteException{
		return r;
	}
	
	public void attach(IObserver observer, String tipo) throws RemoteException {
		if(tipo.compareTo("temperatura")==0) {
			listaObserverTemp.add(observer);
		}else {
			listaObserverPress.add(observer);
		}
	}

}
