package edu.ncsu.csc316.hub_manager.util;

/**
 * This class implements the UpTree data structure.
 * Note: This implementation uses the array representation and algorithms described in Dr. Stallman's disjoint_set_union
 * 	slides. The parent of a particular node can be accessed with upTree[node_key], 
 *  and if the node has no parent (i.e., it is the root), then upTree[node_key] = -1 * number_of_nodes_in_tree.
 * @author Walker Booth (wgbooth)
 *
 */
public class UpTree {
	
	/** List of keys of UpNodes */
	private static int upTree[];
	/** Id variable for each node */
	private int id;
	
	/**
	 * Returns the id of the UpTree
	 * @return id the id of this UpTree
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Must be called when number of nodes in universe is known.
	 * @param size the number of nodes in the universe
	 */
	public static void setSpace(int size) {
		upTree = new int[size];
	}
	
	/**
	 * Constructor for an UpTree
	 * @param key the key of the root of the UpTree
	 */
	public UpTree ( int key ) {
		if (key < 0 || key > upTree.length) {
			throw new IllegalArgumentException("Key is out of bounds");
		}
		upTree[key] = -1;
		this.id = key;
	}
	
	/**
	 * Returns the key of the root of a node in an UpTree
	 * @param nodeKey the key of the node at our current location in the UpTree
	 * @return the key of the root of the node 
	 */
	public static int findRoot (int nodeKey) {
		if (upTree[nodeKey] >= 0) {
			upTree[nodeKey] = findRoot(upTree[nodeKey]);
			return upTree[nodeKey];
		} else {
			return nodeKey;
		}
	}
	
	/**
	 * Unions two roots of two separate sets in the universe.
	 * @param s the root of one set
	 * @param t the root of the other set
	 */
	public static void union(int s, int t) {
		s = findRoot(s);
		t = findRoot(t);
		
		if ((-1 * upTree[s]) > (-1 * upTree[t])) {
			upTree[s] += upTree[t];
			upTree[t] = s;
		} else {
			upTree[t] += upTree[s];
			upTree[s] = t;
		}
	}
	
	
	
}
