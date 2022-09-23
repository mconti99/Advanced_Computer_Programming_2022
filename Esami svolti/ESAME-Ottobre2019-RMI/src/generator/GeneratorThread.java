package generator;

import java.rmi.RemoteException;
import java.util.Random;

import service.IDispatcher;

public class GeneratorThread extends Thread{
	IDispatcher dispatcher;
	public GeneratorThread(IDispatcher dispatcher) {
		// TODO Auto-generated constructor stub
		this.dispatcher=dispatcher;
	}

	public void run() {
		Reading r;
		String tipo = null;
		int valore=0;
		try {
		for(int i=0;i<3;i++) {
			if(Math.random()<0.5) {
				tipo="temperatura";
			}else {
				tipo="pressione";
			}
		
		Random rand=new Random();
		valore=rand.nextInt(51);
		r=new Reading(tipo, valore);
			dispatcher.setReading(r);
			System.out.println("[GENERATOR] Ho generato una lettura di tipo "+ tipo+" e di valore "+ valore);
			Thread.sleep(5000);
		}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
