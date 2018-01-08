/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * Stores the data in a LinkedAbstractList. Access is limited by a queue structure. 
 * @author Walker Booth
 * @author Alex Johnson
 * @author Yuquan 
 * @param <E> the type of the elements in the LinkedQueue
 */
public class LinkedQueue<E> implements Queue<E> {

    /** The list that stores the LinkedQueue*/
	private LinkedAbstractList<E> list;
	
	/**
	 * Creates a LinkedQueue for a given capacity
	 * 
	 * @param capacity the maximum number of elements to be stored in the queue
	 */
	public LinkedQueue(int capacity) {
		list = new LinkedAbstractList<E>(capacity);
	}
	
	/**
	 * Adds the element to the back of the Queue
     * If there is no room (capacity has been reached), 
     * an IllegalArgumentException is thrown
	 * @param element the element that is gonna be added at the end of the queue
	 */
	@Override
	public void enqueue(E element) {
		list.add(list.size(), element);
	}

	/**
	 * Removes and returns the element at the front of the Queue
     * If the Queue is empty, an NoSuchElementException is thrown
	 * @return the element that is dequeued
	 * @throws NoSuchElementException if there is no element to dequeue
	 */
	@Override
	public E dequeue() {
		if (list.size() == 0) {
			throw new NoSuchElementException();
		}
		return list.remove(0);
	}

	/**
	 * Returns true if the Queue is empty
	 * @return true if it is false if it is not
	 */
	@Override
	public boolean isEmpty() {
		return list.size() == 0;
	}

	/**
	 * Returns the list.size() of the queue
	 * @return the list.size() of the queue
	 */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * set the Sets the Queue's capacity
     * If the actual parameter is negative or if it is less 
     * than the number of elements in the Queue, an IllegalArgumentException is thrown
	 * @param capacity Queue's capacity
	 * @throws IllegalArgumentException if the capacity is less than the size
	 */
	@Override
	public void setCapacity(int capacity) {
		if (capacity < list.size()) {
			throw new IllegalArgumentException("Cannot set capacity to less than Queue size");
		}
		list.setCapacity(capacity);
	}

}
