package com.example.shdemo.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Producent;
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
	public void deleteMovie(Movie movie) {
		movie = (Movie) sessionFactory.getCurrentSession().get(Movie.class,
				movie.getId());
		
		for (Producent producent : movie.getProducents()) {
			producent.setCzyWydal(false);
			sessionFactory.getCurrentSession().update(producent);
		}
		sessionFactory.getCurrentSession().delete(movie);
	}

	@Override
	public List<Producent> getWydaneFilmy(Movie movie) {
		movie = (Movie) sessionFactory.getCurrentSession().get(Movie.class,
				movie.getId());
		// lazy loading here - try this code without (shallow) copying
		List<Producent> producents = new ArrayList<Producent>(movie.getProducents());
		return producents;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Movie> getAllMovies() {
		return sessionFactory.getCurrentSession().getNamedQuery("movie.all")
				.list();
	}

	@Override
	public Movie findMovieByTyp(String typ) {
		return (Movie) sessionFactory.getCurrentSession().getNamedQuery("movie.byTyp").setString("typ", typ).uniqueResult();
	}


	@Override
	public Long addProducent(Producent producent) {
		producent.setId(null);
		return (Long) sessionFactory.getCurrentSession().save(producent);
	}

	@Override
	public void dajProducentaDoFilmu(Long personId, Long producentId) {
		Movie movie = (Movie) sessionFactory.getCurrentSession().get(
				Movie.class, personId);
		Producent producent = (Producent) sessionFactory.getCurrentSession()
				.get(Producent.class, producentId);
		producent.setCzyWydal(true);
		movie.getProducents().add(producent);
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

		Producent toRemove = null;
		// lazy loading here (person.getCars)
		for (Producent aCar : movie.getProducents())
			if (aCar.getId().compareTo(producent.getId()) == 0) {
				toRemove = aCar;
				break;
			}

		if (toRemove != null)
			movie.getProducents().remove(toRemove);

		producent.setCzyWydal(false);
	}

	@Override
	public Producent findProducentById(Long id) {
		return (Producent) sessionFactory.getCurrentSession().get(Producent.class, id);
	}

}
