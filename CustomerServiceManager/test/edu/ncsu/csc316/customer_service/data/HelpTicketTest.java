/**
 * 
 */
package edu.ncsu.csc316.customer_service.data;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the HelpTicket class
 * @author Walker Booth (wgbooth)
 */
public class HelpTicketTest {

	/**
	 * Test method for {@link edu.ncsu.csc316.customer_service.data.HelpTicket#HelpTicket(edu.ncsu.csc316.customer_service.data.Customer, edu.ncsu.csc316.customer_service.data.Timestamp, int, java.lang.String)}.
	 */
	@Test
	public void testHelpTicket() {
		Customer c = new Customer("Walker", "Booth");
		Timestamp t = new Timestamp(9, 3, 2017, 10, 0, 0);
		HelpTicket h = new HelpTicket(c, t, 10, "I can't find my gate");
		assertEquals("Walker Booth", h.getCustomer().toString());
		assertEquals("09/03/2017", h.getSubmitTime().dateString());
		assertEquals("10:00:00", h.getSubmitTime().timeString());
		assertEquals(10, h.getPriority());
		assertEquals("I can't find my gate", h.getQuestion());
		assertEquals("Priority 10: submitted at 09/03/2017 10:00:00 by Walker Booth, Question: I can't find my gate", h.toString());
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.customer_service.data.HelpTicket#compareByPriority(java.lang.Object)}.
	 */
	@Test
	public void testCompareByPriority() {
		Customer c1 = new Customer("Walker", "Booth");
		Customer c2 = new Customer("Victor", "Olson");
		Customer c3 = new Customer("Mr", "Anderson");
		Timestamp t1 = new Timestamp(10, 3, 2017, 10, 0, 0);
		Timestamp t2 = new Timestamp(9, 3, 2017, 10, 0, 0);
		Timestamp t3 = new Timestamp(11, 3, 2017, 10, 0, 0);

		HelpTicket h1 = new HelpTicket(c1, t1, 9, "I can't find my gate");
		HelpTicket h2 = new HelpTicket(c1, t2, 9, "I can't find my gate");		//greater than
		HelpTicket h3 = new HelpTicket(c1, t3, 9, "I can't find my gate");		//less than
		HelpTicket h4 = new HelpTicket(c2, t1, 9, "I can't find my gate");		//less than
		HelpTicket h5 = new HelpTicket(c3, t1, 9, "I can't find my gate");		//greater than 
		HelpTicket h6 = new HelpTicket(c1, t1, 8, "I can't find my gate");		//less than 
		HelpTicket h7 = new HelpTicket(c1, t1, 10, "I can't find my gate");		//greater than 

		assertEquals(0, h1.compareByPriority(h1));
		assertEquals(1, h1.compareByPriority(h2));
		assertEquals(-1, h1.compareByPriority(h3));
		assertEquals(-1, h1.compareByPriority(h4));
		assertEquals(1, h1.compareByPriority(h5));
		assertEquals(-1, h1.compareByPriority(h6));
		assertEquals(1, h1.compareByPriority(h7));
	}
}
