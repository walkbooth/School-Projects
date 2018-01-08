/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Interface for the methods which must be implemented by a stack
 * @author Walker Booth
 * @author Alex Johnson
 * @author Yuquan 
 * @param <E> generic type 
 */
public interface Stack<E> {
	
	/**
	 * Adds an element to the stack 
	 * @param element the element to add to the stack 
	 * @throws IllegalArgumentException if the max capacity has been reached
	 */
	public void push (E element);
	
	/**
	 * Removes an element from the stack 
	 * @return element the element removed
	 */
	public E pop ();
	
	/**
	 * Returns whether or not the stack is empty
	 * @return true if the stack is empty, false if not
	 */
	public boolean isEmpty();
	
	/**
	 * Returns the size of the stack
	 * @return size the size of the stack
	 */
	public int size ();
	
	/**
	 * Sets the capacity of the stack
	 * @param capacity the capacity of the stack
	 * @throws IllegalArgumentException if the capacity is negative or less than the current size of the stack.
	 */
	public void setCapacity(int capacity);
}
