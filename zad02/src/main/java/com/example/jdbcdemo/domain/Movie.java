package com.example.jdbcdemo.domain;

public class Movie {
	
	private long id;
	private static long counter = 0;
	private String typ;
	private String name;
	private String dataWydania;
	
	public Movie() {
	}
	
	public Movie(String name, String dataWydania, String typ,) {
		super();
		this.name = name;
		this.dataWydania = dataWydania;
		this.typ = typ;
		id = counter++;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDataWydania() {
		return dataWydania;
	}
	public void setDataWydania(String dataWydania) {
		this.dataWydania = dataWydania;
	}
	public String getTyp() {
		return typ;
	}
	public void setTyp(String typ) {
		this.typ = typ;
	}
}
