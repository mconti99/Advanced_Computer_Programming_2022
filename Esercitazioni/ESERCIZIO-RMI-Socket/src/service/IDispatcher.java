package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IDispatcher extends Remote{
	public boolean printRequest(String docName) throws RemoteException;
	public void addPrinter(String addr, int port) throws RemoteException;
}
