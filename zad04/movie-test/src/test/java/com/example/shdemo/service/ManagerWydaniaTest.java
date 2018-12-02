package com.example.shdemo.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Producent;
import com.example.shdemo.domain.Movie;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class ManagerWydaniaTest {

	@Autowired
	ManagerWydania managerWydania;

	private final String NAME_1 = "Lord of the Rings";
	private final String TYP_1 = "Fantasy";

	private final String NAME_2 = "Your Name";
	private final String TYP_2 = "Drama";

	private final String NAZWA_PRODUCENTA_1 = "Marvel Studios";
	private final String KRAJ_1 = "USA";

	private final String NAZWA_PRODUCENTA_2 = "FOX";
	private final String KRAJ_2 = "USA";

	@Test
	public void addClientCheck() {

		List<Movie> retrievedClients = managerWydania.getAllMovies();

		// If there is a client with PIN_1 delete it
		for (Movie client : retrievedClients) {
			if (client.getTyp().equals(TYP_1)) {
				managerWydania.deleteMovie(client);
			}
		}

		Movie movie = new Movie();
		movie.setNazwa(NAME_1);
		movie.setTyp(TYP_1);
		// ... other properties here

		// Pin is Unique
		managerWydania.addMovie(movie);

		Movie retrievedClient = managerWydania.findMovieByTyp(TYP_1);

		assertEquals(NAME_1, retrievedClient.getNazwa());
		assertEquals(TYP_1, retrievedClient.getTyp());
		// ... check other properties here
	}

	@Test
	public void addCarCheck() {

		Producent producent = new Producent();
		producent.setKraj(KRAJ_1);
		producent.setNazwaProducenta(NAZWA_PRODUCENTA_1);
		// ... other properties here

		Long carId = managerWydania.addProducent(producent);

		Producent retrievedCar = managerWydania.findProducentById(carId);
		assertEquals(KRAJ_1, retrievedCar.getKraj());
		assertEquals(NAZWA_PRODUCENTA_1, retrievedCar.getNazwaProducenta());
		// ... check other properties here

	}

	@Test
	public void sellCarCheck() {

		Movie movie = new Movie();
		movie.setNazwa(NAME_2);
		movie.setTyp(TYP_2);

		managerWydania.addMovie(movie);

		Movie retrievedPerson = managerWydania.findMovieByTyp(TYP_2);

		Producent producent = new Producent();
		producent.setKraj(KRAJ_2);
		producent.setNazwaProducenta(NAZWA_PRODUCENTA_2);

		Long carId = managerWydania.addProducent(producent);

		managerWydania.dajProducentaDoFilmu(retrievedPerson.getId(), carId);

		List<Producent> ownedCars = managerWydania.getWydaneFilmy(retrievedPerson);

		assertEquals(1, ownedCars.size());
		assertEquals(KRAJ_2, ownedCars.get(0).getKraj());
		assertEquals(NAZWA_PRODUCENTA_2, ownedCars.get(0).getNazwaProducenta());
	}

	// @Test -
	public void disposeCarCheck() {
		// Do it yourself
	}

}
