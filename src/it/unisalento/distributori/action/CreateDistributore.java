/**
 * 
 */
package it.unisalento.distributori.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.opensymphony.xwork2.ActionSupport;

import it.unisalento.distributori.domain.Categoria;
import it.unisalento.distributori.domain.Dipendente;
import it.unisalento.distributori.domain.Distributore;
import it.unisalento.distributori.domain.ProdottiErogati;
import it.unisalento.distributori.domain.Prodotto;
import it.unisalento.distributori.factory.FactoryDao;

/**
 * @author aguinaldo
 * spostare le api in un altra classe
 * spezzare i vari passaggi in vari metodi
 */
public class CreateDistributore extends ActionSupport {

	private static final long serialVersionUID = -2021736949462407465L;
	private static final String APIKEYGOOGLEMAPS = "AIzaSyAQViLWgpBLoviw3dOCPBrv7_XqHglmeQw";
	private static final String BASEURLGOOGLEMAPS = "https://maps.googleapis.com/maps/api/geocode/json?";
	private BigDecimal lat;
	private BigDecimal lon;
	private String indirizzo;
	/*  spezzare l'inserimento in parti differenti per validarle singolarmente?
	private String via;
	private String numero;
	private String citta;
	private String provincia;
	private String nazione;
	*/
	private String posizioneEdificio;
	private Integer numPosti;
	private Integer numScaffali;
	private Integer idDipendente;
	private HashSet<Categoria> categorieErogate;
	private String categorieErogabili;
	private String urlMapsGeocodig;
	private Distributore distributore;
	private Set<ProdottiErogati> prodottiErogatiVuoti;
	private Prodotto prodottoVuoto;
	private Integer idDistributore;
	private Integer quantitaIniziale = 0;

	public String execute() {
		/* spezzare l'inserimento in parti differenti per validarle singolarmente? */
//		indirizzo = via + "  " + numero + ", " +  citta + ", " + provincia + ", " + nazione;
		try {
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
			System.out.println("lat:" + lat_string);
			lat = new BigDecimal(lat_string);
			lon = new BigDecimal(lon_string);
			System.out.println("lon:" + lon_string);
			// fine maps api
			
			// inizio parsing checkboxlist (utile da mettere in un altro metodo)
			categorieErogate = new HashSet<Categoria>();
			StringTokenizer st = new StringTokenizer(categorieErogabili, ",");
			while (st.hasMoreTokens()) {
				Integer cat = Integer.parseInt(st.nextToken().trim());
				categorieErogate.add(FactoryDao.getIstance().getCategoriaDao().get(cat, Categoria.class));
			}
			// fine parsing checkboxlist
			
			distributore = new Distributore();
			distributore.setDipendente(FactoryDao.getIstance().getDipendenteDao().get(idDipendente, Dipendente.class));
			distributore.setCategorieFornites((Set<Categoria>) categorieErogate);
			distributore.setIndirizzo(indirizzo);
			distributore.setPosizioneEdificio(posizioneEdificio);
			distributore.setStato(1);
			distributore.setLat(lat);
			distributore.setLon(lon);
			distributore.setNumPosti(numPosti);
			distributore.setNumScaffali(numScaffali);
			
			idDistributore = (Integer) FactoryDao.getIstance().getDistributoreDao().set(distributore);
			distributore.setId(idDistributore);

			prodottoVuoto = FactoryDao.getIstance().getProdottoDao().getProdottoVuoto();
			prodottiErogatiVuoti = new HashSet<ProdottiErogati>();
			for (int i = 1; i <= numScaffali; i++) {
				for (int j = 1; j <= numPosti; j++) {
					prodottiErogatiVuoti.add(new ProdottiErogati(distributore, prodottoVuoto, i, j, quantitaIniziale));
				}

			}
			distributore.setProdottiErogatis(prodottiErogatiVuoti);


		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		} catch (ClassCastException ce) {
			ce.printStackTrace();
			System.err.println(ce.getMessage());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return SUCCESS;

	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getPosizioneEdificio() {
		return posizioneEdificio;
	}

	public void setPosizioneEdificio(String posizioneEdificio) {
		this.posizioneEdificio = posizioneEdificio;
	}

	public Integer getNumPosti() {
		return numPosti;
	}

	public void setNumPosti(Integer numPosti) {
		this.numPosti = numPosti;
	}

	public Integer getNumScaffali() {
		return numScaffali;
	}

	public void setNumScaffali(Integer numScaffali) {
		this.numScaffali = numScaffali;
	}

	public BigDecimal getLat() {
		return lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public BigDecimal getLon() {
		return lon;
	}

	public void setLon(BigDecimal lon) {
		this.lon = lon;
	}

	public String getCategorieErogabili() {
		return categorieErogabili;
	}

	public void setCategorieErogabili(String categorieErogabili) {
		this.categorieErogabili = categorieErogabili;
	}

	public Integer getIdDistributore() {
		return idDistributore;
	}

	public void setIdDistributore(Integer idDistributore) {
		this.idDistributore = idDistributore;
	}

	public Integer getIdDipendente() {
		return idDipendente;
	}

	public void setIdDipendente(Integer idDipendente) {
		this.idDipendente = idDipendente;
	}
/* spezzare l'inserimento in parti differenti per validarle singolarmente?
	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getNazione() {
		return nazione;
	}

	public void setNazione(String nazione) {
		this.nazione = nazione;
	}

	*/
	
	



	


}
