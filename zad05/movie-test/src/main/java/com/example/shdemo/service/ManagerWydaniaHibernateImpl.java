package com.example.shdemo.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Producent;
import com.example.shdemo.domain.Rezyser;
import com.example.shdemo.domain.Movie;

@Component
@Transactional
public class ManagerWydaniaHibernateImpl implements ManagerWydania {

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
	public void deleteRezyser(Rezyser rezyser) {
		rezyser = (Rezyser) sessionFactory.getCurrentSession().get(Rezyser.class, rezyser.getId());
		sessionFactory.getCurrentSession().delete(rezyser);
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
	public Movie findMovieByTyp(String typ) {
		return (Movie) sessionFactory.getCurrentSession().getNamedQuery("movie.byTyp").setString("typ", typ).uniqueResult();
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
	public Long addProducent(Producent producent) {
		producent.setId(null);
		return (Long) sessionFactory.getCurrentSession().save(producent);
	}

	@Override
	public void dajProducentaDoFilmu(Long movieId, Long producentId) {
		Movie movie = (Movie) sessionFactory.getCurrentSession().get(Movie.class, movieId);
		Producent producent = (Producent) sessionFactory.getCurrentSession().get(Producent.class, producentId);
		producent.setCzyWydal(true);
		producent.getMovies().add(movie);
	}
	
	@Override
	public void dajRezyseraDoFilmu(Long movieId, Long rezyserId) {
		Movie movie = (Movie) sessionFactory.getCurrentSession().get(Movie.class, movieId);
		Rezyser rezyser = (Rezyser) sessionFactory.getCurrentSession().get(Rezyser.class, rezyserId);
		rezyser.getMovies().add(movie);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Producent> getAvailableProducent() {
		return sessionFactory.getCurrentSession().getNamedQuery("producent.czyWydal")
				.list();
	}
	@Override
	public void disposeProducent(Movie movie, Producent producent) {

		movie = (Movie) sessionFactory.getCurrentSession().get(Movie.class,
				movie.getId());
		producent = (Producent) sessionFactory.getCurrentSession().get(Producent.class,
				producent.getId());

		Movie toRemove = null;
		// lazy loading here (person.getCars)
		for (Movie aCar : producent.getMovies())
			if (aCar.getId().compareTo(producent.getId()) == 0) {
				toRemove = aCar;
				break;
			}

		if (toRemove != null)
			producent.getMovies().remove(toRemove);

		producent.setCzyWydal(false);
	}

	@Override
	public Producent findProducentById(Long id) {
		return (Producent) sessionFactory.getCurrentSession().get(Producent.class, id);
	}

}
