package com.example.jdbcdemo.domain;

public class Movie {
	
	private long id;
	
	private String typ;
	private String name;
	private int yob;
	private String dataWydania;
	
	public Movie() {
	}
	
	public Movie(String name, int yob) {
		super();
		this.name = name;
		this.yob = yob;
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
	public int getYob() {
		return yob;
	}
	public void setYob(int yob) {
		this.yob = yob;
	}
	public String getDataWydania() {
		return dataWydania;
	}
	public void setDataWydania(String dataWydania) {
		this.dataWydania = dataWydania;
	}
	public String typ() {
		return typ;
	}
	public void setTyp(String typ) {
		this.typ = typ;
	}
}
