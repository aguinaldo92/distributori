package it.unisalento.distributori.util;

import java.util.*;

public class GeneraPwd {
	
	private int dim;//dimensione password
    
    /** Creates a new instance of GeneraPwd */
    public GeneraPwd(int dim) {
    	this.dim=dim;  
    }
    
	Random rnd = new Random(System.currentTimeMillis());
	
    public String getPWD() {

        String pass="";
        int num;
        char alpha;
    	for(int i=0; i<dim; i++){
    		if(i%2==0)
    			pass+=String.valueOf(rnd.nextInt(10));
    		else
    			pass+=String.valueOf((char) (rnd.nextInt(25)+97));
    	}
        
        return pass;
    }

	public int getDim() {
		return dim;
	}    
}