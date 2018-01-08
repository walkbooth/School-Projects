package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Recursive implementation of a LinkedList
 * @author Walker Booth
 * @author Alex Johnson
 * @author Yuquan Cui
 * @param <E> the generic parameter
 */
public class LinkedListRecursive<E> {
	/** The Front node of the List */
	private ListNode front;
	/** The size of the list */
	private int size;
	
	/** 
	 * Constructs a new LinkedListRecursive object
	 */
	public LinkedListRecursive () {
		front = null;
		size = 0;
	}
	
	/**
	 * Returns the size of the list
	 * @return size the size of the list
	 */
	public int size () {
		return size;
	}
	
	/**
	 * Returns whether or not the list is empty
	 * @return true if the list is empty, false if not
	 */
	public boolean isEmpty () {
		return size == 0;
	}
	
	/**
	 * Checks if the list contains a specific element
	 * @param data the data to find
	 * @return true if the list contains data, false if not
	 */
	public boolean contains (E data) {
		if (front == null) {
			return false;
		} else {
			return front.contains(data);
		}
	}	
	
	/**
	 * Adds an element to the end of the list
	 * @param data the data to add
	 * @return true if the addition was successful, false if not
	 */
	public boolean add (E data) {
		if (this.contains(data)) {
			return false;
		}
		
		if (contains(data)) {
			throw new IllegalArgumentException();
		}
		
		if (front == null) {
			size++;
			front = new ListNode(data, null);
			return true;
		} else {
			front.add(size, data);
			return true;
		}
		
	}
	/**
	 * Adds an element to a specific location in the list
	 * @param idx the index where the element should be added
	 * @param data the data to be added to the list
	 */
	public void add (int idx, E data) {
		if (idx < 0 || idx > size) {
			throw new IndexOutOfBoundsException();
		}
		if(data == null)
		{
			throw new NullPointerException();
		}
		if (this.contains(data)) {
			throw new IllegalArgumentException();
		}
		
		if (contains(data)) {
			throw new IllegalArgumentException();
		}
		
		if (front == null) {
			size++;
			front = new ListNode(data, null);
		} else if (idx == 0) {
			ListNode l = new ListNode(data, front);
			front = l;
			size++;
		} else {
			front.add(idx, data);
		}
	}
	
	/**
	 * Gets a specific element from the list
	 * @param idx the index to get from
	 * @return element the element at the specified index
	 */
	public E get (int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		if (front == null) {
			return null;
		} else {
			return front.get(idx);
		}	
	}
	
	/**
	 * Removes an element at a specific index from the list
	 * @param idx the index to remove from
	 * @return element the element at the specified index 
	 */
	public E remove (int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
		} else if (idx == 0) {
			E e = front.data;
			front = front.next;
			size--;
			return e;
		} else {
			return front.remove(idx);
		}	
	}
	
	/**
	 * Removes an element from the list
	 * @param data the element to find and remove
	 * @return true if the element was found and removed, false if not
	 */
	public boolean remove (E data) {
		if (data == null) {
			return false;
		} else if (front == null) {
			return false;
		} else if (front.data == data) {
			front = front.next;
			size--;
			return true;
		} else if (contains(data)){
			front.remove(data);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Sets the data at a specific index to a new value 
	 * @param idx the index to change the data at
	 * @param data the new element 
	 * @return element the old element at the specified index 
	 */
	public E set (int idx, E data) {
		if (data == null) {
			throw new NullPointerException();
		}
		
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
		} else if (contains(data)) {
			throw new IllegalArgumentException();
		} else if (front == null) {
			return null;
		} else if (idx == 0) {
			E e = front.data;
			front.data = data;
			return e;
		} else {
			return front.set(idx, data);
		}	
		
		
	}
	
	/**
	 * Stores a single element int the list
	 */
	private class ListNode {
		/** The data in the node */
		public E data;
		/** The next node in the list */
		public ListNode next;
		
		/**
		 * Constructs a new ListNode for data and next element
		 * @param data the data of the node
		 * @param next the next element in the list
		 */
		ListNode (E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
		
		public void add (int idx, E data) {			
			if (idx == 1 || next == null) {
				size++;
				next = new ListNode(data, next);
			}  else {
				next.add(idx - 1, data);
			}
		}
		
		public E get (int idx) {
			if (idx == 0) {
				return data;
			} else {
				return next.get(idx - 1);
			}
		}
		
		public E remove (int idx) {
			if(idx <= 1) {
				E e = next.data;
				next = next.next;
				size--;
				return e;
			} else {
				return next.remove(idx - 1);	
			}
		}
		
		public boolean remove (E data) {
			if (next == null) {
				return false;
			} else if (next.data == data) {
				next = next.next;
				size--;
				return true;
			} else {
				return next.remove(data);
			}
		}
		
		public E set (int idx, E data) {
			if(idx < 0 || idx > size) {
				throw new IndexOutOfBoundsException();
			} else if (data == null) {
				throw new NullPointerException();
			} else if(idx == 0) {
				E e = this.data;
				this.data = data;	
				return e;
			} else {
				return next.set(idx - 1, data);
			}
		}
		
		public boolean contains (E data) {
			if (this.data == data) { // if THIS node contains data return true
				return true;
			} else { // else check the next node
				if (next == null) { // check if we're at the end of the list
					return false;
				}
				return next.contains(data);
			}
		}
		
	}
	
}
