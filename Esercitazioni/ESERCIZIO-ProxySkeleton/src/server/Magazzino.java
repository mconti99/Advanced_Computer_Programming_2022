package server;

public class Magazzino {
	public static void main(String[] args) {
		CodaCircolare laptop= new CodaCircolare();
		CodaCircolare smartphone=new CodaCircolare();
		
		MagazzinoImpl magazzino = new MagazzinoImpl(Integer.valueOf(args[0]), laptop, smartphone);
		magazzino.runSkeleton();
	}
}
