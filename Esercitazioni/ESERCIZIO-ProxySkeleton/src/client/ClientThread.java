package client;

import java.util.Random;

import service.IMagazzino;

public class ClientThread extends Thread{
	private IMagazzino magazzino;
	private int tipo_client;
	public ClientThread(IMagazzino m, int tipo) {
		magazzino=m;
		tipo_client=tipo;
	}

	public void run() {
		try {
			if(this.tipo_client == 0) {
				for(int i=0;i<3;i++) {
					Random rand= new Random();
					int t=2+rand.nextInt(2);
					Thread.sleep(t);
					Random rand1=new Random();
					int id=1+rand1.nextInt(100);
					if(i%2==0) {
						magazzino.deposita("laptop", id);
						System.out.println("[CLIENT] Ho depositato presso la coda laptop l'id "+ id);
					}else {
						magazzino.deposita("smartphone", id);
						System.out.println("[CLIENT] Ho depositato presso la coda smartphone l'id "+ id);

					}
				}
			}else {
				for(int i=0;i<3;i++) {
					int id_prelevato=0;
					Random rand= new Random();
					int t=2+rand.nextInt(2);
					Thread.sleep(t);
					if(i%2==0) {
						id_prelevato=magazzino.preleva("laptop");
					}else {
						id_prelevato=magazzino.preleva("smartphone");
					}
					System.out.println("[CLIENT] Ho prelevato l'id "+ id_prelevato);
				}
			}
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
