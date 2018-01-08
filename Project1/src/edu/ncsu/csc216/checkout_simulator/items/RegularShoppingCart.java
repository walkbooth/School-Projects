/**
 * 
 */
package edu.ncsu.csc216.checkout_simulator.items;

import java.awt.Color;

import edu.ncsu.csc216.checkout_simulator.queues.CheckoutRegister;

/**
 * A regular shopping cart
 * @author Walker Booth
 */
public class RegularShoppingCart extends Cart {

	/** The color of a regular shopping cart */
	private Color color = Color.blue;
	
	/** 
	 * Constructs a regular shopping cart object
	 * @param arrivalTime the time at which the cart arrives in line
	 * @param processTime the time it takes for a cart to process
	 */
	public RegularShoppingCart(int arrivalTime, int processTime) {
		super(arrivalTime, processTime);
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc216.checkout_simulator.items.Cart#getInLine(edu.ncsu.csc216.checkout_simulator.queues.CheckoutRegister[])
	 */
	@Override
	public void getInLine(CheckoutRegister[] register) {
		int min = 100000, minIndex = 1;
		super.removeFromWaitingLine(); 
		//Determines shortest valid lane
		for (int i = 1; i < register.length; i++) {
			if (register[i].size() < min) {
				min = register[i].size();
				minIndex = i;
			}
		}
		register[minIndex].addCartToLine(this);
		this.setRegisterIndex(minIndex);
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc216.checkout_simulator.items.Cart#getColor()
	 */
	@Override
	public Color getColor() {
		return color;
	}

}
