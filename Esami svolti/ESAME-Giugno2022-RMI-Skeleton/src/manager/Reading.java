package manager;

import java.io.Serializable;

public class Reading implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 668724434159271457L;
	public int data;
	public String type;
	
	Reading(int d, String s){
		data=d;
		type=s;
	}
}
