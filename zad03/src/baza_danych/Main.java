package baza_danych;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import baza_danych.domain.Movie;
import baza_danych.service.MovieService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;

public class Main {

    @SuppressWarnings("resource")
	public static void main(String[] args) throws SQLException, IOException {

    	 while(1!=0) {
    	
    		 MovieService movieService = new MovieService();
        
    		 System.out.println("	MENU:");
    		 System.out.println("1 - uzupe³nij tabelê rekordami");
    		 System.out.println("2 - usun rekord po id");
    		 System.out.println("3 - usun wszystko");
    		 System.out.println("4 - poka¿ wszystkie rekordy");
        
    		 Scanner sc = new Scanner(System.in);
    		 int line = sc.nextInt();

    		 if (line == 1) {
    			 addRecords(movieService);
    			 
    		 }else if(line == 2){
    			 System.out.println("rekordu o jakim id usuwasz?");
    			 Scanner sc2 = new Scanner(System.in);
    			 int id = sc2.nextInt();
    			 movieService.deleteMovie(movieService.findMovie(id));
    			 
    		 }else if(line == 3){
    			 movieService.deleteAllMovies();
    			 
    		 }else if(line == 4){
    			 System.out.println("wed³ug jakiego gatunku chcesz wypisaæ?");
    			 Scanner sc1 = new Scanner(System.in);
    			 String typ = sc1.nextLine();
    			 showAllRecords(typ);
        	
    		 }
        }
        //addRecords(); // dodajemy rekordy
        
        //showAllRecords("drama");

    }

    public static void addRecords(MovieService movie) {
    	/*MovieService movieService = new MovieService(); // zwyk³y sposób
    	Date data1 = new Date(2323223232L);
    	
    	Movie filmJeden = new Movie("Star Wars",data1,"science-fiction");
        Movie filmDwa = new Movie("Shrek",data1,"animation");
        Movie filmTrzy = new Movie("Lord of the rings",data1,"fantasy");
        Movie filmCztery = new Movie("Titanic",data1,"drama");
        Movie filmPiec = new Movie("Your Name",data1,"drama");
        
        
        movieService.addMovie(filmJeden);
        movieService.addMovie(filmDwa);
        movieService.addMovie(filmTrzy);
        movieService.addMovie(filmCztery);
        movieService.addMovie(filmPiec);*/
    	
    	List<Movie> lista = new ArrayList<>(); // sposób transakcji z wyk³adu
    	Date data1 = new Date(2323223232L);
    	
    	lista.add(new Movie("Star Wars",data1,"science-fiction"));
    	lista.add(new Movie("Shrek",data1,"animation"));
    	lista.add(new Movie("Lord of the rings",data1,"fantasy"));
    	lista.add(new Movie("Titanic",data1,"drama"));
    	lista.add(new Movie("Your Name",data1,"drama"));

        movie.addAllMovies(lista);
    }
    
    public static void showAllRecords(String typ) {
    	MovieService movieService = new MovieService();
    	//System.out.println(movieService.getAllMovies(typ)); //Tutaj bierzemy obiekt Movie
    	System.out.println(movieService.getAllMoviesNazwy(typ)); //Tutaj mamy tylko liste nazw dla przejrzystoœci
    }

}