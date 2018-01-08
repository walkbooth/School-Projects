/**
 * 
 */
package edu.ncsu.csc316.customer_service.manager;

import java.io.FileNotFoundException;
import edu.ncsu.csc316.customer_service.data.Customer;
import edu.ncsu.csc316.customer_service.data.HelpTicket;
import edu.ncsu.csc316.customer_service.io.HelpTicketReader;
import edu.ncsu.csc316.customer_service.tree.MultiPurposeTree;

/**
 * This class shows how to instantiate and use a CustomerServiceManager object
 * @author Walker Booth (wgbooth)
 *
 */
public class CustomerServiceManager {
	
	/** The MultiPurposeTree of tickets sorted by customer */
	private MultiPurposeTree customers;
	/** The MultiPurposeTree of tickets sorted by priority*/
	private MultiPurposeTree tickets;
	/** Whether or not the tree is modified */
	boolean isModified;
	
	/**
	 * Constructor for a CustomerServiceManager object
	 * @param fileName the name of the file to gather data from 
	 */
	public CustomerServiceManager(String fileName) {
		HelpTicketReader h;
		try {
			h = new HelpTicketReader(fileName);
			
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException();
		}
		
		tickets = h.getTickets();	
		customers = h.getCustomers();
		tickets.updateTree(customers);
		isModified = false;
		
	}

	/**
	 * Outputs the customer's place in line in the help queue,
	 * along with the help ticket information in the format
	 * (with 2nd line indentation of 4 spaces and NO newline at the end):
	 *
	 * Bob Baker is number 3 in the queue:
	 *     Priority 2 submmited on 08/14/2017 at 06:54:00: Can I change my flight?
	 * 
	 * @param firstName - the customer's first name
	 * @param lastName - the customer's last name
	 * @return the customer's place in line and help ticket information
	 */
	public String getPlaceInLine(String firstName, String lastName)
	{
		Customer toFind = new Customer (firstName, lastName);
		HelpTicket h = new HelpTicket(toFind, null, -1, null);
		if (isModified) {
			tickets.updateTree(customers);
			isModified = false;
		}
		HelpTicket found = customers.search(h);
		String s = "";
		if (found == null) {
			s = "User ".concat(toFind.toString().concat(" has no active help tickets."));
		} else {
			s = found.getCustomer().toString().concat(" is number ");
			s = s.concat(((Integer) found.getPosition()).toString());
			s = s.concat(" in the queue:\n    ");
			s = s.concat("Priority ".concat(((Integer) found.getPriority()).toString().concat(":")));
			s = s.concat(" submitted at ".concat(found.getSubmitTime().dateString()));
			s = s.concat(" ".concat(found.getSubmitTime().timeString()));
			s = s.concat(" by ".concat(found.getCustomer().toString()));
			s = s.concat(", Question: ".concat(found.getQuestion()));
		}
		return s;
	}
	
	/**
	 * Retrieves the complete help ticket queue, sorted in order 
	 * that the customers will be served. Higher priority is served 
	 * first, and (if priorities are the same) the help ticket
	 * with the earlier submission timestamp is serviced first.
	 * 
	 * The help queue is returned in the following format
	 * (with indentation of 4 spaces and NO newline at the very end):
	 *
	 * Priority 8: submitted at 11/11/2017 23:45:00 by John Smith, Question: How much does it cost to change flights on the same day?
	 * Priority 5: submitted at 09/03/2017 10:00:00 by Suzanne Smith, Question: How do I check my mileage balance?
	 * Priority 2: submitted at 10/14/2017 06:54:00 by Bob Baker, Question: Can I change my flight?
	 * 
	 * @return the help ticket queue listed in the order customers will be serviced
	 */
	public String getHelpTicketQueue()
	{
		int l = tickets.printTree().length();
		return tickets.printTree().substring(0, l - 1);
	}
	
	/**
	 * Removes the customer from the help ticket software (for example,
	 * if the customer finds the answer to their question while waiting,
	 * they may cancel their help ticket instead of waiting to be serviced)
	 *
	 * @param firstName - the customer's first named
	 * @param lastName - the customer's last name
	 */
	public void removeCustomerFromQueue(String firstName, String lastName)
	{
		Customer c = new Customer(firstName, lastName);
		HelpTicket h = new HelpTicket(c, null, -1, null);
		HelpTicket toRemove = customers.remove(h, false);
		tickets.remove(toRemove, true);
		isModified = true;
	}

}