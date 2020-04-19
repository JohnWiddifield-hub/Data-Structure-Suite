package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

public class MinimumSpanningTreeUtilTest {


	private MinimumSpanningTreeUtil mUtil;
    private Graph<String, Highway> undirectedGraph;
    private Graph<String, Highway> directedGraph;
    
    /**
     * This will set up for the tests with new undirected and directed graphs
     */
    @Before
    public void setUp() {
    	mUtil = new MinimumSpanningTreeUtil();
        undirectedGraph = new AdjacencyListGraph<String, Highway>();
        directedGraph = new AdjacencyListGraph<String, Highway>(true);
    }
    
	@SuppressWarnings({ "static-access", "unused" })
	@Test
	public void testPrimKrusk() {
		
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
        
        PositionalList<Edge<Highway>> minSpanTree = mUtil.primJarnik(undirectedGraph);
        assertEquals(4, minSpanTree.size());
        assertEquals(e1, minSpanTree.first().getElement());
        assertEquals(e2, minSpanTree.after(minSpanTree.first()).getElement());
        assertEquals(e3, minSpanTree.before(minSpanTree.last()).getElement());
        assertEquals(e4, minSpanTree.last().getElement());
        
        minSpanTree = mUtil.kruskal(undirectedGraph);
        assertEquals(4, minSpanTree.size());
        assertEquals(e1, minSpanTree.first().getElement());
        assertEquals(e2, minSpanTree.after(minSpanTree.first()).getElement());
        assertEquals(e3, minSpanTree.before(minSpanTree.last()).getElement());
        assertEquals(e4, minSpanTree.last().getElement());
        
        
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
        
        minSpanTree = mUtil.primJarnik(directedGraph);
        assertEquals(5, minSpanTree.size());
        assertEquals(e1, minSpanTree.first().getElement());
        assertEquals(e2, minSpanTree.after(minSpanTree.first()).getElement());
        assertEquals(e3, minSpanTree.before(minSpanTree.before(minSpanTree.last())).getElement());
        assertEquals(e11, minSpanTree.last().getElement());
        assertEquals(e4, minSpanTree.before(minSpanTree.last()).getElement());

        
        minSpanTree = mUtil.kruskal(directedGraph);
        assertEquals(5, minSpanTree.size());
        assertEquals(e1, minSpanTree.first().getElement());
        assertEquals(e2, minSpanTree.after(minSpanTree.first()).getElement());
        assertEquals(e3, minSpanTree.before(minSpanTree.before(minSpanTree.last())).getElement());
        assertEquals(e11, minSpanTree.last().getElement());
        assertEquals(e4, minSpanTree.before(minSpanTree.last()).getElement());
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
