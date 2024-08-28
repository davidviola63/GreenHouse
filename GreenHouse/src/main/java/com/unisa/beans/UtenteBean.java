package com.unisa.beans;

import java.io.Serializable;

public class UtenteBean implements Serializable {
	
    private static final long serialVersionUID = 1L;
    private int id;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private String numeroCarta;
    private String ruolo;
    private int idBonus;

    
    public UtenteBean() {
    	
    	 this.id =-1;
         this.nome ="nome";
         this.cognome = "cognome";
         this.email = "email";
         this.password = "password";
         this.numeroCarta = "numeroCarta";
         this.ruolo = "ruolo";
         this.idBonus = -1;
         
    }

    

    //GET
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNumeroCarta() {
        return numeroCarta;
    }

    public String getRuolo() {
        return ruolo;
    }

    public int getIdBonus() {
        return idBonus;
    }

    // SET
    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNumeroCarta(String numeroCarta) {
        this.numeroCarta = numeroCarta;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public void setIdBonus(int idBonus) {
        this.idBonus = idBonus;
    }
}
