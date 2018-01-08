package edu.ncsu.csc316.hub_manager.data;

/**
 * This class represents an airport object 
 * @author Walker Booth (wgbooth)
 *
 */
public class Airport implements Comparable<Object> {

	private int id;
	private String airportCode;
	private double latitude;
	private double longitude;
	
	/**
	 * Constructor for an airport object
	 * @param id the numerical id of the airport 
	 * @param airportCode the alphabetical id of the airport 
	 * @param latitude the latitude of the airport
	 * @param longitude the longitude of the airport 
	 */
	public Airport(int id, String airportCode, double latitude, double longitude) {
		this.id = id;
		this.airportCode = airportCode;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	/**
	 * Returns the id of the airport
	 * @return id the id of the airport 
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Returns the airport code of the airport 
	 * @return airportCode the airport code of the airport 
	 */
	public String getAirportCode () {
		return airportCode;
	}
	
	/**
	 * Returns the latitude of the airport 
	 * @return latitude the latitude of the airport 
	 */
	public double getLatitude () {
		return latitude;
	}
	
	/**
	 * Returns the longitude of the airport 
	 * @return longitude the longitude of the airport 
	 */
	public double getLongitude () {
		return longitude;
	}

	@Override
	public int compareTo(Object o) {
		Airport a = (Airport)(o);
		return this.airportCode.compareTo(a.getAirportCode());
	}
	
}
