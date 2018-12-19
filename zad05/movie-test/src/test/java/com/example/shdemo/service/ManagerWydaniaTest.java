package com.example.shdemo.service;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Producent;
import com.example.shdemo.domain.Rezyser;
import com.example.shdemo.domain.Movie;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class ManagerWydaniaTest {

	@Autowired
	ManagerWydania managerWydania;

	private final String TYP_1 = "Fantasy";
/*	private final String NAME_1 = "Lord of the Rings";

	private final String NAME_2 = "Your Name";
	private final String TYP_2 = "Drama";

	private final String NAZWA_PRODUCENTA_1 = "Marvel Studios";
	private final String KRAJ_1 = "USA";

	private final String NAZWA_PRODUCENTA_2 = "FOX";
	private final String KRAJ_2 = "USA";*/

	
	@Test
	public void test1_addMovieCheck() throws ParseException {

		List<Movie> retrievedClients = managerWydania.getAllMovies();

		for (Movie client : retrievedClients) {
			if (client.getTyp().equals(TYP_1)) {
				managerWydania.deleteMovie(client);
			}
		}
		//Ustawianie testu-------------------------------------------------------
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		Movie movie1 = new Movie();
		movie1.setNazwa("Star-Wars");
		movie1.setTyp("Sci-Fi");
		movie1.setDataWydania(sdf.parse("31-08-1982 10:20:56"));
		Movie movie2 = new Movie();
		movie2.setNazwa("Kimino na wa");
		movie2.setTyp("Drama");
		movie2.setDataWydania(sdf.parse("22-08-2017 10:20:56"));
		Movie movie3 = new Movie();
		movie3.setNazwa("Lord of the Rings");
		movie3.setTyp("Fantasy");
		movie3.setDataWydania(sdf.parse("06-12-2001 10:20:56"));
		managerWydania.addMovie(movie1);
		managerWydania.addMovie(movie2);
		managerWydania.addMovie(movie3);
		//-----------------------------------------------------------------------

		Movie retrievedClient = managerWydania.findMovieByTyp("Drama");

		assertEquals("Kimino na wa", retrievedClient.getNazwa());
		assertEquals("Drama", retrievedClient.getTyp());
		
		//czyszczenie------------------------------------------------------------
		managerWydania.deleteMovie(movie1);
		managerWydania.deleteMovie(movie2);
		managerWydania.deleteMovie(movie3);
		//-----------------------------------------------------------------------

	}
	
	@Test
	public void test2_findById() throws ParseException {
		//Ustawianie testu-------------------------------------------------------
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		Movie movie1 = new Movie();
		movie1.setNazwa("Star-Wars");
		movie1.setTyp("Sci-Fi");
		movie1.setDataWydania(sdf.parse("31-08-1982 10:20:56"));
		managerWydania.addMovie(movie1);
		//-----------------------------------------------------------------------
		Movie retrievedClient = managerWydania.findMovieById(movie1.getId());

		assertEquals("Star-Wars", retrievedClient.getNazwa());
		assertEquals("Sci-Fi", retrievedClient.getTyp());
		
		//czyszczenie------------------------------------------------------------
		managerWydania.deleteMovie(movie1);
		//-----------------------------------------------------------------------
	}
	
	@Test
	public void test3_dodajDoRelacji() throws ParseException {
		//Ustawianie testu-------------------------------------------------------
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		Movie movie1 = new Movie();
		movie1.setNazwa("Star-Wars");
		movie1.setTyp("Sci-Fi");
		movie1.setDataWydania(sdf.parse("31-08-1982 10:20:56"));
		Movie movie2 = new Movie();
		movie2.setNazwa("Kimino na wa");
		movie2.setTyp("Drama");
		movie2.setDataWydania(sdf.parse("22-08-2017 10:20:56"));
		Movie movie3 = new Movie();
		movie3.setNazwa("Lord of the Rings");
		movie3.setTyp("Fantasy");
		movie3.setDataWydania(sdf.parse("06-12-2001 10:20:56"));
		managerWydania.addMovie(movie1);
		managerWydania.addMovie(movie2);
		managerWydania.addMovie(movie3);
		
		
		Producent producent1 = new Producent();
		producent1.setNazwaProducenta("Marvel");
		producent1.setKraj("USA");
		
		Producent producent2 = new Producent();
		producent2.setNazwaProducenta("Warner Brothers");
		producent2.setKraj("USA");
		
		Rezyser rezyser1 = new Rezyser();
		rezyser1.setImie("James");
		rezyser1.setNazwisko("Gunn");
		rezyser1.setDataUrodzenia(new Date());
		
		Rezyser rezyser2 = new Rezyser();
		rezyser2.setImie("James");
		rezyser2.setNazwisko("Wan");
		rezyser2.setDataUrodzenia(new Date());

		managerWydania.addProducent(producent1);
		managerWydania.addProducent(producent2);
		managerWydania.addRezyser(rezyser1);
		managerWydania.addRezyser(rezyser2);
		
		managerWydania.dajProducentaDoFilmu(movie1.getId(), 1l);
		managerWydania.dajProducentaDoFilmu(movie2.getId(), 1l);
		managerWydania.dajRezyseraDoFilmu(movie1.getId(), 1l);
		managerWydania.dajRezyseraDoFilmu(movie2.getId(), 2l);
		
		/*managerWydania.deleteRezyser(rezyser1);
		managerWydania.deleteRezyser(rezyser2);*/
/*		managerWydania.deleteMovie(movie1);
		managerWydania.deleteMovie(movie2);
		managerWydania.deleteMovie(movie3);*/
		
		//-----------------------------------------------------------------------
		
		//sprawdzamy tylko czy błędów nie ma przy relacjach
		assertEquals(1, 1);
		
	}
	
/*
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

	}*/

}
