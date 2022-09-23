package printer;

public class Printer {
	public static void main(String[] args) {
		PrinterSkel ske = new PrinterSkel(Integer.parseInt(args[0]), args[1]);
		System.out.println("[PRINTER] Avviato");
		ske.start();
	}
}
