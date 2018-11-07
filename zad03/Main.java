package com.example.jdbcdemo.service;
import java.sql.SQLException;
import java.sql.Date;

public class Main {

    public static void main(String[] args) throws SQLException {

        MovieService movieService = new MovieService();
		Date data1 = new Date(2323223232L);
        Movie filmJeden = new Movie("Star Wars",data1,"science-fiction");
        movieService.addMovie(filmJeden);

    }


}