package edu.ncsu.csc316.airline_mileage.util;

/**
 * An array-based list data structure (From Project 1)
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

	/**
	 * Returns an element from the index requested in the list 
	 * @param i the index to retrieve the element from 
	 * @return list[i] the element at the supplied index 
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

	/**
	 * Sorts this ArrayList object using a bubble sort. 
	 */
	public void sort() {
		/*
		for (int i = size - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (((Comparable<E>) list[j]).compareTo(list[j + 1]) > 0) {
					E temp = list[j];
					list[j] = list[j + 1];
					list[j + 1] = temp;
				}
			}
		}*/
		list = mergeSort(list, size);
	}
	
	@SuppressWarnings("unchecked")
	private E[] mergeSort(E[] newList, int listLength) {
		if (listLength > 1) {
			
			int leftLength = (listLength + 1) / 2;
			int rightLength = listLength / 2;
			E[] left = (E[]) new Object[leftLength];
			E[] right = (E[]) new Object[rightLength];
			
			for (int i = 0; i < leftLength; i++) {
				left[i] = newList[i];
			}
			
			for (int i = leftLength; i < listLength; i++) {
				right[i - leftLength] = newList[i];
			}
			
			if (left[leftLength - 1] == null) {
				leftLength--;
			}
			if (right[rightLength - 1] == null) {
				rightLength--;
			}
			
			left = mergeSort(left, leftLength);
			right = mergeSort(right, rightLength);
			return merge(left, right, leftLength, rightLength);
		} else {
			return newList;
		}
	}

	@SuppressWarnings("unchecked")
	private E[] merge(E[] left, E[] right, int leftLength, int rightLength) {
		int newSize = leftLength + rightLength;
		E[] newList = (E[]) new Object[newSize];
		int leftPos = 0;
		int rightPos = 0;
		int index = 0;
		
		for (index = 0; leftPos < leftLength && rightPos < rightLength && index < newSize; index++) {
			if (((Comparable<E>) left[leftPos]).compareTo(right[rightPos]) < 0) {
				newList[index] = left[leftPos];
				leftPos++;
			} else {
				newList[index] = right[rightPos];
				rightPos++;
			}
		}
		
		if (leftPos < leftLength) {
			while (leftPos < leftLength) {
				newList[index] = left[leftPos];
				index++;
				leftPos++;
			}
		} else if (rightPos < rightLength) {
			while (rightPos < rightLength) {
				newList[index] = right[rightPos];
				index++;
				rightPos++;
			}
		}
		
		return newList;
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
