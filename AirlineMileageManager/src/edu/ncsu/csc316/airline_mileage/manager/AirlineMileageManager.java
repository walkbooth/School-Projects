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
import edu.ncsu.csc316.airline_mileage.list.ArrayList;

/**
 * This program outputs the mileage of 
 * @author Walker Booth (wgbooth)
 */
public class AirlineMileageManager {
		
	/** The list of all customers on file */
	private ArrayList<Customer> customers;
	/** The list of all airlines on file */
	private ArrayList<Airline> airlines;
	/** The list of all flights on file */
	private ArrayList<Flight> flights;
	
	/**
	 * Constructs an AirlineMileageManager
	 * @param pathToAirlineFile - path to the airline information file
	 * @param pathToCustomerFile - path to the customer information file
	 * @param pathToFlightFile - path to the flight information file
	 * @throws FileNotFoundException 
	 */
	public AirlineMileageManager (String pathToAirlineFile, String pathToCustomerFile, String pathToFlightFile) { 
		
		try {
			airlines = AirlineReader.readAirlineRecords(pathToAirlineFile);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException ();
		}
		try {
			flights = FlightReader.readFlightRecords(pathToFlightFile);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException ();
		}

		flights.sort();
		try {
			customers = CustomerReader.readCustomerRecords(pathToCustomerFile, flights);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException ();
		}
		customers.sort();
		
		for (int i = 0; i < airlines.size(); i++) {
			Airline airline = airlines.get(i);
		
			for (int j = 0; j < customers.size(); j++) {
				Customer customer = customers.get(j);
				customer.addMiles(new Miles(airline));

				for (int k = 0; k < customer.getFlightsForCustomer().size(); k++) {
					Flight flight = customer.getFlightsForCustomer().get(k);				//totals miles by airline for each customer 
					if (flight.getIataCode().equals(airline.getIataCode())) {
						customer.getMilecountsForCustomer().get(i).addMiles(flight.getDistance());
					}
				}
			}
		}
				
	}
	
	/**
	 * Produces the mileage report for all cardholders
	 * as a String, sorted alphabetically by cardholder
	 * last name.
	 * 
	 * @return the mileage report for all customers
	 */
	public String getMileageReport () {
		String mileageReport = "";
		for (int i = 0; i < customers.size(); i++) {
			mileageReport = mileageReport.concat(getMiles(customers.get(i).getFirstName(), customers.get(i).getLastName()));
			if (i != customers.size() - 1) {
				mileageReport = mileageReport.concat("\n\n");
			}
			
		}
		
		return mileageReport;
		
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
		String mileageReport = "";
		Customer customer = new Customer(firstName, lastName);
		int index = customers.indexOf(customer);
		boolean hasMiles = false;
		
		if (index != -1) {
			customer = customers.get(index);
			customer.getMilecountsForCustomer().sort();
		} 
		
		mileageReport = mileageReport.concat(customer.getFirstName().concat(" "));
		mileageReport = mileageReport.concat(customer.getLastName().concat(" earned"));
		
		for (int i = 0; i < customer.getMilecountsForCustomer().size(); i++) {
			if (customer.getMilecountsForCustomer().get(i).getTotalMiles() != 0) {
				hasMiles = true;
			}
		}
		if (hasMiles) {
			for (int i = customer.getMilecountsForCustomer().size() - 1; i >= 0 ; i--) {
				Miles miles = customer.getMilecountsForCustomer().get(i);										//Creates the mileage report
				if (miles.getTotalMiles() != 0) {
					mileageReport = mileageReport.concat("\n    ");
					mileageReport = mileageReport.concat((miles.getTotalMiles()) + " miles with ");
					mileageReport = mileageReport.concat(miles.getAirline().concat(" (".concat(miles.getIataCode().concat(")"))));
					
				}
			}
		} else {
			mileageReport = mileageReport.concat("\n    no miles.");
		}
		
		return mileageReport;		
	}
	

}
