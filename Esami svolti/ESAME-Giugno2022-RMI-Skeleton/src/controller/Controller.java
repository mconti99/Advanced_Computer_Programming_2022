package controller;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.IController;

public class Controller {
	public static void main(String[] args) {
		try {
			Registry rmi=LocateRegistry.getRegistry();
			IController controller= new ControllerImpl();
			rmi.rebind("mycontroller", controller);
			System.out.println("[CONTROLLER] Controller registrato sul RMI registry");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
