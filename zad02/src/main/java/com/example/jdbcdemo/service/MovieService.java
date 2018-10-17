package com.example.jdbcdemo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.jdbcdemo.domain.Movie;

public class MovieService implements MovieManager {

	private Connection connection;
	

	private String url = "jdbc:hsqldb:hsql://localhost/workdb";

	private String createTableMovie = "CREATE TABLE Movie(id bigint GENERATED BY DEFAULT AS IDENTITY, name varchar(20) UNIQUE, yob integer, dataWydania date, typ varchar(20)";
	private String createTableDirector = "CREATE TABLE Director(id bigint GENERATED BY DEFAULT AS IDENTITY, name varchar(20), surname varchar(20), yob integer)";
	
	
	private PreparedStatement addMovieStmt;
	private PreparedStatement deleteAllMoviesStmt;
	private PreparedStatement getAllMoviesStmt;

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

			addMovieStmt = connection.prepareStatement("INSERT INTO Movie (name, yob, dataWydania, typ) VALUES (?, ?, ?, ?)");
			deleteAllMoviesStmt = connection.prepareStatement("DELETE FROM Movie");
			getAllMoviesStmt = connection.prepareStatement("SELECT id, name, yob, dataWydania, typ FROM Movie");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	Connection getConnection() {
		return connection;
	}

	void clearPersons() {
		try {
			deleteAllMoviesStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int addMovie(Movie movie) {
		int count = 0;
		try {
			addMovieStmt.setString(1, movie.getName());
			addMovieStmt.setInt(2, movie.getYob());

			count = addMovieStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<Movie> getAllMovies() {
		List<Movie> persons = new ArrayList<Movie>();

		try {
			ResultSet rs = getAllMoviesStmt.executeQuery();

			while (rs.next()) {
				Movie p = new Movie();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setYob(rs.getInt("yob"));
				persons.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return persons;
	}

	@Override
	public void addAllMovie(List<Movie> persons) {

		try {
			connection.setAutoCommit(false);
			for (Movie person : persons) {
				addMovieStmt.setString(1, person.getName());
				addMovieStmt.setInt(2, person.getYob());
				addMovieStmt.executeUpdate();
			}
			connection.commit();
			
		} catch (SQLException exception) {
			
			try {
				connection.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
				//!!!! ALARM
			}
		}

	}

	public static void main() {
		
		
	}

}
