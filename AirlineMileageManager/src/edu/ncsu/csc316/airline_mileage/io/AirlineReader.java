package edu.ncsu.csc316.airline_mileage.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc316.airline_mileage.data.Airline;
import edu.ncsu.csc316.airline_mileage.list.ArrayList;

/**
 * This class contains static methods which allow for the reading of an Airline file
 * @author Walker Booth (wgbooth)
 *
 */
public class AirlineReader {

	/**
	 * This class should not be constructed. 
	 */
	public AirlineReader () {
		throw new IllegalArgumentException ("This class should not be instantiated."); 
	}
	
	/**
	 * Reads a file with Airline data 
	 * @param file the name of the Airline file 
	 * @return a list of airline data 
	 * @throws FileNotFoundException if the provided file is not found 
	 */
	public static ArrayList<Airline> readAirlineRecords(String file) throws FileNotFoundException {
		
		Scanner fileReader = new Scanner(new File(file), "UTF8");
		ArrayList<Airline> airlines = new ArrayList<Airline> ();
		fileReader.nextLine(); //skips the header line 
		while (fileReader.hasNextLine()) {
			airlines.add(readLine(fileReader.nextLine()));
		}
		fileReader.close();
		return airlines;
		
	}

	/**
	 * Parses a single line for Airline data
	 * @param nextLine the line to parse
	 * @return airline the newly constructed Airline object
	 */
	private static Airline readLine(String nextLine) {
		Scanner lineScan = new Scanner(nextLine);
		lineScan.useDelimiter(",");
		String name = lineScan.next();
		String iataCode = lineScan.next();
		Airline airline = new Airline(name, iataCode);
		lineScan.close();
		return airline;
	}
	
}
