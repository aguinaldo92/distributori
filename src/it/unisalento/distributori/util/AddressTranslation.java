package it.unisalento.distributori.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public abstract class AddressTranslation {
	private static final String APIKEYGOOGLEMAPS = "AIzaSyAQViLWgpBLoviw3dOCPBrv7_XqHglmeQw";



	public static List<BigDecimal> getLatLonFromAddress(String indirizzo){
		final String BASEURLGOOGLEMAPS = "https://maps.googleapis.com/maps/api/geocode/json?";
		BigDecimal lat;
		BigDecimal lon;
		List<BigDecimal> listLatLon = new ArrayList<>();
		String urlMapsGeocodig;
		try{
			String indirizzo_url = URLEncoder.encode(indirizzo, "UTF-8");
			System.out.println(indirizzo_url);
			urlMapsGeocodig = BASEURLGOOGLEMAPS + "address=" + indirizzo_url + "&key=" + APIKEYGOOGLEMAPS;
			System.out.println(urlMapsGeocodig);
			URL url = new URL(urlMapsGeocodig);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			StringBuffer response = new StringBuffer();
			while ((output = br.readLine()) != null) {
				response.append(output);
			}
			JsonObject json = (JsonObject) new JsonParser().parse(response.toString());
			System.out.println(json.toString());

			JsonArray json_array_results = json.getAsJsonArray("results");
			JsonObject json_object_0 = (JsonObject) json_array_results.get(0);
			JsonObject json_object_geometry = json_object_0.getAsJsonObject("geometry");
			JsonObject json_object_location = json_object_geometry.getAsJsonObject("location");
			String lat_string = json_object_location.get("lat").toString();
			String lon_string = json_object_location.get("lng").toString();
			conn.disconnect();

			lat = new BigDecimal(lat_string);
			lon = new BigDecimal(lon_string);
			listLatLon.add(0,lat);
			listLatLon.add(1,lon);

		} catch (MalformedURLException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		} catch (ClassCastException ce) {
			ce.printStackTrace();
			System.err.println(ce.getMessage());
		} catch (IndexOutOfBoundsException iobe){
			listLatLon.add(0,null);
			listLatLon.add(1,null);
			System.out.println(iobe.getMessage());
			System.out.println("settati lat lon a null");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// fine maps api
		return listLatLon;

	}
}
