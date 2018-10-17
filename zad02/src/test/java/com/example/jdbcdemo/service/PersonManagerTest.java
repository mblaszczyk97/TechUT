package com.example.jdbcdemo.service;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.example.jdbcdemo.domain.Movie;

public class PersonManagerTest {
	
	
	MovieService personManager = new MovieService();
	
	private final static String NAME_1 = "Zenek";
	private final static int YOB_1 = 1945;
	
	private final static String NAME_2 = "Ziutek";
	private final static int YOB_2 = 1945;
	
	private final static String NAME_3 = "Bolek";
	private final static int YOB_3 = 1945;
	
	private final static String NAME_4 = "Lolek";
	private final static int YOB_4 = 1945;
		
	Movie person1 = new Movie(NAME_1, YOB_1);
	Movie person2 = new Movie(NAME_2, YOB_2);
	Movie person3 = new Movie(NAME_3, YOB_3);
	Movie person4 = new Movie(NAME_4, YOB_4);
	
	@Test
	public void checkConnection(){
		assertNotNull(personManager.getConnection());
	}
	
	//@Test
	public void checkAdding(){
		
		Movie person = new Movie(NAME_1, YOB_1);
		
		personManager.clearPersons();
		assertEquals(1,personManager.addMovie(person));
		
		List<Movie> persons = personManager.getAllMovies();
		Movie personRetrieved = persons.get(0);
		
		assertEquals(NAME_1, personRetrieved.getName());
		assertEquals(YOB_1, personRetrieved.getYob());
		
	}
	
	@Test
	public void checkAddAll(){
		personManager.clearPersons();		
		
		List<Movie> persons = new ArrayList<>();
		persons.add(person1);
		persons.add(person2);
		//persons.add(person2);
		persons.add(person3);
		persons.add(person4);
		
		personManager.addAllMovie(persons);
		int size = personManager.getAllMovies().size();
		
		//assertTrue(size == 0 || size == 4);
		
		assertThat(size, either(is(4)).or(is(0)));
		
	}

}
