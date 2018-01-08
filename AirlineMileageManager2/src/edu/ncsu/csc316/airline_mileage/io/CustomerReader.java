package edu.ncsu.csc316.airline_mileage.io;

import java.io.File;

import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc316.airline_mileage.data.Customer;
import edu.ncsu.csc316.airline_mileage.data.Flight;
import edu.ncsu.csc316.airline_mileage.util.ArrayList;
import edu.ncsu.csc316.airline_mileage.util.HashTable;

/**
 * This class contains static methods which allow for the reading of customer data files. (From Project 1)
 * @author Walker Booth (wgbooth)
 *
 */
public class CustomerReader {
	
	/**
	 * This class should not be constructed. 
	 */
	public CustomerReader () {
		throw new IllegalArgumentException ("This class should not be instantiated."); 
	}
	
	/**
	 * Reads a file with Airline data 
	 * @param file the name of the Airline file 
	 * @param flights the list of flights 
	 * @return a list of airline data 
	 * @throws FileNotFoundException if the provided file is not found 
	 */
	public static ArrayList<Customer> readCustomerRecords(String file, HashTable<Flight> flights) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new File(file), "UTF8");
		ArrayList<Customer> customers = new ArrayList<Customer> ();
		boolean duplicate = false;
		fileReader.nextLine(); 													//skips the header line 
		while (fileReader.hasNextLine()) {
			
			String line = fileReader.nextLine();
			Customer customer = readForCustomer(line);
			Flight flight = readForFlight(line);
			flight.setDistance(flights.get(flight).getDistance());
			
			for (int i = 0; i < customers.size(); i++) {
				if (customer.getFirstName().equals(customers.get(i).getFirstName()) && 			//checking for already existing customer 
					customer.getLastName().equals(customers.get(i).getLastName() )) {
					duplicate = true;
					customers.get(i).addFlight(flight);
					
				}
			}		
			
			if (!duplicate) {									//customer is only added if the customer doesn't already exist on file
				customer.addFlight(flight);
				customers.add(customer);
			}
			
			duplicate = false;
			
		}
		
		fileReader.close();
		return customers;
		
	}

	/**
	 * Parses a single line for data to construct a flight object
	 * @param line the line parsed for flight data
	 * @return flight the new Flight object 
	 */
	private static Flight readForFlight(String line) {
		Scanner lineScan = new Scanner (line);
		lineScan.useDelimiter(",");
		lineScan.next();
		lineScan.next();											//skips three tokens
		lineScan.next();
		String flightCode = lineScan.next();
		String origin = lineScan.next();
		String destination = lineScan.next();
		lineScan.close();
		return new Flight (flightCode.substring(0, 2), flightCode.substring(2), 0, origin, destination);
	}

	/**
	 * Parses a single line for data to construct a Customer object 
	 * @param nextLine the line parsed for Customer data
	 * @return customer the new Customer object 
	 */
	private static Customer readForCustomer(String nextLine) {
		Scanner lineScan = new Scanner(nextLine);
		lineScan.useDelimiter(",");
		String firstName = lineScan.next();
		String lastName = lineScan.next();
		lineScan.close();
		return new Customer(firstName, lastName);
	}

}
