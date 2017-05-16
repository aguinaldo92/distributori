package it.unisalento.distributori.util;

import java.security.MessageDigest;
import java.util.Random;


public abstract class PasswordUtils {
	
	public static String getPWD(int dim) {
    	Random rnd = new Random();

        String pass="";
    	for(int i=0; i<dim; i++){
    		if(i%2==0)
    			pass+=String.valueOf(rnd.nextInt(10));
    		else
    			pass+=String.valueOf((char) (rnd.nextInt(25)+97));
    	}
        
        return pass;
    }

	public static String getSha256(String value) {
		try{
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(value.getBytes());
			return bytesToHex(md.digest());
		} catch(Exception ex){
			throw new RuntimeException(ex);
		}
	}
	private static String bytesToHex(byte[] bytes) {
		StringBuffer result = new StringBuffer();
		for (byte b : bytes) result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
		return result.toString();
	}
}


