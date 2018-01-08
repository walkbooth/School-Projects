package edu.ncsu.csc316.hub_manager.manager;

import edu.ncsu.csc316.hub_manager.data.Hub;
import edu.ncsu.csc316.hub_manager.useful.AirlineReader;
import edu.ncsu.csc316.hub_manager.util.AdjacencyMatrix;
import edu.ncsu.csc316.hub_manager.util.MinHeap;
import edu.ncsu.csc316.hub_manager.util.Vertex;

/**
 * This class serves as the controller component of the MVC design pattern in this project. 
 * @author Walker Booth (wgbooth)
 *
 */
public class AirlineHubManager {

	private AdjacencyMatrix allConnections;
	private AdjacencyMatrix minFlights;
	
	/**
	 * Constructs a new AirlineHubManager
	 * 
	 * @param pathToFile the path to the file that contains the airports
	 */
	public AirlineHubManager(String pathToFile)
	{
	    allConnections = AirlineReader.readAirportsFile(pathToFile);
		minFlights = allConnections.getMinSpanningTree();
	}
	
	/**
	 * Returns a string representation of the list of Flights contained in the 
	 * minimum spanning set of flights that connect all airports. The returned string is in
	 * the following format, where the flights are sorted in increasing order
	 * by distance. If multiple flights have the same distance, order by airport code
	 * in ascending alphabetical order.
	 *  - Distance should be displayed to 1 decimal place
	 *  - Each flight line is indented by 3 spaces
	 * 
	 * FlightList[
	 *    Flight[airport1=ORH, airport2=RDU, distance=576.4],
	 *    Flight[airport1=SEA, airport2=SFO, distance=679.6],
	 *    Flight[airport1=MIA, airport2=RDU, distance=702.8],
	 *    Flight[airport1=DFW, airport2=RDU, distance=1059.7],
	 *    Flight[airport1=DFW, airport2=SFO, distance=1462.3]
	 * ]
	 * 
	 * @return a string representation of the minimum spanning set of flights
	 */
	public String getMinimumFlights()
	{
		StringBuilder s = new StringBuilder();
		s.append("FlightList[\n");
		if (minFlights.edges().size() == 0) {
			s.append("      No flights connect the provided airports.\n]");
		}
		for (int i = 0; i < minFlights.edges().size(); i++) {
			double toTenthsPlace = Math.floor((minFlights.edges().get(i).getWeight()) * 10 + .5) / 10;
			s.append("   Flight[airport1=");
			s.append(minFlights.edges().get(i).getSrc().getData().getAirportCode());
			s.append(", airport2=");
			s.append(minFlights.edges().get(i).getDest().getData().getAirportCode());
			s.append(", distance=");
			s.append(toTenthsPlace);
			if (i == minFlights.edges().size() - 1) {
				s.append("]\n]");
			} else {
				s.append("],\n");
			}
		}
		return s.toString();
	}
	
	/**
	 * Returns the list of possible airport hubs (airports with at least 
	 * 3 connecting flights in the minimum spanning set of flights).
	 * The list should be output in the following format, where the
	 * airports are listed in descending order by number of connecting flights. 
	 * If multiple airports have the same number of connecting flights, order
	 * the airports alphabetically by airport code.
	 *  - Each airport line is indented by 3 spaces
	 *  
	 * HubReport[
	 *   RDU has 3 connections.
	 * ]
	 * 
	 * @return the string representation of the list of possible airport hubs
	 */
	public String getPossibleHubs()
	{
		MinHeap<Hub> hubs = new MinHeap<Hub>();
		for (int i = 0; i < minFlights.vertices().length; i++ ) {
			Vertex v = minFlights.vertices()[i];
			if (minFlights.incidentEdges(v).size() >= 3) {
				hubs.insert(new Hub(minFlights.incidentEdges(v).size(), v.getData().getAirportCode()));
			}
		}
		
		StringBuilder s = new StringBuilder();
		s.append("HubReport[\n");
		if (hubs.size() == 0) {
			s.append("   No airports have at least 3 connecting flights.\n]");
		}
		while (hubs.size() > 0) {
			Hub h = hubs.removeMin();
			s.append("   ");
			s.append(h.getAirportCode());
			s.append(" has ");
			s.append(h.getConnections());
			s.append(" connections.\n");
			if (hubs.size() == 0) {
				s.append("]");
			}
		}
		
		return s.toString();
	}
}
