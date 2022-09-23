package generator;

import java.io.Serializable;

public class Reading implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6221090099921700902L;
	public String tipo;
	public int valore;
	
	public Reading(String t, int v) {
		tipo=t;
		valore=v;
	}

	public Reading() {
		// TODO Auto-generated constructor stub
		tipo="";
		valore=0;
	}
}
