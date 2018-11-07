package com.example.jdbcdemo.service;

import java.sql.Date;

public class Movie {
	
	private long id;
	private static long counter = 0;
	private String typ;
	private String name;
	private Date dataWydania;
	
	public Movie() {
		super();
	}
	
	public Movie(String name, Date dataWydania, String typ) {
		super();
		this.name = name;
		this.dataWydania = dataWydania;
		this.typ = typ;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDataWydania() {
		return dataWydania;
	}
	public void setDataWydania(Date date) {
		this.dataWydania = date;
	}
	public String getTyp() {
		return typ;
	}
	public void setTyp(String typ) {
		this.typ = typ;
	}
	
	public static final String NAME = "name";
    public static final String DATA_WYDANIA = "dataWydania";
    public static final String TYP = "typ";
}