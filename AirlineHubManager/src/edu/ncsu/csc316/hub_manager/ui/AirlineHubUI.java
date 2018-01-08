package edu.ncsu.csc316.hub_manager.ui;

import java.util.Scanner;

import edu.ncsu.csc316.hub_manager.manager.AirlineHubManager;

/**
 * UI class for the AirlineHubManager project. 
 * @author Walker Booth (wgbooth)
 *
 */
public class AirlineHubUI {

	/**
	 * Runs through the main operations of AirlineHubUI
	 * @param args the command line arguments
	 */
	public static void main (String[] args) {
		boolean correctFile = false;
		Scanner input = new Scanner(System.in);
		AirlineHubManager manager = null;
		
		while (!correctFile) {
			System.out.println("Please specify a filename: ");
			try {
				manager = new AirlineHubManager(input.nextLine());
				correctFile = true;
			} catch (IllegalArgumentException e) {
				System.out.println("Invalid filename, please try again.");
			}
		}
		
		String choice = "";
		while (!choice.equals("Q")) {
			System.out.println("Operations menu: \n\tView (C)onnections\n\tView (H)ubs\n\t(Q)uit\nPlease choose one of the above options: ");
			choice = input.nextLine();
			if (choice.equals("C")) {
				System.out.println(manager.getMinimumFlights());
			} else if (choice.equals("H")) {
				System.out.println(manager.getPossibleHubs());
			}
			System.out.println();
		}
		input.close();
		
	}
	
}
