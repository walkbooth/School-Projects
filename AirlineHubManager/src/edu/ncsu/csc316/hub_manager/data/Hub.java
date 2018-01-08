package edu.ncsu.csc316.hub_manager.data;

/**
 * Class for Hubs in the airport MST
 * @author Walker Booth (wgbooth)
 *
 */
public class Hub implements Comparable<Hub> {
	
	/** The number of connections this hub has */
	private int connections;
	/** The airport code of this hub */
	private String airportCode;
	
	/**
	 * Constructs a new Hub object. 
	 * @param connection the number of connections this hub has 
	 * @param airportCode the airport code of this Hub 
	 */
	public Hub (int connection, String airportCode) {
		this.connections = connection;
		this.airportCode = airportCode;
	}

	/**
	 * Returns the airport code of this hub 
	 * @return airportCode the aiportCode of this hub object 
	 */
	public String getAirportCode () {
		return airportCode;
	}
	
	/**
	 * Returns the number of connections this hub has.
	 * @return connections the number of connections this hub has
	 */
	public int getConnections() {
		return connections;
	}
	
	@Override
	public int compareTo(Hub o) {
		if (connections < o.getConnections()) {
			return 1;
		} else if (connections > o.getConnections()) {
			return -1;
		} else {
			return airportCode.compareTo(o.getAirportCode());
		}
	}

}
