package server;

public class Dispatcher {
	public static void main(String[] args) {
		// Instanziare una coda circolare SENZA sincronizzazione
		CodaCircolare coda= new CodaCircolare(5);
		
		// Instanziare uno dei 'wrapper' (decorator) responsabile della sincronizzazione
		CodaWrapperSynchr wrapper = new CodaWrapperSynchr(coda);
		DispatcherImpl dispatcher = new DispatcherImpl(Integer.valueOf(args[0]), wrapper);
		dispatcher.runSkeleton();
	}
}
