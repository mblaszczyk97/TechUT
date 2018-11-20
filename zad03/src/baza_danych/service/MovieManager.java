package baza_danych.service;

import java.sql.SQLException;
import java.util.List;

import baza_danych.domain.Movie;

public interface MovieManager {
	
	public int addMovie(Movie movie) throws SQLException;
	void addAllMovies(List<Movie> movies);


    void deleteMovieByName(String name);

    void deleteAllMovies();
	Movie deleteMovie(Movie movie);
	List<Movie> getAllMovies(String typ);
	List<String> getAllMoviesNazwy(String typ);
	Movie updateMovie(Movie movie);
	Movie findMovie(long id);

}
