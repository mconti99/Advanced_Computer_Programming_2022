package server;


public class CodaWrapperSynchr extends CodaWrapper {

	
	public CodaWrapperSynchr( Coda c ){
		super (c);		
	}
	
	
	
	public void inserisci( int i){
		
		// Implementare sincronizzazione con blocchi synchronized
		synchronized(coda) { //this non va bene perchè non è thread safe
			while(coda.full()) { //monitor di tipo signal & continue
				try {
					coda.wait();//operazioni di wait e notify relative al mutex utilizzato nel blocco synchronized
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
			coda.inserisci(i);
			coda.notifyAll();
		}
	}
	
	
	public int preleva(){
		int x=0;
		
		// Implementare sincronizzazione con blocchi synchronized
		
			synchronized(coda) {
				while(coda.empty()) {
					try {
						coda.wait();
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
				x = coda.preleva();
				coda.notifyAll();
			}
		return x;
	}
	
}
