package com.unisa.beans;

import java.io.Serializable;
import java.time.LocalDate;

public class OrdineBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idOrdine;
	private int idUtente;
	private String stato;
	private LocalDate dataAcquisto;
	private String codFattura;
	
	public OrdineBean() {
		this.idOrdine=-1;
		this.idUtente=-1;
		this.stato="";
		this.dataAcquisto=LocalDate.of(2000, 01, 01);
		this.codFattura="000000";
	}

	public int getIdOrdine() {
		return idOrdine;
	}
	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}
	public int getIdUtente() {
		return idUtente;
	}
	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public LocalDate getDataAcquisto() {
		return dataAcquisto;
	}
	public void setDataAcquisto(LocalDate dataAcquisto) {
		this.dataAcquisto = dataAcquisto;
	}
	public String getCodFattura() {
		return codFattura;
	}
	public void setCodFattura(String codFattura) {
		this.codFattura = codFattura;
	}
	
	
	
	
}
