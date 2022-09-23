package subscriber;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class TExecutor extends Thread{
	private CodaCircolare coda;

	public TExecutor(CodaCircolare c){
		coda= c;
	}

	public void run() {
		try {
			while(true) {
				FileOutputStream fileOut=new FileOutputStream("./CmdLog.txt", true);
				PrintStream outStream=new PrintStream(fileOut);
				while(!coda.isVuoto()) {
					String s = coda.preleva();
					outStream.println(s);
				}
				outStream.close();
				Thread.sleep(10000);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
