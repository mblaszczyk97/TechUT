package com.example.shdemo.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Car;
import com.example.shdemo.domain.Movie;

@Component
@Transactional
public class SellingMangerHibernateImpl implements SellingManager {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void addClient(Movie person) {
		person.setId(null);
		sessionFactory.getCurrentSession().persist(person);
	}
	
	@Override
	public void deleteClient(Movie person) {
		person = (Movie) sessionFactory.getCurrentSession().get(Movie.class,
				person.getId());
		
		// lazy loading here
		for (Car car : person.getCars()) {
			car.setSold(false);
			sessionFactory.getCurrentSession().update(car);
		}
		sessionFactory.getCurrentSession().delete(person);
	}

	@Override
	public List<Car> getOwnedCars(Movie person) {
		person = (Movie) sessionFactory.getCurrentSession().get(Movie.class,
				person.getId());
		// lazy loading here - try this code without (shallow) copying
		List<Car> cars = new ArrayList<Car>(person.getCars());
		return cars;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Movie> getAllClients() {
		return sessionFactory.getCurrentSession().getNamedQuery("person.all")
				.list();
	}

	@Override
	public Movie findClientByPin(String pin) {
		return (Movie) sessionFactory.getCurrentSession().getNamedQuery("person.byPin").setString("pin", pin).uniqueResult();
	}


	@Override
	public Long addNewCar(Car car) {
		car.setId(null);
		return (Long) sessionFactory.getCurrentSession().save(car);
	}

	@Override
	public void sellCar(Long personId, Long carId) {
		Movie person = (Movie) sessionFactory.getCurrentSession().get(
				Movie.class, personId);
		Car car = (Car) sessionFactory.getCurrentSession()
				.get(Car.class, carId);
		car.setSold(true);
		person.getCars().add(car);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Car> getAvailableCars() {
		return sessionFactory.getCurrentSession().getNamedQuery("car.unsold")
				.list();
	}
	@Override
	public void disposeCar(Movie person, Car car) {

		person = (Movie) sessionFactory.getCurrentSession().get(Movie.class,
				person.getId());
		car = (Car) sessionFactory.getCurrentSession().get(Car.class,
				car.getId());

		Car toRemove = null;
		// lazy loading here (person.getCars)
		for (Car aCar : person.getCars())
			if (aCar.getId().compareTo(car.getId()) == 0) {
				toRemove = aCar;
				break;
			}

		if (toRemove != null)
			person.getCars().remove(toRemove);

		car.setSold(false);
	}

	@Override
	public Car findCarById(Long id) {
		return (Car) sessionFactory.getCurrentSession().get(Car.class, id);
	}

}
