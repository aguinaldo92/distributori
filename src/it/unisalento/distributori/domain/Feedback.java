package it.unisalento.distributori.domain;
// Generated 17-feb-2017 10.07.25 by Hibernate Tools 4.3.1.Final

import java.util.Date;

/**
 * Feedback generated by hbm2java
 */
public class Feedback implements java.io.Serializable {

	private FeedbackId id;
	private Persona persona;
	private String testo;
	private Date data;
	private Byte letto;

	public Feedback() {
	}

	public Feedback(FeedbackId id, Persona persona, String testo) {
		this.id = id;
		this.persona = persona;
		this.testo = testo;
	}

	public Feedback(FeedbackId id, Persona persona, String testo, Date data, Byte letto) {
		this.id = id;
		this.persona = persona;
		this.testo = testo;
		this.data = data;
		this.letto = letto;
	}

	public FeedbackId getId() {
		return this.id;
	}

	public void setId(FeedbackId id) {
		this.id = id;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public String getTesto() {
		return this.testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Byte getLetto() {
		return this.letto;
	}

	public void setLetto(Byte letto) {
		this.letto = letto;
	}

}
