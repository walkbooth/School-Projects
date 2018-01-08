package edu.ncsu.csc316.airline_mileage.data;

/**
 * Stores the amount of miles flown on a given airline for the customer (From Project 1)
 * @author Walker Booth (wgbooth)
 *
 */
public class Miles implements Comparable<Object> {
	
	private int totalMiles;
	private String airline;
	private String iataCode;
	
	/**
	 * Constructor for a Miles object
	 * @param airline the airline the miles are collected with
	 */
	public Miles (Airline airline) {
		this.totalMiles = 0;
		this.airline = airline.getAirline();
		this.iataCode = airline.getIataCode();
		
	}
	
	/**
	 * Returns the name of the airline miles are collected with
	 * @return airline the name of the airline 
	 */
	public String getAirline () {
		return airline;
	}
	
	/**
	 * Returns the total miles accrued on the airline
	 * @return totalMiles the amount of miles accrued with an airline
	 */
	public int getTotalMiles () {
		return totalMiles;
	}
	
	/**
	 * Adds more miles to the total mile count with an airline
	 * @param miles the miles to add to an airline's mile count 
	 */
	public void addMiles (int miles) {
		totalMiles += miles;
	}

	/**
	 * Returns the IATA_CODE of the airline
	 * @return the iataCode
	 */
	public String getIataCode() {
		return iataCode;
	}

	@Override
	public int compareTo(Object o) {
		Miles other = (Miles) o;
		if (this.totalMiles < other.getTotalMiles()) {
			return -1;
		} else if (this.totalMiles > other.getTotalMiles()) {
			return 1;
		} else {
			if (this.iataCode.compareTo(other.iataCode) < 0) {
				return 1;
			} else if (this.iataCode.compareTo(other.iataCode) > 0) {
				return -1;
			} else {
				return 0;
			}
		}
	}
}
