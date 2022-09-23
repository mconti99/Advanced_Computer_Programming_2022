package controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import manager.Reading;
import service.IActuator;
import service.IController;

public class ControllerImpl extends UnicastRemoteObject implements IController {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5194097231410876239L;
	Vector<Integer> vector; 
	/**
	 * 
	 */
	protected ControllerImpl() throws RemoteException {
		super();
		vector=new Vector<Integer>();
		// TODO Auto-generated constructor stub
	}
	public void addActuator(int port) throws RemoteException{
		vector.add(port);
	}
	public boolean sensorRead(Reading l) throws RemoteException{
		String type=l.type;
		int data=l.data;
		String stringa=type+"#"+data;
		boolean trovato=false;
		int i=0;
		while(!trovato && i<vector.size()) {
			IActuator proxy=new ControllerProxy(vector.get(i));
			trovato = proxy.execute(stringa);
			System.out.println("[CONTROLLER] L'esito per la stringa "+ type + " e il dato "+data+" è "+ trovato +" sulla posizione "+ i);
			i++;
		}
		
		return trovato;
	}
}
