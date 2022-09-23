package disk;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import service.ILogger;

public class DiskProxy implements ILogger{

	private DatagramSocket socket;
	private int port;
	public DiskProxy(int port){
		this.port=port;
		try {
			socket=new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void registraDato(int dato) {
		// TODO Auto-generated method stub
		String message=String.valueOf(dato);
		try {
			DatagramPacket request=new DatagramPacket(message.getBytes(), message.getBytes().length, InetAddress.getLocalHost(), port);
			socket.send(request);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
