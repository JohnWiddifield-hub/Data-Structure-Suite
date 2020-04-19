package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;
import edu.ncsu.csc316.dsa.queue.ArrayBasedQueue;
import edu.ncsu.csc316.dsa.set.HashSet;
import edu.ncsu.csc316.dsa.set.Set;

public class GraphTraversalUtil {
    
    public static <V,E> Map<Vertex<V>, Edge<E>> depthFirstSearch(Graph<V, E> graph, Vertex<V> start) {
        Set<Vertex<V>> known = new HashSet<Vertex<V>>();
        Map<Vertex<V>, Edge<E>> forest = new LinearProbingHashMap<Vertex<V>, Edge<E>>();
        dfsHelper(graph, start, known, forest);
        return forest;
    }
    
    private static <V,E> void dfsHelper(Graph<V,E> graph, Vertex<V> u, Set<Vertex<V>> known, Map<Vertex<V>, Edge<E>> forest) {
        known.add(u);
        for(Edge<E> e : graph.outgoingEdges(u)) {
        	Vertex<V> v = graph.opposite(u, e);
        	if(!known.contains(v)) {
        		forest.put(v,  e);
        		dfsHelper(graph, v, known, forest);
        	}
        }
    }
    
    public static <V,E> Map<Vertex<V>, Edge<E>> breadthFirstSearch(Graph<V,E> graph, Vertex<V> start) {
    	Set<Vertex<V>> visited = new HashSet<Vertex<V>>();
    	Map<Vertex<V>, Edge<E>> forest = new LinearProbingHashMap<Vertex<V>, Edge<E>>();
    	ArrayBasedQueue<Vertex<V>> reachableVertices = new ArrayBasedQueue<Vertex<V>>();
    	
    	visited.add(start);
    	reachableVertices.enqueue(start);
    	while(!reachableVertices.isEmpty()) {
    		Vertex<V> u = reachableVertices.dequeue();
    		for(Edge<E> e : graph.outgoingEdges(u)) {
    			Vertex<V> w = graph.opposite(u, e);
    			if(!visited.contains(w)) {
    				visited.add(w);
    				forest.put(w, e);
    				reachableVertices.enqueue(w);
    			}
    		}
    	}
    	return forest;
    }
}