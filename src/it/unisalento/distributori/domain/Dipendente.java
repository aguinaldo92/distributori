package it.unisalento.distributori.domain;
// Generated 20-feb-2017 14.41.46 by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Dipendente generated by hbm2java
 */
public class Dipendente implements java.io.Serializable {

	private int personaId;
	private Persona persona;
	private String telefono;
	private Set rifornisces = new HashSet(0);
	private Set manutienes = new HashSet(0);
	private Set distributores = new HashSet(0);

	public Dipendente() {
	}

	public Dipendente(Persona persona) {
		this.persona = persona;
	}

	public Dipendente(Persona persona, String telefono, Set rifornisces, Set manutienes, Set distributores) {
		this.persona = persona;
		this.telefono = telefono;
		this.rifornisces = rifornisces;
		this.manutienes = manutienes;
		this.distributores = distributores;
	}

	public int getPersonaId() {
		return this.personaId;
	}

	public void setPersonaId(int personaId) {
		this.personaId = personaId;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Set getRifornisces() {
		return this.rifornisces;
	}

	public void setRifornisces(Set rifornisces) {
		this.rifornisces = rifornisces;
	}

	public Set getManutienes() {
		return this.manutienes;
	}

	public void setManutienes(Set manutienes) {
		this.manutienes = manutienes;
	}

	public Set getDistributores() {
		return this.distributores;
	}

	public void setDistributores(Set distributores) {
		this.distributores = distributores;
	}

}