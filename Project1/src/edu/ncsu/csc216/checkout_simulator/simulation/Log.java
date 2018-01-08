/**
 * 
 */
package edu.ncsu.csc216.checkout_simulator.simulation;

import edu.ncsu.csc216.checkout_simulator.items.Cart;

/**
 * Logs data about all of the carts
 * @author Walker Booth
 */
public class Log {

	/** Number of carts processed */
	private int numCompleted;
	/** Total wait time of all carts */
	private int totalWaitTime;
	/** Total process time of all carts */
	private int totalProcessTime;
	
	/**
	 * Constructs a log object (null constructor)
	 */
	public Log () {
		this.numCompleted = 0;
		this.totalWaitTime = 0;
		this.totalProcessTime = 0;
	}
	
	/**
	 * Gets the number of carts processed
	 * @return numCompleted the number of carts processed
	 */
	public int getNumCompleted () {
		return numCompleted;
	}
	
	/**
	 * Logs a cart
	 * @param cart the cart to add to the log
	 */
	public void logCart(Cart cart) {
		this.numCompleted++;
		this.totalWaitTime += cart.getWaitTime();
		this.totalProcessTime += cart.getProcessTime();
	}
	
	/**
	 * Calculates the average wait time of all carts
	 * @return  averageWaitTime the average wait time of all carts
	 */
	public double averageWaitTime() {
		if (numCompleted == 0) {
			return 0;
		} else {
			return (double)(totalWaitTime) / (double)(numCompleted);

		}
	}
	
	/**
	 * Calculates the average process time of all carts
	 * @return averageProcessTime the average process time of all carts
	 */
	public double averageProcessTime() {
		if (numCompleted == 0) {
			return 0;
		} else {
			return (double)(totalProcessTime) / (double)(numCompleted);
		}

	}
	
}
