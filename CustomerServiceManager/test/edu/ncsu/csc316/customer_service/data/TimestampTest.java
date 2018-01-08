/**
 * 
 */
package edu.ncsu.csc316.customer_service.data;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the Timestamp class
 * @author Walker Booth (wgbooth)
 */
public class TimestampTest {

	/**
	 * Test method for {@link edu.ncsu.csc316.customer_service.data.Timestamp#Timestamp(int, int, int, int, int, int)}.
	 */
	@Test
	public void testTimestamp() {
		Timestamp t1 = new Timestamp(9, 3, 2017, 10, 0, 0);
		Timestamp t2 = new Timestamp(10, 31, 2017, 9, 30, 10);
		assertEquals(9, t1.getMonth());
		assertEquals(3, t1.getDay());
		assertEquals(2017, t1.getYear());
		assertEquals(10, t1.getHour());
		assertEquals(0, t1.getMinute());
		assertEquals(0, t1.getSecond());
		
		assertEquals("09/03/2017", t1.dateString());
		assertEquals("10:00:00", t1.timeString());
		assertEquals("10/31/2017", t2.dateString());
		assertEquals("09:30:10", t2.timeString());
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.customer_service.data.Timestamp#compareTo(java.lang.Object)}.
	 */
	@Test
	public void testCompareTo() {
		Timestamp t1 = new Timestamp(9, 3, 2017, 10, 1, 1);
		Timestamp t2 = new Timestamp(9, 3, 2018, 10, 1, 1);
		Timestamp t3 = new Timestamp(9, 3, 2016, 10, 1, 1);
		Timestamp t4 = new Timestamp(10, 3, 2017, 10, 1, 1);
		Timestamp t5 = new Timestamp(8, 3, 2017, 10, 1, 1);
		Timestamp t6 = new Timestamp(9, 4, 2017, 10, 1, 1);
		Timestamp t7 = new Timestamp(9, 2, 2017, 10, 1, 1);
		Timestamp t8 = new Timestamp(9, 3, 2017, 9, 1, 1);
		Timestamp t9 = new Timestamp(9, 3, 2017, 11, 1, 1);
		Timestamp t10 = new Timestamp(9, 3, 2017, 10, 2, 1);
		Timestamp t11 = new Timestamp(9, 3, 2017, 10, 0, 1);
		Timestamp t12 = new Timestamp(9, 3, 2017, 10, 1, 2);
		Timestamp t13 = new Timestamp(9, 3, 2017, 10, 1, 0);
		
		assertEquals(0, t1.compareTo(t1));
		assertEquals(1, t1.compareTo(t3));
		assertEquals(-1, t1.compareTo(t2));
		assertEquals(-1, t1.compareTo(t4));
		assertEquals(1, t1.compareTo(t5));
		assertEquals(-1, t1.compareTo(t6));
		assertEquals(1, t1.compareTo(t7));
		assertEquals(1, t1.compareTo(t8));
		assertEquals(-1, t1.compareTo(t9));
		assertEquals(-1, t1.compareTo(t10));
		assertEquals(1, t1.compareTo(t11));
		assertEquals(-1, t1.compareTo(t12));
		assertEquals(1, t1.compareTo(t13));

	}

}
