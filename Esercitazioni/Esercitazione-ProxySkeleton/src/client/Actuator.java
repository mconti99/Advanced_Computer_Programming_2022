package client;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import service.IDispatcher;

public class Actuator {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		IDispatcher dispatcher = new DispatcherProxy(args[0], Integer.valueOf(args[1]));
		int cmd=0;
		
		try {
			FileOutputStream fileOut= new FileOutputStream ("./cmdlog.txt");
			PrintStream outStream = new PrintStream(fileOut);
			
			while(true) {
				cmd = dispatcher.getCmd();
				System.out.println("[ACT] ricevuto comando # "+ cmd);
				outStream.println("comando = " + cmd);
				
				Thread.sleep(1000);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
