package edu.ncsu.csc316.airline_mileage.list;

/**
 * The interface which lists must extend in P1P2 of CSC316.
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
	 * Returns one requested element from the list
	 * @param index the index where the element is located
	 * @return e the element to retrieve from the list
	 */
	public E get (int index);
		
	/**
	 * Returns the size of the list 
	 * @return size the size of the list 
	 */
	public int size ();
	
}
