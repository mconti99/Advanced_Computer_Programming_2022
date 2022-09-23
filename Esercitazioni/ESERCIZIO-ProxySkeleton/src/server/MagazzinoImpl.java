package server;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class MagazzinoImpl extends MagazzinoSkel {
	private CodaCircolare laptop;
	private CodaCircolare smartphone;

	public MagazzinoImpl(int p, CodaCircolare laptop, CodaCircolare smartphone) {
		super(p);
		this.laptop=laptop;
		this.smartphone=smartphone;
	}

	public void deposita(String articolo, int id) {
		System.out.println("[MAGAZZINO] deposita su coda: "+ articolo+ " id: "+ id);
		if(articolo.compareToIgnoreCase("laptop")==0) {
			laptop.deposita(id);
		}else {
			smartphone.deposita(id);
		}
	}
	public int preleva(String articolo) {
		int id=0;
		System.out.println("[MAGAZZINO] prelievo su coda: "+ articolo);
		try {
			if(articolo.compareToIgnoreCase("laptop")==0) {
				id=laptop.preleva();
				System.out.println("[MAGAZZINO] ho prelevato l'id "+id);
				FileOutputStream fileOut1;
				fileOut1 = new FileOutputStream ("./file1.txt", true);
				PrintStream outStream1 = new PrintStream(fileOut1);
				outStream1.println(id);
				outStream1.close();
				return id;

			}else {
				id=smartphone.preleva();
				System.out.println("[MAGAZZINO] ho prelevato l'id "+id);
				FileOutputStream fileOut2;
				fileOut2 = new FileOutputStream ("./file2.txt", true);
				PrintStream outStream2 = new PrintStream(fileOut2);
				outStream2.println(id);
				outStream2.close();
				return id;
			}
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

}
