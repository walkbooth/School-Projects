/**
 * 
 */
package edu.ncsu.csc216.checkout_simulator.queues;

import edu.ncsu.csc216.checkout_simulator.items.Cart;
import edu.ncsu.csc216.checkout_simulator.simulation.Log;

/**
 * Stores data about a checkout register
 * @author Walker Booth
 *
 */
public class CheckoutRegister implements LineOfItems {

	/** The ShoppingCartQueue of carts waiting for or being processed at this register */
	private ShoppingCartQueue line;
	
	/** The log for this checkout register */
	private Log log;
	
	/** The time when the line for this checkout register will finally be clear of all carts currently in line */
	private int timeWhenAvailable;
	
	/**
	 * Constructor for a CheckoutRegister object
	 * @param log the log of a register
	 */
	public CheckoutRegister(Log log) {
		this.line = new ShoppingCartQueue(); 
		this.log = log;
		timeWhenAvailable = 0;
	}
	
	/* (non-Javadoc)
	 * @see edu.ncsu.csc216.checkout_simulator.queues.LineOfItems#hasNext()
	 */
	@Override
	public boolean hasNext() {
		if (line.isEmpty()) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc216.checkout_simulator.queues.LineOfItems#processNext()
	 */
	@Override
	public Cart processNext() {
		Cart c = line.remove();
		c.removeFromWaitingLine();
		log.logCart(c);
		return c;
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc216.checkout_simulator.queues.LineOfItems#departTimeNext()
	 */
	@Override
	public int departTimeNext() {
		if (line.isEmpty()) {
			return Integer.MAX_VALUE;
		} else {
			return line.front().getArrivalTime() + line.front().getProcessTime() + line.front().getWaitTime();
		}
		
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc216.checkout_simulator.queues.LineOfItems#size()
	 */
	@Override
	public int size() {
		return line.size();
	}
	
	/**
	 * Adds a cart to a checkout line
	 * @param cart the cart to be added
	 */
	public void addCartToLine(Cart cart) {
		if (line.isEmpty()) {
			cart.setWaitTime(0);
		} else {
			cart.setWaitTime(timeWhenAvailable - cart.getArrivalTime());
		}
		line.add(cart);
		timeWhenAvailable = cart.getArrivalTime() + cart.getProcessTime() + cart.getWaitTime();
		
	}

}
