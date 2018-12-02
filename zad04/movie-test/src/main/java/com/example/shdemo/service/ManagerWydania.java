package com.example.shdemo.service;

import java.util.List;

import com.example.shdemo.domain.Producent;
import com.example.shdemo.domain.Movie;

public interface ManagerWydania {
	
	void addMovie(Movie movie);
	List<Movie> getAllMovies();
	void deleteMovie(Movie movie);
	Movie findMovieByTyp(String typ);
	
	Long addProducent(Producent producent);
	List<Producent> getAvailableProducent();
	void disposeProducent(Movie movie, Producent producent);
	Producent findProducentById(Long id);

	List<Producent> getWydaneFilmy(Movie movie);
	void dajProducentaDoFilmu(Long personId, Long producentId);

}
