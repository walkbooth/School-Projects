package edu.ncsu.csc316.hub_manager.util;

import edu.ncsu.csc316.hub_manager.data.Airport;

/**
 * This class represents a Vertex object.
 * @author Walker Booth (wgbooth)
 *
 */
public class Vertex implements Comparable<Object> {

	/** The numerical id assigned to the vertex */
	private int id;
	/** The Airport that the vertex represents */
	private Airport data;
	
	/**
	 * Constructs a vertex object
	 * @param data the airport that the vertex represents 
	 */
	public Vertex(Airport data) {
		this.id = data.getId();
		this.data = data;
	}
	
	/**
	 * Returns the id of the vertex
	 * @return id the id of the vertex
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Returns the airport that the vertex represents
	 * @return data the airport that the vertex represents
	 */
	public Airport getData() {
		return data;
	}

	@Override
	public int compareTo(Object o) {
		Vertex v = (Vertex) o;
		return this.data.compareTo(v.getData());
	}
	
}
