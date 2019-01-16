package com.example.shdemo.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "movie.all", query = "Select m from Movie m"),
	@NamedQuery(name = "movie.byTyp", query = "Select m from Movie m where m.typ = :typ"),
	@NamedQuery(name = "movie.byId", query = "Select m from Movie m where m.id = :id"),
	@NamedQuery(name = "movie.byNazwa", query = "Select m from Movie m where m.nazwa = :nazwa"),
	@NamedQuery(name = "movie.wedlugDlugosci", query = "Select p from Movie p left join p.daneTechniczne m where m.minut_filmu = :minut_filmu")

	//@NamedQuery(name = "movie.byProducent", query = "Select m from Movie m "
			//+ "LEFT JOIN Producent_Movie ON Movie.id  = Producent_Movie.movies_id " + 
			//"LEFT JOIN Producent on Producent_Movie.producent_id = producent.id where m.nazwaproducenta = :nazwaproducenta")
	
	//SELECT nazwa, typ, nazwaproducenta from Movie 
	//LEFT JOIN Producent_Movie ON Movie.id  = Producent_Movie.movies_id
	//LEFT JOIN Producent on Producent_Movie.producent_id = producent.id
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
	
    private DaneTechniczne daneTechniczne;
	

	public void setDaneTechniczne(DaneTechniczne daneTechniczne) {
		this.daneTechniczne = daneTechniczne;
	}
	
	@OneToOne(fetch = FetchType.LAZY,
              cascade =  CascadeType.ALL)
	public DaneTechniczne getDaneTechniczne() {
		return daneTechniczne;
	}
	public Movie() {
		
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
