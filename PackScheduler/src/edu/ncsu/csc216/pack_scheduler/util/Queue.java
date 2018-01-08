/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Interface for ArrayQueue and LinkedQueue
 * @author Walker Booth
 * @author Alex Johnson
 * @author Yuquan 
 * @param <E> the generic parameter  
 */
public interface Queue<E> {
	/**
	 * Adds the element to the back of the Queue
     * If there is no room (capacity has been reached), 
     * an IllegalArgumentException is thrown
	 * @param element the element that is gonna be added at the end of the queue
	 */
	void enqueue(E element);
	/**
	 * Removes and returns the element at the front of the Queue
     * If the Queue is empty, an NoSuchElementException is thrown
	 * @return the element that is dequeued
	 */
	E dequeue();
	/**
	 * Returns true if the Queue is empty
	 * @return true if it is false if it is not
	 */
	boolean isEmpty();
	/**
	 * Returns the size of the queue
	 * @return the size of the queue
	 */
	int size();
	/**
	 * set the Sets the Queue's capacity
     * If the actual parameter is negative or if it is less 
     * than the number of elements in the Queue, an 1IllegalArgumentException is thrown
	 * @param capacity Queue's capacity
	 */
	void setCapacity(int capacity);

}
