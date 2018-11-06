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

	private String createTableMovie = "CREATE TABLE Movie(id bigint GENERATED BY DEFAULT AS IDENTITY, name varchar(20) UNIQUE, dataWydania date, typ varchar(20)";
	

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

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	Connection getConnection() {
		return connection;
	}


	public void addMovie(Movie movie) throws SQLException {
        String addMovieSql = "INSERT INTO Movie (name ,dataWydania ,typ) VALUES " +
                "(\'" + movie.getName() + "\' ,\'" + movie.getDataWydania() + "\' ," + dog.getTyp() + "\' ")";
        statement.executeUpdate(addMovieSql);
   	}	



}
