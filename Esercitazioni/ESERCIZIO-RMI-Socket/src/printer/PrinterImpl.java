package printer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;

import service.IPrinter;

public class PrinterImpl implements IPrinter{
	private boolean state;
	private String file;
	
	PrinterImpl(String filename){
		file=filename;
		state=true;
	}
	
	synchronized boolean setState() {
		if(state==false) {
			return false;
		}
		state=false;
		return true;
	}
	
	synchronized void resetState() {
		state=true;
	}
	
	public boolean print(String docName) {
		try {
		if(setState()) {
			System.out.println("[PRINTER] Richiesta presa in carico..");
			Random rand= new Random();
			Thread.sleep(5+rand.nextInt(6));
			FileOutputStream fs=new FileOutputStream(file, true);
			PrintStream outStream=new PrintStream(fs);
			outStream.println(docName);
			outStream.close();
			fs.close();
			System.out.println("[PRINTER] Print effettuata per il documento "+ docName);
			resetState();
			return true;
			}
		System.out.println("[PRINTER] Print non effettuata per il documento "+ docName);
		return false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
