package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import manager.Reading;

public interface IController extends Remote{
	public void addActuator(int port) throws RemoteException;
	public boolean sensorRead(Reading l) throws RemoteException;
	
}
