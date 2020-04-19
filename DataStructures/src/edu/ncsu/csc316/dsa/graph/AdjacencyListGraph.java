package edu.ncsu.csc316.dsa.graph;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * This class represents a graph using an adjacency list
 * @author John Widdifield and NCSU staff
 *
 * @param <V>	Generic vertex 
 * @param <E>	Generic Edge
 */
public class AdjacencyListGraph<V, E> extends AbstractGraph<V, E> {

    private PositionalList<Vertex<V>> vertexList;
    private PositionalList<Edge<E>> edgeList;
    
    /**
     * This will construct an undirected adjacency list graph
     */
    public AdjacencyListGraph() {
        this(false);
    }
    
    /**
     * This will construct an adjacency list graph
     * @param directed whether or not you wish the graph to be directed
     */
    public AdjacencyListGraph(boolean directed) {
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
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex) {
        return validate(vertex).getOutgoing();
    }
    
    @Override
    public Iterable<Edge<E>> incomingEdges(Vertex<V> vertex) {
        return validate(vertex).getIncoming();
    }

    @Override
    public Edge<E> getEdge(Vertex<V> vertex1, Vertex<V> vertex2) {
        ALVertex v1 = validate(vertex1);
        ALVertex v2 = validate(vertex2);
        Iterator<Edge<E>> it = edgeList.iterator();
        while (it.hasNext()) {
            GraphEdge current = validate(it.next());
            Vertex<V>[] ends = current.getEndpoints();
            if(!isDirected() && ends[1] == v1 && ends[0] == v2) {
                return current;
            }
            if (ends[0] == v1 && ends[1] == v2) {
                return current;
            }
        }
        return null;
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
    public Vertex<V> insertVertex(V vertexData) {
        ALVertex vertex = new ALVertex(vertexData, isDirected());
        Position<Vertex<V>> pos = vertexList.addLast(vertex);
        vertex.setPosition(pos);
        return vertex;
        
    }

    @Override
    public Edge<E> insertEdge(Vertex<V> v1, Vertex<V> v2, E edgeData) {
        ALVertex origin = validate(v1);
        ALVertex destination = validate(v2);       
        ALEdge edge = new ALEdge(edgeData, origin, destination);
        Position<Edge<E>> pos = edgeList.addLast(edge);
        edge.setPosition(pos);
        edge.setOutgoingListPosition(origin.getOutgoing().addLast(edge));
        edge.setIncomingListPosition(destination.getIncoming().addLast(edge));
        return edge;
    }

    @Override
    public Vertex<V> removeVertex(Vertex<V> vertex) {
        ALVertex v = validate(vertex);
        for(Edge<E> edge : v.getOutgoing()) {
        	v = validate(vertex);
        	this.removeEdge(edge);
        }
        if(super.isDirected()) {
        	for(Edge<E> edge : v.getIncoming()) {
        		this.removeEdge(edge);
        	}
        }
        
        return vertexList.remove(v.getPosition());
    }

    @Override
    public Edge<E> removeEdge(Edge<E> edge) {
        ALEdge e = validate(edge);
        Vertex<V>[] ends = e.getEndpoints();
        ALVertex origin = validate(ends[0]);
        ALVertex dest = validate(ends[1]);
        
        dest.getIncoming().remove(e.getIncomingListPosition());
        e = validate(edge);
        origin.getOutgoing().remove(e.getOutgoingListPosition());
        return edgeList.remove(e.getPosition());
    }
    
    /**
     * This class represents an adjacency list vertex
     * @author John Widdifield and NCSU Staff
     *
     */
    private class ALVertex extends GraphVertex {
        
        private PositionalList<Edge<E>> outgoing;
        private PositionalList<Edge<E>> incoming;
        
        public ALVertex(V data, boolean isDirected) {
            super(data);
            outgoing = new PositionalLinkedList<Edge<E>>();
            if(isDirected) {
                incoming = new PositionalLinkedList<Edge<E>>();
            } else {
                incoming = outgoing;
            }
        }
        
        public PositionalList<Edge<E>> getOutgoing() {
            return outgoing;
        }
        
        public PositionalList<Edge<E>> getIncoming() {
            return incoming;
        }
    }
    
    /**
     * This class represents an edge for an adjacency list
     * @author John Widdifield and NCSU staff
     *
     */
    private class ALEdge extends GraphEdge {    
        private Position<Edge<E>> outgoingListPosition;
        private Position<Edge<E>> incomingListPosition;
        
        public ALEdge(E element, Vertex<V> v1,
                Vertex<V> v2) {
            super(element, v1, v2);
        }
        
        public Position<Edge<E>> getOutgoingListPosition() {
            return outgoingListPosition;
        }
        public void setOutgoingListPosition(Position<Edge<E>> outgoingListPosition) {
            this.outgoingListPosition = outgoingListPosition;
        }
        public Position<Edge<E>> getIncomingListPosition() {
            return incomingListPosition;
        }
        public void setIncomingListPosition(Position<Edge<E>> incomingListPosition) {
            this.incomingListPosition = incomingListPosition;
        }
    }

    private ALVertex validate(Vertex<V> v) {
        if(!(v instanceof AdjacencyListGraph.ALVertex)) {
            throw new IllegalArgumentException("Vertex is not a valid adjacency list vertex.");
        }
        return (ALVertex)v;
    }
    
    /**
     * This will validate that an edge is an instance of ALEdge
     * @param e edge to validate
     * @return e casted to ALEdge
     */
    protected ALEdge validate(Edge<E> e) {
        if(!(e instanceof AdjacencyListGraph.ALEdge)) {
            throw new IllegalArgumentException("Edge is not a valid adjacency list edge.");
        }
        return (ALEdge)e;
    }
}