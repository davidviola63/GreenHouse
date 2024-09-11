package com.unisa.model;

import java.io.Serializable;

public class ComponeBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idArticolo;
	private int idOrdine;
	private String nome;
	private double prezzo;
	private int quantita;
	private int valutazione;
	
	public ComponeBean() {
		
		this.setIdArticolo(-1);
		this.setIdOrdine(-1);
		this.setNome("");
		this.setPrezzo(0);
		this.setQuantita(0);
		this.setValutazione(-1);
		
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

	public int getValutazione() {
		return valutazione;
	}

	public void setValutazione(int valutazione) {
		this.valutazione = valutazione;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	

}
