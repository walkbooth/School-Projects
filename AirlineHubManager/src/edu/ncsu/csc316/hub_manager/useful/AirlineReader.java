package edu.ncsu.csc316.hub_manager.useful;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc316.hub_manager.data.Airport;
import edu.ncsu.csc316.hub_manager.util.AdjacencyMatrix;
import edu.ncsu.csc316.hub_manager.util.ArrayList;
import edu.ncsu.csc316.hub_manager.util.Vertex;

/**
 * Reads in an Adjacency Matrix of Airports.
 * @author Walker Booth(wgbooth)
 *
 */
public class AirlineReader {

	/**
	 * Builds an Adjacency Matrix using the airport data from a file.
	 * @param filename the name of the file to read from 
	 * @return m the adjacency matrix created from the input file 
	 */
	public static AdjacencyMatrix readAirportsFile(String filename) {
		ArrayList<Airport> airports = new ArrayList<Airport> ();
		Scanner fileReader;
		try {
			fileReader = new Scanner(new File(filename));
			fileReader.nextLine();									//skips the first header line
			int lineNumber = 0;
			while(fileReader.hasNextLine()) {
				String line = fileReader.nextLine();
				Airport a = readLine(line, lineNumber);
				lineNumber++;
				airports.add(a);
			}
			fileReader.close();
			
			AdjacencyMatrix m = new AdjacencyMatrix(airports.size());
			
			for (int i = 0; i < airports.size(); i++) {
				for (int j = i + 1; j < airports.size(); j++) {
					Vertex a = new Vertex(airports.get(i));
					Vertex b = new Vertex(airports.get(j));
					m.insertEdge(a, b, DistanceUtility.getDistance(a.getData().getLatitude(), a.getData().getLongitude(), b.getData().getLatitude(), b.getData().getLongitude()));
				}
			}
			return m;
			
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Invalid filename.");
		}
		
		
	}

	private static Airport readLine(String line, int lineNumber) {
		Scanner lineScanner = new Scanner(line);
		lineScanner.useDelimiter(",");
		String name = lineScanner.next();
		double latitude = lineScanner.nextDouble();
		double longitude = lineScanner.nextDouble();
		lineScanner.close();
		return new Airport(lineNumber, name, latitude, longitude);		
	}
	
	
}
