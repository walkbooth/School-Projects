package edu.ncsu.csc316.hub_manager.util;

/**
 * This class represents the data structure used to implement the Graph ADT, based upon the slides provided
 * by Dr. Stallman (12-graphs)
 * @author Walker Booth (wgbooth)
 *
 */
public class AdjacencyMatrix {
	
	/** The adjacency matrix that stores edge weights */
	private Edge[][] adjacency;
	
	private ArrayList<Edge> edges;
	private Vertex[] vertices;
	
	/**
	 * Constructor for an AdjacencyMatrix object 
	 * @param size the number of rows and columns of the adjacency matrix 
	 */
	public AdjacencyMatrix (int size) {
		adjacency = new Edge[size][size];
		edges = new ArrayList<Edge>();
		vertices = new Vertex[size];
	}
	
	/**
	 * Inserts an edge into the matrix 
	 * @param src the source vertex of the edge
	 * @param dest the destination vertex of the edge 
	 * @param weight the weight of the edge
	 */
	public void insertEdge(Vertex src, Vertex dest, double weight) {
		Edge e = new Edge(weight, src, dest);
		edges.add(e);
		if (vertices[src.getId()] == null) {
			vertices[src.getId()] = src;
		}
		if (vertices[dest.getId()] == null) {
			vertices[dest.getId()] = dest;
		}
		adjacency[src.getId()][dest.getId()] = e;
		adjacency[dest.getId()][src.getId()] = e;
		
	}
	
	/**
	 * Reports the edges connected to the given vertex
	 * @param v the vertex to find incident edges for
	 * @return edges the ArrayList of incident edges 
	 */
	public ArrayList<Edge> incidentEdges(Vertex v) {		
		if (v == null) {
			return new ArrayList<Edge>();
		}
		int id = v.getId();
		ArrayList<Edge> incidents = new ArrayList<Edge>();
		for(int i = 0; i < adjacency[id].length; i++) {
			if(adjacency[id][i] != null) {
				incidents.add(adjacency[id][i]);
			}
		}
		return incidents;
		
	}
	
	/**
	 * Returns all of the edges in the graph 
	 * @return edges the ArrayList of edges in the graph 
	 */
	public ArrayList<Edge> edges () {
		return edges;
	}
	
	/**
	 * Finds the minimum spanning tree of this graph using Kruskal's algorithm
	 * @return m the minimum spanning tree of this graph 
	 */
	public AdjacencyMatrix getMinSpanningTree () {
		UpTree.setSpace(adjacency.length);
		UpTree[] vertexList = new UpTree[adjacency.length]; 
		MinHeap<Edge> lowestEdges = new MinHeap<Edge>();
		
		for (int i = 0; i < adjacency.length; i++) {
			vertexList[i] = new UpTree(i);
		}
		
		for (int i = 0; i < edges.size(); i++) {
			lowestEdges.insert(edges.get(i));
		}
		
		AdjacencyMatrix m = new AdjacencyMatrix(adjacency.length);
		while (m.edges().size() < adjacency.length - 1) {
			Edge e = lowestEdges.removeMin();
			Vertex u = e.getSrc();
			Vertex v = e.getDest();
			if (UpTree.findRoot(u.getId()) != UpTree.findRoot(v.getId())) {
				m.insertEdge(u, v, e.getWeight());
				UpTree.union(u.getId(), v.getId());
			}
		}
		return m;		
	}
	
	/**
	 * Returns the number of vertices of this AdjacencyMatrix
	 * @return size the number of vertices in this AdjacencyMatrix
	 */
	public Vertex[] vertices () {
		return vertices;
	}
	
}
