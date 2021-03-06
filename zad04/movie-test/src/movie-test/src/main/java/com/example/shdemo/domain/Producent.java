package com.example.shdemo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
		@NamedQuery(name = "producent.czyWydal", query = "Select p from Producent p where p.czyWydal = false")
})
public class Producent {

	private Long id;
	private String kraj;
	private String nazwaProducenta;
	private Boolean czyWydal = false;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKraj() {
		return kraj;
	}

	public void setKraj(String kraj) {
		this.kraj = kraj;
	}

	public String getNazwaProducenta() {
		return nazwaProducenta;
	}

	public void setNazwaProducenta(String nazwaProducenta) {
		this.nazwaProducenta = nazwaProducenta;
	}

	public Boolean getCzyWydal() {
		return czyWydal;
	}

	public void setCzyWydal(Boolean czyWydal) {
		this.czyWydal = czyWydal;
	}
}
