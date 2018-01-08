/**
 * 
 */
package edu.ncsu.csc316.customer_service.io;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import edu.ncsu.csc316.customer_service.data.Customer;
import edu.ncsu.csc316.customer_service.data.HelpTicket;
import edu.ncsu.csc316.customer_service.data.Timestamp;
import edu.ncsu.csc316.customer_service.tree.MultiPurposeTree;


/**
 * This class tests the HelpTicketReader class
 * @author Walker Booth (wgbooth)
 *
 */
public class HelpTicketReaderTest {

	/**
	 * Test method for {@link edu.ncsu.csc316.customer_service.io.HelpTicketReader#readTicketsFromFile(java.lang.String)}.
	 */
	@Test
	public void testReadTicketsFromFile() {
		MultiPurposeTree tPriority = new MultiPurposeTree();
		MultiPurposeTree tCustomers = new MultiPurposeTree();
		try {
			@SuppressWarnings("unused")
			HelpTicketReader h = new HelpTicketReader("input/invalid-source.txt");
			fail();
		} catch (FileNotFoundException e) {
			assertEquals(0, tPriority.size());
			assertEquals(0, tCustomers.size());
		}
		
		try {
			HelpTicketReader h = new HelpTicketReader("input/help-ticket-input.txt");
			tPriority = h.getTickets();
			tCustomers = h.getCustomers();
			HelpTicket h1 = new HelpTicket(new Customer ("John", "Smith"), 
										   new Timestamp (8, 11, 2017, 23, 45, 0),
										   8, "How much does it cost to change flights on the same day?");
			HelpTicket h2 = new HelpTicket(new Customer ("Suzanne", "Smith"), 
					   					   new Timestamp (9, 3, 2017, 10, 0, 0),
					   					   5, "How do I check my mileage balance?");
			HelpTicket h3 = new HelpTicket(new Customer ("Bob", "Baker"), 
										   new Timestamp (8, 14, 2017, 6, 54, 0),
										   2, "Can I change my flight?");
			assertEquals(3, tPriority.size());
			assertEquals(3, tCustomers.size());
			tPriority.updateTree(tCustomers);
			assertEquals(1, tCustomers.search(h1).getPosition());
			assertEquals(2, tCustomers.search(h2).getPosition());
			assertEquals(3, tCustomers.search(h3).getPosition());
		} catch (FileNotFoundException e) {
			fail();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.customer_service.io.HelpTicketReader#writeTicketsToFile(java.lang.String, edu.ncsu.csc316.customer_service.tree.MultiPurposeTree)}.
	 * @throws IOException 
	 */
	@Test
	public void testWriteTicketsToFile() throws IOException {
		try {
			HelpTicketReader h = new HelpTicketReader("input/help-ticket-input.txt");
			MultiPurposeTree t = h.getTickets();
			HelpTicketReader.writeTicketsToFile("output/actual.txt", t);
			File expected = new File("output/expected.txt");
			File actual = new File("output/actual.txt");
			Scanner scanExpected = new Scanner(expected);
			Scanner scanActual = new Scanner(actual);
			assertEquals(scanExpected.nextLine(), scanActual.nextLine());
			assertEquals(scanExpected.nextLine(), scanActual.nextLine());
			assertEquals(scanExpected.nextLine(), scanActual.nextLine());
			scanExpected.close();
			scanActual.close();
		} catch (FileNotFoundException e) {
			fail();
		}
		
		try {
			MultiPurposeTree t = new MultiPurposeTree ();
			HelpTicketReader.writeTicketsToFile("output/actual.txt", t);
			File actual = new File("output/actual.txt");
			Scanner scanActual = new Scanner(actual);
			assertEquals("The help ticket queue is empty", scanActual.nextLine());
			scanActual.close();
		} catch (FileNotFoundException e) {
			fail();
		}
		
	}

}
