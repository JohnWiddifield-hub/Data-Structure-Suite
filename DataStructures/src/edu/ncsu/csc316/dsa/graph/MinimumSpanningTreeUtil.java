package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.disjoint_set.UpTreeDisjointSetForest;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;
import edu.ncsu.csc316.dsa.priority_queue.AdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.HeapAdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.HeapPriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;
import edu.ncsu.csc316.dsa.set.HashSet;
import edu.ncsu.csc316.dsa.set.Set;

/**
 * This class can determine minimum spanning trees for graphs using either kruskal
 * or prims algorithm
 * @author John Widdifield and NCSU staff
 */
public class MinimumSpanningTreeUtil {
    

	/**
	 * This will perform kruskals algorithm on a graph and return the resulting 
	 * minimum spanning tree
	 * @param <V>	generic vertex for the graph
	 * @param <E>	generic edge for the graph
	 * @param g		Graph you wish to get the MST for 
	 * @return	a positional list of edges representing the MST
	 */
    public static <V, E extends Weighted> PositionalList<Edge<E>> kruskal(Graph<V, E> g) {
    	PositionalList<Edge<E>> tree = new PositionalLinkedList<>();
    	HeapPriorityQueue<Integer, Edge<E>> Q = new HeapPriorityQueue<>();
    	UpTreeDisjointSetForest<Vertex<V>> D = new UpTreeDisjointSetForest<>();
    	
    	for(Edge<E> e : g.edges()) {
    		Q.insert(e.getElement().getWeight(), e);
    	}
    	
    	for(Vertex<V> v : g.vertices()) {
    		D.makeSet(v);
    	}
    	
    	int components = g.numVertices();
    	
    	while(components > 1) {
    		Edge<E> e = Q.deleteMin().getValue();
    		Position<Vertex<V>> u = D.find(g.endVertices(e)[0]);
    		Position<Vertex<V>> v = D.find(g.endVertices(e)[1]);
    		if(!u.equals(v)) {
    			D.union(u, v);
    			tree.addLast(e);
    			components--;
    		}
    		
    	}
    	return tree;
    }
    
	/**
	 * This will perform the Prim-Jarnik algorithm on a graph and return the resulting 
	 * minimum spanning tree
	 * @param <V>	generic vertex for the graph
	 * @param <E>	generic edge for the graph
	 * @param g		Graph you wish to get the MST for 
	 * @return	a positional list of edges representing the MST
	 */
    public static <V, E extends Weighted> PositionalList<Edge<E>> primJarnik(Graph<V,E> g) {
        AdaptablePriorityQueue<Integer, Vertex<V>> q = new HeapAdaptablePriorityQueue<>();
        Map<Vertex<V>, Integer> weights = new LinearProbingHashMap<>();
        Set<Vertex<V>> known = new HashSet<>();
        Map<Vertex<V>, Entry<Integer, Vertex<V>>> pqEntries = new LinearProbingHashMap<>();
        Map<Vertex<V>, Edge<E>> connectingEdges = new LinearProbingHashMap<>();
        
        PositionalList<Edge<E>> tree = new PositionalLinkedList<>();
        
        Vertex<V> src = g.vertices().iterator().next();
        
        for(Vertex<V> v : g.vertices()) {
            if(v == src) {
                weights.put(v, 0);
            } else {
                weights.put(v, Integer.MAX_VALUE);
            }
            pqEntries.put( v, q.insert(weights.get(v), v));
        }
        while(!q.isEmpty()) {
            Entry<Integer, Vertex<V>> entry = q.deleteMin();
            Vertex<V> u = entry.getValue();
            if(connectingEdges.get(u) != null) {
                tree.addLast(connectingEdges.get(u));
            }
            known.add(u);
            for(Edge<E> e : g.outgoingEdges(u)) {
                Vertex<V> z = g.opposite(u, e);
                int r = e.getElement().getWeight();
                if(!known.contains(z) && r < weights.get(z)) {
                    weights.put(z, r);
                    connectingEdges.put(z, e);
                    q.replaceKey(pqEntries.get(z), r);
                }
            }
        }
        return tree;
    }

}
