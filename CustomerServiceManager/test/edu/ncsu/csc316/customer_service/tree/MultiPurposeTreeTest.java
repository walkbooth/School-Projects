package edu.ncsu.csc316.customer_service.tree;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.customer_service.data.Customer;
import edu.ncsu.csc316.customer_service.data.HelpTicket;
import edu.ncsu.csc316.customer_service.data.Timestamp;

/**
 * Tests the MultiPurposeTree class
 * @author Walker Booth
 *
 */
public class MultiPurposeTreeTest {

	/**
	 * Tests the constructor for MultiPurposeTree
	 */
	@Test
	public void testMultiPurposeTree() {
		MultiPurposeTree t = new MultiPurposeTree();
		assertEquals(0, t.size());
	}
	
	/**
	 * Tests the search method for MultiPurposeTree
	 */
	@Test
	public void testSearch () {
		MultiPurposeTree tPriority = new MultiPurposeTree();
		MultiPurposeTree tCustomers = new MultiPurposeTree();
		HelpTicket h1 = new HelpTicket(new Customer ("John", "Smith"), 
				new Timestamp (8, 11, 2017, 23, 45, 0),
				8, "How much does it cost to change flights on the same day?");
		HelpTicket h2 = new HelpTicket(new Customer ("Suzanne", "Smith"), 
				new Timestamp (9, 3, 2017, 10, 0, 0),
				5, "How do I check my mileage balance?");
		HelpTicket h3 = new HelpTicket(new Customer ("Bob", "Baker"), 
				new Timestamp (8, 14, 2017, 6, 54, 0),
				2, "Can I change my flight?");
		tPriority.updateTree(tCustomers);
		assertNull(tCustomers.search(h1));
		tPriority.insert(h1, true);
		tCustomers.insert(h1, false);
		tPriority.updateTree(tCustomers);
		assertEquals(1, tCustomers.search(h1).getPosition());
		
		tPriority.insert(h2, true);
		tCustomers.insert(h2, false);
		tPriority.updateTree(tCustomers);
		assertEquals(1, tCustomers.search(h1).getPosition());
		assertEquals(2, tCustomers.search(h2).getPosition());
		
		tPriority.insert(h3, true);
		tCustomers.insert(h3, false);
		tPriority.updateTree(tCustomers);
		assertEquals(1, tCustomers.search(h1).getPosition());
		assertEquals(2, tCustomers.search(h2).getPosition());
		assertEquals(3, tCustomers.search(h3).getPosition());

	}
	
	/**
	 * Tests the insert method for MultiPurposeTree
	 */
	@Test
	public void testInsert() {
		MultiPurposeTree tPriority = new MultiPurposeTree();
		MultiPurposeTree tCustomers = new MultiPurposeTree();
		HelpTicket h1 = new HelpTicket(new Customer ("John", "Smith"), 
				new Timestamp (8, 11, 2017, 23, 45, 0),
				8, "How much does it cost to change flights on the same day?");
		HelpTicket h2 = new HelpTicket(new Customer ("Suzanne", "Smith"), 
				new Timestamp (9, 3, 2017, 10, 0, 0),
				5, "How do I check my mileage balance?");
		HelpTicket h3 = new HelpTicket(new Customer ("Bob", "Baker"), 
				new Timestamp (8, 14, 2017, 6, 54, 0),
				2, "Can I change my flight?");
		HelpTicket h4 = new HelpTicket(new Customer ("Walker", "Booth"), 
									   new Timestamp(1, 5, 2017, 11, 0, 0), 6, "I need help");
		HelpTicket h5 = new HelpTicket(new Customer ("Willie", "Earl"), 
								       new Timestamp(1, 7, 2018, 12, 0, 0), 7, "I can't find my terminal");
		HelpTicket h6 = new HelpTicket(new Customer ("Suzanne", "Booth"), 
									   new Timestamp (9, 3, 2017, 10, 0, 0),
									   5, "How do I check my mileage balance?")	;
		HelpTicket h7 = new HelpTicket(new Customer ("Bob", "Baker"), 
									   new Timestamp (8, 14, 2017, 6, 54, 0),
									   3, "Can I change my flight?");
		tPriority.insert(h1, true);
		tCustomers.insert(h1, false);
		tPriority.updateTree(tCustomers);
		assertEquals(1, tPriority.size());
		assertEquals(1, tCustomers.size());
		assertEquals(1, tCustomers.search(h1).getPosition());
		
		tPriority.insert(h2, true);
		tCustomers.insert(h2, false);
		tPriority.updateTree(tCustomers);
		assertEquals(2, tPriority.size());
		assertEquals(2, tCustomers.size());
		assertEquals(1, tCustomers.search(h1).getPosition());
		assertEquals(2, tCustomers.search(h2).getPosition());
		
		tPriority.insert(h3, true);
		tCustomers.insert(h3, false);
		tPriority.updateTree(tCustomers);
		assertEquals(3, tPriority.size());
		assertEquals(3, tCustomers.size());
		assertEquals(1, tCustomers.search(h1).getPosition());
		assertEquals(2, tCustomers.search(h2).getPosition());
		assertEquals(3, tCustomers.search(h3).getPosition());
		
		tPriority.insert(h4, true);
		tPriority.insert(h5, true);
		tPriority.insert(h6, true);
		tPriority.insert(h7, true);
		
		tCustomers.insert(h4, false);
		tCustomers.insert(h5, false);
		tCustomers.insert(h6, false);
		tCustomers.insert(h7, false);

	}

	/**
	 * Tests the remove method for MultiPurposeTree
	 */
	@Test
	public void testRemove() {
		MultiPurposeTree tPriority = new MultiPurposeTree();
		MultiPurposeTree tCustomers = new MultiPurposeTree();
		HelpTicket h1 = new HelpTicket(new Customer ("John", "Smith"), 
				new Timestamp (8, 11, 2017, 23, 45, 0),
				8, "How much does it cost to change flights on the same day?");
		HelpTicket h2 = new HelpTicket(new Customer ("Suzanne", "Smith"), 
				new Timestamp (9, 3, 2017, 10, 0, 0),
				5, "How do I check my mileage balance?");
		HelpTicket h3 = new HelpTicket(new Customer ("Bob", "Baker"), 
				new Timestamp (8, 14, 2017, 6, 54, 0),
				2, "Can I change my flight?");
		HelpTicket h4 = new HelpTicket(new Customer ("Walker", "Booth"), 
									   new Timestamp(1, 5, 2017, 11, 0, 0), 6, "I need help");
		HelpTicket h5 = new HelpTicket(new Customer ("Willie", "Earl"), 
								       new Timestamp(1, 7, 2018, 12, 0, 0), 7, "I can't find my terminal");
		
		tPriority.insert(h1, true);
		tPriority.insert(h2, true);
		tPriority.insert(h3, true);
		tPriority.insert(h4, true);
		tPriority.insert(h5, true);
		tCustomers.insert(h1, false);
		tCustomers.insert(h2, false);
		tCustomers.insert(h3, false);
		tCustomers.insert(h4, false);
		tCustomers.insert(h5, false);
		tPriority.updateTree(tCustomers);
		assertEquals(1, tCustomers.search(h1).getPosition());
		assertEquals(2, tCustomers.search(h5).getPosition());
		assertEquals(3, tCustomers.search(h4).getPosition());
		assertEquals(4, tCustomers.search(h2).getPosition());
		assertEquals(5, tCustomers.search(h3).getPosition());
		
		
		tPriority.remove(h5, true);
		tCustomers.remove(h5, false);
		tPriority.updateTree(tCustomers);
		assertEquals(4, tPriority.size());
		assertEquals(4, tCustomers.size());
		assertEquals(1, tCustomers.search(h1).getPosition());
		assertEquals(2, tCustomers.search(h4).getPosition());
		assertEquals(3, tCustomers.search(h2).getPosition());
		assertEquals(4, tCustomers.search(h3).getPosition());		
		
		tPriority.remove(h2, true);
		tCustomers.remove(h2, false);
		tPriority.updateTree(tCustomers);
		assertEquals(3, tPriority.size());
		assertEquals(3, tCustomers.size());
		tPriority.updateTree(tCustomers);
		assertEquals(1, tCustomers.search(h1).getPosition());
		assertEquals(2, tCustomers.search(h4).getPosition());
		assertEquals(3, tCustomers.search(h3).getPosition());	
		
		tPriority.remove(h2, true);
		tCustomers.remove(h2, false);
		tPriority.updateTree(tCustomers);
		assertEquals(3, tPriority.size());
		assertEquals(3, tCustomers.size());
		tPriority.updateTree(tCustomers);
		assertEquals(1, tCustomers.search(h1).getPosition());
		assertEquals(2, tCustomers.search(h4).getPosition());
		assertEquals(3, tCustomers.search(h3).getPosition());	
		
	}
	
	/**
	 * Tests the printTree method for MultiPurposeTree
	 */
	@Test
	public void testPrintTree() {
		MultiPurposeTree t = new MultiPurposeTree();
		HelpTicket h1 = new HelpTicket(new Customer ("John", "Smith"), 
				new Timestamp (11, 11, 2017, 23, 45, 0),
				8, "How much does it cost to change flights on the same day?");
		HelpTicket h2 = new HelpTicket(new Customer ("Suzanne", "Smith"), 
				new Timestamp (9, 3, 2017, 10, 0, 0),
				5, "How do I check my mileage balance?");
		HelpTicket h3 = new HelpTicket(new Customer ("Bob", "Baker"), 
				new Timestamp (8, 14, 2017, 6, 54, 0),
				2, "Can I change my flight?");
		t.insert(h1, true);
		t.insert(h2, true);
		t.insert(h3, true);
		
		assertEquals("Priority 8: submitted at 11/11/2017 23:45:00 by John Smith, "
				   + "Question: How much does it cost to change flights on the same day?\n" 
				   + "Priority 5: submitted at 09/03/2017 10:00:00 by Suzanne Smith, "
				   + "Question: How do I check my mileage balance?\n" 
				   + "Priority 2: submitted at 08/14/2017 06:54:00 by Bob Baker, "
				   + "Question: Can I change my flight?\n", t.printTree());
	}

	

}
