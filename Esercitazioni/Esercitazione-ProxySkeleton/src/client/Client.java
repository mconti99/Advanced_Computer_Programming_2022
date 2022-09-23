package client;

import service.IDispatcher;

public class Client {
	public static void main(String[] args) {
		IDispatcher dispatcher = new DispatcherProxy(args[0], Integer.valueOf(args[1]));

		ClientThread[] workers = new ClientThread[5];

		for(int i=0;i<5;i++) {
			workers[i] = new ClientThread(dispatcher);
			workers[i].start();
		}
		// Attendere la terminazione dei thread
		for(int i=0;i<5;i++) {
			try {
				workers[i].join();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		
	}
}
