/**
 * 
 */
package edu.ncsu.csc216.checkout_simulator.items;

import java.awt.Color;

import edu.ncsu.csc216.checkout_simulator.queues.CheckoutRegister;

/**
 * A type of cart which go in an express lane
 * @author Walker Booth
 */
public class ExpressCart extends Cart {

	/** The color of this cart */
	private Color color = Color.green;
	
	/**
	 * Constructs an express cart
	 * @param arrivalTime the time which the cart arrives
	 * @param processTime the time it takes to check a cart out
	 */
	public ExpressCart(int arrivalTime, int processTime) {
		super(arrivalTime, processTime);
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc216.checkout_simulator.items.Cart#getInLine(edu.ncsu.csc216.checkout_simulator.queues.CheckoutRegister[])
	 */
	@Override
	public void getInLine(CheckoutRegister[] register) {
		int min = 100000, minIndex = 0;
		super.removeFromWaitingLine();
		//Determines shortest valid lane
		for (int i = 0; i < register.length; i++) {
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
