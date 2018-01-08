package edu.ncsu.csc316.airline_mileage.util;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.airline_mileage.data.Flight;

/**
 * Tests the HashTable class
 * @author Walker Booth (wgbooth)
 *
 */
public class HashTableTest {

	/**
	 * Tests the HashTable constructor
	 */
	@Test
	public void testHashTable() {
		HashTable<String> h = null;
		try {
			h = new HashTable<String>(4);
			assertEquals(0, h.size());
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		try {
			h = new HashTable<String>(-1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(0, h.size());
		}

	}

	/**
	 * Tests HashTable's add method 
	 */
	@Test
	public void testAdd() {
		HashTable<Flight> h = new HashTable<Flight>(8);
		Flight f1 = new Flight("A", "A", 0, "A", "A");
		Flight f2 = new Flight("B", "B", 0, "B", "B");
		Flight f3 = new Flight("BB", "BB", 0, "BB", "BB");
		Flight f4 = new Flight("CCC", "AAA", 0, "Walker", "Booth");
		Flight f5 = new Flight("UA", "346", 0, "UA", "ORD");
		Flight f6 = new Flight("DL", "1233", 0, "ATL", "ORF");
		Flight f7 = new Flight("B6", "1316", 0, "FLL", "Jax");
		h.add(f1);
		h.add(f2);
		h.add(f3);
		h.add(f4);
		h.add(f5);
		h.add(f6);
		h.add(f7);
		h.add(f1);
		h.add(f2);
		h.add(f3);
		h.add(f4);
		h.add(f5);
		h.add(f6);
		h.add(f7);
		h.add(f1);
		h.add(f2);
		h.add(f3);
		h.add(f4);
		h.add(f5);
		h.add(f6);
		h.add(f7);
		h.add(f1);
		h.add(f2);
		h.add(f3);
		h.add(f4);
		h.add(f5);
		h.add(f6);
		h.add(f7);
		
		
		
		assertEquals(f1, h.get(f1));
		assertEquals(f2, h.get(f2));
		assertEquals(f3, h.get(f3));
		assertEquals(f4, h.get(f4));	
		assertEquals(f5, h.get(f5));
		assertEquals(f6, h.get(f6));
		assertEquals(f7, h.get(f7));
		
		
		HashTable<Integer> experiment = new HashTable<Integer>(250);
		int i;
		for (i = 0; i < 250; i++) {
			experiment.add(new Integer((int) (Math.random() * 100000000)));
		}
		i = 0;

	}

}
