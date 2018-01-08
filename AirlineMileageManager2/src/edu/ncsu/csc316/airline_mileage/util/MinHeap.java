package edu.ncsu.csc316.airline_mileage.util;

/**
 * Class for implementation of MinHeap data structure. Made based on algorithms in slides from Dr. Stallman, 10-priority_queues.
 * @author Walker Booth (wgbooth)
 * @param <E> This class works with generic types
 */
public class MinHeap<E> {
	
	private E[] heap;
	private int size;
	private int capacity;
	
	/**
	 * Constructs a MinHeap object
	 */
	@SuppressWarnings("unchecked")
	public MinHeap() {
		capacity = 8;
		size = 1;
		heap = (E[])(new Object[capacity]);
	}
	
	/**
	 * Inserts an object into the minheap
	 * @param data the object to insert 
	 */
	public void insert(E data) {
		if (size >= capacity) {
			growArray();
		}
		heap[size] = data;
		upheap();
		size++;
		
	}
	
	/**
	 * Restores heap-order property after an insert
	 * @param currentPos the current position in the heap 
	 */
	@SuppressWarnings("unchecked")
	private void upheap() {
		int currentPos = size;
		while (heap[currentPos / 2] != null && 
			   ((Comparable<E>)heap[currentPos]).compareTo( (E)(Comparable<E>)heap[currentPos / 2]) < 0) {
			E temp = heap[currentPos];
			heap[currentPos] = heap[currentPos / 2]; 
			heap[currentPos / 2] = temp;
			currentPos /= 2;
		}
	}
	
	/**
	 * Removes the smallest object (which should be at the top) from the minheap
	 * @return the element removed
	 */
	public E removeMin () {
		if (size == 1) {
			throw new IllegalArgumentException("Nothing to remove from heap, heap is empty.");
		}
		
		E toReturn = heap[1];
		size--;
		heap[1] = heap[size];
		heap[size] = null;
		downheap();
		return toReturn;
	}
	
	@SuppressWarnings("unchecked")
	private void downheap () {
		int currentPos = 1;
		boolean heapOrder = false;
		while (!heapOrder && currentPos * 2 < capacity && heap[currentPos * 2] != null) {
			/** If we have heap order */
			if (((Comparable<E>)heap[currentPos]).compareTo( (E)(Comparable<E>)heap[currentPos * 2]) < 0 && 
				(heap[currentPos * 2 + 1] == null || ((Comparable<E>)heap[currentPos]).compareTo( (E)(Comparable<E>)heap[currentPos * 2 + 1]) < 0)) {
				heapOrder = true;
			/** If left is the smaller element, swap with it */
			} else if ( heap[currentPos * 2 + 1] != null && ((Comparable<E>)heap[currentPos * 2]).compareTo( (E)(Comparable<E>)heap[currentPos * 2 + 1]) < 0){
				E temp = heap[currentPos];
				heap[currentPos] = heap[currentPos * 2]; 
				heap[currentPos * 2] = temp;
				currentPos *= 2;
			/** If right is the smaller element, swap with it */
			} else if (heap[currentPos * 2 + 1] != null && ((Comparable<E>)heap[currentPos * 2]).compareTo( (E)(Comparable<E>)heap[currentPos * 2 + 1]) > 0) {
				E temp = heap[currentPos];
				heap[currentPos] = heap[currentPos * 2 + 1]; 
				heap[currentPos * 2 + 1] = temp;
				currentPos = currentPos * 2 + 1;
			} else if (((Comparable<E>)heap[currentPos]).compareTo( (E)(Comparable<E>)heap[currentPos * 2]) > 0) {
				E temp = heap[currentPos];
				heap[currentPos] = heap[currentPos * 2]; 
				heap[currentPos * 2] = temp;
				currentPos *= 2;
			}
		}
	}
	
	/**
	 * Grows the array, if length is past capacity 
	 */
	@SuppressWarnings("unchecked")
	private void growArray() {
		capacity *= 2;
	
		E[] temp = heap;
		heap = (E[]) new Object[capacity];	
		for (int i = 1; i < temp.length; i++) {
			heap[i] = temp[i];
		}
	}
	
	/**
	 * Returns the size of the list 
	 * @return (size-1) the size of the list 
	 */
	public int size() {
		return size - 1;
	}

}
