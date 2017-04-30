package it.unisalento.distributori.util;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.Normalizer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.JsonObject;

/*
 * La seguente classe ha bisogno della libreria httpcore-4.4.6.jar
 */

public class FCMSender{

	//ServerKey della sezione cloud messaging della console di firebase (codice univoco di questo progetto per le notifiche)
	private String ServerKey="AIzaSyD4uOmklDmojlgUTq5DNliSNAaottNcEsY";
	
	private Logger logger = LogManager.getLogger(this.getClass().getName());
	private String url="https://fcm.googleapis.com/fcm/send";
	private String content_type="application/json";
	private String FCMtopic;
	private String testo;
	private String titolo;
	private JsonObject messaggioJSON;

	public FCMSender(){
	}
	
	public FCMSender(String FCMtopic, String testo, String titolo) {
		
		//gestione dell'invio dei caratteri accentati (da "à" a "a'")
		String testo_temp = Normalizer.normalize(testo, Normalizer.Form.NFD);
		this.testo = testo_temp.replaceAll("\\p{InCombiningDiacriticalMarks}+", "'");
		
		this.FCMtopic=FCMtopic;
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
		
		Runnable codice_backgr = new Runnable() {
			public void run() {
				try{
					URL http= new URL(url);
	    	    	HttpURLConnection conn = (HttpURLConnection) http.openConnection();
	    	    	conn.setDoOutput(true);
	    	    	conn.setDoInput(true);
	    	    	conn.setUseCaches(false);
	    	    	conn.setConnectTimeout(15000);
	    	    	conn.setRequestMethod("POST");
	    	    	conn.setRequestProperty("Content-Type", content_type);
	    	    	conn.setRequestProperty("Authorization", "key="+ServerKey);
	    	    	
	    	    	System.out.println("Parametri di connessione Firebase creati.");
	    	    	
	    	    	OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
	    	    	wr.write(messaggioJSON.toString());
	    	    	wr.flush();
	    	    	conn.getInputStream();
	    	    	System.out.println("Messaggio inviato a Firebase.");
				}catch (Exception e){
					logger.error("Impossibile inviare la notifica HTTP a Firebase ",e);
					System.out.println("Problemi all'invio della notifica a Firebase: "+e);
				}
			}
		};
 
		Thread thread = new Thread(codice_backgr);
//		thread.start(); // avvia il codice in background
		
		thread.run(); 
		/*
		*	decommentare in caso di JUnit test se si vuole testare la avvenuta ricezione
		*	e commentare il rigo thread.start()
		*/
		
		System.out.println("FCMSender: Fine metodo sendPOST().");
		
		return "success";
	}

	public String getFCMtopic() {
		return FCMtopic;
	}

	public void setFCMtopic(String fCMtopic) {
		FCMtopic = fCMtopic;
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
		
		//gestione dell'invio dei caratteri accentati (da "à" a "a'")
		String testo_temp = Normalizer.normalize(testo, Normalizer.Form.NFD);
		this.testo = testo_temp.replaceAll("\\p{InCombiningDiacriticalMarks}+", "'");
	}

	public String getUrl() {
		return url;
	}

	public String getContent_type() {
		return content_type;
	}

	public String getServerKey() {
		return ServerKey;
	}

	public void setServerKey(String serverKey) {
		ServerKey = serverKey;
	}
	
	
}
