package edu.ncsu.csc316.customer_service.manager;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the CustomerServiceManager class
 * @author Walker Booth (wgbooth)
 *
 */
public class CustomerServiceManagerTest {
	
	String validInput = "input/help-ticket-input.txt";
	
	/**
	 * Tests the constructor of CustomerServiceManager
	 */
	@Test
	public void testCustomerServiceManager() {
		CustomerServiceManager m = null;
		try {
			m = new CustomerServiceManager("invalidFile.txt");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(m);
		}
		
		try {
			m = new CustomerServiceManager(validInput);
			assertNotNull(m);
		} catch (IllegalArgumentException e) {
			fail();
		}
	}
	
	/**
	 * Tests the getPlaceInLine method 
	 */
	@Test
	public void testGetPlaceInLine() {
		CustomerServiceManager m = new CustomerServiceManager(validInput);
		assertEquals("Bob Baker is number 3 in the queue:\n    Priority 2: submitted at 08/14/2017 06:54:00 by Bob Baker, Question: Can I change my flight?", m.getPlaceInLine("Bob", "Baker"));
		assertEquals("Suzanne Smith is number 2 in the queue:\n    Priority 5: submitted at 09/03/2017 10:00:00 by Suzanne Smith, Question: How do I check my mileage balance?", m.getPlaceInLine("Suzanne", "Smith"));
		assertEquals("John Smith is number 1 in the queue:\n    Priority 8: submitted at 08/11/2017 23:45:00 by John Smith, Question: How much does it cost to change flights on the same day?", m.getPlaceInLine("John", "Smith"));
		assertEquals("User Notin Line has no active help tickets.", m.getPlaceInLine("Notin", "Line"));

	}

	/**
	 * Tests the getHelpTicketQueue method 
	 */
	@Test
	public void testGetHelpTicketQueue() {
		CustomerServiceManager m = new CustomerServiceManager(validInput);
		String s = "Priority 8: submitted at 08/11/2017 23:45:00 by John Smith, Question: How much does it cost to change flights on the same day?\n" +
				   "Priority 5: submitted at 09/03/2017 10:00:00 by Suzanne Smith, Question: How do I check my mileage balance?\n" + 
				   "Priority 2: submitted at 08/14/2017 06:54:00 by Bob Baker, Question: Can I change my flight?";
		assertEquals(s, m.getHelpTicketQueue());
	}

	/**
	 * Tests the removeCustomerFromQueue method
	 */
	@Test
	public void testRemoveCustomerFromQueue() {
		CustomerServiceManager m = new CustomerServiceManager(validInput);
		String s = "Priority 8: submitted at 08/11/2017 23:45:00 by John Smith, Question: How much does it cost to change flights on the same day?\n" +
				   "Priority 2: submitted at 08/14/2017 06:54:00 by Bob Baker, Question: Can I change my flight?";
		m.removeCustomerFromQueue("Suzanne", "Smith");
		assertEquals(s, m.getHelpTicketQueue());
		assertEquals("Bob Baker is number 2 in the queue:\n    Priority 2: submitted at 08/14/2017 06:54:00 by Bob Baker, Question: Can I change my flight?", m.getPlaceInLine("Bob", "Baker"));
		assertEquals("John Smith is number 1 in the queue:\n    Priority 8: submitted at 08/11/2017 23:45:00 by John Smith, Question: How much does it cost to change flights on the same day?", m.getPlaceInLine("John", "Smith"));
		
		m.removeCustomerFromQueue("Notin", "line");
		assertEquals(s, m.getHelpTicketQueue());
		assertEquals("Bob Baker is number 2 in the queue:\n    Priority 2: submitted at 08/14/2017 06:54:00 by Bob Baker, Question: Can I change my flight?", m.getPlaceInLine("Bob", "Baker"));
		assertEquals("John Smith is number 1 in the queue:\n    Priority 8: submitted at 08/11/2017 23:45:00 by John Smith, Question: How much does it cost to change flights on the same day?", m.getPlaceInLine("John", "Smith"));

	}

}
