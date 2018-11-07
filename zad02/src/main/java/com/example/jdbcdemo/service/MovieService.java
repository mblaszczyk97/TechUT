package com.example.jdbcdemo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MovieService implements MovieManager{

	private Connection connection;
	

	private String url = "jdbc:hsqldb:hsql://localhost/workdb";

	private String createTableMovie = "CREATE TABLE Movie(id bigint GENERATED BY DEFAULT AS IDENTITY," +
            " name varchar(20) UNIQUE, dataWydania DATETIME, typ varchar(20))";


	private PreparedStatement deleteAllMoviesStmt;
	private PreparedStatement addMovieStmt;
    private PreparedStatement getAllMoviesStmt;
    private PreparedStatement deleteMovieByIdStmt;
    private PreparedStatement getMovieNameStmt;

	private Statement statement;

	public MovieService() {
		try {
			connection = DriverManager.getConnection(url);
			statement = connection.createStatement();

			ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
			boolean tableExists = false;
			while (rs.next()) {
				if ("Movie".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					tableExists = true;
					break;
				}
			}

			if (!tableExists)
				statement.executeUpdate(createTableMovie);
				addMovieStmt = connection.prepareStatement("INSERT INTO Movie (name, dataWydania, typ) VALUES (?, ?, ?)");
				deleteAllMoviesStmt = connection.prepareStatement("DELETE FROM Movie");
				getAllMoviesStmt = connection.prepareStatement("SELECT *  FROM Movie");
	            deleteMovieByIdStmt = connection.prepareStatement("DELETE FROM Movie WHERE name=?");
	            getMovieNameStmt = connection.prepareStatement("SELECT *  FROM Movie WHERE name=?");

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	Connection getConnection() {
		return connection;
	}

	@Override
	public int addMovie(Movie movie) {
		int count = 0;
        try {
            addMovieStmt.setString(1, movie.getName());
            addMovieStmt.setDate(2, movie.getDataWydania());
            addMovieStmt.setString(3, movie.getTyp());
            count = addMovieStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
   	}	
	
	@Override
	public void deleteAllMovies() {
        try {
            deleteAllMoviesStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
}


	@Override
	public void addAllMovies(List<Movie> movies) 
	{
		try
		{
			
            connection.setAutoCommit(false);
            
            for (Movie movie : movies) 
            {
                addMovieStmt.setString(1, movie.getName());
                addMovieStmt.setDate(2, movie.getDataWydania());
                addMovieStmt.setString(3, movie.getTyp());

                addMovieStmt.executeUpdate();
            }
            
            connection.commit();

        }catch (SQLException exception)
		{

            try 
            {
            	
                connection.rollback();
                
            }catch (SQLException e) 
            {
            	
                e.printStackTrace();
                
            }
        }
		
	}

	@Override
	public List<Movie> getAllMovies() 
	{
		List<Movie> movies = new ArrayList<>();

        try {
            ResultSet rs = getAllMoviesStmt.executeQuery();

            while (rs.next()) {
                Movie mv = new Movie();

                mv.setName(rs.getString("name"));
                mv.setDataWydania(rs.getDate("dataWydania"));
                mv.setTyp(rs.getString("typ"));

                movies.add(mv);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
	}

	@Override
	public Movie getMovieByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteMovieByName(String name) {
		// TODO Auto-generated method stub
		
	}
	
	Boolean tableExists() {
        boolean tableExists = false;
        try {
            ResultSet rs = connection.getMetaData().getTables(null, null, null, null);

            while (rs.next()) {
                if ("BoardGame".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
                    tableExists = true;
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableExists;
    }

}
