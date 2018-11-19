package baza_danych;

import java.sql.SQLException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;

public class Main {

    public static void main(String[] args) throws SQLException, IOException {

    	 while(1!=0) {
    	
    		 MovieService movieService = new MovieService();
        
        
    		 System.out.println("	MENU:");
    		 System.out.println("1 - dodaj rekordy");
    		 System.out.println("2 - usun rekord");
    		 System.out.println("3 - poka¿ wszystkie rekordy");
        
    		 Scanner sc = new Scanner(System.in);
    		 int line = sc.nextInt();

    		 if (line == 1) {
    			 addRecords();
    			 
    		 }else if(line == 2){
    			 movieService.removeMovie(movieService.findMovieById(2));
    			 
    		 }else if(line == 3){
    			 showAllRecords("drama");
        	
    		 }
        }
        //addRecords(); // dodajemy rekordy
        
        //showAllRecords("drama");
        
        //movieService.removeMovie(movieService.findMovieById(2)); // usuwamy rekord gdzie id=2

    }

    public static void addRecords() {
    	MovieService movieService = new MovieService();
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
        movieService.addMovie(filmPiec);
    }
    
    public static void showAllRecords(String typ) {
    	MovieService movieService = new MovieService();
    	//System.out.println(movieService.getAllMovies(typ)); //Tutaj bierzemy obiekt Movie
    	System.out.println(movieService.getAllMoviesNazwy(typ)); //Tutaj mamy tylko liste nazw dla przejrzystoœci
    }

}