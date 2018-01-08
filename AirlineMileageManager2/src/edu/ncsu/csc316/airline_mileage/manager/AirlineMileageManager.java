/**
 * 
 */
package edu.ncsu.csc316.airline_mileage.manager;

import java.io.FileNotFoundException;


import edu.ncsu.csc316.airline_mileage.data.Airline;
import edu.ncsu.csc316.airline_mileage.data.Customer;
import edu.ncsu.csc316.airline_mileage.data.Flight;
import edu.ncsu.csc316.airline_mileage.data.Miles;
import edu.ncsu.csc316.airline_mileage.io.AirlineReader;
import edu.ncsu.csc316.airline_mileage.io.CustomerReader;
import edu.ncsu.csc316.airline_mileage.io.FlightReader;
import edu.ncsu.csc316.airline_mileage.util.ArrayList;
import edu.ncsu.csc316.airline_mileage.util.HashTable;

/**
 * This program serves as the controller in the MVC design pattern I am using. (Modified, but derived from Project 1)
 * @author Walker Booth (wgbooth)
 */
public class AirlineMileageManager {
		
	/** The hash table of all customers on file */
	private HashTable<Customer> customers;
	/** The list of all airlines on file */
	private ArrayList<Airline> airlines;
	/** The hash table of all flights on file */
	private HashTable<Flight> flights;
	
	private ArrayList<Customer> listCustomers;
	
	private ArrayList<Flight> listFlights;
	
	/**
	 * Constructs an AirlineMileageManager
	 * @param pathToAirlineFile - path to the airline information file
	 * @param pathToCustomerFile - path to the customer information file
	 * @param pathToFlightFile - path to the flight information file
	 * @throws IllegalArgumentException if the filename is invalid 
	 */
	public AirlineMileageManager (String pathToAirlineFile, String pathToCustomerFile, String pathToFlightFile) { 
		
		try {
			airlines = AirlineReader.readAirlineRecords(pathToAirlineFile);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException ();
		}
		
		try {
			listFlights = FlightReader.readFlightRecords(pathToFlightFile);
			flights = new HashTable <Flight> (listFlights.size());
			for (int i = 0; i < listFlights.size(); i++) {												//Read in flights and add to hash table
				flights.add(listFlights.get(i));
			}
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException ();
		}

		try {
			listCustomers = CustomerReader.readCustomerRecords(pathToCustomerFile, flights);
			customers = new HashTable <Customer> (listCustomers.size());
			for (int i = 0; i < listCustomers.size(); i++) {												//Read in customers and add to hash table
				customers.add(listCustomers.get(i));
			}
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException ();
		}

		/*
		for (int i = 0; i < airlines.size(); i++) {
			Airline airline = airlines.get(i);
		
			for (int j = 0; j < customers.size(); j++) {
				Customer customer = listCustomers.get(j);
				customer.addMiles(new Miles(airline));

				for (int k = 0; k < customer.getFlightsForCustomer().size(); k++) {
					Flight flight = customer.getFlightsForCustomer().get(k);				//totals miles by airline for each customer 
					if (flight.getIataCode().equals(airline.getIataCode())) {
						customer.getMilecountsForCustomer().get(i).addMiles(flight.getDistance());
					}
				}
			}
		}
		*/
		listCustomers.sort();	
	}
	
	/**
	 * Produces the mileage report for all cardholders
	 * as a String, sorted alphabetically by cardholder
	 * last name.
	 * 
	 * @return the mileage report for all customers
	 */
	public String getMileageReport () {
		StringBuilder mileageReport = new StringBuilder();
		for (int i = 0; i < customers.size(); i++) {
			Customer customer = listCustomers.get(i);
			mileageReport.append(getMiles(customer.getFirstName(), customer.getLastName()));
			if (i != customers.size() - 1) {
				mileageReport.append("\n\n");
			}
			
		}
		
		return mileageReport.toString();
		
	}
	
	/**
	 * Retrieves the frequent flier mileage for the specified
	 * cardholder with the given first name and last name.
	 * Miles are listed in descending order by distance
	 * 
	 * @param firstName - the cardholder's first name
	 * @param lastName - the cardholder's last name
	 * @return the frequent flier mileage information for the cardholder
	 */
	public String getMiles(String firstName, String lastName) throws IllegalArgumentException {
		StringBuilder mileageReport = new StringBuilder();
		Customer customer = new Customer(firstName, lastName);
		Customer foundCustomer = customers.get(customer);
		
		if (foundCustomer != null) {
			customer = foundCustomer;
			customer.getMilecountsForCustomer().sort();
		} 
		
		mileageReport.append(customer.getFirstName());
		mileageReport.append(" ");
		mileageReport = mileageReport.append(customer.getLastName());
		mileageReport.append(" earned");		
		
		/** Adds a milecounter for each airline */
		for (int i = 0; i < airlines.size(); i++) {
			Miles m = new Miles(airlines.get(i));
			customer.addMiles(m);
		}
		
		/** Adds mileage values to all milecounters */
		for (int i = 0; i < customer.getFlightsForCustomer().size(); i++) {
			Flight flight = customer.getFlightsForCustomer().get(i);		
			/** Linear search for airline matching flight, add to milecounter of that airline */
			for (int j = 0; j < customer.getMilecountsForCustomer().size(); j++) {
				if (flight.getIataCode().compareTo(customer.getMilecountsForCustomer().get(j).getIataCode()) == 0) {
					customer.getMilecountsForCustomer().get(j).addMiles(flight.getDistance());
				}
			}
		}
		
		customer.getMilecountsForCustomer().sort();
		
		if (customer.hasMiles()) {
			for (int i = customer.getMilecountsForCustomer().size() - 1; i >= 0 ; i--) {
				Miles miles = customer.getMilecountsForCustomer().get(i);										//Creates the mileage report
				if (miles.getTotalMiles() != 0) {
					mileageReport.append("\n    ");
					mileageReport.append(miles.getTotalMiles());
					mileageReport.append(" miles with ");
					mileageReport.append(miles.getAirline());
					mileageReport.append(" (");
					mileageReport.append(miles.getIataCode());
					mileageReport.append(")");
					
				}
			}
		} else {
			mileageReport.append("\n    no miles.");
		}
		
		return mileageReport.toString();		
	}
	

}
