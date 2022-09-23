package server;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import service.ILogger;

public class LoggerImpl implements ILogger{

	@Override
	public synchronized void registraDato(int dato) {
		// TODO Auto-generated method stub
		System.out.println("[LOGGER] Il dato è "+dato);
		try {
			FileOutputStream fileOut=new FileOutputStream("./fileDati.txt", true);
			PrintStream outStream=new PrintStream(fileOut);
			outStream.println("Saved: "+dato);
			outStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
