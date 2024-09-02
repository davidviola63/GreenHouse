package com.unisa.model;

import java.io.Serializable;
import java.time.LocalDate;

public class OrdineBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static int counter = 0; //counter serve per generare il codice di fattura
	
	private int idOrdine;
	private String emailUtente;
	private String stato;
	private LocalDate dataAcquisto;
	private String codFattura;
	
	public OrdineBean() {
		
	}
	
	public OrdineBean(int idOrdine,String emailUtente, String stato, LocalDate dataAcquisto, String codFattura) {
		this.idOrdine=idOrdine;
		this.emailUtente=emailUtente;
		this.stato=stato;
		this.dataAcquisto=dataAcquisto;
		this.codFattura=generateInvoiceCode();
	}

	public int getIdOrdine() {
		return idOrdine;
	}
	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}
	public String getEmailUtente() {
		return emailUtente;
	}
	public void setEmailUtente(String emailUtente) {
		this.emailUtente = emailUtente;
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
	
	public void setCodFattura(String codFattura) {
		this.codFattura=codFattura;
	}
	public String getCodFattura() {
		return codFattura;
	}
	
	

    public static synchronized String generateInvoiceCode() {
        counter++;
        return "INV-" + String.format("%05d", counter); // Genera un codice come INV-00001, INV-00002, etc.
    }
	
	
	
}
