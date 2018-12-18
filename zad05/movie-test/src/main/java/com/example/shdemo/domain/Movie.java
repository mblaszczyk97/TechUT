package com.example.shdemo.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "movie.all", query = "Select m from Movie m"),
	@NamedQuery(name = "movie.byTyp", query = "Select m from Movie m where m.typ = :typ"),
	@NamedQuery(name = "movie.byId", query = "Select m from Movie m where m.id = :id"),
	@NamedQuery(name = "movie.byNazwa", query = "Select m from Movie m where m.nazwa = :nazwa")
})
public class Movie {

	private Long id;

	private String nazwa = "Film1";
	private String typ = "Rodzaj1";
	private Date dataWydania = new Date();


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
/*
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Producent> getProducents() {
		return producents;
	}
	public void setProducents(List<Producent> producents) {
		this.producents = producents;
	}
	*/
	
}
