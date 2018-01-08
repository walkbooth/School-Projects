/**
 * 
 */
package edu.ncsu.csc216.checkout_simulator.simulation;

import java.awt.Color;

import edu.ncsu.csc216.checkout_simulator.items.Cart;
import edu.ncsu.csc216.checkout_simulator.queues.CheckoutRegister;
import edu.ncsu.csc216.checkout_simulator.queues.Store;

/**
 * Walks through the EKIA simulation
 * @author Walker Booth
 */
public class Simulator {

	private static final int MIN_NUM_REGISTERS = 3;
	private static final int MAX_NUM_REGISTERS = 12;
	private int numRegisters;
	private int numCarts;
	private int stepsTaken;
	private CheckoutRegister[] register;
	private Store theStore;
	private Cart currentCart;
	private EventCalendar theCalendar;  
	private Log log;

	
	/**
	 * Simulates the operation of a store
	 * @param numCarts the number of carts in the store
	 * @param numRegisters the number of registers in the store
	 */
	public Simulator(int numRegisters, int numCarts) {  
		if (numRegisters < MIN_NUM_REGISTERS || numRegisters > MAX_NUM_REGISTERS) {
			throw new IllegalArgumentException("Number of registers must be between 3 and 12 inclusive.");
		} else {
			this.numRegisters = numRegisters;
		}
		
		if (numCarts < 1) {
			throw new IllegalArgumentException("There must be at least one shopping cart in the simulation.");
		} else {
			this.numCarts = numCarts;
		}
		
		log = new Log();
		
		register = new CheckoutRegister[this.numRegisters];
		for (int i = 0; i < numRegisters; i++) {
			register[i] = new CheckoutRegister(log);
		}
		theStore = new Store (numCarts, register);
		theCalendar = new EventCalendar(register, theStore);
		
	}
	
	/** 
	 * Gets the colors of a simulation
	 * @return the array of colors of the simulation
	 */
	public static Color[] simulationColors() {
		Color[] simulationColors = new Color[3];
		simulationColors[0] = Color.GREEN;
		simulationColors[1] = Color.BLUE;
		simulationColors[2] = Color.RED;
		
		return simulationColors;
	}
	
	/**
	 * Gets the labels of a simulation
	 * @return the array of labels of the simulation
	 */
	public static String[] simulationLabels() {
		String[] simulationLabels = new String[3];
		simulationLabels[0] = "Express Cart";
		simulationLabels[1] = "Regular Cart";
		simulationLabels[2] = "Special Handling Cart";		
		
		return simulationLabels;
	}
	
	/**
	 * Handles the next event from the event calendar
	 */
	public void step () throws IllegalStateException {
		currentCart = null; 
		currentCart = theCalendar.nextToBeProcessed().processNext();
		stepsTaken++;
	}
	
	/** 
	 * Gets number of steps taken so far
	 * @return stepsTaken number of steps taken so far
	 */
	public int getStepsTaken () {
		return stepsTaken;
	}
	
	/**
	 * Gets total number of steps to take in the simulation
	 * @return total number of steps to take in the simulation
	 */
	public int totalNumberOfSteps () {
		return numCarts * 2;
		
	}
	
	/**
	 * Determines whether or not moreSteps need to be taken 
	 * @return whether or not the simulation hasn't finished
	 */
	public boolean moreSteps() {
		if (stepsTaken < totalNumberOfSteps()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Gets the index of CheckoutRegister selected by the current cart, or -1 if null
	 * @return the index of CheckoutRegister selected by the current cart, or -1 if null
	 */
	public int getCurrentIndex () {
		if (currentCart == null) {
			return -1;
		}
		
		return currentCart.getRegisterIndex();
	}
	
	/**
	 * Gets the color of the current cart
	 * @return color of the current cart
	 */
	public Color getCurrentCartColor () {
		if (currentCart == null) {
			return null;
		}
		
		return currentCart.getColor();		
	}
	
	/**
	 * Determines whether or not an item is processed 
	 * @return whether or not an item is processed 
	 */
	public boolean itemLeftSimulation () {
		if (currentCart == null || currentCart.isWaitingInRegisterLine()) {
			return false;
		}
		return true;
	}
	
	/**
	 * Gets the average wait time experienced by each cart 
	 * @return the average wait time experienced by each cart
	 */
	public double averageWaitTime() {
		return log.averageWaitTime();
	}
	
	/**
	 * Gets the average process time experienced by each cart
	 * @return the average process time experienced by each cart
	 */
	public double averageProcessTime() {
		return log.averageProcessTime();
	}
	
}
