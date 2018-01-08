package edu.ncsu.csc316.hub_manager.useful;

/**
 * Gets the distance between two latitudes. Written by teaching staff. 
 * @author Jason King, Matt Stallman
 *
 */
public class DistanceUtility {

	private static final double R = 3961.0;
	
	/**
	 * Gets the distance between two coordinates
	 * @param latitude1 the first latitude
	 * @param longitude1 the first longitude
	 * @param latitude2 the second latitude
	 * @param longitude2 the second longitude
	 * @return the distance between each coordinate 
	 */
	public static double getDistance(double latitude1, double longitude1, double latitude2, double longitude2)
	{
		// Convert degrees lat/lon to radians
		double lat1 = Math.toRadians(latitude1);
		double lat2 = Math.toRadians(latitude2);
		double lon1 = Math.toRadians(longitude1);
		double lon2 = Math.toRadians(longitude2);
		
		// Apply Haversine formula
		double dlon = lon2 - lon1; 
		double dlat = lat2 - lat1;
		double a  = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
		double c  = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a)); // great circle distance in radians
		double d = R * c;
		return d;
	}
}