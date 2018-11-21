package com.example.shdemo.service;

import java.util.List;

import com.example.shdemo.domain.Car;
import com.example.shdemo.domain.Movie;

public interface SellingManager {
	
	void addClient(Movie person);
	List<Movie> getAllClients();
	void deleteClient(Movie person);
	Movie findClientByPin(String pin);
	
	Long addNewCar(Car car);
	List<Car> getAvailableCars();
	void disposeCar(Movie person, Car car);
	Car findCarById(Long id);

	List<Car> getOwnedCars(Movie person);
	void sellCar(Long personId, Long carId);

}
