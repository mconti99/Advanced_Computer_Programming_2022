package subscriber;

public class TManager extends Thread{
	String comando;
	CodaCircolare coda;
	
	public TManager(String s, CodaCircolare c) {
		comando=s;
		coda=c;
	}
	
	public void run() {
		coda.deposita(comando);
		System.out.println("[TMANAGER] Ho depositato il comando "+ comando);
	}

}
