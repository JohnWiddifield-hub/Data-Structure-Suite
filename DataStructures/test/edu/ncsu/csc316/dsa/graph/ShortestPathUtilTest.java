package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;

public class ShortestPathUtilTest {

	private ShortestPathUtil sUtil;
    private Graph<String, Highway> undirectedGraph;
    private Graph<String, Highway> directedGraph;
    
    /**
     * This will set up for the tests with new undirected and directed graphs
     */
    @Before
    public void setUp() {
    	sUtil = new ShortestPathUtil();
        undirectedGraph = new AdjacencyListGraph<String, Highway>();
        directedGraph = new AdjacencyListGraph<String, Highway>(true);
    }
	@SuppressWarnings({ "unused", "static-access" })
	@Test
	public void testDijkstra() {
		 	Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
	        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
	        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
	        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
	        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
	        
	        Edge<Highway> e1 = undirectedGraph.insertEdge(v1, v2, new Highway("R2A", 5));
	        Edge<Highway> e2 = undirectedGraph.insertEdge(v1, v3, new Highway("R2W", 10));
	        Edge<Highway> e3 = undirectedGraph.insertEdge(v1, v4, new Highway("R2D", 15));
	        Edge<Highway> e4 = undirectedGraph.insertEdge(v1, v5, new Highway("R2G", 20));
	        Edge<Highway> e5 = undirectedGraph.insertEdge(v2, v3, new Highway("A2W", 25));
	        Edge<Highway> e6 = undirectedGraph.insertEdge(v2, v4, new Highway("A2D", 30));
	        Edge<Highway> e7 = undirectedGraph.insertEdge(v2, v5, new Highway("A2G", 35));
	        Edge<Highway> e8 = undirectedGraph.insertEdge(v3, v4, new Highway("W2D", 40));
	        Edge<Highway> e9 = undirectedGraph.insertEdge(v3, v5, new Highway("W2G", 45));
	        Edge<Highway> e10 = undirectedGraph.insertEdge(v4, v5, new Highway("D2G", 50));
	        
	        Map<Vertex<String>, Integer> trav = sUtil.dijkstra(undirectedGraph, v1);
	        assertEquals(5, trav.size());
	        assertEquals(0, trav.get(v1).intValue());
	        assertEquals(5, trav.get(v2).intValue());
	        assertEquals(10, trav.get(v3).intValue());
	        assertEquals(15, trav.get(v4).intValue());
	        assertEquals(20, trav.get(v5).intValue());
	        
	        trav = sUtil.dijkstra(undirectedGraph, v3);
	        assertEquals(5, trav.size());
	        assertEquals(10, trav.get(v1).intValue());
	        assertEquals(15, trav.get(v2).intValue());
	        assertEquals(0, trav.get(v3).intValue());
	        assertEquals(25, trav.get(v4).intValue());
	        assertEquals(30, trav.get(v5).intValue());
	        
	        //directed graph tests
		 	v1 = directedGraph.insertVertex("Raleigh");
	        v2 = directedGraph.insertVertex("Asheville");
	        v3 = directedGraph.insertVertex("Wilmington");
	        v4 = directedGraph.insertVertex("Durham");
	        v5 = directedGraph.insertVertex("Greenville");
	        Vertex<String> v6 = directedGraph.insertVertex("Boone");
	        
	        e1 = directedGraph.insertEdge(v1, v2, new Highway("R2A", 5));
	        e2 = directedGraph.insertEdge(v1, v3, new Highway("R2W", 10));
	        e3 = directedGraph.insertEdge(v1, v4, new Highway("R2D", 15));
	        e4 = directedGraph.insertEdge(v1, v5, new Highway("R2G", 20));
	        e5 = directedGraph.insertEdge(v2, v3, new Highway("A2W", 25));
	        e6 = directedGraph.insertEdge(v2, v4, new Highway("A2D", 30));
	        e7 = directedGraph.insertEdge(v2, v5, new Highway("A2G", 35));
	        e8 = directedGraph.insertEdge(v3, v4, new Highway("W2D", 40));
	        e9 = directedGraph.insertEdge(v3, v5, new Highway("W2G", 45));
	        e10 = directedGraph.insertEdge(v4, v5, new Highway("D2G", 50));
	        Edge<Highway> e11 = directedGraph.insertEdge(v5, v6, new Highway("G2B", 55));
	        
	        trav = sUtil.dijkstra(directedGraph, v1);
	        assertEquals(6, trav.size());
	        assertEquals(0, trav.get(v1).intValue());
	        assertEquals(5, trav.get(v2).intValue());
	        assertEquals(10, trav.get(v3).intValue());
	        assertEquals(15, trav.get(v4).intValue());
	        assertEquals(20, trav.get(v5).intValue());
	        assertEquals(75, trav.get(v6).intValue());
	        
	        trav = sUtil.dijkstra(directedGraph, v3);
	        assertEquals(6, trav.size());
	        assertEquals(Integer.MAX_VALUE, trav.get(v1).intValue());
	        assertEquals(Integer.MAX_VALUE, trav.get(v2).intValue());
	        assertEquals(0, trav.get(v3).intValue());
	        assertEquals(40, trav.get(v4).intValue());
	        assertEquals(45, trav.get(v5).intValue());
	        assertEquals(100, trav.get(v6).intValue());
	        
	        trav = sUtil.dijkstra(directedGraph, v6);
	        assertEquals(6, trav.size());
	        assertEquals(Integer.MAX_VALUE, trav.get(v1).intValue());
	        assertEquals(Integer.MAX_VALUE, trav.get(v2).intValue());
	        assertEquals(Integer.MAX_VALUE, trav.get(v3).intValue());
	        assertEquals(Integer.MAX_VALUE, trav.get(v4).intValue());
	        assertEquals(Integer.MAX_VALUE, trav.get(v5).intValue());
	        assertEquals(0, trav.get(v6).intValue());
	}
	
	@SuppressWarnings({ "unused", "static-access" })
	@Test
	public void testShortTree() {
	 	Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        
        Edge<Highway> e1 = undirectedGraph.insertEdge(v1, v2, new Highway("R2A", 5));
        Edge<Highway> e2 = undirectedGraph.insertEdge(v1, v3, new Highway("R2W", 10));
        Edge<Highway> e3 = undirectedGraph.insertEdge(v1, v4, new Highway("R2D", 15));
        Edge<Highway> e4 = undirectedGraph.insertEdge(v1, v5, new Highway("R2G", 20));
        Edge<Highway> e5 = undirectedGraph.insertEdge(v2, v3, new Highway("A2W", 25));
        Edge<Highway> e6 = undirectedGraph.insertEdge(v2, v4, new Highway("A2D", 30));
        Edge<Highway> e7 = undirectedGraph.insertEdge(v2, v5, new Highway("A2G", 35));
        Edge<Highway> e8 = undirectedGraph.insertEdge(v3, v4, new Highway("W2D", 40));
        Edge<Highway> e9 = undirectedGraph.insertEdge(v3, v5, new Highway("W2G", 45));
        Edge<Highway> e10 = undirectedGraph.insertEdge(v4, v5, new Highway("D2G", 50));
        
        Map<Vertex<String>, Integer> trav = sUtil.dijkstra(undirectedGraph, v1);
        Map<Vertex<String>, Edge<Highway>> shortestPathTree = sUtil.shortestPathTree(undirectedGraph, v1, trav);
        assertEquals(4, shortestPathTree.size());
        assertEquals(5, shortestPathTree.get(v2).getElement().getWeight());
        assertEquals(null, shortestPathTree.get(v1));
        assertEquals(10, shortestPathTree.get(v3).getElement().getWeight());
        assertEquals(15, shortestPathTree.get(v4).getElement().getWeight());
        assertEquals(20, shortestPathTree.get(v5).getElement().getWeight());
        
        trav = sUtil.dijkstra(undirectedGraph, v3);
        shortestPathTree = sUtil.shortestPathTree(undirectedGraph, v3, trav);
        assertEquals(4, shortestPathTree.size());
        assertEquals(10, shortestPathTree.get(v1).getElement().getWeight());
        assertEquals(null, shortestPathTree.get(v3));
        assertEquals(5, shortestPathTree.get(v2).getElement().getWeight());
        assertEquals(15, shortestPathTree.get(v4).getElement().getWeight());
        assertEquals(20, shortestPathTree.get(v5).getElement().getWeight());
        
        
        
	 	v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        Vertex<String> v6 = directedGraph.insertVertex("Boone");
        
        e1 = directedGraph.insertEdge(v1, v2, new Highway("R2A", 5));
        e2 = directedGraph.insertEdge(v1, v3, new Highway("R2W", 10));
        e3 = directedGraph.insertEdge(v1, v4, new Highway("R2D", 15));
        e4 = directedGraph.insertEdge(v1, v5, new Highway("R2G", 20));
        e5 = directedGraph.insertEdge(v2, v3, new Highway("A2W", 25));
        e6 = directedGraph.insertEdge(v2, v4, new Highway("A2D", 30));
        e7 = directedGraph.insertEdge(v2, v5, new Highway("A2G", 35));
        e8 = directedGraph.insertEdge(v3, v4, new Highway("W2D", 40));
        e9 = directedGraph.insertEdge(v3, v5, new Highway("W2G", 45));
        e10 = directedGraph.insertEdge(v4, v5, new Highway("D2G", 50));
        Edge<Highway> e11 = directedGraph.insertEdge(v5, v6, new Highway("D2G", 55));
        
        trav = sUtil.dijkstra(directedGraph, v1);
        shortestPathTree = sUtil.shortestPathTree(directedGraph, v1, trav);
        assertEquals(5, shortestPathTree.size());
        assertEquals(5, shortestPathTree.get(v2).getElement().getWeight());
        assertEquals(null, shortestPathTree.get(v1));
        assertEquals(10, shortestPathTree.get(v3).getElement().getWeight());
        assertEquals(15, shortestPathTree.get(v4).getElement().getWeight());
        assertEquals(20, shortestPathTree.get(v5).getElement().getWeight());
        
        trav = sUtil.dijkstra(directedGraph, v3);
        shortestPathTree = sUtil.shortestPathTree(directedGraph, v3, trav);
        assertEquals(3, shortestPathTree.size());
        assertEquals(null, shortestPathTree.get(v3));
        assertEquals(55, shortestPathTree.get(v6).getElement().getWeight());
        assertEquals(40, shortestPathTree.get(v4).getElement().getWeight());
        assertEquals(45, shortestPathTree.get(v5).getElement().getWeight());
		
	}
    private class Highway implements Weighted {
        @SuppressWarnings("unused")
		private String name;
        private int length;
        
        public Highway(String n, int l) {
            setName(n);
            setLength(l);
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        @Override
        public int getWeight() {
            return getLength();
        }
    }
}
