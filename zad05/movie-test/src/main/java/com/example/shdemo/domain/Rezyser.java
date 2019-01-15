package com.example.shdemo.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
		@NamedQuery(name = "rezyser.byId", query = "Select m from Rezyser m where m.id = :id"),
		@NamedQuery(name = "rezyser.all", query = "Select m from Rezyser m"),
		@NamedQuery(name = "rezyser.Filmu", query = "Select p from Rezyser p left join p.movies m where m.nazwa = :nazwa"),
		@NamedQuery(name = "rezyser.FilmuKategorii", query = "Select p from Rezyser p left join p.movies m where m.typ = :typ")


})
public class Rezyser {
	
	private Long id;

	private String imie = "imie1";
	private String nazwisko = "nazwisko1";
	private Date dataUrodzenia = new Date();
	
	private List<Movie> movies = new ArrayList<Movie>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getImie() {
		return imie;
	}
	public void setImie(String imie) {
		this.imie = imie;
	}
	public String getNazwisko() {
		return nazwisko;
	}
	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	@Temporal(TemporalType.DATE)
	public Date getDataUrodzenia() {
		return dataUrodzenia;
	}
	public void setDataUrodzenia(Date dataUrodzenia) {
		this.dataUrodzenia = dataUrodzenia;
	}
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Movie> getMovies() {
		return movies;
	}
	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	
}
