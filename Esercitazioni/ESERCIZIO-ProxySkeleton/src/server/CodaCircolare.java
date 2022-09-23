package server;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CodaCircolare {
	private int coda[];
	private int putptr, takeptr, count;
	
	private Lock lock;
	private final Condition nonPieno;
	private final Condition nonVuoto;
	
	protected CodaCircolare() {
		coda=new int[5];
		putptr=0;
		takeptr=0;
		count =0;
		lock=new ReentrantLock();
		nonPieno=lock.newCondition();
		nonVuoto=lock.newCondition();
	}
	
	public void deposita(int i) {
		lock.lock();
		try {
			while(isPieno()) nonPieno.await();
			
			coda[putptr]=i;
			putptr=(putptr+1)%coda.length;
			count++;
			
			nonVuoto.signal();
			System.out.println("[CODA] Depositato "+ i);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	public int preleva() {
		lock.lock();
		try {
			while(isVuoto()) nonVuoto.await();
			int valorePrelevato=coda[takeptr];
			takeptr=(takeptr+1)%coda.length;
			count--;
			
			nonPieno.signal();
			System.out.println("[CODA] Prelevato "+ valorePrelevato);
			return valorePrelevato;
		}catch(InterruptedException e){
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
		return 0;
	}
	
	private boolean isPieno() {
		return count == coda.length;
	}
	
	private boolean isVuoto() {
		return count == 0;
	}
}
