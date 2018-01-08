/**
 * 
 */
package edu.ncsu.csc216.checkout_simulator.queues;

import edu.ncsu.csc216.checkout_simulator.items.Cart;
import edu.ncsu.csc216.checkout_simulator.items.CartFactory;

/**
 * Holds data about a store object
 * @author Walker Booth
 *
 */
public class Store implements LineOfItems {

	/** The queue of shopping carts */
	private ShoppingCartQueue shopping;
	/** The array of registers in the store */
	private CheckoutRegister[] register;
	
	/**
	 * Constructs a store object and generates the carts in the store
	 * @param numberOfCarts number of carts to build in store
	 * @param register the array of registers in the store
	 */
	public Store (int numberOfCarts, CheckoutRegister[] register) {
		shopping = new ShoppingCartQueue();
		this.register = register;
		for (int i = 0; i < numberOfCarts; i++) {
			shopping.add(CartFactory.createCart()); 
		}
	}
	
	/* (non-Javadoc)
	 * @see edu.ncsu.csc216.checkout_simulator.queues.LineOfItems#hasNext()
	 */
	@Override
	public boolean hasNext() {
		if (shopping.isEmpty()) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc216.checkout_simulator.queues.LineOfItems#processNext()
	 */
	@Override
	public Cart processNext() {
		Cart c = shopping.remove();
		c.getInLine(register);
		return c;
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc216.checkout_simulator.queues.LineOfItems#departTimeNext()
	 */
	@Override
	public int departTimeNext() {
		if (shopping.isEmpty()) {
			return Integer.MAX_VALUE;
		}
		return shopping.front().getArrivalTime();
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc216.checkout_simulator.queues.LineOfItems#size()
	 */
	@Override
	public int size() {
		return shopping.size();
	}

}
