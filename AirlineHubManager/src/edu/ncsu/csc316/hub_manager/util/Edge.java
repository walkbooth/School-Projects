package edu.ncsu.csc316.hub_manager.util;

import edu.ncsu.csc316.hub_manager.util.Vertex;

/**
 * This class represents and Edge object 
 * @author Walker Booth (wgbooth)
 *
 */
public class Edge implements Comparable<Object> {

	/** The weight of the edge */
	private double weight;
	/** The source vertex of the edge */
	private Vertex src;
	/** The destination vertex of the edge */
	private Vertex dest;
	
	/**
	 * Constructor for an edge object 
	 * @param weight the weight of the edge 
	 * @param src one of the two vertices the edge connects to
	 * @param dest the other vertex that the edge connects to 
	 */
	public Edge(double weight, Vertex src, Vertex dest) {
		this.weight = weight;
		this.src = src;
		this.dest = dest;
	}

	/**
	 * Returns the weight of this edge 
	 * @return weight the weight of the edge
	 */
	public double getWeight() {
		return weight;
	}
	
	/**
	 * Gets the source vertex of the edge
	 * @return src the source vertex of the edge 
	 */
	public Vertex getSrc() {
		return src;
	}
	
	/**
	 * Gets the destination vertex of the edge
	 * @return dest the destination vertex of the edge 
	 */
	public Vertex getDest() {
		return dest;
	}

	@Override
	public int compareTo(Object o) {
		Edge e = (Edge)(o);
		if(this.weight < e.getWeight()) {
			return -1;
		} else if (this.weight > e.getWeight()) {
			return 1;
		} else {
			return this.src.compareTo(e.getSrc());
		}
	}
	
	
	
}