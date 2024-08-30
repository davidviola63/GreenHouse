package com.unisa.model;

import java.io.Serializable;
import java.time.LocalDate;

public class MetodoPagamentoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private String numeroCarta;
	private String email;
	private LocalDate data; 
	private String circuito;
	private String cvc;
	

	public MetodoPagamentoBean() {
		
		this.numeroCarta="0000000000000000";
		this.email="example@example.com";
		this.data=LocalDate.of(2000,01,01);
		this.circuito="assente";
		this.cvc="000";
	}

	//GET
	
	public String getNumeroCarta() {
		return numeroCarta;
	}
	
	public String getEmail() {
		return email;
	}
	
	public LocalDate getData() {
		return data;
	}
	
	public String getCircuito() {
		return circuito;
	}
	
	public String getCvc() {
		return cvc;
	}
	
	
	
	//SET

	public void setNumeroCarta(String numeroCarta) {
		this.numeroCarta = numeroCarta;
	}
	
	public void setEmail(String email) {
		this.email=email;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public void setCircuito(String circuito) {
		this.circuito = circuito;
	}

	public void setCvc(String cvc) {
		this.cvc = cvc;
	}

}
