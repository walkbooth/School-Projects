/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * The stack data structure using an array.
 * 
 * @author Walker Booth
 * @author Alex Johnson
 * @author Yuquan 
 * @param <E> the type of the element stored in ArrayStack
 */
public class ArrayStack<E> implements Stack<E> {
	/** The list that holds the stack */
	private ArrayList<E> list;
	
	/** The capacity of the list */
	private int capacity;
	
	/**
	 * Creates an ArrayStack for a given capacity
	 * 
	 * @param capacity the maximum number of elements to be stored in the stack
	 * @throws IllegalArgumentException if the capacity is less than 1
	 */
	public ArrayStack(int capacity) {
		list = new ArrayList<E>();
		this.capacity = capacity;
	}
	
	/**
	 * Pushes an element onto the stack
	 *
	 * @param element the element to push
	 * @throws IllegalArgumentException if the element exceeds capacity
	 */
	@Override
	public void push(E element) {
		if (list.size() == capacity) {
			throw new IllegalArgumentException();
		}
		list.add(list.size(), element);
	}

	/**
	 * Pops an element off the stack
	 * 
	 * @return the element popped off the stack
	 * @throws EmptyStackException if there is no element to pop
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
		} else if (capacity < list.size()) {
			throw new IllegalArgumentException("Capacity cannot be less than the current size");
		}

		this.capacity = capacity;
	}
}
