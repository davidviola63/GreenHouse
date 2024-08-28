package com.unisa.beans;

import java.io.Serializable;

public class ComponeBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idArticolo;
	private int idOrdine;
	private double prezzo;
	private int quantita;
	
	public ComponeBean() {
		
		this.setIdArticolo(-1);
		this.setIdOrdine(-1);
		this.setPrezzo(0);
		this.setQuantita(0);
		
	}

	public int getIdArticolo() {
		return idArticolo;
	}

	public void setIdArticolo(int idArticolo) {
		this.idArticolo = idArticolo;
	}

	public int getIdOrdine() {
		return idOrdine;
	}

	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	

}
