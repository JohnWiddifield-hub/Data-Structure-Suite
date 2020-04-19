package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;
import edu.ncsu.csc316.dsa.priority_queue.AdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.HeapAdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.HeapPriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;
import edu.ncsu.csc316.dsa.set.HashSet;
import edu.ncsu.csc316.dsa.set.Set;

public class ShortestPathUtil {
    
    public static <V, E extends Weighted> Map<Vertex<V>, Integer> dijkstra(Graph<V,E> g, Vertex<V> src) {
    	Map<Vertex<V>, Integer> weights = new LinearProbingHashMap<>();
    	HeapAdaptablePriorityQueue<Integer, Vertex<V>> que = new HeapAdaptablePriorityQueue<>();
    	Map<Vertex<V>, Entry<Integer, Vertex<V>>> pqEntries = new LinearProbingHashMap<>();
    	Set<Vertex<V>> known = new HashSet<>();
    	
    	for(Vertex<V> vert : g.vertices()) {
    		if(vert == src) {
    			weights.put(vert, 0);
    		} else {
    			weights.put(vert, Integer.MAX_VALUE);
    		}
    		pqEntries.put(vert, que.insert(weights.get(vert), vert));
    	}
    		
    	while(!que.isEmpty()) {
    		Entry<Integer, Vertex<V>> entry = que.deleteMin();
    		Vertex<V> u = entry.getValue();
    		Integer entWeight = entry.getKey();
    		weights.put(u, entWeight);
    		known.add(u);
    		for(Edge<E> e : g.outgoingEdges(u)) {
    			Vertex<V> z = g.opposite(u, e);
    			Integer r;
    			if(e.getElement().getWeight() == Integer.MAX_VALUE  || weights.get(u) == Integer.MAX_VALUE) {
    				r = Integer.MAX_VALUE;
    			} else {
    				r = e.getElement().getWeight() + weights.get(u);
    			}
    			if(!known.contains(z) && r < weights.get(z)) {
    				weights.put(z, r);
    				que.replaceKey(pqEntries.get(z), r);
    			}
    		}
    	}
 	return weights;
    }

    public static <V, E extends Weighted> Map<Vertex<V>, Edge<E>> shortestPathTree(Graph<V,E> g, Vertex<V> s, Map<Vertex<V>, Integer> distances) {
    	Map<Vertex<V>, Edge<E>> edges = new LinearProbingHashMap<>();
    	for(Vertex<V> v : distances) {
    		if(!v.equals(s)) {
    			for(Edge<E> e : g.incomingEdges(v)) {
    				Vertex<V> u = g.opposite(v, e);
    				if(distances.get(v).equals(distances.get(u) + e.getElement().getWeight())) {
    					edges.put(v, e);
    				}
    			}
    		}
    	}
    	return edges;
    }
}