package com.example.jdbcdemo.service;

import java.sql.SQLException;
import java.util.List;

public interface MovieManager {
	
	public int addMovie(Movie movie) throws SQLException;
	void addAllMovies(List<Movie> movies);
	public List<Movie> getAllMovies();

	Movie getMovieByName(String name);

    void deleteMovieByName(String name);

    void deleteAllMovies();

}
