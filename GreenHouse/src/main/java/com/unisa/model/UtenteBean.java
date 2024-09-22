package com.unisa.model;

import java.io.Serializable;

public class UtenteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	    private String nome;
	    private String cognome;
	    private String email;
	    private String password;
	    private String citta;
	    private String via;
	    private String civico;
	    private String ruolo;
	    private int idBonus; 

	    // Costruttore senza argomenti
	    public UtenteBean() {
	    }

	    // Costruttore con tutti gli argomenti
	    public UtenteBean(String nome, String cognome, String email, String password,
	                  String citta, String via, String civico) {	       
	        this.nome = nome;
	        this.cognome = cognome;
	        this.email = email;
	        this.password = password;
	        this.citta = citta;
	        this.via = via;
	        this.civico = civico;
	        this.ruolo="Cliente";
	    }

	    // Getter e setter per ogni attributo


	    public String getNome() {
	        return nome;
	    }

	    public void setNome(String nome) {
	        this.nome = nome;
	    }

	    public String getCognome() {
	        return cognome;
	    }

	    public void setCognome(String cognome) {
	        this.cognome = cognome;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
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

	    public String getCivico() {
	        return civico;
	    }

	    public void setCivico(String civico) {
	        this.civico = civico;
	    }

	    public String getRuolo() {
	        return ruolo;
	    }

	    public void setRuolo(String ruolo) {
	        this.ruolo = ruolo;
	    }

	    public int getIdBonus() {
	        return idBonus;
	    }

	    public void setIdBonus(int idBonus) {
	        this.idBonus = idBonus;
	    }

	    // Override del metodo toString
	    @Override
	    public String toString() {
	        return "Utente{"+	                
	                ", nome='" + nome + '\'' +
	                ", cognome='" + cognome + '\'' +
	                ", email='" + email + '\'' +
	                ", password='" + password + '\'' +
	                ", citta='" + citta + '\'' +
	                ", via='" + via + '\'' +
	                ", civico='" + civico + '\'' +
	                ", ruolo='" + ruolo + '\'' +
	                ", idBonus=" + idBonus +
	                '}';
	    }
}