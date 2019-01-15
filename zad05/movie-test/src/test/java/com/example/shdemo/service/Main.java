package com.example.shdemo.service;


import org.springframework.beans.factory.annotation.Autowired;

import com.example.shdemo.domain.Movie;

public class Main {
	
	@Autowired
	static
	ManagerBazyFilmowej managerBazyFilmowej;
	
	public static void wstawFilm(Long id, String name, String typ)
	{
		Movie movie = new Movie();
		movie.setId(id);
		movie.setNazwa(name);
		movie.setTyp(typ);
		managerBazyFilmowej.addMovie(movie);
	}
	
	public static void main(String[] args) {
		
		
		wstawFilm(1l,"Star", "Sci-Fi");
		wstawFilm(2l,"Name", "Drama");
		wstawFilm(3l,"1999", "Documental");

	}

}
