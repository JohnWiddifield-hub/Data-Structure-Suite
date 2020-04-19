package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;

/**
 * This class tests the graph implementation of the classes data structure
 * @author John Widdifield and NCSU staff
 *
 */
public class EdgeListGraphTest {

    private Graph<String, Integer> undirectedGraph;
    private Graph<String, Integer> directedGraph;
    
    /**
     * This will set up for the tests with new undirected and directed graphs
     */
    @Before
    public void setUp() {
        undirectedGraph = new EdgeListGraph<String, Integer>();
        directedGraph = new EdgeListGraph<String, Integer>(true);
    }
    
    private void buildUndirectedSample() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        
        undirectedGraph.insertEdge(v1, v2, 5);
        undirectedGraph.insertEdge(v1, v3, 10);
        undirectedGraph.insertEdge(v1, v4, 15);
        undirectedGraph.insertEdge(v1, v5, 20);
        undirectedGraph.insertEdge(v2, v3, 25);
        undirectedGraph.insertEdge(v2, v4, 30);
        undirectedGraph.insertEdge(v2, v5, 35);
        undirectedGraph.insertEdge(v3, v4, 40);
        undirectedGraph.insertEdge(v3, v5, 45);
        undirectedGraph.insertEdge(v4, v5, 50);
    }
    
    private void buildDirectedSample() {
        Vertex<String> v1 = directedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = directedGraph.insertVertex("Asheville");
        Vertex<String> v3 = directedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = directedGraph.insertVertex("Durham");
        Vertex<String> v5 = directedGraph.insertVertex("Greenville");
        Vertex<String> v6 = directedGraph.insertVertex("Boone");
        
        directedGraph.insertEdge(v1, v2, 5);
        directedGraph.insertEdge(v1, v3, 10);
        directedGraph.insertEdge(v1, v4, 15);
        directedGraph.insertEdge(v1, v5, 20);
        directedGraph.insertEdge(v2, v3, 25);
        directedGraph.insertEdge(v2, v4, 30);
        directedGraph.insertEdge(v2, v5, 35);
        directedGraph.insertEdge(v3, v4, 40);
        directedGraph.insertEdge(v3, v5, 45);
        directedGraph.insertEdge(v4, v5, 50);
        directedGraph.insertEdge(v5, v6, 55);
    }
    
    /**
     * This will test the numVertices method
     */
    @Test
    public void testNumVertices() {
        assertEquals(0, undirectedGraph.numVertices());
        buildUndirectedSample();
        assertEquals(5, undirectedGraph.numVertices());
        
        assertEquals(0, directedGraph.numVertices());
        buildDirectedSample();
        assertEquals(6, directedGraph.numVertices());
        
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Vertex<String> v7 = undirectedGraph.insertVertex("Boone2");
        undirectedGraph.insertEdge(v7, v6, 55);
        assertEquals(7, undirectedGraph.numVertices());
    }

    /**
     * This will test the vertices method
     */
    @Test
    public void testVertices() {
		// We cannot call buildUndirectedSample() because
		// then we would not be able to reference specific edges
		// or vertices when testing
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        undirectedGraph.insertEdge(v1, v2, 5);
        undirectedGraph.insertEdge(v1, v3, 10);
        undirectedGraph.insertEdge(v1, v4, 15);
        undirectedGraph.insertEdge(v1, v5, 20);
        undirectedGraph.insertEdge(v2, v3, 25);
        undirectedGraph.insertEdge(v2, v4, 30);
        undirectedGraph.insertEdge(v2, v5, 35);
        undirectedGraph.insertEdge(v3, v4, 40);
        undirectedGraph.insertEdge(v3, v5, 45);
        undirectedGraph.insertEdge(v4, v5, 50);
        
        Iterator<Vertex<String>> it = undirectedGraph.vertices().iterator();
        assertTrue(it.hasNext());
        
        assertEquals(v1, it.next());
        assertEquals(v2, it.next());
        assertEquals(v3, it.next());
        assertEquals(v4, it.next());
        assertEquals(v5, it.next());
        assertFalse(it.hasNext());
        
        
        
        
        // DIRECTED
		// We cannot call buildDirectedSample() because
		// then we would not be able to reference specific edges
		// or vertices when testing		
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        Vertex<String> v6 = directedGraph.insertVertex("Boone");
        directedGraph.insertEdge(v1, v2, 5);
        directedGraph.insertEdge(v1, v3, 10);
        directedGraph.insertEdge(v1, v4, 15);
        directedGraph.insertEdge(v1, v5, 20);
        directedGraph.insertEdge(v2, v3, 25);
        directedGraph.insertEdge(v2, v4, 30);
        directedGraph.insertEdge(v2, v5, 35);
        directedGraph.insertEdge(v3, v4, 40);
        directedGraph.insertEdge(v3, v5, 45);
        directedGraph.insertEdge(v4, v5, 50);
        directedGraph.insertEdge(v5, v6, 55);
        
        it = directedGraph.vertices().iterator();
        assertTrue(it.hasNext());
        
        assertEquals(v1, it.next());
        assertEquals(v2, it.next());
        assertEquals(v3, it.next());
        assertEquals(v4, it.next());
        assertEquals(v5, it.next());
        assertEquals(v6, it.next());
        
        assertFalse(it.hasNext());
    }

    /**
     * This will test the numEdges method
     */
    @Test
    public void testNumEdges() {
        assertEquals(0, undirectedGraph.numEdges());
        buildUndirectedSample();
        assertEquals(10, undirectedGraph.numEdges());
        
        assertEquals(0, directedGraph.numEdges());
        buildDirectedSample();
        assertEquals(11, directedGraph.numEdges());
        
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Vertex<String> v7 = undirectedGraph.insertVertex("Boone2");
        undirectedGraph.insertEdge(v7, v6, 55);
        assertEquals(11, undirectedGraph.numEdges());   
    }

    /**
     * This will test the edges method
     */
    @Test
    public void testEdges() {
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
        
        Iterator<Edge<Integer>> it = undirectedGraph.edges().iterator();
        assertTrue(it.hasNext());
        assertEquals(e1, it.next());
        assertEquals(e2, it.next());
        assertEquals(e3, it.next());
        assertEquals(e4, it.next());
        assertEquals(e5, it.next());
        assertEquals(e6, it.next());
        assertEquals(e7, it.next());
        assertEquals(e8, it.next());
        assertEquals(e9, it.next());
        assertEquals(e10, it.next());
        assertFalse(it.hasNext());
        
        // DIRECTED
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
        
        it = directedGraph.edges().iterator();
        assertTrue(it.hasNext());
        assertEquals(e1, it.next());
        assertEquals(e2, it.next());
        assertEquals(e3, it.next());
        assertEquals(e4, it.next());
        assertEquals(e5, it.next());
        assertEquals(e6, it.next());
        assertEquals(e7, it.next());
        assertEquals(e8, it.next());
        assertEquals(e9, it.next());
        assertEquals(e10, it.next());
        assertEquals(e11, it.next());
        assertFalse(it.hasNext());
    }

    /**
     * This will test the getEdge method
     */
    @Test
    public void testGetEdge() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
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
        
        assertEquals(e1, undirectedGraph.getEdge(v1, v2));
        assertEquals(e2, undirectedGraph.getEdge(v1, v3));
        assertEquals(e3, undirectedGraph.getEdge(v1, v4));
        assertEquals(e4, undirectedGraph.getEdge(v1, v5));
        assertEquals(e5, undirectedGraph.getEdge(v2, v3));
        assertEquals(e6, undirectedGraph.getEdge(v2, v4));
        assertEquals(e7, undirectedGraph.getEdge(v2, v5));
        assertEquals(e8, undirectedGraph.getEdge(v3, v4));
        assertEquals(e9, undirectedGraph.getEdge(v3, v5));
        assertEquals(e10, undirectedGraph.getEdge(v4, v5));
        assertEquals(e10, undirectedGraph.getEdge(v5, v4));
        
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
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
        
        assertEquals(e1, directedGraph.getEdge(v1, v2));
        assertEquals(e2, directedGraph.getEdge(v1, v3));
        assertEquals(e3, directedGraph.getEdge(v1, v4));
        assertEquals(e4, directedGraph.getEdge(v1, v5));
        assertEquals(e5, directedGraph.getEdge(v2, v3));
        assertEquals(e6, directedGraph.getEdge(v2, v4));
        assertEquals(e7, directedGraph.getEdge(v2, v5));
        assertEquals(e8, directedGraph.getEdge(v3, v4));
        assertEquals(e9, directedGraph.getEdge(v3, v5));
        assertEquals(e11, directedGraph.getEdge(v5, v6));
        assertEquals(e10, directedGraph.getEdge(v4, v5));
    }

    /**
     * This will test the endVertices method
     */
    @Test
    public void testEndVertices() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
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
         
        assertEquals(v1, undirectedGraph.endVertices(e1)[0]);
        assertEquals(v2, undirectedGraph.endVertices(e1)[1]);
        assertEquals(v1, undirectedGraph.endVertices(e2)[0]);
        assertEquals(v3, undirectedGraph.endVertices(e2)[1]);
        assertEquals(v1, undirectedGraph.endVertices(e3)[0]);
        assertEquals(v4, undirectedGraph.endVertices(e3)[1]);
        assertEquals(v1, undirectedGraph.endVertices(e4)[0]);
        assertEquals(v5, undirectedGraph.endVertices(e4)[1]);
        assertEquals(v2, undirectedGraph.endVertices(e5)[0]);
        assertEquals(v3, undirectedGraph.endVertices(e5)[1]);
        assertEquals(v2, undirectedGraph.endVertices(e6)[0]);
        assertEquals(v4, undirectedGraph.endVertices(e6)[1]);
        assertEquals(v2, undirectedGraph.endVertices(e7)[0]);
        assertEquals(v5, undirectedGraph.endVertices(e7)[1]);
        assertEquals(v3, undirectedGraph.endVertices(e8)[0]);
        assertEquals(v4, undirectedGraph.endVertices(e8)[1]);
        assertEquals(v3, undirectedGraph.endVertices(e9)[0]);
        assertEquals(v5, undirectedGraph.endVertices(e9)[1]);
        assertEquals(v4, undirectedGraph.endVertices(e10)[0]);
        assertEquals(v5, undirectedGraph.endVertices(e10)[1]);
      
             
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
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
        
        assertEquals(v1, undirectedGraph.endVertices(e1)[0]);
        assertEquals(v2, undirectedGraph.endVertices(e1)[1]);
        assertEquals(v1, undirectedGraph.endVertices(e2)[0]);
        assertEquals(v3, undirectedGraph.endVertices(e2)[1]);
        assertEquals(v1, undirectedGraph.endVertices(e3)[0]);
        assertEquals(v4, undirectedGraph.endVertices(e3)[1]);
        assertEquals(v1, undirectedGraph.endVertices(e4)[0]);
        assertEquals(v5, undirectedGraph.endVertices(e4)[1]);
        assertEquals(v2, undirectedGraph.endVertices(e5)[0]);
        assertEquals(v3, undirectedGraph.endVertices(e5)[1]);
        assertEquals(v2, undirectedGraph.endVertices(e6)[0]);
        assertEquals(v4, undirectedGraph.endVertices(e6)[1]);
        assertEquals(v2, undirectedGraph.endVertices(e7)[0]);
        assertEquals(v5, undirectedGraph.endVertices(e7)[1]);
        assertEquals(v3, undirectedGraph.endVertices(e8)[0]);
        assertEquals(v4, undirectedGraph.endVertices(e8)[1]);
        assertEquals(v3, undirectedGraph.endVertices(e9)[0]);
        assertEquals(v5, undirectedGraph.endVertices(e9)[1]);
        assertEquals(v4, undirectedGraph.endVertices(e10)[0]);
        assertEquals(v5, undirectedGraph.endVertices(e10)[1]);
        assertEquals(v5, undirectedGraph.endVertices(e11)[0]);
        assertEquals(v6, undirectedGraph.endVertices(e11)[1]);
    }

    /**
     * This will test the opposite method
     */
    @Test
    public void testOpposite() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
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
        
        assertEquals(v2, undirectedGraph.opposite(v1,  e1));
        assertEquals(v1, undirectedGraph.opposite(v2,  e1));
        assertEquals(v3, undirectedGraph.opposite(v1,  e2));
        assertEquals(v1, undirectedGraph.opposite(v3,  e2));
        assertEquals(v4, undirectedGraph.opposite(v1,  e3));
        assertEquals(v1, undirectedGraph.opposite(v4,  e3));
        assertEquals(v5, undirectedGraph.opposite(v1,  e4));
        assertEquals(v1, undirectedGraph.opposite(v5,  e4));
        assertEquals(v3, undirectedGraph.opposite(v2,  e5));
        assertEquals(v2, undirectedGraph.opposite(v3,  e5));
        assertEquals(v4, undirectedGraph.opposite(v2,  e6));
        assertEquals(v2, undirectedGraph.opposite(v4,  e6));
        assertEquals(v5, undirectedGraph.opposite(v2,  e7));
        assertEquals(v2, undirectedGraph.opposite(v5,  e7));
        assertEquals(v4, undirectedGraph.opposite(v3,  e8));
        assertEquals(v3, undirectedGraph.opposite(v4,  e8));
        assertEquals(v5, undirectedGraph.opposite(v3,  e9));
        assertEquals(v3, undirectedGraph.opposite(v5,  e9));
        assertEquals(v5, undirectedGraph.opposite(v4,  e10));
        assertEquals(v4, undirectedGraph.opposite(v5,  e10));
        
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
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
        
        assertEquals(v2, undirectedGraph.opposite(v1,  e1));
        assertEquals(v1, undirectedGraph.opposite(v2,  e1));
        assertEquals(v3, undirectedGraph.opposite(v1,  e2));
        assertEquals(v1, undirectedGraph.opposite(v3,  e2));
        assertEquals(v4, undirectedGraph.opposite(v1,  e3));
        assertEquals(v1, undirectedGraph.opposite(v4,  e3));
        assertEquals(v5, undirectedGraph.opposite(v1,  e4));
        assertEquals(v1, undirectedGraph.opposite(v5,  e4));
        assertEquals(v3, undirectedGraph.opposite(v2,  e5));
        assertEquals(v2, undirectedGraph.opposite(v3,  e5));
        assertEquals(v4, undirectedGraph.opposite(v2,  e6));
        assertEquals(v2, undirectedGraph.opposite(v4,  e6));
        assertEquals(v5, undirectedGraph.opposite(v2,  e7));
        assertEquals(v2, undirectedGraph.opposite(v5,  e7));
        assertEquals(v4, undirectedGraph.opposite(v3,  e8));
        assertEquals(v3, undirectedGraph.opposite(v4,  e8));
        assertEquals(v5, undirectedGraph.opposite(v3,  e9));
        assertEquals(v3, undirectedGraph.opposite(v5,  e9));
        assertEquals(v5, undirectedGraph.opposite(v4,  e10));
        assertEquals(v4, undirectedGraph.opposite(v5,  e10));
        assertEquals(v5, undirectedGraph.opposite(v6,  e11));
        assertEquals(v6, undirectedGraph.opposite(v5,  e11));
    }

    /**
     * This will test the outDegree method
     */
    @SuppressWarnings("unused")
	@Test
    public void testOutDegree() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
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
        
        assertEquals(4, undirectedGraph.outDegree(v1));
        assertEquals(4, undirectedGraph.outDegree(v2));
        assertEquals(4, undirectedGraph.outDegree(v3));
        assertEquals(4, undirectedGraph.outDegree(v4));
        assertEquals(4, undirectedGraph.outDegree(v5));
        
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
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
        
        assertEquals(4, directedGraph.outDegree(v1));
        assertEquals(3, directedGraph.outDegree(v2));
        assertEquals(2, directedGraph.outDegree(v3));
        assertEquals(1, directedGraph.outDegree(v4));
        assertEquals(1, directedGraph.outDegree(v5));
    }

    /**
     * This will test the inDegree method
     */
    @SuppressWarnings("unused")
	@Test
    public void testInDegree() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
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
        
        assertEquals(4, undirectedGraph.inDegree(v1));
        assertEquals(4, undirectedGraph.inDegree(v2));
        assertEquals(4, undirectedGraph.inDegree(v3));
        assertEquals(4, undirectedGraph.inDegree(v4));
        assertEquals(4, undirectedGraph.inDegree(v5));
        
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
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
        
        assertEquals(0, directedGraph.inDegree(v1));
        assertEquals(1, directedGraph.inDegree(v2));
        assertEquals(2, directedGraph.inDegree(v3));
        assertEquals(3, directedGraph.inDegree(v4));
        assertEquals(4, directedGraph.inDegree(v5));
        assertEquals(1, directedGraph.inDegree(v6));
        
    }

    /**
     * This will test the outGoingEdges method
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testOutgoingEdges() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
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
        
        // We can use a custom arrayContains() helper method to check that
        // an array *contains* a certain target edge.
        // This is helpful for testing graph ADT behaviors where an order
        // of edges cannot be guaranteed (such as .outgoingEdges or .incomingEdges
        // in adjacencyMaps, etc.)      
        Edge<Integer>[] temp = (Edge<Integer>[])(new Edge[4]);
        int count = 0;
        Iterator<Edge<Integer>> it = undirectedGraph.outgoingEdges(v1).iterator();
        assertTrue(it.hasNext());
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        assertFalse(it.hasNext());
        assertTrue(arrayContains(temp, e1));
        assertTrue(arrayContains(temp, e2));
        assertTrue(arrayContains(temp, e3));
        assertTrue(arrayContains(temp, e4));
        
        temp = (Edge<Integer>[])(new Edge[4]);
        count = 0;
        it = undirectedGraph.outgoingEdges(v2).iterator();
        assertTrue(it.hasNext());
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        
        assertTrue(arrayContains(temp, e5));
        assertTrue(arrayContains(temp, e6));
        assertTrue(arrayContains(temp, e7));
        assertTrue(arrayContains(temp, e1));
        
        
        temp = (Edge<Integer>[])(new Edge[4]);
        count = 0;
        it = undirectedGraph.outgoingEdges(v3).iterator();
        assertTrue(it.hasNext());
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        
        assertTrue(arrayContains(temp, e2));
        assertTrue(arrayContains(temp, e5));
        assertTrue(arrayContains(temp, e8));
        assertTrue(arrayContains(temp, e9));
        
        temp = (Edge<Integer>[])(new Edge[4]);
        count = 0;
        it = undirectedGraph.outgoingEdges(v4).iterator();
        assertTrue(it.hasNext());
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        
        assertTrue(arrayContains(temp, e3));
        assertTrue(arrayContains(temp, e6));
        assertTrue(arrayContains(temp, e8));
        assertTrue(arrayContains(temp, e10));
        
        temp = (Edge<Integer>[])(new Edge[4]);
        count = 0;
        it = undirectedGraph.outgoingEdges(v5).iterator();
        assertTrue(it.hasNext());
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        
        assertTrue(arrayContains(temp, e4));
        assertTrue(arrayContains(temp, e7));
        assertTrue(arrayContains(temp, e9));
        assertTrue(arrayContains(temp, e10));
        
        
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
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
        
        
        temp = (Edge<Integer>[])(new Edge[4]);
        count = 0;
        it = directedGraph.outgoingEdges(v1).iterator();
        assertTrue(it.hasNext());
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        assertFalse(it.hasNext());
        assertTrue(arrayContains(temp, e1));
        assertTrue(arrayContains(temp, e2));
        assertTrue(arrayContains(temp, e3));
        assertTrue(arrayContains(temp, e4));
        
        temp = (Edge<Integer>[])(new Edge[3]);
        count = 0;
        it = directedGraph.outgoingEdges(v2).iterator();
        assertTrue(it.hasNext());
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        
        assertTrue(arrayContains(temp, e5));
        assertTrue(arrayContains(temp, e6));
        assertTrue(arrayContains(temp, e7));
        
        
        temp = (Edge<Integer>[])(new Edge[2]);
        count = 0;
        it = directedGraph.outgoingEdges(v3).iterator();
        assertTrue(it.hasNext());
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        
        assertTrue(arrayContains(temp, e8));
        assertTrue(arrayContains(temp, e9));
        
        temp = (Edge<Integer>[])(new Edge[1]);
        count = 0;
        it = directedGraph.outgoingEdges(v4).iterator();
        assertTrue(it.hasNext());
        temp[count] = it.next();
        count++;

        assertTrue(arrayContains(temp, e10));
        
        temp = (Edge<Integer>[])(new Edge[1]);
        count = 0;
        it = directedGraph.outgoingEdges(v5).iterator();
        assertTrue(it.hasNext());
        temp[count] = it.next();
        count++;
        assertTrue(arrayContains(temp, e11));
    }
    
    // Helper method to check that an array contains a certain target.
    // This is helpful for testing graph ADT behaviors where an order
    // of edges cannot be guaranteed (such as .outgoingEdges or .incomingEdges)
    private boolean arrayContains(Edge<Integer>[] temp, Edge<Integer> target) {
        for(Edge<Integer> e : temp) {
            if(e == target) {
                return true;
            }
        }
        return false;
    }

    /**
     * This will test the incomingEdges method
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testIncomingEdges() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
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
        
        // We can use a custom arrayContains() helper method to check that
        // an array *contains* a certain target edge.
        // This is helpful for testing graph ADT behaviors where an order
        // of edges cannot be guaranteed (such as .outgoingEdges or .incomingEdges
        // in adjacencyMaps, etc.)      
        Edge<Integer>[] temp = (Edge<Integer>[])(new Edge[4]);
        int count = 0;
        Iterator<Edge<Integer>> it = undirectedGraph.incomingEdges(v1).iterator();
        assertTrue(it.hasNext());
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        assertFalse(it.hasNext());
        assertTrue(arrayContains(temp, e1));
        assertTrue(arrayContains(temp, e2));
        assertTrue(arrayContains(temp, e3));
        assertTrue(arrayContains(temp, e4));
        
        temp = (Edge<Integer>[])(new Edge[4]);
        count = 0;
        it = undirectedGraph.incomingEdges(v2).iterator();
        assertTrue(it.hasNext());
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        
        assertTrue(arrayContains(temp, e5));
        assertTrue(arrayContains(temp, e6));
        assertTrue(arrayContains(temp, e7));
        assertTrue(arrayContains(temp, e1));
        
        
        temp = (Edge<Integer>[])(new Edge[4]);
        count = 0;
        it = undirectedGraph.incomingEdges(v3).iterator();
        assertTrue(it.hasNext());
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        
        assertTrue(arrayContains(temp, e2));
        assertTrue(arrayContains(temp, e5));
        assertTrue(arrayContains(temp, e8));
        assertTrue(arrayContains(temp, e9));
        
        temp = (Edge<Integer>[])(new Edge[4]);
        count = 0;
        it = undirectedGraph.incomingEdges(v4).iterator();
        assertTrue(it.hasNext());
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        
        assertTrue(arrayContains(temp, e3));
        assertTrue(arrayContains(temp, e6));
        assertTrue(arrayContains(temp, e8));
        assertTrue(arrayContains(temp, e10));
        
        temp = (Edge<Integer>[])(new Edge[4]);
        count = 0;
        it = undirectedGraph.incomingEdges(v5).iterator();
        assertTrue(it.hasNext());
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        
        assertTrue(arrayContains(temp, e4));
        assertTrue(arrayContains(temp, e7));
        assertTrue(arrayContains(temp, e9));
        assertTrue(arrayContains(temp, e10));
        
        
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
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
        
        
        it = directedGraph.incomingEdges(v1).iterator();
        assertFalse(it.hasNext());

        
        temp = (Edge<Integer>[])(new Edge[1]);
        count = 0;
        it = directedGraph.incomingEdges(v2).iterator();
        assertTrue(it.hasNext());
        temp[count] = it.next();
        count++;
        
        assertTrue(arrayContains(temp, e1));
        
        
        temp = (Edge<Integer>[])(new Edge[2]);
        count = 0;
        it = directedGraph.incomingEdges(v3).iterator();
        assertTrue(it.hasNext());
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        
        assertTrue(arrayContains(temp, e2));
        assertTrue(arrayContains(temp, e5));
        
        temp = (Edge<Integer>[])(new Edge[3]);
        count = 0;
        it = directedGraph.incomingEdges(v4).iterator();
        assertTrue(it.hasNext());
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;

        assertTrue(arrayContains(temp, e3));
        assertTrue(arrayContains(temp, e6));
        assertTrue(arrayContains(temp, e8));
        
        temp = (Edge<Integer>[])(new Edge[4]);
        count = 0;
        it = directedGraph.incomingEdges(v5).iterator();
        assertTrue(it.hasNext());
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        assertTrue(arrayContains(temp, e4));
        assertTrue(arrayContains(temp, e7));
        assertTrue(arrayContains(temp, e9));
        assertTrue(arrayContains(temp, e10));
        
        temp = (Edge<Integer>[])(new Edge[1]);
        count = 0;
        it = directedGraph.incomingEdges(v6).iterator();
        assertTrue(it.hasNext());
        temp[count] = it.next();
        count++;
        assertTrue(arrayContains(temp, e11));
    }

    /**
     * This will test the insertVertex method
     */
    @Test
    public void testInsertVertex() {
        assertEquals(0, undirectedGraph.numVertices());
        Vertex<String> v1 = undirectedGraph.insertVertex("Fayetteville");
        assertTrue(undirectedGraph.vertices().iterator().next().getElement().contentEquals("Fayetteville"));
        assertEquals(1, undirectedGraph.numVertices());
        
        Iterator<Vertex<String>> it = undirectedGraph.vertices().iterator();
        assertTrue(it.hasNext());
        assertEquals(v1, it.next());
        assertFalse(it.hasNext());      

        //directed
        assertEquals(0, directedGraph.numVertices());
        v1 = directedGraph.insertVertex("Fayetteville");
        assertEquals(1, directedGraph.numVertices());
        
        it = directedGraph.vertices().iterator();
        assertTrue(it.hasNext());
        assertEquals(v1, it.next());
        assertFalse(it.hasNext());  
    }

    /**
     * This will test the insertEdge method
     */
    @Test
    public void testInsertEdge() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        
        assertEquals(0, undirectedGraph.numEdges());
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 99);
        assertEquals(0, undirectedGraph.getEdge(v1, v2).getElement().compareTo(e1.getElement()));
        assertEquals(undirectedGraph.getEdge(v1, v2).toString(), "Edge[element=99]");
        assertEquals(1, undirectedGraph.numEdges());
        Iterator<Edge<Integer>> it = undirectedGraph.edges().iterator();
        assertTrue(it.hasNext());
        assertEquals(e1, it.next());
        assertFalse(it.hasNext());


        //Directed
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        
        assertEquals(0, directedGraph.numEdges());
        e1 = directedGraph.insertEdge(v1, v2, 99);
        assertEquals(1, directedGraph.numEdges());
        it = directedGraph.edges().iterator();
        assertTrue(it.hasNext());
        assertEquals(e1, it.next());
        assertFalse(it.hasNext());
    }

    /**
     * This will test the removeVertex method
     */
    @SuppressWarnings("unused")
	@Test
    public void testRemoveVertex() {
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
        
        assertEquals(5, undirectedGraph.numVertices());
        assertEquals(10, undirectedGraph.numEdges());
        assertEquals(v5, undirectedGraph.removeVertex(v5));
        assertEquals(4, undirectedGraph.numVertices());
        assertEquals(6, undirectedGraph.numEdges());
        assertEquals(v4, undirectedGraph.removeVertex(v4));
        assertEquals(3, undirectedGraph.numVertices());
        assertEquals(3, undirectedGraph.numEdges());
        assertEquals(v3, undirectedGraph.removeVertex(v3));
        assertEquals(2, undirectedGraph.numVertices());
        assertEquals(1, undirectedGraph.numEdges());
        assertEquals(v2, undirectedGraph.removeVertex(v2));
        assertEquals(1, undirectedGraph.numVertices());
        assertEquals(0, undirectedGraph.numEdges());
        assertEquals(v1, undirectedGraph.removeVertex(v1));
        assertEquals(0, undirectedGraph.numVertices());
        assertEquals(0, undirectedGraph.numEdges());
        
        // DIRECTED
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
        
        assertEquals(6, directedGraph.numVertices());
        assertEquals(11, directedGraph.numEdges());
        assertEquals(v6, directedGraph.removeVertex(v6));
        assertEquals(5, directedGraph.numVertices());
        assertEquals(10, directedGraph.numEdges());
        assertEquals(5, directedGraph.numVertices());
        assertEquals(10, directedGraph.numEdges());
        assertEquals(v5, directedGraph.removeVertex(v5));
        assertEquals(4, directedGraph.numVertices());
        assertEquals(6, directedGraph.numEdges());
        assertEquals(v4, directedGraph.removeVertex(v4));
        assertEquals(3, directedGraph.numVertices());
        assertEquals(3, directedGraph.numEdges());
        assertEquals(v3, directedGraph.removeVertex(v3));
        assertEquals(2, directedGraph.numVertices());
        assertEquals(1, directedGraph.numEdges());
        assertEquals(v2, directedGraph.removeVertex(v2));
        assertEquals(1, directedGraph.numVertices());
        assertEquals(0, directedGraph.numEdges());
        assertEquals(v1, directedGraph.removeVertex(v1));
        assertEquals(0, directedGraph.numVertices());
        assertEquals(0, directedGraph.numEdges());
    }

    /**
     * This will test the removeEdge method
     */
    @Test
    public void testRemoveEdge() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        @SuppressWarnings("unused")
		Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
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
        
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(10, undirectedGraph.numEdges());
        assertEquals(e1, undirectedGraph.removeEdge(e1));
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(9, undirectedGraph.numEdges());
        assertEquals(e2, undirectedGraph.removeEdge(e2));
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(8, undirectedGraph.numEdges());
        assertEquals(e10, undirectedGraph.removeEdge(e10));
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(7, undirectedGraph.numEdges());
        assertEquals(e3, undirectedGraph.removeEdge(e3));
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(6, undirectedGraph.numEdges());
        assertEquals(e4, undirectedGraph.removeEdge(e4));
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(5, undirectedGraph.numEdges());
        assertEquals(e5, undirectedGraph.removeEdge(e5));
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(4, undirectedGraph.numEdges());
        assertEquals(e6, undirectedGraph.removeEdge(e6));
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(3, undirectedGraph.numEdges());
        assertEquals(e7, undirectedGraph.removeEdge(e7));
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(2, undirectedGraph.numEdges());
        assertEquals(e8, undirectedGraph.removeEdge(e8));
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(1, undirectedGraph.numEdges());
        assertEquals(e9, undirectedGraph.removeEdge(e9));
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(0, undirectedGraph.numEdges());
        
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
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
        
        assertEquals(6, directedGraph.numVertices());
        assertEquals(10, directedGraph.numEdges());
        assertEquals(e1, directedGraph.removeEdge(e1));
        assertEquals(6, directedGraph.numVertices());
        assertEquals(9, directedGraph.numEdges());
        assertEquals(e2, directedGraph.removeEdge(e2));
        assertEquals(6, directedGraph.numVertices());
        assertEquals(8, directedGraph.numEdges());
        assertEquals(e10, directedGraph.removeEdge(e10));
        assertEquals(6, directedGraph.numVertices());
        assertEquals(7, directedGraph.numEdges());
        assertEquals(e3, directedGraph.removeEdge(e3));
        assertEquals(6, directedGraph.numVertices());
        assertEquals(6, directedGraph.numEdges());
        assertEquals(e4, directedGraph.removeEdge(e4));
        assertEquals(6, directedGraph.numVertices());
        assertEquals(5, directedGraph.numEdges());
        assertEquals(e5, directedGraph.removeEdge(e5));
        assertEquals(6, directedGraph.numVertices());
        assertEquals(4, directedGraph.numEdges());
        assertEquals(e6, directedGraph.removeEdge(e6));
        assertEquals(6, directedGraph.numVertices());
        assertEquals(3, directedGraph.numEdges());
        assertEquals(e7, directedGraph.removeEdge(e7));
        assertEquals(6, directedGraph.numVertices());
        assertEquals(2, directedGraph.numEdges());
        assertEquals(e8, directedGraph.removeEdge(e8));
        assertEquals(6, directedGraph.numVertices());
        assertEquals(1, directedGraph.numEdges());
        assertEquals(e9, directedGraph.removeEdge(e9));
        assertEquals(6, directedGraph.numVertices());
        assertEquals(0, directedGraph.numEdges());
    }
}