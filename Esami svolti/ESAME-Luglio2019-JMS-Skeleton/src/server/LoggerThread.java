package server;

import java.net.DatagramPacket;

import service.ILogger;

public class LoggerThread extends Thread{
	private DatagramPacket p;
	private ILogger skel;
	public LoggerThread(DatagramPacket request, ILogger loggerSkel) {
		this.p=request;
		this.skel=loggerSkel;
		// TODO Auto-generated constructor stub
	}
	
	public void run() {
		String message=new String(p.getData(), 0, p.getLength());
		int x=Integer.valueOf(message).intValue();
		skel.registraDato(x);
	}

}
