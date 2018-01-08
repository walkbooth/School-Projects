package edu.ncsu.csc216.bbtp.util;

import java.io.Serializable;

/**
 * An implementation of the List interface with a data structure of linked Nodes.
 * 
 * @author Noah Trimble
 * @author Walker Booth
 */
public class LinkedList implements List, Serializable {

	/** Serial number */
	private static final long serialVersionUID = 349987L;
	/** Size of the list */
	private int size;
	/** Pointer to the head of the list */
	private Node head;
	
	/**
	 * Constructor for LinkedAbstractList.
	 * Sets the head element to null and the size to zero.
	 */
	public LinkedList() {
		head = null;
		size = 0;
	}
	
	/**
     * Returns the number of elements in this list. If this list contains more
     * than Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
     *
     * @return the number of elements in this list
     */
	public int size()
    {
		if(size > Integer.MAX_VALUE)
			return Integer.MAX_VALUE;
		else
			return size;
    }

    /**
     * Returns true if this list contains no elements.
     *
     * @return true if this list contains no elements
     */
    public boolean isEmpty()
    {
    	return size == 0;
    }

    /**
     * Returns true if this list contains the specified element. More formally,
     * returns true if and only if this list contains at least one element e
     * such that (o==null ? e==null : o.equals(e)).
     *
     * @param o element whose presence in this list is to be tested
     * @return true if this list contains the specified element
     */
    public boolean contains(Object o)
    {
    	Node current = head;
		for(int i = 0; i < size; i++)
		{
			if(current.value.equals(o))
				return true;
			current = current.next;
		}
		return false;
    }

    // Modification Operations

    /**
     * Appends the specified element to the end of this list (optional
     * operation).
     *
     * Lists that support this operation may place limitations on what elements
     * may be added to this list. In particular, some lists will refuse to add
     * null elements, and others will impose restrictions on the type of
     * elements that may be added. List classes should clearly specify in their
     * documentation any restrictions on what elements may be added.
     *
     * @param o element to be appended to this list
     * @return true if the add is successful (as specified by {@link Collection#add})
     */
    public boolean add(Object o)
    {
    	this.add(size, o);
    	return true;
    }

    // Positional Access Operations

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range (index less than 0
     *             || index greater than or equal to size())
     */
    public Object get(int index)
    {
    	//checks for a valid index
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}

		Node current = head;
		for(int i = 0; i < index; i++)
			current = current.next;
		return current.value;
    }

    /**
     * Inserts the specified element at the specified position in this list
     * (optional operation). Shifts the element currently at that position (if
     * any) and any subsequent elements to the right (adds one to their
     * indices).
     *
     * @param index index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws NullPointerException if the specified element is null and this
     *             list does not permit null elements
     * @throws IllegalArgumentException if the element is a duplicate
     * @throws IndexOutOfBoundsException if the index is out of range (index less than 0
     *             || index greater than size())
     */
    public void add(int index, Object element)
    {
    	//checks for null element
		if (element == null) {
			throw new NullPointerException();
		}
		
		//checks that the element does not already exist in the list
		if (indexOf(element) != -1) {
			throw new IllegalArgumentException();
		}
		
		//checks for a valid index
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		
		//adds element
		if(index == 0)
		{
			head = new Node(element, head);
		}
		else
		{
			Node current = head;
			for(int i = 0; i < index - 1; i++)
				current = current.next;
			current.next = new Node(element, current.next);
		}
		size++;
    }

    /**
     * Removes the element at the specified position in this list (optional
     * operation). Shifts any subsequent elements to the left (subtracts one
     * from their indices). Returns the element that was removed from the list.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range (index less than 0
     *             || index greater than or equal to size())
     */
    public Object remove(int index)
    {
    	//checks for a valid index
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		
		Object elementRemoved = get(index);
		//removes an element
		if(index == 0)
		{
			head = head.next;
		}
		else
		{
			Node current = head;
			for(int i = 0; i < index - 1; i++)
				current = current.next;
			current.next = current.next.next;
		}
		
		size--;
		return elementRemoved;
    }

    // Search Operations

    /**
     * Returns the index of the first occurrence of the specified element in
     * this list, or -1 if this list does not contain the element. More
     * formally, returns the lowest index i such that (o==null ? get(i)==null :
     * o.equals(get(i))), or -1 if there is no such index.
     *
     * @param o element to search for
     * @return the index of the first occurrence of the specified element in
     *         this list, or -1 if this list does not contain the element
     */
    public int indexOf(Object o)
    {
    	Node current = head;
		for(int i = 0; i < size; i++)
		{
			if(current.value.equals(o))
				return i;
			current = current.next;
		}
		return -1;
    }
    
	/**
	 * Node class used by the LinkedList to hold the data being stored
	 * and reference to the next node.
	 * 
	 * @author Noah Trimble
	 * @author Walker Booth
	 */
	private class Node implements Serializable {
		/** Serial number */
		private static final long serialVersionUID = 484909840L;
		/** The value to be stored in the node */
		protected Object value;
		/** Reference to the next node in the list */
		protected Node next;

		/**
		 * Creates a new node with a reference to another node.
		 * 
		 * @param value
		 *            value that the node will hold
		 * @param next
		 *            reference to the next node in the list
		 */
		public Node(Object value, Node next) {
			this.value = value;
			this.next = next;
		}
	}
}
