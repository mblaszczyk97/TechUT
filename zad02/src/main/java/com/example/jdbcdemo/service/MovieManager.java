package com.example.jdbcdemo.service;

import java.util.List;

import com.example.jdbcdemo.domain.Movie;

public interface MovieManager {
	
	public int addMovie(Movie movie);
	public List<Movie> getAllMovies();
	
	/* batch insert - transactional */
	public void addAllMovie(List<Movie> movies);

}
