package edu.ncsu.csc316.airline_mileage.util;

/**
 * The interface which lists must extend in P1P2 of CSC316. (From Project 1)
 * @author Walker Booth (wgbooth)
 *
 * @param <E> the generic type 
 */
public interface List<E> {

	/**
	 * Adds a generic element to the list
	 * @param element the element to add to the list
	 */
	public void add (E element);
		
	/**
	 * Returns the size of the list 
	 * @return size the size of the list 
	 */
	public int size ();
	
}
