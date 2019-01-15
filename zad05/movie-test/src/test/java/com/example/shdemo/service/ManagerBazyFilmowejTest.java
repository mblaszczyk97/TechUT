package com.example.shdemo.service;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.After;
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
import com.example.shdemo.domain.DaneTechniczne;
import com.example.shdemo.domain.Movie;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class ManagerBazyFilmowejTest {

	@Autowired
	ManagerBazyFilmowej managerBazyFilmowej;
	
	
	@Test
	public void checkDodanyFilmWBazie() throws ParseException {
		
/* Tylko do ręcznego wypisywania sobie
 * bardzo przydatne
 * 		List<Movie> retrievedClients = managerWydania.getAllMovies();

		for (Movie client : retrievedClients) {
			if (client.getTyp().equals("Fantasy")) {
				managerWydania.deleteMovie(client);
			}
		}*/
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
		managerBazyFilmowej.addMovie(movie1);
		managerBazyFilmowej.addMovie(movie2);
		managerBazyFilmowej.addMovie(movie3);

		Movie retrievedClient = managerBazyFilmowej.findMovieById(movie2.getId());

		assertEquals("Kimino na wa", retrievedClient.getNazwa());
		assertEquals("Drama", retrievedClient.getTyp());

	}
	
	@Test
	public void checkZnajdzFilmPrzezId() throws ParseException {
		//Ustawianie testu-------------------------------------------------------
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		Movie movie1 = new Movie();
		movie1.setNazwa("Star-Wars");
		movie1.setTyp("Sci-Fi");
		movie1.setDataWydania(sdf.parse("31-08-1982 10:20:56"));
		managerBazyFilmowej.addMovie(movie1);
		//-----------------------------------------------------------------------
		Movie retrievedClient = managerBazyFilmowej.findMovieById(movie1.getId());

		assertEquals("Star-Wars", retrievedClient.getNazwa());
		assertEquals("Sci-Fi", retrievedClient.getTyp());
	}
	
	@Test
	public void checkRelacjaOneToOne() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		Movie movie1 = new Movie();
		movie1.setNazwa("Star-Wars");
		movie1.setTyp("Sci-Fi");
		movie1.setDataWydania(sdf.parse("31-08-1982 10:20:56"));
		
		DaneTechniczne dane1 = new DaneTechniczne();
		dane1.setMinut_filmu(180);
		dane1.setOpis("Film opowiada historię bardzo dawno temu w odległej galaktyce");
		dane1.setRozdzielczosc("4:3");
		dane1.setTyp_kamery("analogowa");
		managerBazyFilmowej.addMovie(movie1);
		managerBazyFilmowej.addDaneTechniczne(dane1);
		managerBazyFilmowej.dajOpisDoFilmu(movie1.getId(), dane1.getId());
		//System.out.println(movie1.getDaneTechniczne().getOpis());
		assertEquals(movie1.getDaneTechniczne().getOpis(),dane1.getOpis());

	}
	
	@Test
	public void checkRelacjaOneToMany() throws ParseException {
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
		managerBazyFilmowej.addMovie(movie1);
		managerBazyFilmowej.addMovie(movie2);
		managerBazyFilmowej.addMovie(movie3);
		
		
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

		managerBazyFilmowej.addProducent(producent1);
		managerBazyFilmowej.addProducent(producent2);
		managerBazyFilmowej.addRezyser(rezyser1);
		managerBazyFilmowej.addRezyser(rezyser2);
		
		managerBazyFilmowej.dajProducentaDoFilmu(movie1.getId(), producent1.getId());
		managerBazyFilmowej.dajProducentaDoFilmu(movie2.getId(), producent1.getId());
		managerBazyFilmowej.dajRezyseraDoFilmu(movie1.getId(), rezyser1.getId());
		managerBazyFilmowej.dajRezyseraDoFilmu(movie2.getId(), rezyser2.getId());
		managerBazyFilmowej.dajRezyseraDoFilmu(movie2.getId(), rezyser1.getId());
		
		Producent retrievedClient = managerBazyFilmowej.findProducentaDanegoFilmu("Kimino na wa");

		//-----------------------------------------------------------------------
		assertEquals(retrievedClient.getNazwaProducenta(), "Marvel");
		
	}
	
	@Test
	public void checkRelacjaManyToMany() throws ParseException {
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
		managerBazyFilmowej.addMovie(movie1);
		managerBazyFilmowej.addMovie(movie2);
		managerBazyFilmowej.addMovie(movie3);
		
		
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

		managerBazyFilmowej.addProducent(producent1);
		managerBazyFilmowej.addProducent(producent2);
		managerBazyFilmowej.addRezyser(rezyser1);
		managerBazyFilmowej.addRezyser(rezyser2);
		
		managerBazyFilmowej.dajProducentaDoFilmu(movie1.getId(), producent1.getId());
		managerBazyFilmowej.dajProducentaDoFilmu(movie2.getId(), producent1.getId());
		managerBazyFilmowej.dajRezyseraDoFilmu(movie1.getId(), rezyser1.getId());
		managerBazyFilmowej.dajRezyseraDoFilmu(movie2.getId(), rezyser2.getId());
		managerBazyFilmowej.dajRezyseraDoFilmu(movie2.getId(), rezyser1.getId());
		
		List<Rezyser> retrievedClient2 = managerBazyFilmowej.getAllRezyserowDanegoFilmu("Kimino na wa");

		
		//-----------------------------------------------------------------------
		assertEquals(retrievedClient2.size(), 2);
		
	}
	
	@Test
	public void checkCRUDSelectAll() throws ParseException {
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
		managerBazyFilmowej.addMovie(movie1);
		managerBazyFilmowej.addMovie(movie2);
		managerBazyFilmowej.addMovie(movie3);
		
		
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

		managerBazyFilmowej.addProducent(producent1);
		managerBazyFilmowej.addProducent(producent2);
		managerBazyFilmowej.addRezyser(rezyser1);
		managerBazyFilmowej.addRezyser(rezyser2);
		
		List<Rezyser> retrievedClients = managerBazyFilmowej.getAllRezyserzy();
		List<Movie> retrievedClients1 = managerBazyFilmowej.getAllMovies();
		List<Producent> retrievedClients2 = managerBazyFilmowej.getAllProducenci();

		assertEquals(retrievedClients.size(), 2);
		assertEquals(retrievedClients1.size(), 3);
		assertEquals(retrievedClients2.size(), 2);

	}
	
	@Test
	public void checkCRUDDelete() throws ParseException {
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
		managerBazyFilmowej.addMovie(movie1);
		managerBazyFilmowej.addMovie(movie2);
		managerBazyFilmowej.addMovie(movie3);
		
		
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

		managerBazyFilmowej.addProducent(producent1);
		managerBazyFilmowej.addProducent(producent2);
		managerBazyFilmowej.addRezyser(rezyser1);
		managerBazyFilmowej.addRezyser(rezyser2);
		managerBazyFilmowej.deleteMovie(movie1);
		managerBazyFilmowej.deleteMovie(movie2);
		managerBazyFilmowej.deleteProducent(producent1);
		managerBazyFilmowej.deleteRezyser(rezyser1);
		
		List<Rezyser> retrievedClients = managerBazyFilmowej.getAllRezyserzy();
		List<Movie> retrievedClients1 = managerBazyFilmowej.getAllMovies();
		List<Producent> retrievedClients2 = managerBazyFilmowej.getAllProducenci();
		List<Producent> retrievedClients3 = managerBazyFilmowej.getAllProducencizFilmami();
		
		
		assertEquals(retrievedClients.size(), 1);
		assertEquals(retrievedClients1.size(), 1);
		assertEquals(retrievedClients2.size(), 1);

	}
	
	@Test
	public void checkCRUDCreate() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		Movie movie1 = new Movie();
		movie1.setNazwa("Star-Wars");
		movie1.setTyp("Sci-Fi");
		movie1.setDataWydania(sdf.parse("31-08-1982 10:20:56"));
		Producent producent1 = new Producent();
		producent1.setNazwaProducenta("Marvel");
		producent1.setKraj("USA");
		Rezyser rezyser1 = new Rezyser();
		rezyser1.setImie("James");
		rezyser1.setNazwisko("Gunn");
		rezyser1.setDataUrodzenia(new Date());
		managerBazyFilmowej.addMovie(movie1);
		managerBazyFilmowej.addProducent(producent1);
		managerBazyFilmowej.addRezyser(rezyser1);
		Movie retrievedClient = managerBazyFilmowej.findMovieById(movie1.getId());
		Producent retrievedClient1 = managerBazyFilmowej.findProducentById(producent1.getId());
		Rezyser retrievedClient2 = managerBazyFilmowej.findRezyserById(rezyser1.getId());
		
		assertEquals(retrievedClient.getNazwa(), "Star-Wars");
		assertEquals(retrievedClient1.getNazwaProducenta(), "Marvel");
		assertEquals(retrievedClient2.getNazwisko(), "Gunn");
	}
	
	@Test
	public void checkCRUDUpdate() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		Movie movie1 = new Movie();
		movie1.setNazwa("Star-Wars");
		movie1.setTyp("Sci-Fi");
		movie1.setDataWydania(sdf.parse("31-08-1982 10:20:56"));
		managerBazyFilmowej.addMovie(movie1);
		movie1.setNazwa("StarWars");
		managerBazyFilmowej.updateMovie(movie1);
		
		Producent producent1 = new Producent();
		producent1.setNazwaProducenta("Marvel");
		producent1.setKraj("USA");
		managerBazyFilmowej.addProducent(producent1);
		producent1.setKraj("Niemcy");
		managerBazyFilmowej.updateProducent(producent1);

		
		Rezyser rezyser1 = new Rezyser();
		rezyser1.setImie("James");
		rezyser1.setNazwisko("Gunn");
		rezyser1.setDataUrodzenia(new Date());
		managerBazyFilmowej.addRezyser(rezyser1);
		rezyser1.setImie("Henry");
		managerBazyFilmowej.updateRezyser(rezyser1);

		DaneTechniczne dane1 = new DaneTechniczne();
		dane1.setMinut_filmu(120);
		dane1.setOpis("Film opowiada historię bardzo dawno temu w odległej galaktyce");
		dane1.setRozdzielczosc("4:3");
		dane1.setTyp_kamery("analogowa");
		managerBazyFilmowej.addDaneTechniczne(dane1);
		dane1.setMinut_filmu(200);
		managerBazyFilmowej.updateDaneTechniczne(dane1);
		
		assertEquals(managerBazyFilmowej.findMovieByNazwa("StarWars").getTyp(), "Sci-Fi");
		assertEquals(managerBazyFilmowej.getAllRezyserzy().get(0).getImie(), "Henry");
		assertEquals(managerBazyFilmowej.getAllProducenci().get(0).getKraj(), "Niemcy");
		assertEquals(managerBazyFilmowej.getAllDaneTechniczne().get(0).getMinut_filmu(), 200);
	}
	
	@Test
	public void checkWypisywanieFilmowDanejDlugosci() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		
		Movie movie1 = new Movie();
		movie1.setNazwa("Star-Wars");
		movie1.setTyp("Sci-Fi");
		
		Movie movie2 = new Movie();
		movie2.setNazwa("Gone Home");
		movie2.setTyp("Thriller");
		
		DaneTechniczne dane1 = new DaneTechniczne();
		dane1.setMinut_filmu(120);
		dane1.setOpis("Film opowiada historię bardzo dawno temu w odległej galaktyce");
		dane1.setRozdzielczosc("4:3");
		dane1.setTyp_kamery("analogowa");
		
		DaneTechniczne dane2 = new DaneTechniczne();
		dane1.setMinut_filmu(120);
		dane1.setOpis("Film jest Thrillerem");
		dane1.setRozdzielczosc("16:9");
		dane1.setTyp_kamery("cyfrowa");
		
		managerBazyFilmowej.addMovie(movie1);
		managerBazyFilmowej.addMovie(movie2);
		managerBazyFilmowej.addDaneTechniczne(dane1);
		managerBazyFilmowej.addDaneTechniczne(dane2);
		managerBazyFilmowej.dajOpisDoFilmu(movie1.getId(), dane1.getId());
		managerBazyFilmowej.dajOpisDoFilmu(movie2.getId(), dane2.getId());
		List<Movie> retrievedClients10 = managerBazyFilmowej.getAllFilmyDlugosci(120);
		for (Movie client : retrievedClients10) {
			System.out.println(client.getNazwa());
		}
		assertEquals(retrievedClients10.size(), 2);
	}
	

}
