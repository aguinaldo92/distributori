package it.unisalento.distributori.util;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.JsonObject;

/*
 * La seguente classe ha bisogno della libreria httpcore-4.4.6.jar
 */

public class FCMSender {

	private Logger logger = LogManager.getLogger(this.getClass().getName());
	private String url;
	private String FCMtopic;
	private String content_type;
	private String APIkey;
	private String testo;
	private String titolo;
	private JsonObject messaggioJSON;

	public FCMSender(){
		this.url="https://fcm.googleapis.com/fcm/send";
		this.content_type="application/json";
	}
	
	public FCMSender(String FCMtopic, String APIkey, String testo, String titolo) {
		this.url="https://fcm.googleapis.com/fcm/send";
		this.content_type="application/json";
		this.FCMtopic=FCMtopic;
		this.APIkey=APIkey;
		this.testo=testo;
		this.titolo=titolo;
	}
	
	private void componi_messaggioJSON(){
		//compongo il messaggio per la notifica da inviare a FIREBASE
		messaggioJSON = new JsonObject();
		messaggioJSON.addProperty("to", "/topics/"+FCMtopic);
		messaggioJSON.addProperty("priority", "high");
		JsonObject notificaJSON = new JsonObject();
		notificaJSON.addProperty("body", testo);
		notificaJSON.addProperty("title", titolo);
		messaggioJSON.add("notification",notificaJSON);
	}
	
	public String sendPOST(){

		componi_messaggioJSON();
		
	    try {
	    	URL http= new URL(url);
	    	HttpURLConnection conn = (HttpURLConnection) http.openConnection();
	    	conn.setDoOutput(true);
	    	conn.setDoInput(true);
	    	conn.setUseCaches(false);
	    	conn.setRequestMethod("POST");
	    	conn.setRequestProperty("Content-Type", content_type);
	    	conn.setRequestProperty("Authorization", "key="+APIkey);
	    	
	    	OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
	    	wr.write(messaggioJSON.toString());
	    	wr.flush();
	    	conn.getInputStream();
	    } catch (Exception e) {
	    	logger.error("Impossibile inviare la notifica HTTP a Firebase ",e);
	    	return "error";
	    }
		
		return "success";
	}

	public String getFCMtopic() {
		return FCMtopic;
	}

	public void setFCMtopic(String fCMtopic) {
		FCMtopic = fCMtopic;
	}

	public String getAPIkey() {
		return APIkey;
	}

	public void setAPIkey(String aPIkey) {
		APIkey = aPIkey;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public String getUrl() {
		return url;
	}

	public String getContent_type() {
		return content_type;
	}
	
	
}
