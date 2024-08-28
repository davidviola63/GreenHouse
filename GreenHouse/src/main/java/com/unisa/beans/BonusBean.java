package com.unisa.beans;

import java.io.Serializable;

public class BonusBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private int perc_sconto;
	private String descrizione;
	
	public BonusBean() {
		
		this.id=0;
		this.perc_sconto=0;
		this.descrizione="Bonus non attivo";
		
	}
	
	
	
	//GET
	public int getId() {
		return id;
	}
	
	public int getPerc_sconto() {
		return perc_sconto;
	}
	

	public String getDescrizione() {
		return descrizione;
	}


	
	//SET
	public void setId(int id) {
		this.id = id;
	}

	public void setPerc_sconto(int perc_sconto) {
		this.perc_sconto = perc_sconto;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
}
