package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * This will test the GraphTraversalUtil class for proper traversal of graphs
 * @author John Widdifield
 */
public class GraphTraversalUtilTest {

	private GraphTraversalUtil tUtil;
    private Graph<String, Integer> undirectedGraph;
    private Graph<String, Integer> directedGraph;
    
    /**
     * This will set up for the tests with new undirected and directed graphs
     */
    @Before
    public void setUp() {
    	tUtil = new GraphTraversalUtil();
        undirectedGraph = new AdjacencyListGraph<String, Integer>();
        directedGraph = new AdjacencyListGraph<String, Integer>(true);
    }
    
    /**
     * This will test the depthFirstSearch method
     */
	@SuppressWarnings("static-access")
	@Test
	public void testDFS() {
		
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        undirectedGraph.insertEdge(v1, v3, 10);
        undirectedGraph.insertEdge(v1, v4, 15);
        undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v2, v3, 25);
        undirectedGraph.insertEdge(v2, v4, 30);
        undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v3, v4, 40);
        undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v4, v5, 50);
        
        @SuppressWarnings("static-access")
		Map<Vertex<String>, Edge<Integer>> trav = tUtil.depthFirstSearch(undirectedGraph, v1);
        assertEquals(trav.size(), 4);
        assertEquals(trav.get(v2), e1);
        assertEquals(trav.get(v3), e2);
        assertEquals(trav.get(v4), e3);
        assertEquals(trav.get(v5), e4); 
        
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        Vertex<String> v6 = directedGraph.insertVertex("Boone");
        
        e1 = directedGraph.insertEdge(v1, v2, 5);
        directedGraph.insertEdge(v1, v3, 10);
        directedGraph.insertEdge(v1, v4, 15);
        directedGraph.insertEdge(v1, v5, 20);
        e2 = directedGraph.insertEdge(v2, v3, 25);
        directedGraph.insertEdge(v2, v4, 30);
        directedGraph.insertEdge(v2, v5, 35);
        e3 = directedGraph.insertEdge(v3, v4, 40);
        directedGraph.insertEdge(v3, v5, 45);
        e4 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e5 = directedGraph.insertEdge(v5, v6, 55);
        
        trav = tUtil.depthFirstSearch(directedGraph, v1);
        assertEquals(trav.size(), 5);
        assertEquals(trav.get(v2), e1);
        assertEquals(trav.get(v3), e2);
        assertEquals(trav.get(v4), e3);
        assertEquals(trav.get(v5), e4);
        assertEquals(trav.get(v6), e5);
		
	}
	
    /**
     * This will test the breadthFirstSearch method
     */
	@SuppressWarnings({ "unused", "static-access" })
	@Test
	public void testBFS() {
		
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
		Map<Vertex<String>, Edge<Integer>> trav = tUtil.breadthFirstSearch(undirectedGraph, v1);
        assertEquals(trav.size(), 4);
        assertEquals(trav.get(v2), e1);
        assertEquals(trav.get(v3), e2);
        assertEquals(trav.get(v4), e3);
        assertEquals(trav.get(v5), e4);
        
        
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        Vertex<String> v6 = directedGraph.insertVertex("Boone");
        
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        trav = tUtil.breadthFirstSearch(directedGraph, v1);
        assertEquals(trav.size(), 5);
        assertEquals(trav.get(v2), e1);
        assertEquals(trav.get(v3), e2);
        assertEquals(trav.get(v4), e3);
        assertEquals(trav.get(v5), e4);
        assertEquals(trav.get(v6), e11);
		
	}
	

}
