package com.unisa.model;

public class MobileRiciclatoBean {
	
	    private int id;
	    private String emailUtente;
	    private byte[] immagine;
	    private String tipoMobile;
	    private String commento;

	    // Costruttore vuoto
	    public MobileRiciclatoBean() {
	    }

	    // Costruttore con parametri
	    public MobileRiciclatoBean(int id, String emailUtente, byte[] immagine, String tipoMobile, String commento) {
	        this.id = id;
	        this.emailUtente = emailUtente;
	        this.immagine = immagine;
	        this.tipoMobile = tipoMobile;
	        this.commento = commento;
	    }

	    // Getter e Setter per l'ID
	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    // Getter e Setter per ID_Utente
	    public String getEmailUtente() {
	        return emailUtente;
	    }

	    public void setEmailUtente(String emailUtente) {
	        this.emailUtente = emailUtente;
	    }

	    // Getter e Setter per Immagine
	    public byte[] getImmagine() {
	        return immagine;
	    }

	    public void setImmagine(byte[] immagine) {
	        this.immagine = immagine;
	    }

	    // Getter e Setter per Tipo_Mobile
	    public String getTipoMobile() {
	        return tipoMobile;
	    }

	    public void setTipoMobile(String tipoMobile) {
	        this.tipoMobile = tipoMobile;
	    }

	    // Getter e Setter per Commento
	    public String getCommento() {
	        return commento;
	    }

	    public void setCommento(String commento) {
	        this.commento = commento;
	    }
	
}
