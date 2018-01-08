package edu.ncsu.csc316.hub_manager.util;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.hub_manager.data.Airport;
import edu.ncsu.csc316.hub_manager.useful.DistanceUtility;

/**
 * Tests the Edge class. 
 * @author Walker Booth (wgbooth)
 *
 */
public class EdgeTest {

	/**
	 * Tests the edge constructor 
	 */
	@Test
	public void test() {
		Airport a1 = new Airport(0, "RDU", 35.877601623535156, -78.7874984742122);
		Vertex v1 = new Vertex(a1);
		assertEquals(a1, v1.getData());
		assertEquals(a1.getId(), v1.getId());
		
		Airport a2 = new Airport(0, "UDR", -78.7874984742122, 35.877601623535156);
		Vertex v2 = new Vertex(a2);
		Edge e = new Edge(DistanceUtility.getDistance(a1.getLatitude(), a1.getLongitude(), a2.getLatitude(), a2.getLongitude()), v1, v2);
		assertEquals(a2, v2.getData());
		assertEquals(a2.getId(), v2.getId());
		
		assertTrue(v1.compareTo(v2) < 0);
		
		assertEquals(v1, e.getSrc());
		assertEquals(v2, e.getDest());
		assertEquals(DistanceUtility.getDistance(a1.getLatitude(), a1.getLongitude(), a2.getLatitude(), a2.getLongitude()), e.getWeight(), .000000001);
	}

}
