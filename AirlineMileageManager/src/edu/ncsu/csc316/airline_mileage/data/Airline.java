package edu.ncsu.csc316.airline_mileage.data;

/**
 * This class stores Airline data
 * @author Walker Booth (wgbooth)
 *
 */
public class Airline {

	/** The name of the airline */
	private String airline;
	/** The IATA Code of the airline */
	private String iataCode;
	
	/**
	 * Constructor for Airline objects 
	 * @param airline the name of the airline 
	 * @param iataCode the iataCode of the airline 
	 */
	public Airline (String airline, String iataCode) {
		this.airline = airline;
		this.iataCode = iataCode;
	}
	
	/**
	 * Returns the name of the airline 
	 * @return airline the name of the airline 
	 */
	public String getAirline () {
		return airline;
	}
	
	/**
	 * Returns the iata code of the airline 
	 * @return iataCode the iataCode of the airline 
	 */
	public String getIataCode () {
		return iataCode;
	}
	
}
