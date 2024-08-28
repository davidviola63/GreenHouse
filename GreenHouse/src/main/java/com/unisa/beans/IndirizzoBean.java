package com.unisa.beans;

import java.io.Serializable;

public class IndirizzoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String citta;
	private String via;
	private int civico;
	
	public IndirizzoBean() {
		this.setId(-1);
		this.setCitta("");
		this.setVia("");
		this.setCivico(0);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public int getCivico() {
		return civico;
	}

	public void setCivico(int civico) {
		this.civico = civico;
	}

}
