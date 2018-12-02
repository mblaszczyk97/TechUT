package com.example.shdemo.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "movie.all", query = "Select m from Movie m"),
	@NamedQuery(name = "movie.byTyp", query = "Select m from Movie m where m.typ = :typ")
})
public class Movie {

	private Long id;

	private String nazwa = "unknown";
	private String typ = "";
	private Date dataWydania = new Date();

	private List<Producent> producents = new ArrayList<Producent>();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNazwa() {
		return nazwa;
	}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	@Column(unique = true, nullable = false)
	public String getTyp() {
		return typ;
	}
	public void setTyp(String typ) {
		this.typ = typ;
	}

	@Temporal(TemporalType.DATE)
	public Date getDataWydania() {
		return dataWydania;
	}
	public void setDataWydania(Date dataWydania) {
		this.dataWydania = dataWydania;
	}

	// Be careful here, both with lazy and eager fetch type
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Producent> getProducents() {
		return producents;
	}
	public void setProducents(List<Producent> producents) {
		this.producents = producents;
	}
}
