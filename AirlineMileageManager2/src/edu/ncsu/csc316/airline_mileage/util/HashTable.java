package edu.ncsu.csc316.airline_mileage.util;

/**
 * Class for the Hash Table data structure, based off of slides from Dr. Stallmann 
 * @author Walker Booth (wgbooth)
 *
 * @param <E> the generic type parameter 
 */
public class HashTable<E> implements List<E> {

	private Bucket<E>[] table;
	private int size;
	private int capacity;
	
	/**
	 * Constructor for a HashTable object 
	 * @param numCodes the number of unique hashcodes the table can hold 
	 */
	@SuppressWarnings("unchecked")
	public HashTable(int numCodes) {
		if (numCodes <= 0 ) {
			throw new IllegalArgumentException();
		}
		/** Ensures a load factor of .5 */
		this.capacity = numCodes * 2;
		table = new Bucket[this.capacity];
		size = 0;
	}

	@Override
	public void add(E element) {
		/*if (size == capacity) {
			rehash();
		}*/
		
		int index = compression(element.hashCode());
		
		if (table[index] == null) {
			table[index] = new Bucket<E>(element);
		} else {
			Bucket<E> current = table[index];
			while (current.next != null) {
				current = current.next;
			}
			current.next = new Bucket<E>(element);
		}
		
		
		size++;
	}
	
	/**
	 * Retrives an element from the list 
	 * @param element the element to look for in the list 
	 * @return current.element the element found in the list, or null if not found 
	 */
	public E get(E element) {
		int index = compression(element.hashCode());
		Bucket <E> current = table[index];
		while (current != null) {
			if (current.element.equals(element)) {
				return current.element;
			}
			current = current.next;
		}
		return null;
	}

	@Override
	public int size() {
		return size;
	}
	
	/**
	@SuppressWarnings("unchecked")
	private void rehash() {
		int oldCapacity = capacity;
		capacity *= 2;
		Bucket<E>[] temp = table;
		table = new Bucket[capacity];
		// Go through all of the buckets in the list 
		for (int i = 0; i < oldCapacity; i++) {
			Bucket<E> current = temp[i];
			while (current != null) {
				// Re-add the element, but don't change the size
				add(current.element);
				size--;
				current = current.next;
			}	
		}
	}
	*/
	
	/**
	 * This inner class represents a bucket object, which is a container for an element and a pointer to the next element in the bucket 
	 * @author Walker Booth (wgbooth)
	 *
	 * @param <E> the generic type parameter 
	 */
	@SuppressWarnings("hiding")
	private class Bucket<E> {
		
		private E element;
		private Bucket<E> next;
		
		public Bucket(E element) {
			this.element = element;
			this.next = null;
		}
		
	}
	
	private int compression(int hashcode) {
		hashcode = Math.abs(hashcode);
		double compression = hashcode * (2 / (1 + Math.sqrt(5)));
		compression -= Math.floor(compression);
		int index = (int) Math.floor(compression * capacity);
		return index;
	}

}
