package edu.ncsu.csc316.airline_mileage.list;

/**
 * The linked list data structure used in P1P2 of CSC316.
 * @author Walker Booth
 *
 * @param <E> the generic type 
 */
public class LinkedList<E> implements List<E> {

	/** The size of the list */
	private int size;
	
	/** The front of the list */
	private Node front;
	
	/**
	 * Creates a LinkedList object 
	 */
	public LinkedList () {
		size = 0;
		front = null;
	}
	
	@Override
	public void add(E element) {
		if (front == null) {
			front = new Node (element);
		} else {
			Node current = front;
			for (int i = 0; i < size - 1; i++) {
				current = current.next;
			}
			
			current.next = new Node (element);
		}
		size++;
		
	}

	@Override
	public E get(int index) {
		if (size == 0) {
			throw new IllegalArgumentException("List is empty, cannot retrieve item.");
		}
		
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Requested index is out of bounds.");
		}
		
		Node current = front;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		
		return current.data;
	}
	
	@Override
	public int size() {

		return size;
	}
	
	/**
	 * The object that represents a single element of a Linked List
	 * @author Walker Booth (wgbooth)
	 *
	 */
	private class Node {
		
		/** The data stored by this instance of a Node */
		private E data;
		/** The next node in the list */
		private Node next;
		
		public Node (E data) {
			
			this.data = data;
			this.next = null;
		}
		
	}
	
}




	