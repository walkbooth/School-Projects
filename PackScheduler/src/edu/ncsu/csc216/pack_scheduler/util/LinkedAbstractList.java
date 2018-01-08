package edu.ncsu.csc216.pack_scheduler.util;
import java.util.AbstractList;
/**
 * The LinkedList constructor that handles the ListNodes
 * and their information.
 * @author James Ritchey (jtritche)
 *
 * @param <E> The object used in the list
 */
public class LinkedAbstractList<E> extends AbstractList<E> {
	/** The size of the list */
	private int size;
	/** The capacity of the list */
	private int capacity;
	/** The front node of the list */
	private ListNode front;
	
	/**
	 * Creates the LinkedList
	 * @param cap The max capacity
	 */
	public LinkedAbstractList(int cap){
		if(cap < 0){
			throw new IllegalArgumentException("List capacity can't be zero");
		}
		this.size = 0;
		this.capacity = cap;
		this.front = null;
	}
	
	/**
	 * Gets the data from the node at the specified index
	 * @param idx The index to access
	 */
	@Override
	public E get(int idx) {
		if(idx < 0 || idx >= size){
			throw new IndexOutOfBoundsException();
		}
		
		ListNode temp = front;
		for(int i = 0; i < idx; i++){
			temp = temp.next;
		}
		return temp.data;
	}
	
	/**
	 * Gets the size of the list
	 * @return Returns the list's size
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Gets if the list is empty
	 * @return Returns the list's size
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Sets the node at the specified index to 
	 * the specified value
	 * @param idx The index to set
	 * @param item The value to set the node to
	 * @return Returns the node's old data
	 */
	@Override
	public E set(int idx, E item){
		if(item == null){
			throw new NullPointerException();
		}
		ListNode temp = front;
		for(int i = 0; i < this.size; i++){
			if(item.equals(temp.data)){
				throw new IllegalArgumentException();
			}
			temp = temp.next;
		}
		if(idx < 0 || idx >= size()){
			throw new IndexOutOfBoundsException();
		}
		temp = front;
		for(int i = 0; i < idx; i++){
			temp = temp.next;
		}
		E toReturn = temp.data;
		temp.data = item;
		return toReturn;
	}
	
	/**
	 * Adds a new node at the specified index, or the end of the 
	 * list, using the given value
	 * @param idx The index to access
	 * @param item The value to set
	 */
	@Override
	public void add(int idx, E item){
		// Don't add element if list is full
		if(this.size == this.capacity){
			throw new IllegalArgumentException("List is at capactiy");
		}
		// Don't add item if it is null
		if(item == null){
			throw new NullPointerException();
		}
		// Don't 
		if(idx < 0 || idx > this.size){
			throw new IndexOutOfBoundsException();
		}
        // check for duplicate before adding		
		for(int i = 0; i < size; i++){
			if(this.get(i).equals(item)){
				throw new IllegalArgumentException();
			}
		}

		// We know it's a valid element, add it
		if(size == 0){
			ListNode newNode = new ListNode(item);
			front = newNode;
		} else {
			if (idx == 0) {
				front = new ListNode(item, front);
			} else {
				// Traverse though list to get to index to add at
				ListNode current = front;
				int i;
				for(i = 0; i < idx - 1; i++){
					current = current.next;
				}
				current.next = new ListNode(item, current.next);
				// Traverse though remainder of list to get back, if
				// already in back then current.next will already equal null
				while(current.next != null){
					current = current.next;
				}
			}
		}
		size++;
	}
	
	/**
	 * Removes the node at the specified index
	 * @param idx The index to remove
	 * @return Returns the removed nodes data
	 */
	@Override
	public E remove(int idx){
		if(idx < 0 || idx >= size){
			throw new IndexOutOfBoundsException();
		}
		
		if(size == 1){
			ListNode temp = front;
			front = null;
			size--;
			return temp.data;
		}
		
		ListNode toReturn = front;
		if(idx > 0){
			ListNode current = front;
			for(int i = 0; i < idx - 1; i++){
				current = current.next;
			}		
			toReturn = current.next;
			current.next = current.next.next;
			while(current.next != null){
				current = current.next;
			}
		} else {
			// Update front, will not change back
			toReturn = front;
			front = front.next;
		}
		size--;
		return toReturn.data;
	}
	
	/**
	 * Sets the capacity of the LinkedAbstractList
	 * @param capacity the capacity to set capacity to
	 */
	public void setCapacity(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException("Capacity cannot be less that 0");
		}
		this.capacity = capacity;
	}
	
	/**
	 * Creates the ListNodes for the LinkedList
	 * @author James Ritchey (jtritche)
	 */
	private class ListNode {
		/**The node's data*/
		private E data;
		/**The node following the current node*/
		private ListNode next;
		/**
		 * Creates a listnode with a null next value
		 * @param data The node's value
		 */
		public ListNode(E data){
			this.data = data;
			this.next = null;
		}
		/**
		 * Creates a list node with a specified next value
		 * @param data The node's value
		 * @param next The node following this node
		 */
		public ListNode(E data, ListNode next){
			this.data = data;
			this.next = next;
		}
	}
}
