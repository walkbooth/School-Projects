/**
 * 
 */
package edu.ncsu.csc316.airline_mileage.data;

/**
 * This class contains the data on Flight objects 
 * @author Walker Booth (wgbooth)
 */
public class Flight implements Comparable<Object> {

	/** Airline code */
	private String iataCode;
	/** Number of the flight */
	private String flightNumber;
	/** Distance of the flight */
	private int distance;
	/** Origin of the flight */
	private String origin;
	/** Destination of the flight */
	private String destination;
	
	/**
	 * Constructor for a flight object 
	 * @param iataCode the airline code for the flight 
	 * @param flightNumber the flight number of a flight
	 * @param distance the distance traveled in the flight 
	 * @param origin the origin of the flight
	 * @param destination the destination of the flight 
	 */
	public Flight (String iataCode, String flightNumber, int distance, String origin, String destination) {
		this.iataCode = iataCode;
		this.flightNumber = flightNumber;
		this.distance = distance;
		this.origin = origin;
		this.destination = destination;
		
	}
	
	/**
	 * Returns the flightNumber of the flight
	 * @return flightNumber the flightNumber of the flight 
	 */
	public String getFlightNumber () {
		return flightNumber;
	}
	
	/**
	 * Returns the distance traveled on the flight
	 * @return distance the distance traveled on the flight 
	 */
	public int getDistance () {
		return distance;
	}
	
	/**
	 * Sets the value of the distance traveled on the flight
	 * @param distance the new distance to set
	 */
	public void setDistance (int distance) {
		this.distance = distance;
	}

	/**
	 * Gets the origin of the flight 
	 * @return the origin
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * Gets the destination of the flight
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * Gets the airline code of the flight 
	 * @return the iataCode
	 */
	public String getIataCode() {
		return iataCode;
	}

	@Override
	public int compareTo(Object o) {
		if (this.iataCode.compareTo(((Flight) o).getIataCode()) < 0) {
			return -1;
		} else if (this.iataCode.compareTo(((Flight) o).getIataCode()) > 0) {
			return 1;
		} else {
			if (this.flightNumber.compareTo(((Flight) o).getFlightNumber()) < 0) {
				return -1;
			} else if (this.flightNumber.compareTo(((Flight) o).getFlightNumber()) > 0) {
				return 1;
			} else {
				if (this.origin.compareTo(((Flight) o).getOrigin()) < 0) {
					return -1;
				} else if (this.origin.compareTo(((Flight) o).getOrigin()) > 0) {
					return 1;
				} else {
					if (this.destination.compareTo(((Flight) o).getDestination()) < 0) {
						return -1;
					} else if (this.destination.compareTo(((Flight) o).getDestination()) > 0) {
						return 1;
					} else {
						return 0;
					}
				}
			}
		}
	}
}
