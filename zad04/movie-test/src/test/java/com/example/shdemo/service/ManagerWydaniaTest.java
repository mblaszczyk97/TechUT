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
	public void addMovieCheck() {

		List<Movie> retrievedClients = managerWydania.getAllMovies();

		for (Movie client : retrievedClients) {
			if (client.getTyp().equals(TYP_1)) {
				managerWydania.deleteMovie(client);
			}
		}

		Movie movie = new Movie();
		movie.setNazwa(NAME_1);
		movie.setTyp(TYP_1);

		managerWydania.addMovie(movie);

		Movie retrievedClient = managerWydania.findMovieByTyp(TYP_1);

		assertEquals(NAME_1, retrievedClient.getNazwa());
		assertEquals(TYP_1, retrievedClient.getTyp());

	}

	@Test
	public void addProducentCheck() {

		Producent producent = new Producent();
		producent.setKraj(KRAJ_1);
		producent.setNazwaProducenta(NAZWA_PRODUCENTA_1);
		
		Long producentId = managerWydania.addProducent(producent);

		Producent retrievedProducent = managerWydania.findProducentById(producentId);
		assertEquals(KRAJ_1, retrievedProducent.getKraj());
		assertEquals(NAZWA_PRODUCENTA_1, retrievedProducent.getNazwaProducenta());

	}

	@Test
	public void wydanieCheck() {

		Movie movie = new Movie();
		movie.setNazwa(NAME_2);
		movie.setTyp(TYP_2);

		managerWydania.addMovie(movie);

		Movie retrievedMovie = managerWydania.findMovieByTyp(TYP_2);

		Producent producent = new Producent();
		producent.setKraj(KRAJ_2);
		producent.setNazwaProducenta(NAZWA_PRODUCENTA_2);

		Long producentId = managerWydania.addProducent(producent);

		managerWydania.dajProducentaDoFilmu(retrievedMovie.getId(), producentId);

		List<Producent> producenciWydanie = managerWydania.getWydaneFilmy(retrievedMovie);

		assertEquals(1, producenciWydanie.size());
		assertEquals(KRAJ_2, producenciWydanie.get(0).getKraj());
		assertEquals(NAZWA_PRODUCENTA_2, producenciWydanie.get(0).getNazwaProducenta());
	}

	// @Test
	public void disposeCarCheck() {

	}

}
