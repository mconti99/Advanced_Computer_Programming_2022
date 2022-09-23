package subscriber;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CodaCircolare {
	private String coda[];
	private int count;
	private int takeptr,putptr;
	
	private Lock lock;
	private Condition nonPieno;
	private Condition nonVuoto;
	
	public CodaCircolare() {
		count=0;
		takeptr=0;
		coda=new String[5];
		putptr=0;
		
		lock=new ReentrantLock();
		nonPieno=lock.newCondition();
		nonVuoto=lock.newCondition();
	}
	
	private boolean isPieno() {
		return count==5;
	}
	
	public boolean isVuoto() {
		return count==0;
	}
	
	public void deposita(String i) {
		lock.lock();
		try {
			while(isPieno()) nonPieno.await();
			coda[putptr]=i;
			putptr=(putptr+1)%5;
			count++;
			nonVuoto.signal();
			
			System.out.println("[CODA] Depositato "+i);
		} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	

	public String preleva() {
		String x = null;
		lock.lock();
		try {
			while(isVuoto()) nonVuoto.await();
			x=coda[takeptr];
			takeptr=(takeptr+1)%5;
			count--;
			nonPieno.signal();
			
			System.out.println("[CODA] Prelevato "+x);
			return x;
		} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}finally {
			lock.unlock();
		}
		return x;
	}
	
}
