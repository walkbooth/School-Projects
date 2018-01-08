package edu.ncsu.csc316.hub_manager.useful;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the DistanceUtility class.
 * @author Walker Booth (wgbooth)
 *
 */
public class DistanceUtilityTest {

	/**
	 * Tests the getDistance method
	 */
	@Test
	public void testGetDistance() {
		
		DistanceUtility t = new DistanceUtility();
		t.getClass();
		
		int distance1 = (int)(DistanceUtility.getDistance(42.2672996, -71.875702, 35.877601, -78.787498));
		int distance2 = (int)(DistanceUtility.getDistance(47.449001, -122.308998, 37.618999, -122.375));
		assertEquals(576, distance1);
		assertEquals(679, distance2);
	}

}
