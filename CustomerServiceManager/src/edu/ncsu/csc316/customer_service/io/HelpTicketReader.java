package edu.ncsu.csc316.customer_service.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import edu.ncsu.csc316.customer_service.data.Customer;
import edu.ncsu.csc316.customer_service.data.HelpTicket;
import edu.ncsu.csc316.customer_service.data.Timestamp;
import edu.ncsu.csc316.customer_service.tree.MultiPurposeTree;

/**
 * This class contains static methods that facilitate the reading and writing of helpticket Trees
 * @author Walker Booth (wgbooth)
 *
 */
public class HelpTicketReader {

	private MultiPurposeTree customers;
	private MultiPurposeTree tickets;
	
	/**
	 * Constructor for a HelpTicketReader object
	 * @param filename the name of the file to read from 
	 * @throws FileNotFoundException if the file could not be found
	 */
	public HelpTicketReader (String filename) throws FileNotFoundException {
		customers = new MultiPurposeTree();
		tickets = new MultiPurposeTree();
		readTicketsFromFile(filename);
	}
	
	/**
	 * Fully parses a file for helpTickets
	 * @param filename the name of the provided file 
	 * @throws FileNotFoundException if the filename provided is invalid
	 */
	public void readTicketsFromFile (String filename) throws FileNotFoundException {
		Scanner fileScan = new Scanner(new File(filename));
		String line;
		HelpTicket h;
		/** Skips the first line */
		fileScan.nextLine();
		while (fileScan.hasNextLine()) {
			line = fileScan.nextLine();
			h = readTicket(line);
			tickets.insert(h, true);
			customers.insert(h, false);
		}
		fileScan.close();
	}

	/**
	 * Reads a single ticket given a line from the input file 
	 * @param line the line to parse from the input file 
	 * @return h the helpticket created using the line from the input file 
	 */
	private static HelpTicket readTicket (String line) {
		Scanner lineScanner = new Scanner(line);
		lineScanner.useDelimiter("/");
		int month = lineScanner.nextInt();
		int day = lineScanner.nextInt();
		lineScanner.skip("/");
		lineScanner.useDelimiter(" ");
		int year = lineScanner.nextInt();
		lineScanner.skip(" ");
		lineScanner.useDelimiter(":");
		int hour = lineScanner.nextInt();
		int minute = lineScanner.nextInt();
		lineScanner.skip(":");
		lineScanner.useDelimiter(",");
		int second = lineScanner.nextInt();
		int priority = lineScanner.nextInt();
		Timestamp t = new Timestamp(month, day, year, hour, minute, second);
		Customer c = new Customer(lineScanner.next(), lineScanner.next());
		lineScanner.useDelimiter("\n");
		String question = lineScanner.next();
		question = question.substring(1);
		lineScanner.close();
		HelpTicket h = new HelpTicket(c, t, priority, question);
		return h;
	}
	
	/**
	 * Prints the tree's data to a file
	 * @param filename the file to print to 
	 * @param t the tree to print to a file 
	 * @throws FileNotFoundException if the output file could not be found
	 */
	public static void writeTicketsToFile (String filename, MultiPurposeTree t) throws FileNotFoundException {		
		PrintStream output = new PrintStream(new File(filename));
		if (t.size() == 0) {
			output.println("The help ticket queue is empty");
		} else {
			output.println(t.printTree());
		}
		
		output.close();
		
	}
	
	/**
	 * Returns the tree of tickets by priority
	 * @return tickets the tree of tickets 
	 */
	public MultiPurposeTree getTickets () {
		return tickets;
	}
	
	/**
	 * Returns the tree of tickets by customer 
	 * @return customers the tree of tickets by customer 
	 */
	public MultiPurposeTree getCustomers () {
		return customers;
	}
	
}
