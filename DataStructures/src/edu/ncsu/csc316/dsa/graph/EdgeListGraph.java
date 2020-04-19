package edu.ncsu.csc316.dsa.graph;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * This class represents a graph using an edge list
 * @author John Widdifield and NCSU staff
 *
 * @param <V>	Generic vertex 
 * @param <E>	Generic Edge
 */
public class EdgeListGraph<V, E> extends AbstractGraph<V, E> {

    private PositionalList<Vertex<V>> vertexList;
    private PositionalList<Edge<E>> edgeList;

    /**
     * This will construct an undirected edge list graph
     */
    public EdgeListGraph() {
        this(false);
    }
    
    /**
     * This will construct an edge list graph
     * @param directed whether or not you wish the graph to be directed
     */
    public EdgeListGraph(boolean directed) {
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

    private GraphVertex validate(Vertex<V> v) {
        if (!(v instanceof AbstractGraph.GraphVertex)) {
            throw new IllegalArgumentException("Vertex is not a valid edge list vertex.");
        }
        return (GraphVertex) v;
    }

    @Override
    public Edge<E> getEdge(Vertex<V> vertex1, Vertex<V> vertex2) {
        GraphVertex v1 = validate(vertex1);
        GraphVertex v2 = validate(vertex2);
        Iterator<Edge<E>> it = edgeList.iterator();
        while (it.hasNext()) {
            GraphEdge current = validate(it.next());
            Vertex<V>[] ends = current.getEndpoints();
            if(!isDirected() && ends[1] == v1 && ends[0] == v2) {
                return current;
            }
            if (ends[0] == vertex1 && ends[1] == v2) {
                return current;
            }
        }
        return null;
    }

    @Override
    public int outDegree(Vertex<V> vertex) {
        return outgoingEdgeList(vertex).size();
    }
    
    private List<Edge<E>> outgoingEdgeList(Vertex<V> vertex) {
        GraphVertex v = validate(vertex);
        List<Edge<E>> list = new SinglyLinkedList<Edge<E>>();
        Iterator<Edge<E>> it = edgeList.iterator();
        while(it.hasNext()) {
            GraphEdge edge = validate(it.next());
            Vertex<V>[] ends = edge.getEndpoints();
            if(ends[0] == v) {
                list.addLast(edge);
            }
            else if(!isDirected() && ends[1] == v)
            {
                list.addLast(edge);
            }
        }
        return list;
    }
    
    private List<Edge<E>> incomingEdgeList(Vertex<V> vertex) {
        GraphVertex v = validate(vertex);
        List<Edge<E>> list = new SinglyLinkedList<Edge<E>>();
        Iterator<Edge<E>> it = edgeList.iterator();
        while(it.hasNext()) {
            GraphEdge edge = validate(it.next());
            Vertex<V>[] ends = edge.getEndpoints();
            if(ends[1] == v) {
                list.addLast(edge);
            }
            else if(!isDirected() && ends[0] == v)
            {
                list.addLast(edge);
            }
        }
        return list;
    }

    @Override
    public int inDegree(Vertex<V> vertex) {
        return incomingEdgeList(vertex).size();
    }

    @Override
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex) {
        return outgoingEdgeList(vertex);
    }

    @Override
    public Iterable<Edge<E>> incomingEdges(Vertex<V> vertex) {
        return incomingEdgeList(vertex);
    }

    @Override
    public Vertex<V> insertVertex(V vertexData) {
        GraphVertex v = new GraphVertex(vertexData);
        Position<Vertex<V>> pos = vertexList.addLast(v);
        v.setPosition(pos);
        return v;
    }

    @Override
    public Edge<E> insertEdge(Vertex<V> vertex1, Vertex<V> vertex2, E edgeData) {
        GraphVertex origin = validate(vertex1);
        GraphVertex destination = validate(vertex2);
        GraphEdge e = new GraphEdge(edgeData, origin, destination);
        Position<Edge<E>> pos = edgeList.addLast(e);
        e.setPosition(pos);
        return e;
    }

    @Override
    public Vertex<V> removeVertex(Vertex<V> vertex) {
        GraphVertex v = validate(vertex);
        for(Edge<E> e: outgoingEdges(v)) {
            removeEdge(e);
        }
        for(Edge<E> e: incomingEdges(v)) {
            removeEdge(e);
        }
        return vertexList.remove(v.getPosition());
    }

    @Override
    public Edge<E> removeEdge(Edge<E> edge) {
        GraphEdge e = validate(edge);
        return edgeList.remove(e.getPosition());
    }
}