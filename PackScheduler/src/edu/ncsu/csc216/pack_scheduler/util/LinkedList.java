package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Contains data in the form of a LinkedList
 * @author Walker Booth
 * @author Alex Johnson
 * @author Yuquan Cui
 * @param <E> the generic parameter
 */
public class LinkedList<E> extends AbstractSequentialList<E> {

	/** The ListNode at the front of the LinkedList */
	private ListNode front;
	/** The ListNode at the back of the LinkedList */
	private ListNode back;
	/** The size of the LinkedList */
	private int size;
	
	/**
	 * Constructor for a new LinkedList
	 */
	public LinkedList () {
		// creating front and back buffer elements 
		front = new ListNode (null);
		back = new ListNode (null, front, null);
		front.next = back;
		size = 0;
	}
	
	@Override
	public int size() {
		return size;
	}

	/* (non-Javadoc)
	 * @see java.util.AbstractSequentialList#add(int, java.lang.Object)
	 */
	@Override
	public void add(int index, E element) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		
		if(contains(element)) {
			throw new IllegalArgumentException("Duplicate element");
		} else {
			super.add(index, element);
		}	
	}


	/* (non-Javadoc)
	 * @see java.util.AbstractSequentialList#set(int, java.lang.Object)
	 */
	@Override
	public E set(int index, E element) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		if(contains(element)) {
			throw new IllegalArgumentException("Duplicate element");
		} else {
			return super.set(index, element);
		}
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		LinkedListIterator listIterator = new LinkedListIterator(index);
		return listIterator;
	}
	
	/**
	 * List node for the LinkedList
	 * @author Walker Booth
	 * @author Alex Johnson
	 * @author Yuquan Cui
	 */
	private class ListNode {
		
		/** The data contained by this ListNode */
		private E data;
		/** The previous node referenced by this ListNode */
		private ListNode previous;
		/** The next node referenced by this ListNode */
		private ListNode next;
		
		/**
		 * The constructor for ListNode which adds data to the back of the list 
		 * @param data the data for the ListNode to store
		 */
		public ListNode(E data) {
			this.data = data;
			this.previous = null;
			this.next = null;
		}
		
		/**
		 * The constructor for ListNode which adds data to a specific position in the
		 * list
		 * @param data the data for the ListNode to store 
		 * @param prev the node before the new node
		 * @param next the node after the new node
		 */
		public ListNode(E data, ListNode prev, ListNode next) {
			this.data = data;
			this.previous = prev;
			this.next = next;
		
		}
		
		
	}
	
	/**
	 * Iterates through the list in multiple directions
	 * @author Walker Booth
	 * @author Alex Johnson
	 * @author Yuquan Cui
	 */
	private class LinkedListIterator implements ListIterator<E> {

		/** represents the ListNode that would be returned on a call to previous() */
		private ListNode previous;
		/** represents the ListNode that would be returned on a call to next() */
		private ListNode next;
		/** the index that would be returned on a call to previousIndex() */
		private int previousIndex;
		/** the index that would be returned on a call to nextIndex() */
		private int nextIndex; 
		/** represents the ListNode that was returned on the last call to either previous() or next() or null */
		private ListNode lastRetrieved; 
		
		/**
		 * Created a new iterator at a give index
		 * @param index the index to place the iterator
		 */
		public LinkedListIterator(int index) { 
			if (index <= -1 || index > size) { // if invalid index throw exception
				throw new IndexOutOfBoundsException();
			}
			
			// start iterator in initial position
			previousIndex = -1;
			nextIndex = 0;
			previous = front;
			next = previous.next;
			
			// get to the first element of the iterator
			while (nextIndex < index)
				next();
			
			// set next to current
			lastRetrieved = null;
		}

		/** 
		 * If the iterator has a next element
		 * @return if the iterator has a next element
		 */
		@Override  
		public boolean hasNext() {
			return next.data != null;
		}

		/** 
		 * Iterates forward once 
		 * @return the data in the next element
		 */
		@Override
		public E next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			} else {
				// increment indices
				nextIndex++;
				previousIndex++;
				previous = next;
			    next = next.next;  // <Iterator>next equals its next node

				lastRetrieved = previous;
				return lastRetrieved.data;
			}
		}

		/** 
		 * If the iterator has a previous element
		 * @return if the iterator has a previous element
		 */
		@Override
		public boolean hasPrevious() {
			return previous.data != null;	
		}

		/** 
		 * Iterates backwards once 
		 * @return the data in the previous element
		 */
		@Override
		public E previous() {
			if(!hasPrevious()) {
				throw new NoSuchElementException();
			} else {
				// decrement indices
				nextIndex--;
				previousIndex--;
				next = previous;
			    previous = previous.previous;  // <Iterator>previous equals its previous node
			    
				lastRetrieved = next;
				return lastRetrieved.data;
			}
		}

		/** 
		 * Gets the index of the next element
		 * @return the index of the next element
		 */
		@Override
		public int nextIndex() {
			return nextIndex;
		}

		/** 
		 * Gets the index of the previous element
		 * @return the index of the previous element
		 */
		@Override
		public int previousIndex() {
			return previousIndex;
		}

		/** 
		 * Removes the current element from the list
		 * @throws IllegalStateException if the last operation from add or remove
		 */
		@Override
		public void remove() {
			if (lastRetrieved == null) {
				throw new IllegalStateException();
			}
			
			next.previous = previous.previous;
			previous.previous.next = next;
			previous = previous.previous;
			size--;
			lastRetrieved = null;
		}

		/** 
		 * Sets the current node's data
		 * @param e the data to store in the current element
		 * @throws NullPointerException if the data is null
		 * @throws IllegalStateException if the last operation from add or remove
		 */
		@Override
		public void set(E e) {
			if(e == null) {
			    throw new NullPointerException();
		    } else if (lastRetrieved == null) {
				throw new IllegalStateException();
		    }
			
	    	lastRetrieved.data = e;
		} 

		/** 
		 * Adds an element to the list
		 * @param e the data to store in the current element
		 * @throws NullPointerException if the data is null
		 */
		@Override
		public void add(E e) {
			if (e == null) {
				throw new NullPointerException();
			}
			ListNode newElement = new ListNode(e, previous, next);
			previous.next = newElement;
			next.previous = newElement;
			size++;
			lastRetrieved = null;
		}
	}
}
