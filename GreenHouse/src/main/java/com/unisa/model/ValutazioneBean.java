package com.unisa.model;

import java.io.Serializable;
import java.time.LocalDate;

public class ValutazioneBean implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private int idUtente;
	private int idArticolo;
	private String commento;
	private LocalDate data;
	
	public ValutazioneBean () {
		this.setIdUtente(-1);
		this.setIdArticolo(-1);
		this.setCommento("");
		this.setData(LocalDate.of(2000, 01, 01));
	}

	public int getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}

	public int getIdArticolo() {
		return idArticolo;
	}

	public void setIdArticolo(int idArticolo) {
		this.idArticolo = idArticolo;
	}

	public String getCommento() {
		return commento;
	}

	public void setCommento(String commento) {
		this.commento = commento;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
	

}
