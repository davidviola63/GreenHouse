package com.unisa.model;

import java.io.Serializable;

public class ArticoloBean implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String tipologia;
	private String nome;
	private String descrizione;
	private double prezzo;
	private int quantita;
	private double iva;
	private String image_path;
	
	public ArticoloBean() {
		
		this.id= -1 ;
		this.tipologia="";
		this.nome="";
		this.descrizione="articolo assente";
		this.prezzo=0.0;
		this.quantita=-1;
		this.iva=0.0;
		this.image_path=null;
		
	}
	
	//GET
    
	public String getTipologia() {
		return tipologia;
	}

	public String getNome() {
		return nome;
	}

	public String getDescrizione() {
		return descrizione;
	}
	
	public double getIva() {
		return iva;
	}
	

	public double getPrezzo() {
		return prezzo;
	}
	
	public int getId() {
		return id;
	}

	public int getQuantita() {
		return quantita;
	}

	public String getImage_path() {
		return image_path;
	}
	//SET
	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

}
