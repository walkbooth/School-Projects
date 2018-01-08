package edu.ncsu.csc316.airline_mileage.list;

/**
 * The array-based list data structure used in P1P2 of CSC316
 * @author Walker Booth (wgbooth)
 *
 * @param <E> the generic type
 */
public class ArrayList<E> implements List <E> {

	/** The current capacity of this instance of ArrayList */
	private int capacity;
	
	/** The size of this instance of ArrayList */
	private int size;
	
	/** The array that this data structure is based on */
	private E[] list;
	
	
	/**
	 * Constructs an ArrayList object
	 */
	@SuppressWarnings("unchecked")
	public ArrayList () {
		capacity = 10;
		size = 0;
		list = (E[]) new Object[capacity];
	}
	
	@Override
	public void add(E element) {

		if (size == capacity) {
			growArray();
		}
		
		list[size] = element;
		size++;
	}

	@Override
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

	/**
	 * Sorts this ArrayList object using a bubble sort. 
	 */
	@SuppressWarnings("unchecked")
	public void sort() {
		for (int i = size - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (((Comparable<E>) list[j]).compareTo(list[j + 1]) > 0) {
					E temp = list[j];
					list[j] = list[j + 1];
					list[j + 1] = temp;
				}
			}
		}
	}

	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns the index of an element to search for using a binary search 
	 * @param element the element to search for 
	 * @return the index of the element to find if found, -1 if not 
	 */
	@SuppressWarnings("unchecked")
	public int indexOf (E element) {
		int mid, low = 0, high = size - 1;
		if (size >= 1 && size <= 2) {
			for (int i = 0; i < size; i++) {
				if(((Comparable<E>) element).compareTo(list[i]) == 0) {
					return i;
				}
			}
		}
		while (low <= high) {
			mid = (low + high) / 2;
			
			if (((Comparable<E>) element).compareTo(list[mid]) < 0) {
				high = mid - 1;
			} else if (((Comparable<E>) element).compareTo(list[mid]) > 0) {
				low = mid + 1;
			} else {
				return mid;
			}
		}
		
		return -1;
	}	
}
