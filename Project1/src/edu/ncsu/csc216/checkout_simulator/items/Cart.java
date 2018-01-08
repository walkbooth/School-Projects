/**
 * 
 */
package edu.ncsu.csc216.checkout_simulator.items;

import java.awt.Color;

import edu.ncsu.csc216.checkout_simulator.queues.CheckoutRegister;

/**
 * The superclass of the three types of carts: ExpressCarts, RegularShoppingCarts, and SpecialHandlingCarts
 * @author Walker Booth
 *
 */
public abstract class Cart {

	/** Index of the first register */
	public static final int INITIAL_REGISTER_IDX = -1;
	/** The arrival time (in seconds) of a cart to a register */
	private int arrivalTime;
	/** The wait time (in seconds) of a cart at a register */
	private int waitTime;
	/** The number of seconds it takes to process a single cart */
	private int processTime;
	/** The index of a register that the cart has selected */
	private int registerIndex = INITIAL_REGISTER_IDX;
	/** Whether or not the cart is in a line*/
	private boolean waitingProcessing;
	
	/** 
	 * The constructor for a cart 
	 * @param arrivalTime The arrival time (in seconds) of a cart to a register
	 * @param processTime The number of seconds it takes to process a single cart
	 */
	public Cart (int arrivalTime, int processTime) {
		if (arrivalTime < 0) {
			throw new IllegalArgumentException("Arrival time cannot be less than 0.");
		}
		
		if (processTime < 0) {
			throw new IllegalArgumentException("Process time cannot be less than 0.");
		}
		
		this.arrivalTime = arrivalTime;
		this.processTime = processTime;
		this.waitTime = 0;
		this.waitingProcessing = false;
	}

	/**
	 * Gets the arrival time of a cart
	 * @return the arrivalTime
	 */
	public int getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * Gets the wait time of a cart
	 * @return the waitTime
	 */
	public int getWaitTime() {
		return waitTime;
	}

	/**
	 * Sets the wait time of a cart
	 * @param waitTime the waitTime to set
	 */
	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

	/**
	 * Returns the time it takes to process a cart
	 * @return the processTime
	 */
	public int getProcessTime() {
		return processTime;
	}
	
	/**
	 * Gets the index where the cart is checking out at
	 * @return the index of the register where the cart is checking out at
	 */
	public int getRegisterIndex() {
		return registerIndex;
	}
	
	/**
	 * Gets whether or not the cart is waiting in line
	 * @return the waitingProcessing
	 */
	public boolean isWaitingInRegisterLine() {
		return waitingProcessing;
	}
	
	/**
	 * Removes a cart from the waiting line
	 */
	public void removeFromWaitingLine () {
		//this.registerIndex = INITIAL_REGISTER_IDX;
		waitingProcessing = false;
	}
	
	/**
	 * Sets which lane a specific cart is checking out at
	 * @param lineIndex where a cart is located at a register
	 */
	protected void setRegisterIndex(int lineIndex) {
		this.registerIndex = lineIndex;
		waitingProcessing = true;
	}
	
	/**
	 * Adds a cart to a register
	 * @param register the array of registers in a store
	 */
	public abstract void getInLine (CheckoutRegister[] register);
	
	/**
	 * Returns the color of a specific cart
	 * @return Color the color of a specific type of cart
	 */
	public abstract Color getColor();
	
	
}
