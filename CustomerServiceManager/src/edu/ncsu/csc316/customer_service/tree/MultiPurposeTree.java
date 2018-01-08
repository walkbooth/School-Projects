package edu.ncsu.csc316.customer_service.tree;

import edu.ncsu.csc316.customer_service.data.HelpTicket;

/**
 * This is the class for the construction and modification of a Splay Tree data structure. 
 * @author Walker Booth (wgbooth)
 */
public class MultiPurposeTree {
	
	/** The root of the entire tree */
	private TreeNode overallRoot;
	/** The global counter used to set position in traversals */
	private int counter = 1;
	/** The number of elements in the tree */
	private int size;
	/** The string that is returned when printTree() is called */
	private String treeString;
	/** The data that is removed by a call to remove() */
	private HelpTicket removed;
	
	/**
	 * Constructor for a Splay Tree
	 */
	public MultiPurposeTree() {
		overallRoot = null;
		size = 0;
		treeString = "";
	}
	
	/**
	 * Inserts a particular element into the tree
	 * @param element the element to insert
	 * @param byPriority whether or not the list is sorted by priority
	 */
	public void insert ( HelpTicket element, boolean byPriority ) {
		if (byPriority) {
			overallRoot = recursiveInsertPriority(overallRoot, element);
		} else {
			overallRoot = recursiveInsertCustomer(overallRoot, element);
		}
		size++;
	}
	
	private TreeNode recursiveInsertCustomer(TreeNode current, HelpTicket t) {
		if (current == null) {
			return new TreeNode (t);
		}
		
		if (t.compareByCustomer(current.data) > 0) {
			current.right = recursiveInsertCustomer(current.right, t);
		} else if  (t.compareByCustomer(current.data) < 0 ) {
			current.left = recursiveInsertCustomer(current.left, t);
		}
		
		return current;	
	}

	/**
	 * Recursive helper method for insert(E)
	 * @param current the current node we are inspecting for traversal
	 * @param element the element to insert
	 * @return current a TreeNode with new
	 */
	private TreeNode recursiveInsertPriority(TreeNode current, HelpTicket element) {
		if (current == null) {
			return new TreeNode (element);
		}
		
		if (element.compareByPriority(current.data) > 0) {
			current.right = recursiveInsertPriority(current.right, element);
		} else if  (element.compareByPriority(current.data) < 0 ) {
			current.left = recursiveInsertPriority(current.left, element);
		}
		
		return current;	
	}

	/**
	 * Removes a specified element from the tree
	 * @param element the element to remove 
	 * @param byPriority whether or not the list is sorted by priority
	 * @return removed the HelpTicket removed by this method
	 */
	public HelpTicket remove ( HelpTicket element, boolean byPriority ) {
		if (byPriority) {
			overallRoot = recursiveRemovePriority(overallRoot, element);
			return null;
		} else {
			overallRoot = recursiveRemoveCustomer(overallRoot, element);
			return removed;
		}
	}
	
	/**
	 * Recursive helper method for remove(E)
	 * @param current the current node we are inspecting for traversal
	 * @param element the element to insert
	 * @return current the node we are looking at currently
	 */
	private TreeNode recursiveRemovePriority(TreeNode current, HelpTicket element) {
		if (current == null) {
			return null;
		}
		if (element.compareByPriority(current.data) > 0) {
			current.right = recursiveRemovePriority(current.right, element);
		} else if  (element.compareByPriority(current.data) < 0 ) {
			current.left = recursiveRemovePriority(current.left, element);
		} else {
			if (current.left == null && current.right != null) {
				size--;
				return current.right;
			} else if (current.right == null && current.left != null) {
				size--;
				return current.left;
			} else if (current.left == null && current.right == null) {
				size--;
				return null;
			} else {
				current.data = successor(current.right);
				current.right = recursiveRemovePriority(current.right, current.data); 
			}
		}
		return current;
	}
	
	/**
	 * Recursive helper method for deleting a node that is not a leaf
	 * @param current the current node we are inspecting for traversal
	 * @return the data at the node we are shifting to replace the node we delete in recursiveRemove
	 */
	private HelpTicket successor(TreeNode current) {
		if (current.left == null) {
			return current.data;
		} else {
			return successor(current.left);
		}
	}
	
	/**
	 * Recursive helper method for remove(E)
	 * @param current the current node we are inspecting for traversal
	 * @param element the element to insert
	 * @return current the node we are looking at currently 
	 */
	private TreeNode recursiveRemoveCustomer(TreeNode current, HelpTicket element) {
		if (current == null) {
			return null;
		}
		if (element.compareByCustomer(current.data) > 0) {
			current.right = recursiveRemoveCustomer(current.right, element);
		} else if  (element.compareByCustomer(current.data) < 0 ) {
			current.left = recursiveRemoveCustomer(current.left, element);
		} else {
			removed = current.data;
			if (current.left == null && current.right != null) {
				size--;
				return current.right;
			} else if (current.right == null && current.left != null) {
				size--;
				return current.left;
			} else if (current.left == null && current.right == null) {
				size--;
				return null;
			} else {
				current.data = successor(current.right);
				current.right = recursiveRemovePriority(current.right, current.data); 
			}
		}
		return current;
	}
	
	/**
	 * Updates the queue positions of every element in the tree using an in-order traversal
	 * @param t the customer tree which also needs to be updated
	 */
	public void updateTree(MultiPurposeTree t) {
		updateTreeRecursive(overallRoot, t);
		counter = 1;
	}
	
	/**
	 * Recursive helper method for updateTree()
	 * @param current the current node we are inspecting for traversal
	 */
	private void updateTreeRecursive(TreeNode current, MultiPurposeTree t) {
		if (current != null) {
			updateTreeRecursive(current.left, t);
			current.data.setPosition(counter);
			if (t != null) {
				t.search(current.data).setPosition(counter);
			}
			counter++;
			updateTreeRecursive(current.right, t);
		}		
	}
	
	/**
	 * Writes out the contents of the tree 
	 * @return a string containing all of the data in the tree
	 */
	public String printTree() {
		treeString = "";
		printTreeRecursive(overallRoot);
		return treeString;
	}
	
	/**
	 * Adds the contents of the tree to this object's toString string
	 * @param current the current node we are inspecting
	 */
	private void printTreeRecursive (TreeNode current) {
		if (current != null) {
			printTreeRecursive(current.left);
			treeString = treeString.concat(current.data.toString().concat("\n"));
			printTreeRecursive(current.right);
		}
	}
	
	/**
	 * Returns the size of the tree.
	 * @return size the size of the tree
	 */
	public int size () {
		return size;
	}
	
	/**
	 * Searches for an element in the tree
	 * PRECONDITIONS: MultiPurposeTree.updateTree() must be called immediately before this method is called for accuracy 
	 * @param element the element to find 
	 * @return position the position of the element in the tree
	 */
	public HelpTicket search (HelpTicket element) {
		return recursiveSearch(overallRoot, element);
	}
	
	/**
	 * Recursive helper method for searching for an element
	 * @param element the element to find
	 * @param current the current place in the search 
	 * @return position the position of the element in the tree 
	 */
	private HelpTicket recursiveSearch(TreeNode current, HelpTicket element) {
		if (current == null) {
			return null;
		}
		if ( element.compareByCustomer(current.data) > 0) {
			return recursiveSearch(current.right, element);
		} else if  (element.compareByCustomer(current.data) < 0 ) {
			return recursiveSearch(current.left, element);
		}
		return current.data;
		
	}
	
	
	/**
	 * This class represents a single node in the Splay Tree data structure
	 * @author Walker Booth (wgbooth)
	 *
	 */
	private class TreeNode {
		private TreeNode left;
		private TreeNode right;
		private HelpTicket data;
		
		/**
		 * Constructor for a TreeNode (without references to children)
		 * @param element the data to use for the new node 
		 */
		public TreeNode(HelpTicket element) {
			left = null;
			right = null;
			data = element;
		}
	}

}
