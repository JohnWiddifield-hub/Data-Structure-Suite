package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Position;
//import edu.ncsu.csc316.dsa.graph.AdjacencyListGraph.ALVertex;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;

/**
 * This is an adjacency Map implementation of a graph.  
 * 
 * Implemented using Data Structures and Algorithms in 
 * Java 6th edition by Michael T. Goodrich
 * @author John Widdifield
 *
 * @param <V>	Generic vertex param
 * @param <E>	Generic edge param
 */
public class AdjacencyMapGraph<V, E> extends AbstractGraph<V, E> {

    private PositionalList<Vertex<V>> vertexList;
    private PositionalList<Edge<E>> edgeList;
    
    /**
     * This will construct an undirected adjacency map graph
     */
    public AdjacencyMapGraph() {
        this(false);
    }
    
    /**
     * This will construct an adjacency map graph
     * @param directed whether or not you wish the graph to be directed
     */
    public AdjacencyMapGraph(boolean directed) {
        super(directed);
        vertexList = new PositionalLinkedList<Vertex<V>>();
        edgeList = new PositionalLinkedList<Edge<E>>();
    }
    
    @Override
    public int numVertices() {
        return vertexList.size();
    }

    @Override
    public Iterable<Vertex<V>> vertices() {
        return vertexList;
    }

    @Override
    public int numEdges() {
        return edgeList.size();
    }

    @Override
    public Iterable<Edge<E>> edges() {
        return edgeList;
    }

    @Override
    public Edge<E> getEdge(Vertex<V> vertex1, Vertex<V> vertex2) {
        return validate(vertex1).getOutgoing().get(vertex2);
    }

    @Override
    public int outDegree(Vertex<V> vertex) {
        return validate(vertex).getOutgoing().size();
    }

    @Override
    public int inDegree(Vertex<V> vertex) {
        return validate(vertex).getIncoming().size();
    }

    @Override
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex) {
        return validate(vertex).getOutgoing().values();
    }

    @Override
    public Iterable<Edge<E>> incomingEdges(Vertex<V> vertex) {
        return validate(vertex).getIncoming().values();
    }

    @Override
    public Vertex<V> insertVertex(V vertexData) {
        AMVertex vertex = new AMVertex(vertexData, isDirected());
        Position<Vertex<V>> pos = vertexList.addLast(vertex);
        vertex.setPosition(pos);
        return vertex;
    }

    @Override
    public Edge<E> insertEdge(Vertex<V> v1, Vertex<V> v2, E edgeData) {
        AMVertex origin = validate(v1);
        AMVertex destination = validate(v2);
        GraphEdge edge = new GraphEdge(edgeData, origin, destination);
        Position<Edge<E>> pos = edgeList.addLast(edge);
        edge.setPosition(pos);
        origin.getOutgoing().put(v2, edge);
        destination.getIncoming().put(v1, edge);
        return edge;
        // Remember to add the edge to the maps for each endpoint vertex
    }

    @Override
    public Vertex<V> removeVertex(Vertex<V> vertex) {
        AMVertex v = validate(vertex);
        
        for(Edge<E> e: v.getOutgoing().values()) {
        	removeEdge(e);
        }
        
        for(Edge<E> e: v.getIncoming().values()) {
        	removeEdge(e);
        }
		return vertexList.remove(v.getPosition());
    }

    @Override
    public Edge<E> removeEdge(Edge<E> edge) {
        GraphEdge e = validate(edge);
        Vertex<V>[] ends = e.getEndpoints();
        AMVertex origin = validate(ends[0]);
        AMVertex dest = validate(ends[1]);
        Edge<E> ret = edgeList.remove(e.getPosition());
        
        dest.getIncoming().remove(ends[0]);
        origin.getOutgoing().remove(ends[1]);
        return ret;
    }
    
    /**
     * This class represents a vertex for an adjacency map graph
     * @author John Widdifield and NCSU staff
     *
     */
    private class AMVertex extends GraphVertex {
        
        private Map<Vertex<V>, Edge<E>> outgoing;
        private Map<Vertex<V>, Edge<E>> incoming;
        
        public AMVertex(V data, boolean isDirected) {
            super(data);
            outgoing = new LinearProbingHashMap<Vertex<V>, Edge<E>>();
            if(isDirected) {
                incoming = new LinearProbingHashMap<>();
            } else {
                incoming = outgoing;
            }
        }
        
        public Map<Vertex<V>, Edge<E>> getOutgoing() {
            return outgoing;
        }
        
        public Map<Vertex<V>, Edge<E>> getIncoming() {
            return incoming;
        }
    }
    
    private AMVertex validate(Vertex<V> v) {
        if(!(v instanceof AdjacencyMapGraph.AMVertex)) {
            throw new IllegalArgumentException("Vertex is not a valid adjacency map vertex.");
        }
        return (AMVertex)v;
    }
}