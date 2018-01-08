/**
 * 
 */
package edu.ncsu.csc316.customer_service.ui;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import edu.ncsu.csc316.customer_service.manager.CustomerServiceManager;

/**
 * User interface for the Customer Service project
 * @author Walker Booth (wgbooth)
 */
public class CustomerServiceUI {

	/**
	 * The method that runs through the execution of the UI
	 * @param args the array of Strings found on the command line 
	 * @throws FileNotFoundException if the output file cannot be opened
	 */
	public static void main (String[] args) throws FileNotFoundException {
		
		boolean validFile = false;
		CustomerServiceManager manager = null;
		Scanner input = new Scanner(System.in);
		System.out.print("Please enter an input file:");
		String fileName = "";
		
		while(!validFile) {
			fileName = input.nextLine();
			try {
				manager = new CustomerServiceManager(fileName);
				validFile = true;
			} catch (IllegalArgumentException e) {
				System.out.print("Invalid input file, please specify another input file: ");
			}
		}
		
		System.out.print("Loaded tickets from " + fileName);
		System.out.println("\n\nOptions:\n(T)ickets for a user\n(F)ull Ticket report");
		
		String choice = input.nextLine();
		
		if (choice.equals("T")) {
			String name = input.nextLine();
			Scanner lineScan = new Scanner(name);
			System.out.print(manager.getPlaceInLine(lineScan.next(), lineScan.next()));
			lineScan.close();
		} else if (choice.equals("F")) {
			PrintStream output = new PrintStream("output/output.txt");
			output.print(manager.getHelpTicketQueue());
			output.close();
		} else {
			input.close();
			System.exit(1);
		}
		
		input.close();		
	}
}
