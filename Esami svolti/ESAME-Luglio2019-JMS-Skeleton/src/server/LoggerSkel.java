package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import service.ILogger;

public class LoggerSkel implements ILogger{

	private ILogger logger;
	private int port;
	public LoggerSkel(ILogger logger, int i) {
		// TODO Auto-generated constructor stub
		this.logger=logger;
		this.port=i;
	}

	public void runSkeleton() {
		// TODO Auto-generated method stub
		try {
			@SuppressWarnings("resource")
			DatagramSocket socket=new DatagramSocket(port);
			while(true) {
				byte[] buffer=new byte[100];
				DatagramPacket request=new DatagramPacket(buffer, buffer.length);
				socket.receive(request);
				LoggerThread thread=new LoggerThread(request, this);
				thread.start();
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void registraDato(int dato) {
		// TODO Auto-generated method stub
		logger.registraDato(dato);
	}

}
