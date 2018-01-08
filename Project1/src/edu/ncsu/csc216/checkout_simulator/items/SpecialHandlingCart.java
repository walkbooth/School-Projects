/**
 * 
 */
package edu.ncsu.csc216.checkout_simulator.items;

import java.awt.Color;

import edu.ncsu.csc216.checkout_simulator.queues.CheckoutRegister;

/**
 * A cart which requires special handling and a special checkout lane
 * @author Walker Booth
 */
public class SpecialHandlingCart extends Cart {

	/** The color of a special cart */
	private Color color = Color.red;
	
	/**
	 * Constructs a special handling cart object
	 * @param arrivalTime the time that the cart arrives to a register
	 * @param processTime the time that it takes to process the cart
	 */
	public SpecialHandlingCart(int arrivalTime, int processTime) {
		super(arrivalTime, processTime);
	}

	/* (non-Javadoc)
	 * @see edu.ncsu.csc216.checkout_simulator.items.Cart#getInLine(edu.ncsu.csc216.checkout_simulator.queues.CheckoutRegister[])
	 */
	@Override
	public void getInLine(CheckoutRegister[] register) {
		int min = 100000, 
		    minIndex = 0,
		    startingRegister = (int) Math.ceil(((double)register.length - 1) * .75);
		super.removeFromWaitingLine();
		//Determines shortest valid lane
		for (int i = startingRegister; i < register.length; i++) {
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
