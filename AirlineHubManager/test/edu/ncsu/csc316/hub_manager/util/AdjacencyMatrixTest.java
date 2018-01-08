package edu.ncsu.csc316.hub_manager.util;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.hub_manager.data.Airport;
import edu.ncsu.csc316.hub_manager.useful.DistanceUtility;

/**
 * Tests the AdjacencyMatrix class. 
 * @author Walker Booth (wgbooth)
 *
 */
public class AdjacencyMatrixTest {

	/**
	 * Tests behavior of the AdjacencyMatrix class
	 */
	@Test
	public void testBehavior() {
		
		Airport a1 = new Airport(0, "DFW", 32.89680099487305, -97.03800201416016);
		Airport a2 = new Airport(1, "MIA", 25.79319953918457, -80.29060363769531);
		Airport a3 = new Airport(2, "ORH", 42.26729965209961, -71.87570190429688);
		Airport a4 = new Airport(3, "RDU", 35.877601623535156, -78.7874984741211);
		
		Vertex v1 = new Vertex(a1);
		Vertex v2 = new Vertex(a2);
		Vertex v3 = new Vertex(a3);
		Vertex v4 = new Vertex(a4);
		
		Edge e12 = new Edge(DistanceUtility.getDistance(a1.getLatitude(), a1.getLongitude(), a2.getLatitude(), a2.getLongitude()) , v1, v2);
		Edge e13 = new Edge(DistanceUtility.getDistance(a1.getLatitude(), a1.getLongitude(), a3.getLatitude(), a3.getLongitude()) , v1, v3);
		Edge e43 = new Edge(DistanceUtility.getDistance(a4.getLatitude(), a4.getLongitude(), a3.getLatitude(), a3.getLongitude()) , v4, v3);		
		
		AdjacencyMatrix m = new AdjacencyMatrix(4);
		m.insertEdge(v1, v2, DistanceUtility.getDistance(a1.getLatitude(), a1.getLongitude(), a2.getLatitude(), a2.getLongitude()));
		m.insertEdge(v1, v3, DistanceUtility.getDistance(a1.getLatitude(), a1.getLongitude(), a3.getLatitude(), a3.getLongitude()));
		m.insertEdge(v4, v3, DistanceUtility.getDistance(a4.getLatitude(), a4.getLongitude(), a3.getLatitude(), a3.getLongitude()));

		assertEquals(4, m.vertices().length);
		assertEquals(v1, m.vertices()[0]);
		assertEquals(v2, m.vertices()[1]);
		assertEquals(v3, m.vertices()[2]);
		assertEquals(v4, m.vertices()[3]);		
		
		assertEquals(1, m.incidentEdges(v2).size());
		assertEquals(e12.getSrc(), m.incidentEdges(v2).get(0).getSrc());
		assertEquals(e12.getDest(), m.incidentEdges(v2).get(0).getDest());
		assertEquals(e12.getWeight(), m.incidentEdges(v2).get(0).getWeight(), .000000001);
		
		assertEquals(2, m.incidentEdges(v3).size());
		assertEquals(e13.getSrc(), m.incidentEdges(v3).get(0).getSrc());
		assertEquals(e13.getDest(), m.incidentEdges(v3).get(0).getDest());
		assertEquals(e13.getWeight(), m.incidentEdges(v3).get(0).getWeight(), .000000001);
		assertEquals(e43.getSrc(), m.incidentEdges(v3).get(1).getSrc());
		assertEquals(e43.getDest(), m.incidentEdges(v3).get(1).getDest());
		assertEquals(e43.getWeight(), m.incidentEdges(v3).get(1).getWeight(), .000000001);
		
		assertEquals(2, m.incidentEdges(v1).size());
		assertEquals(e12.getSrc(), m.incidentEdges(v1).get(0).getSrc());
		assertEquals(e12.getDest(), m.incidentEdges(v1).get(0).getDest());
		assertEquals(e12.getWeight(), m.incidentEdges(v1).get(0).getWeight(), .000000001);
		assertEquals(e13.getSrc(), m.incidentEdges(v1).get(1).getSrc());
		assertEquals(e13.getDest(), m.incidentEdges(v1).get(1).getDest());
		assertEquals(e13.getWeight(), m.incidentEdges(v1).get(1).getWeight(), .000000001);	
		
		assertEquals(3, m.edges().size());
		assertEquals(e12.getSrc(), m.edges().get(0).getSrc());
		assertEquals(e12.getDest(), m.edges().get(0).getDest());
		assertEquals(e12.getWeight(), m.edges().get(0).getWeight(), .000000001);
		assertEquals(e13.getSrc(), m.edges().get(1).getSrc());
		assertEquals(e13.getDest(), m.edges().get(1).getDest());
		assertEquals(e13.getWeight(), m.edges().get(1).getWeight(), .000000001);	
		assertEquals(e43.getSrc(), m.edges().get(2).getSrc());
		assertEquals(e43.getDest(), m.edges().get(2).getDest());
		assertEquals(e43.getWeight(), m.edges().get(2).getWeight(), .000000001);		
		
	}
	
	/**
	 * Tests the Adjacency matrix's ability to find its own MST
	 */
	@Test
	public void testMST() {
		Airport a1 = new Airport(0, "DFW", 32.89680099487305, -97.03800201416016);
		Airport a2 = new Airport(1, "MIA", 25.79319953918457, -80.29060363769531);
		Airport a3 = new Airport(2, "ORH", 42.26729965209961, -71.87570190429688);
		Airport a4 = new Airport(3, "RDU", 35.877601623535156, -78.7874984741211);
		
		Vertex v1 = new Vertex(a1);
		Vertex v2 = new Vertex(a2);
		Vertex v3 = new Vertex(a3);
		Vertex v4 = new Vertex(a4);
		
		Edge e12 = new Edge(10, v1, v2);
		Edge e23 = new Edge(10, v2, v3);
		Edge e24 = new Edge(5, v2, v4);
		
		AdjacencyMatrix m = new AdjacencyMatrix(4);
		m.insertEdge(v1, v2, 10);
		m.insertEdge(v1, v3, 40);
		m.insertEdge(v1, v4, 20);
		m.insertEdge(v2, v3, 10);
		m.insertEdge(v2, v4, 5);
		m.insertEdge(v3, v4, 70);
		
		AdjacencyMatrix mst = m.getMinSpanningTree();
		
		assertEquals(4, mst.vertices().length);
		assertEquals(v1, mst.vertices()[0]);
		assertEquals(v2, mst.vertices()[1]);
		assertEquals(v3, mst.vertices()[2]);
		assertEquals(v4, mst.vertices()[3]);		
		
		ArrayList<Edge> mstEdges = mst.edges();
		
		assertEquals(3, mstEdges.size());
		
		assertEquals(e24.getWeight(), mstEdges.get(0).getWeight(), .000001);
		assertEquals(e24.getSrc(), mstEdges.get(0).getSrc());
		assertEquals(e24.getDest(), mstEdges.get(0).getDest());
		
		assertEquals(e12.getWeight(), mstEdges.get(1).getWeight(), .000001);
		assertEquals(e12.getSrc(), mstEdges.get(1).getSrc());
		assertEquals(e12.getDest(), mstEdges.get(1).getDest());
		
		assertEquals(e23.getWeight(), mstEdges.get(2).getWeight(), .000001);
		assertEquals(e23.getSrc(), mstEdges.get(2).getSrc());
		assertEquals(e23.getDest(), mstEdges.get(2).getDest());		
	}

}
