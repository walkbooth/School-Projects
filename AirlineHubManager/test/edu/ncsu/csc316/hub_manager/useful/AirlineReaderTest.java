package edu.ncsu.csc316.hub_manager.useful;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.hub_manager.data.Airport;
import edu.ncsu.csc316.hub_manager.util.AdjacencyMatrix;
import edu.ncsu.csc316.hub_manager.util.Edge;
import edu.ncsu.csc316.hub_manager.util.Vertex;

/**
 * Tests the AirlineReader class with a test file derived from the "Example Input File" provided in the Project 3 
 * software requirements.
 * @author Walker Booth (wgbooth)
 *
 */
public class AirlineReaderTest {

	/**
	 * Tests the readFile method's correctness
	 */
	@SuppressWarnings("unused")
	@Test
	public void testReadFile() {
		
		AirlineReader a = new AirlineReader();
		a.getClass();
		
		Airport a1 = new Airport(0, "DFW", 32.89680099487305, -97.03800201416016);
		Airport a2 = new Airport(1, "MIA", 25.79319953918457, -80.29060363769531);
		Airport a3 = new Airport(2, "ORH", 42.26729965209961, -71.87570190429688);
		Airport a4 = new Airport(3, "RDU", 35.877601623535156, -78.7874984741211);
		Airport a5 = new Airport(4, "SEA", 47.44900131225586, -122.30899810791016);
		Airport a6 = new Airport(5, "SFO", 37.61899948120117, -122.375);
		
		Vertex v1 = new Vertex(a1);
		Vertex v2 = new Vertex(a2);
		Vertex v3 = new Vertex(a3);
		Vertex v4 = new Vertex(a4);
		Vertex v5 = new Vertex(a5);
		Vertex v6 = new Vertex(a6);
		
		Edge e12 = new Edge(DistanceUtility.getDistance(a1.getLatitude(), a1.getLongitude(), a2.getLatitude(), a2.getLongitude()), v1, v2);
		Edge e13 = new Edge(DistanceUtility.getDistance(a1.getLatitude(), a1.getLongitude(), a3.getLatitude(), a3.getLongitude()), v1, v3);
		Edge e14 = new Edge(DistanceUtility.getDistance(a1.getLatitude(), a1.getLongitude(), a4.getLatitude(), a4.getLongitude()), v1, v4);
		Edge e15 = new Edge(DistanceUtility.getDistance(a1.getLatitude(), a1.getLongitude(), a5.getLatitude(), a5.getLongitude()), v1, v5);
		Edge e16 = new Edge(DistanceUtility.getDistance(a1.getLatitude(), a1.getLongitude(), a6.getLatitude(), a6.getLongitude()), v1, v6);
		Edge e23 = new Edge(DistanceUtility.getDistance(a2.getLatitude(), a2.getLongitude(), a3.getLatitude(), a3.getLongitude()), v2, v3);
		Edge e24 = new Edge(DistanceUtility.getDistance(a2.getLatitude(), a2.getLongitude(), a4.getLatitude(), a4.getLongitude()), v2, v4);
		Edge e25 = new Edge(DistanceUtility.getDistance(a2.getLatitude(), a2.getLongitude(), a5.getLatitude(), a5.getLongitude()), v2, v5);
		Edge e26 = new Edge(DistanceUtility.getDistance(a2.getLatitude(), a2.getLongitude(), a6.getLatitude(), a6.getLongitude()), v2, v6);
		Edge e34 = new Edge(DistanceUtility.getDistance(a3.getLatitude(), a3.getLongitude(), a4.getLatitude(), a4.getLongitude()), v3, v4);
		Edge e35 = new Edge(DistanceUtility.getDistance(a3.getLatitude(), a3.getLongitude(), a5.getLatitude(), a5.getLongitude()), v3, v5);
		Edge e36 = new Edge(DistanceUtility.getDistance(a3.getLatitude(), a3.getLongitude(), a6.getLatitude(), a6.getLongitude()), v3, v6);
		Edge e45 = new Edge(DistanceUtility.getDistance(a4.getLatitude(), a4.getLongitude(), a5.getLatitude(), a5.getLongitude()), v4, v5);
		Edge e46 = new Edge(DistanceUtility.getDistance(a4.getLatitude(), a4.getLongitude(), a6.getLatitude(), a6.getLongitude()), v4, v6);
		Edge e56 = new Edge(DistanceUtility.getDistance(a5.getLatitude(), a5.getLongitude(), a6.getLatitude(), a6.getLongitude()), v5, v6);
		
		try {
			AdjacencyMatrix m = AirlineReader.readAirportsFile("input/invalid.txt");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid filename.", e.getMessage());
		}
		
		try {
			AdjacencyMatrix m = AirlineReader.readAirportsFile("input/airports.txt");
			assertEquals(15, m.edges().size());
			assertEquals(e12.getSrc().getData().getAirportCode(), m.edges().get(0).getSrc().getData().getAirportCode());
			assertEquals(e12.getDest().getData().getAirportCode(), m.edges().get(0).getDest().getData().getAirportCode());
			assertEquals(e12.getWeight(), m.edges().get(0).getWeight(), .0000000001);
			
			assertEquals(e13.getSrc().getData().getAirportCode(), m.edges().get(1).getSrc().getData().getAirportCode());
			assertEquals(e13.getDest().getData().getAirportCode(), m.edges().get(1).getDest().getData().getAirportCode());
			assertEquals(e13.getWeight(), m.edges().get(1).getWeight(), .0000000001);
			
			assertEquals(e14.getSrc().getData().getAirportCode(), m.edges().get(2).getSrc().getData().getAirportCode());
			assertEquals(e14.getDest().getData().getAirportCode(), m.edges().get(2).getDest().getData().getAirportCode());
			assertEquals(e14.getWeight(), m.edges().get(2).getWeight(), .0000000001);
			
			assertEquals(e15.getSrc().getData().getAirportCode(), m.edges().get(3).getSrc().getData().getAirportCode());
			assertEquals(e15.getDest().getData().getAirportCode(), m.edges().get(3).getDest().getData().getAirportCode());
			assertEquals(e15.getWeight(), m.edges().get(3).getWeight(), .0000000001);
			
			assertEquals(e16.getSrc().getData().getAirportCode(), m.edges().get(4).getSrc().getData().getAirportCode());
			assertEquals(e16.getDest().getData().getAirportCode(), m.edges().get(4).getDest().getData().getAirportCode());
			assertEquals(e16.getWeight(), m.edges().get(4).getWeight(), .0000000001);
			
			assertEquals(e23.getSrc().getData().getAirportCode(), m.edges().get(5).getSrc().getData().getAirportCode());
			assertEquals(e23.getDest().getData().getAirportCode(), m.edges().get(5).getDest().getData().getAirportCode());
			assertEquals(e23.getWeight(), m.edges().get(5).getWeight(), .0000000001);
			
			assertEquals(e24.getSrc().getData().getAirportCode(), m.edges().get(6).getSrc().getData().getAirportCode());
			assertEquals(e24.getDest().getData().getAirportCode(), m.edges().get(6).getDest().getData().getAirportCode());
			assertEquals(e24.getWeight(), m.edges().get(6).getWeight(), .0000000001);
			
			assertEquals(e25.getSrc().getData().getAirportCode(), m.edges().get(7).getSrc().getData().getAirportCode());
			assertEquals(e25.getDest().getData().getAirportCode(), m.edges().get(7).getDest().getData().getAirportCode());
			assertEquals(e25.getWeight(), m.edges().get(7).getWeight(), .0000000001);
			
			assertEquals(e26.getSrc().getData().getAirportCode(), m.edges().get(8).getSrc().getData().getAirportCode());
			assertEquals(e26.getDest().getData().getAirportCode(), m.edges().get(8).getDest().getData().getAirportCode());
			assertEquals(e26.getWeight(), m.edges().get(8).getWeight(), .0000000001);
			
			assertEquals(e34.getSrc().getData().getAirportCode(), m.edges().get(9).getSrc().getData().getAirportCode());
			assertEquals(e34.getDest().getData().getAirportCode(), m.edges().get(9).getDest().getData().getAirportCode());
			assertEquals(e34.getWeight(), m.edges().get(9).getWeight(), .0000000001);
			
			assertEquals(e35.getSrc().getData().getAirportCode(), m.edges().get(10).getSrc().getData().getAirportCode());
			assertEquals(e35.getDest().getData().getAirportCode(), m.edges().get(10).getDest().getData().getAirportCode());
			assertEquals(e35.getWeight(), m.edges().get(10).getWeight(), .0000000001);
			
			assertEquals(e36.getSrc().getData().getAirportCode(), m.edges().get(11).getSrc().getData().getAirportCode());
			assertEquals(e36.getDest().getData().getAirportCode(), m.edges().get(11).getDest().getData().getAirportCode());
			assertEquals(e36.getWeight(), m.edges().get(11).getWeight(), .0000000001);
			
			assertEquals(e45.getSrc().getData().getAirportCode(), m.edges().get(12).getSrc().getData().getAirportCode());
			assertEquals(e45.getDest().getData().getAirportCode(), m.edges().get(12).getDest().getData().getAirportCode());
			assertEquals(e45.getWeight(), m.edges().get(12).getWeight(), .0000000001);
			
			assertEquals(e46.getSrc().getData().getAirportCode(), m.edges().get(13).getSrc().getData().getAirportCode());
			assertEquals(e46.getDest().getData().getAirportCode(), m.edges().get(13).getDest().getData().getAirportCode());
			assertEquals(e46.getWeight(), m.edges().get(13).getWeight(), .0000000001);
			
			assertEquals(e56.getSrc().getData().getAirportCode(), m.edges().get(14).getSrc().getData().getAirportCode());
			assertEquals(e56.getDest().getData().getAirportCode(), m.edges().get(14).getDest().getData().getAirportCode());
			assertEquals(e56.getWeight(), m.edges().get(14).getWeight(), .0000000001);
			
		} catch (Exception e) {
			fail();
		}
	}

}
