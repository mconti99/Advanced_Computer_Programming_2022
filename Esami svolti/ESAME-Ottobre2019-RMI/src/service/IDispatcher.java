package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import generator.Reading;

public interface IDispatcher extends Remote {
	public void setReading(Reading r) throws RemoteException;
	public void attach(IObserver observer, String tipo) throws RemoteException;
	public Reading getReading() throws RemoteException;
}
