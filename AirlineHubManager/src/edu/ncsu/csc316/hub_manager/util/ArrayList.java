package edu.ncsu.csc316.hub_manager.util;

/**
 * This class is a slightly modified version of the ArrayList class used in Project 1 Part 2. 
 * @author Walker Booth (wgbooth)
 * @param <E> This class works with generic types.
 *
 */
public class ArrayList<E> {

	/** The current capacity of this instance of ArrayList */
	private int capacity;

	/** The size of this instance of ArrayList */
	private int size;

	/** The array that this data structure is based on */
	private E[] list;


	/**
	 * Returns the size of the ArrayList
	 * @return size the size of the ArrayList
	 */
	public int size () {
		return size;
	}
	
	/**
	 * Constructs an ArrayList object
	 */
	@SuppressWarnings("unchecked")
	public ArrayList () {
		capacity = 10;
		size = 0;
		list = (E[]) new Object[capacity];
	}

	/**
	 * Adds an element to the list
	 * @param element the element to add
	 */
	public void add(E element) {

		if (size == capacity) {
			growArray();
		}

		list[size] = element;
		size++;
	}

	/**
	 * Returns an element from the list 
	 * @param i the index of the element to retrieve 
	 * @return list[i] the element retrieved from the list 
	 */
	public E get(int i) {
		if (size == 0) {
			throw new IllegalArgumentException("List is empty, cannot retrieve item.");
		}

		if (i < 0 || i >= size) {
			throw new IndexOutOfBoundsException("Requested index is out of bounds.");
		}

		return list[i];

	}

	@SuppressWarnings("unchecked")
	private void growArray () {
		capacity *= 2;

		E[] temp = list;
		list = (E[]) new Object[capacity];	
		for (int i = 0; i < size; i++) {
			list[i] = temp[i];
		}
	}

}
