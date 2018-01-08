package edu.ncsu.csc216.bbtp.util;

import java.io.Serializable;

/**
 * An implementation of the List interface with an array data structure.
 * 
 * @author Noah Trimble
 * @author Walker Booth
 */
public class ArrayList implements List, Serializable {

	/** Serial number */
	private static final long serialVersionUID = 28592L;
	/** Initial size of the internal array */
	private static final int RESIZE = 10;
	/** Array holding the list elements */
	private Object[] list;
	/** Number of elements currently in the list */
	private int size;

	/**
	 * Constructs an ArrayList object by initializing the array and setting size to zero.
	 * Capacity is set automatically to RESIZE value.
	 */
	public ArrayList() {
		this(RESIZE);
	}
	
	/**
	 * Constructs an ArrayList object by initializing the array and setting size to zero.
	 * Capacity is given by user.
	 * @param capacity Capacity to set
	 * @throws IllegalArgumentException if the capacity is less than or equal to 0
	 */
	public ArrayList(int capacity) {
		if(capacity <= 0)
			throw new IllegalArgumentException("Capacity must be greater than zero.");
		
		list = new Object[capacity];
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
    	for(int i = 0; i < size; i++)
    	{
    		if(list[i].equals(o))
    			return true;
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
     * @return true if add was successful (as specified by {@link Collection#add})
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
    	if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
    	
    	return list[index];
    }

    /**
     * Inserts the specified element at the specified position in this list
     * (optional operation). Shifts the element currently at that position (if
     * any) and any subsequent elements to the right (adds one to their
     * indices).  Will grow the array when necessary.
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
		
		//checks for a valid index
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		
		//throws exception if the element is a duplicate
		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		
		//doubles the size of array if the size is equal to the capacity
		if (size == list.length) {
			growArray();
		}
		
		for (int i = size - 1; i >= index; i--) {
			list[i + 1] = list[i];
		}
		
		list[index] = element;
		size++;
    }

	/**
	 * Doubles the size of the array, preserving the order and contents of the array
	 */
	private void growArray() {
		Object[] tempList = new Object[size * 2];
		
		for (int i = 0; i < size; i++) {
			tempList[i] = list[i];
		}
		list = tempList;
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
		//shifts all elements in the array to left 
		for (int i = index + 1; i < size; i++) {
			list[i - 1] = list[i];
		}
		
		//removes the reference to the duplicate last object in the array (after shifting to the left)
		list[size - 1] = null;
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
    	for(int i = 0; i < size; i++)
    	{
    		if(list[i].equals(o))
    			return i;
    	}
    	return -1;
    }
}
