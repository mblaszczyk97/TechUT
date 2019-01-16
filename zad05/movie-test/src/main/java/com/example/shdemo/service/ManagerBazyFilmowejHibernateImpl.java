package com.example.shdemo.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Producent;
import com.example.shdemo.domain.Rezyser;
import com.example.shdemo.domain.DaneTechniczne;
import com.example.shdemo.domain.Movie;

@Component
@Transactional
public class ManagerBazyFilmowejHibernateImpl implements ManagerBazyFilmowej {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void addMovie(Movie movie) {
		//sessionFactory.getCurrentSession().getTransaction().begin();
		movie.setId(null);
		sessionFactory.getCurrentSession().persist(movie);
		//sessionFactory.getCurrentSession().getTransaction().commit();
	}
	
	@Override
	public void addDaneTechniczne(DaneTechniczne dane) {
		//sessionFactory.getCurrentSession().getTransaction().begin();
		dane.setId(null);
		sessionFactory.getCurrentSession().persist(dane);
		//sessionFactory.getCurrentSession().getTransaction().commit();
	}
	
	@Override
	public Long addProducent(Producent producent) {
		producent.setId(null);
		return (Long) sessionFactory.getCurrentSession().save(producent);
	}
	
	@Override
	public void addRezyser(Rezyser rezyser) {
		//sessionFactory.getCurrentSession().getTransaction().begin();
		rezyser.setId(null);
		sessionFactory.getCurrentSession().persist(rezyser);
		//sessionFactory.getCurrentSession().getTransaction().commit();
	}
	
	@Override
	public void deleteMovie(Movie movie) {
		movie = (Movie) sessionFactory.getCurrentSession().get(Movie.class, movie.getId());
		sessionFactory.getCurrentSession().delete(movie);
	}
	
	@Override
	public void deleteProducent(Producent producent) {
		producent = (Producent) sessionFactory.getCurrentSession().get(Producent.class, producent.getId());
		sessionFactory.getCurrentSession().delete(producent);
	}
	
	@Override
	public void deleteRezyser(Rezyser rezyser) {
		rezyser = (Rezyser) sessionFactory.getCurrentSession().get(Rezyser.class, rezyser.getId());
		sessionFactory.getCurrentSession().delete(rezyser);
	}
	
	@Override
	public void deleteDaneTechniczne(DaneTechniczne dane) {
		dane = (DaneTechniczne) sessionFactory.getCurrentSession().get(DaneTechniczne.class, dane.getId());
		sessionFactory.getCurrentSession().delete(dane);
	}

	@Override
    public void updateMovie(Movie movie) {
		sessionFactory.getCurrentSession().saveOrUpdate(movie);
    }
	
	@Override
    public void updateProducent(Producent producent) {
		sessionFactory.getCurrentSession().saveOrUpdate(producent);
    }
	
	@Override
    public void updateRezyser(Rezyser rezyser) {
		sessionFactory.getCurrentSession().saveOrUpdate(rezyser);
    }
	
	@Override
    public void updateDaneTechniczne(DaneTechniczne daneTechniczne) {
		sessionFactory.getCurrentSession().saveOrUpdate(daneTechniczne);
    }
	
	@Override
	public List<Movie> getWydaneFilmy(Producent producent) {
		producent = (Producent) sessionFactory.getCurrentSession().get(Producent.class,producent.getId());
		List<Movie> movies = new ArrayList<Movie>(producent.getMovies());
		return movies;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Movie> getAllMovies() {
		return sessionFactory.getCurrentSession().getNamedQuery("movie.all").list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<DaneTechniczne> getAllDaneTechniczne() {
		return sessionFactory.getCurrentSession().getNamedQuery("danetechniczne.all").list();
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Rezyser> getAllRezyserowDanegoFilmu(String nazwa) {
		return sessionFactory.getCurrentSession().getNamedQuery("rezyser.Filmu").setString("nazwa", nazwa).list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Movie> getAllFilmyDlugosci(int dlugosc) {
		return sessionFactory.getCurrentSession().getNamedQuery("movie.wedlugDlugosci").setInteger("minut_filmu", dlugosc).list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Rezyser> getAllRezyserowDanejKategoriiFilmow(String typ) {
		return sessionFactory.getCurrentSession().getNamedQuery("rezyser.FilmuKategorii").setString("typ", typ).list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Producent> getAllProducenci() {
		return sessionFactory.getCurrentSession().getNamedQuery("producent.all").list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Rezyser> getAllRezyserzy() {
		return sessionFactory.getCurrentSession().getNamedQuery("rezyser.all").list();
	}

	@Override
	public Producent findProducentaDanegoFilmu(String nazwa) {
		return (Producent) sessionFactory.getCurrentSession().getNamedQuery("producent.danegoFilmu").setString("nazwa", nazwa).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Movie> findMovieByTyp(String typ) {
		return sessionFactory.getCurrentSession().getNamedQuery("movie.byTyp").setString("typ", typ).list();
	}
	
	@Override
	public Movie findMovieByNazwa(String nazwa) {
		return (Movie) sessionFactory.getCurrentSession().getNamedQuery("movie.byNazwa").setString("nazwa", nazwa).uniqueResult();
	}
	
	@Override
	public Movie findMovieById(Long id) {
		return (Movie) sessionFactory.getCurrentSession().getNamedQuery("movie.byId").setLong("id", id).uniqueResult();
	}

	@Override
	public Rezyser findRezyserById(Long id) {
		return (Rezyser) sessionFactory.getCurrentSession().getNamedQuery("rezyser.byId").setLong("id", id).uniqueResult();
	}

	@Override
	public Producent findProducentById(Long id) {
		return (Producent) sessionFactory.getCurrentSession().get(Producent.class, id);
	}

	@Override
	public void dajProducentaDoFilmu(Long movieId, Long producentId) {
		Movie movie = (Movie) sessionFactory.getCurrentSession().get(Movie.class, movieId);
		Producent producent = (Producent) sessionFactory.getCurrentSession().get(Producent.class, producentId);
		producent.setCzyWydal(true);
		producent.getMovies().add(movie);
	}
	
	@Override
	public void dajOpisDoFilmu(Long movieId, Long daneId) {
		Movie movie = (Movie) sessionFactory.getCurrentSession().get(Movie.class, movieId);
		DaneTechniczne dane = (DaneTechniczne) sessionFactory.getCurrentSession().get(DaneTechniczne.class, daneId);
		movie.setDaneTechniczne(dane);
	}
	
	@Override
	public void dajRezyseraDoFilmu(Long movieId, Long rezyserId) {
		Movie movie = (Movie) sessionFactory.getCurrentSession().get(Movie.class, movieId);
		Rezyser rezyser = (Rezyser) sessionFactory.getCurrentSession().get(Rezyser.class, rezyserId);
		rezyser.getMovies().add(movie);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Producent> getProducentKtoryWydal() {
		return sessionFactory.getCurrentSession().getNamedQuery("producent.czyWydal").list();
	}


}
