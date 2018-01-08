package edu.ncsu.csc316.airline_mileage.data;

import edu.ncsu.csc316.airline_mileage.list.ArrayList;

/**
 * This class contains data for Customer objects 
 * @author Walker Booth (wgbooth)
 *
 */
public class Customer implements Comparable<Object> {
	
	/** The list of flights taken by the customer */
	private ArrayList<Flight> flights;
	/** The list of miles on each airline used by the customer */
	private ArrayList<Miles> miles;
	/** The first name of the customer */
	private String firstName;
	/** The last name of the customer */
	private String lastName;
	
	/**
	 * Constructor for customer objects 
	 * @param firstName the first name of the customer
	 * @param lastName the last name of the customer 
	 */
	public Customer (String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		
		flights = new ArrayList<Flight> ();
		miles = new ArrayList<Miles> ();
		
	}
	
	/**
	 * Returns the first name of the customer 
	 * @return firstName the first name of the customer 
	 */
	public String getFirstName () {
		return firstName;
	}
	
	/**
	 * Returns the last name of the customer 
	 * @return lastName the last name of the customer 
	 */
	public String getLastName () {
		return lastName;
	}
	
	/**
	 * Adds a flight to the list of flights 
	 * @param flight the flight object to add to the list
	 */
	public void addFlight (Flight flight) {
		flights.add(flight);
	}
	
	/** 
	 * Adds an airline to total miles for to the list of miles
	 * @param mileCount the miles object to add to the list
	 */
	public void addMiles (Miles mileCount) {
		miles.add(mileCount);
	}
	
	/**
	 * Returns the list of flights for this customer
	 * @return flights the list of flights for this customer 
	 */
	public ArrayList<Flight> getFlightsForCustomer () {
		return flights;
	}
	
	/**
	 * Returns the list of mile counts for this customer
	 * @return miles the list of mile counts for this customer
	 */
	public ArrayList<Miles> getMilecountsForCustomer () {
		return miles;
	}

	@Override
	public int compareTo(Object o) {
		if (this.lastName.compareTo(((Customer) o).getLastName()) < 0) {
			return -1;
		} else if (this.lastName.compareTo(((Customer) o).getLastName()) > 0) {
			return 1;
		} else {
			if (this.firstName.compareTo(((Customer) o).getFirstName()) < 0) {
				return -1;
			} else if (this.firstName.compareTo(((Customer) o).getFirstName()) > 0) {
				return 1;
			} else {
				return 0;
			}
		}
	}

}
