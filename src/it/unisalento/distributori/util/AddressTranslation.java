package it.unisalento.distributori.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public abstract class AddressTranslation {
	private static final String APIKEYGOOGLEMAPS = "AIzaSyAQViLWgpBLoviw3dOCPBrv7_XqHglmeQw";
	 


	public static List<BigDecimal> getLatLonFromAddress(String indirizzo){
		final String BASEURLGOOGLEMAPS = "https://maps.googleapis.com/maps/api/geocode/json?";
		Logger logger = LogManager.getLogger(AddressTranslation.class.getName());
		List<BigDecimal> listLatLon = new ArrayList<>(2);
		try{
			String indirizzo_url = URLEncoder.encode(indirizzo, "UTF-8");
			logger.trace(indirizzo_url);
			String urlMapsGeocodig = BASEURLGOOGLEMAPS + "address=" + indirizzo_url + "&key=" + APIKEYGOOGLEMAPS;
			logger.trace(urlMapsGeocodig);
			URL url = new URL(urlMapsGeocodig);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				logger.warn("Failed : HTTP error code : " + conn.getResponseCode());
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			StringBuffer response = new StringBuffer();
			while ((output = br.readLine()) != null) {
				response.append(output);
			}
			JsonObject json = (JsonObject) new JsonParser().parse(response.toString());
			logger.trace("json restituito da Maps API: "+json.toString());

			JsonArray json_array_results = json.getAsJsonArray("results");
			JsonObject json_object_0 = (JsonObject) json_array_results.get(0);
			JsonObject json_object_geometry = json_object_0.getAsJsonObject("geometry");
			JsonObject json_object_location = json_object_geometry.getAsJsonObject("location");
			String lat_string = json_object_location.get("lat").toString();
			String lon_string = json_object_location.get("lng").toString();

			BigDecimal lat = new BigDecimal(lat_string);
			BigDecimal lon = new BigDecimal(lon_string);
			listLatLon.add(0,lat);
			listLatLon.add(1,lon);

		} catch (IndexOutOfBoundsException | MalformedURLException ce){
			listLatLon.add(0,null);
			listLatLon.add(1,null);
			logger.warn("Indirizzo non corretto o sconosciuto a Google Maps: "+indirizzo , ce);
			
		} catch (Exception e) {
			logger.error("Impossibile completare la richiesta",e);
		}
		// fine maps api
		return listLatLon;

	}
	
}
