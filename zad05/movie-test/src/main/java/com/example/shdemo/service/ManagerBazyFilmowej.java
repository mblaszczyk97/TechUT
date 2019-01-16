package com.example.shdemo.service;

import java.util.List;

import com.example.shdemo.domain.Producent;
import com.example.shdemo.domain.Rezyser;
import com.example.shdemo.domain.DaneTechniczne;
import com.example.shdemo.domain.Movie;

public interface ManagerBazyFilmowej {
	
	void addMovie(Movie movie);
	void addDaneTechniczne(DaneTechniczne dane);
	Long addProducent(Producent producent);
	void addRezyser(Rezyser rezyser);

	List<Movie> getAllMovies();
	List<Rezyser> getAllRezyserowDanegoFilmu(String nazwa);
	List<Producent> getAllProducenci();
	List<Rezyser> getAllRezyserzy();
	List<Movie> findMovieByTyp(String typ);
	List<Producent> getProducentKtoryWydal();
	List<Movie> getWydaneFilmy(Producent producent);
	List<Rezyser> getAllRezyserowDanejKategoriiFilmow(String typ);
	List<Movie> getAllFilmyDlugosci(int dlugosc);
	List<DaneTechniczne> getAllDaneTechniczne();
	
	void deleteMovie(Movie movie);
	void deleteRezyser(Rezyser rezyser);
	void deleteProducent(Producent producent);
	void deleteDaneTechniczne(DaneTechniczne dane);
	
	void dajProducentaDoFilmu(Long personId, Long producentId);
	void dajRezyseraDoFilmu(Long personId, Long rezyserId);
	void dajOpisDoFilmu(Long movieId, Long daneId);
	
	Movie findMovieById(Long id);
	Movie findMovieByNazwa(String nazwa);
	Producent findProducentaDanegoFilmu(String nazwa);
	Rezyser findRezyserById(Long id);
	Producent findProducentById(Long id);
	
	void updateMovie(Movie movie);
	void updateProducent(Producent producent);
	void updateRezyser(Rezyser rezyser);
	void updateDaneTechniczne(DaneTechniczne daneTechniczne);


}
