package client;

import service.IDispatcher;

public class ClientThread extends Thread {
	private IDispatcher dispatcher;			 

	public ClientThread ( IDispatcher p){
		dispatcher=p;
	}

	public void run (){
		for(int i=0;i<3;i++) {
			try {
				Thread.sleep((long) (2+Math.random()*2));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int x= (int)(Math.random()*4);
			System.out.println("[CLN] invio comando # "+ x);
			dispatcher.sendCmd(x);
		}
	}
}
