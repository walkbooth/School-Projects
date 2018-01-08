package edu.ncsu.csc316.airline_mileage.ui;

import java.util.Scanner;

import edu.ncsu.csc316.airline_mileage.manager.AirlineMileageManager;

/**
 * UI Program for AirlineMileageManager
 * @author Walker Booth (wgbooth)
 *
 */
public class AirlineMileageManagerUI {
	
	/**
	 * The main method which serves as the view for this program.
	 * @param args command line arguments
	 */
	public static void main (String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Please enter the airline data file: ");
		String airlineDataFile = input.nextLine();
		System.out.print("Please enter the customer data file: ");
		String customerDataFile = input.nextLine();
		System.out.print("Please enter the flight data file: ");
		String flightDataFile = input.nextLine();
		
		AirlineMileageManager manager = null;
		
		try {
			manager = new AirlineMileageManager(airlineDataFile, customerDataFile, flightDataFile);
		} catch (IllegalArgumentException e) {
			System.out.println("One of your input files does not exist. Please try again.");
			input.close();
			System.exit(1);
		}
		
		System.out.println("Now, type either generateCardHolderReport followed by the first and last name of a customer to print mileage data for that person \n" +
						   "or type generateMileageReport to print out all data on file for all customers.");
		
		String operation = input.nextLine();
		Scanner lineScan = new Scanner(operation);
		String nextOperation = lineScan.next();
		if (nextOperation.equals("generateCardHolderReport")) {
			String firstName = lineScan.next();
			String lastName = lineScan.next();
			if (firstName.contains("#") || lastName.contains("#")) {
				System.out.println("Illegal Characters Detected, please do not use numbers or symbols");
				lineScan.close();
				input.close();
				System.exit(1);
			}
			System.out.println(manager.getMiles(firstName, lastName));
		} else if (nextOperation.equals("generateMileageReport")) {
			System.out.println(manager.getMileageReport());
		} else {
			System.out.println("That input was not valid. Please try again later.");
			lineScan.close();
			input.close();
			System.exit(1);
		}
		lineScan.close();	
		input.close();
	}

}
