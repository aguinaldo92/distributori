package it.unisalento.distributori.action;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import it.unisalento.distributori.domain.Dipendente;
import it.unisalento.distributori.domain.Distributore;
import it.unisalento.distributori.domain.Persona;
import it.unisalento.distributori.domain.ProdottiErogati;
import it.unisalento.distributori.domain.Rifornisce;
import it.unisalento.distributori.factory.FactoryDao;
import it.unisalento.distributori.util.FCMSender;

public class UpdateQuantitaProdottiByDistributore extends ActionSupport implements SessionAware{
	private static final long serialVersionUID = -1064172065932355077L;
	private Integer idDistributore;
	private List<Integer> ids;
	private SessionMap<String, Object> session;
	private List<Integer> quantita;
	private Logger logger = LogManager.getLogger(this.getClass().getName());

	public String execute() {
		try {
			logger.debug("execute()");
			System.out.println("idDistributore: " + idDistributore);
			Iterator<Integer> idsIterator = ids.iterator();
			Iterator<Integer> quantitaIterator = quantita.iterator();
			Distributore distributore = FactoryDao.getIstance().getDistributoreDao().get(idDistributore, Distributore.class);
			Persona personaBySession = (Persona) session.get("persona");
			Dipendente dipendente = FactoryDao.getIstance().getDipendenteDao().get(personaBySession.getId(), Dipendente.class);
			
			/*** ids.size must be equal to quantita.size() */
			while (idsIterator.hasNext()) {
				Integer newQuantita = quantitaIterator.next();
				ProdottiErogati prodottiErogatiUpdated = FactoryDao.getIstance().getProdottiErogatiDao().get(idsIterator.next(),ProdottiErogati.class);
				
				 Integer old_Quantita = prodottiErogatiUpdated.getQuantita();
				if (!old_Quantita.equals(newQuantita) && !prodottiErogatiUpdated.getProdotto().getNome().equals("vuoto")) {
					prodottiErogatiUpdated.setQuantita(newQuantita);
					FactoryDao.getIstance().getProdottiErogatiDao().update(prodottiErogatiUpdated);
					Integer statoOk = 2;
					Integer statoRichiestoRifornimento = 1;
					if (FactoryDao.getIstance().getProdottiErogatiDao().getProdottiScarseggiantiByDistributore(idDistributore).isEmpty())
						distributore.setStato(statoOk);
					else 
						distributore.setStato(statoRichiestoRifornimento);
					FactoryDao.getIstance().getDistributoreDao().update(distributore);
					
					if(old_Quantita==0){
						//invio il messaggio a Firebase per notificare 
						//all'utente la associazione del nuovo prodotto
						FCMSender sender = new FCMSender("distributore_"+idDistributore, 
								"Distributore di "+prodottiErogatiUpdated.getDistributore().getIndirizzo()+": "+
								"disponibile "+prodottiErogatiUpdated.getProdotto().getNome(), 
								"DrinkSnacks");
						String FCMresult = sender.sendPOST();
					}
					
				}
			}
			
			Rifornisce rifornisce = new Rifornisce(dipendente,distributore,new Date());
			FactoryDao.getIstance().getRifornisceDao().set(rifornisce);

			return SUCCESS;

		} catch (Exception e){
			logger.error("Impossibile aggiornare la quantità dei prodotti nel distributore con id:" +idDistributore + " da parte del dipendente",e);
			addActionError("Impossibile aggiornare la quantità dei prodotti nel distributore");
			return ERROR;
		}
	}

	public Integer getIdDistributore() {
		return idDistributore;
	}
	public void setIdDistributore(Integer idDistributore) {
		this.idDistributore = idDistributore;
	}
	public List<Integer> getIds() {
		return ids;
	}
	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	public List<Integer> getQuantita() {
		return quantita;
	}
	public void setQuantita(List<Integer> quantita) {
		this.quantita = quantita;
	}

	public void setSession(Map<String, Object> map) {
		this.session =  (SessionMap<String, Object>) map;
	}

}