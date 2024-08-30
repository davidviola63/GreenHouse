package com.unisa.model;
import java.sql.Blob;
public class MobileRiciclatoBean {
	
	    private int id;
	    private String emailUtente;
	    private Blob immagine;
	    private String tipoMobile;
	    private String commento;

	    // Costruttore vuoto
	    public MobileRiciclatoBean() {
	    }

	    // Costruttore con parametri
	    public MobileRiciclatoBean(int id, String emailUtente, Blob immagine, String tipoMobile, String commento) {
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
	    public String getemailUtente() {
	        return emailUtente;
	    }

	    public void setIdUtente(String emailUtente) {
	        this.emailUtente = emailUtente;
	    }

	    // Getter e Setter per Immagine
	    public Blob getImmagine() {
	        return immagine;
	    }

	    public void setImmagine(Blob immagine) {
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
