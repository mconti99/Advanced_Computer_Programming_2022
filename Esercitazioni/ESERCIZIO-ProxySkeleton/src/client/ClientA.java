package client;

import service.IMagazzino;

public class ClientA {
	public static void main(String[] args) {
		IMagazzino magazzino= new MagazzinoProxy(Integer.valueOf(args[0]));
		ClientThread[] workers = new ClientThread[5];
		
		for(int i=0;i<5;i++) {
			workers[i]=new ClientThread(magazzino, 0);
			workers[i].start();
		}
		
		for(int i=0;i<5;i++) {
			try {
				workers[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
