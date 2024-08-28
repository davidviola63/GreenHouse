package com.unisa.beans;

import java.io.Serializable;
import java.time.LocalDate;

public class MetodoPagamentoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int idUtente;
	private String numeroCarta;
	private LocalDate data; 
	private String circuito;
	private int cvc;
	

	public MetodoPagamentoBean() {
		this.idUtente=-1;
		this.numeroCarta="0000000000000000";
		this.data=LocalDate.of(2000,01,01);
		this.circuito="assente";
		this.cvc=000;
	}

	//GET
	public int getIdUtente() {
		return idUtente;
	}
	
	public String getNumeroCarta() {
		return numeroCarta;
	}
	
	public LocalDate getData() {
		return data;
	}
	
	public String getCircuito() {
		return circuito;
	}
	
	public int getCvc() {
		return cvc;
	}
	
	
	
	//SET
	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}

	public void setNumeroCarta(String numeroCarta) {
		this.numeroCarta = numeroCarta;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public void setCircuito(String circuito) {
		this.circuito = circuito;
	}

	public void setCvc(int cvc) {
		this.cvc = cvc;
	}

}
