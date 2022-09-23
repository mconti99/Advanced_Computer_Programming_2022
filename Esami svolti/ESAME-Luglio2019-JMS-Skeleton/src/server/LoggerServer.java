package server;

public class LoggerServer {
	public static void main(String[] args) {
		LoggerImpl logger=new LoggerImpl();
		LoggerSkel skeleton=new LoggerSkel(logger, Integer.parseInt(args[0]));
		skeleton.runSkeleton();
	}
}
