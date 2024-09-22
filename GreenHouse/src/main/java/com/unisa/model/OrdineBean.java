package com.unisa.model;

import java.io.Serializable;
import java.security.SecureRandom;
import java.time.LocalDate;

public class OrdineBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final int LENGTH = 15;

	
	private int idOrdine;
	private String emailUtente;
	private String stato;
	private LocalDate dataAcquisto;
	private String codFattura;
	private double prezzoTotale;
	
	public OrdineBean() {
		this.codFattura=generateRandomCode();
	}
	
	public OrdineBean(int idOrdine,String emailUtente, String stato, LocalDate dataAcquisto, String codFattura) {
		this.idOrdine=idOrdine;
		this.emailUtente=emailUtente;
		this.stato=stato;
		this.dataAcquisto=dataAcquisto;
		this.codFattura=generateRandomCode();
		this.prezzoTotale=0.0;
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
	
	public double getPrezzoTotale() {
		return prezzoTotale;
	}

	public void setPrezzoTotale(double prezzoTotale) {
		this.prezzoTotale = prezzoTotale;
	}

    public static synchronized String generateRandomCode() {
    	
    	SecureRandom random = new SecureRandom();
    	StringBuilder codice = new StringBuilder(LENGTH);

    	for (int i = 0; i < LENGTH; i++) {
    	     int index = random.nextInt(CHARACTERS.length());
    	     codice.append(CHARACTERS.charAt(index));
    	     }

     return codice.toString();
    	    
    }

}
