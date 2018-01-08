package edu.ncsu.csc316.airline_mileage.io;

import java.io.File;

import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc316.airline_mileage.data.Flight;
import edu.ncsu.csc316.airline_mileage.list.ArrayList;

/**
 * This class contains static methods which read a flight data file
 * @author Walker Booth (wgbooth)
 *
 */
public class FlightReader {

	/**
	 * This class should not be constructed. 
	 */
	public FlightReader () {
		throw new IllegalArgumentException ("This class should not be instantiated."); 
	}
	
	/**
	 * Reads a file with Flight data 
	 * @param file the name of the Airline file 
	 * @return a list of airline data 
	 * @throws FileNotFoundException if the provided file is not found 
	 */
	public static ArrayList<Flight> readFlightRecords(String file) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new File(file), "UTF8");
		ArrayList<Flight> flights = new ArrayList<Flight> ();
		fileReader.nextLine(); //skips the header line 
		while (fileReader.hasNextLine()) {
			
			String line = fileReader.nextLine();
			Flight flight = readForFlight(line);
			flights.add(flight);
			
		}
		
		fileReader.close();
		return flights;
		
	}

	/**
	 * Parses a single line to create a flight object
	 * @param line the line supplied by the input file
	 * @return flight the new flight created using the line of data 
	 */
	private static Flight readForFlight(String line) {
		Scanner lineScan = new Scanner (line);
		lineScan.useDelimiter(",");
		lineScan.next();
		lineScan.next();
		lineScan.next();
		lineScan.next();
		String iataCode = lineScan.next();
		String flightNumber = lineScan.next();			//concatenates next two tokens to make flightNumber
		String origin = lineScan.next();
		String destination = lineScan.next();
		lineScan.next();
		lineScan.next();
		int distance = lineScan.nextInt();
		lineScan.close();
		return new Flight (iataCode, flightNumber, distance, origin, destination);
	}
	
}
