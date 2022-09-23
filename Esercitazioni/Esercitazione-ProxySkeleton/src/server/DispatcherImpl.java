package server;

public class DispatcherImpl extends DispatcherSkel{
	private int cmd;
	private Coda wrapper;	
	public DispatcherImpl(int p, Coda c) {
		super(p);
		wrapper=c;
	}
	
	public void sendCmd(int cmd) {
		System.out.println(" 	+ [DispImpl] sendCmd: "+ cmd);
		wrapper.inserisci(cmd);
	}
	
	public int getCmd() {
		System.out.println("  	+ [DispImpl] getCmd: "+ cmd);
		return wrapper.preleva();
	}
}
