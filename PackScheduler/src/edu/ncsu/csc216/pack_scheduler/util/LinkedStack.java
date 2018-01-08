/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * The stack data structure using the linked node pattern.
 * 
 * @author Walker Booth
 * @author Alex Johnson
 * @author Yuquan 
 * @param <E> the type of the elements in the LinkedStack
 */
public class LinkedStack<E> implements Stack<E> {
	/** The LinkedList containing the stack */
	private LinkedAbstractList<E> list;
	
	/**
	 * Creates a LinkedStack for a given capacity
	 * 
	 * @param capacity the maximum number of elements to be stored in the Stack
	 * @throws IllegalArgumentException if the capacity is less than 1
	 */
	public LinkedStack(int capacity) {
		list = new LinkedAbstractList<E>(capacity);
	}
	
	/**
	 * Pushes an element onto the stack
	 * 
	 * @param element the element to push
	 * @throws IllegalArgumentException if the element exceeds capacity
	 */
	@Override
	public void push(E element) {
		list.add(list.size(), element);
	}

	/**
	 * Pops an element off the stack
	 * 
	 * @return the element popped off the stack
	 * @throws IllegalArgumentException if there is no element to pop
	 */
	@Override
	public E pop() {
		if(list.size() == 0)
			throw new EmptyStackException();
		E toPop = list.remove(list.size() - 1);
		return toPop;
	}

	/**
	 * Determines if the stack is empty 
	 * @return true if the stack is empty, false if not
	 */
	@Override
	public boolean isEmpty() {
		return list.size() == 0;
	}

	/**
	 * Gets the size of the stack
	 * 
	 * @return the size of the stack
	 */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * Sets the capacity of the stack
	 * 
	 * @param capacity the capacity to set
	 * @throws IllegalArgumentException if the new capacity is less than the current size
	 */
	@Override
	public void setCapacity(int capacity) {
		if (capacity < 1) {
			throw new IllegalArgumentException("Capacity cannot be less than 1");
		}
		
		if (capacity < list.size()) {
			throw new IllegalArgumentException("Capacity cannot be less than the current size");
		}
		
		list.setCapacity(capacity);
	}

}
