package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Position;

/**
 * This is an abstract implementation of a graph meant to be adapted into a full data structure
 * @author John Widdifield and NCSU staff
 *
 * @param <V> Generic vertex
 * @param <E> generic edge
 */
public abstract class AbstractGraph<V, E> implements Graph<V, E> {
    
    private boolean isDirected;
    
    /**
     * This will construct an abstract graph 
     * @param directed whether or not you want a directed graph or an undirected graph
     */
    public AbstractGraph(boolean directed) {
        setDirected(directed);
    }
    
    private void setDirected(boolean directed) {
        isDirected = directed;
    }
    
    /**
     * This will tell you if the graph is directed or not
     * @return true if it is directed false otherwise
     */
    public boolean isDirected() {
        return isDirected;
    }
    
    @Override
    public Vertex<V>[] endVertices(Edge<E> edge) {
        return validate(edge).getEndpoints();
    }

    @Override
    public Vertex<V> opposite(Vertex<V> vertex, Edge<E> edge) {
        GraphEdge temp = validate(edge);
        Vertex<V>[] ends = temp.getEndpoints();
        if(ends[0] == vertex) {
            return ends[1];
        }
        if(ends[1] == vertex) {
            return ends[0];
        }
        throw new IllegalArgumentException("Vertex is not incident on this edge.");
    }
    
    /**
     * This class is for a vertex within the graph
     * @author John Widdifield and ncsu staff
     *
     */
    protected class GraphVertex implements Vertex<V> {
        private V element;
        private Position<Vertex<V>> position;
        
        /**
         * This will construct a new graph vertex
         * @param element element for the vertex to contain
         */
        public GraphVertex(V element) {
            setElement(element);
        }
        
        /**
         * This will set the vertex element
         * @param element element you wish to set
         */
        public void setElement(V element) {
            this.element = element;
        }
        
        /**
         * This will get the element from the vertex
         * @return vertex element that the vertex contains
         */
        public V getElement() {
            return element;
        }
        
        /**
         * This will get the position of the vertex
         * @return the position of the vertex
         */
        public Position<Vertex<V>> getPosition(){
            return position;
        }
        
        /**
         * This will set the position for the vertex
         * @param pos	position you wish to set the vertex to have
         */
        public void setPosition(Position<Vertex<V>> pos) {
            position = pos;
        }
    }
    
    /**
     * This represents an edge within the graph
     * @author John Widdifield and NCSU staff
     *
     */
    protected class GraphEdge implements Edge<E> {
        private E element;
        private Vertex<V>[] endpoints;
        private Position<Edge<E>> position;
        
        /**
         * This constructs a graph edge
         * @param element	element you wish the edge to have
         * @param v1	vertex the edge is leaving
         * @param v2	vertex the edge is entering
         */
        @SuppressWarnings("unchecked")
        public GraphEdge(E element, Vertex<V> v1, Vertex<V> v2) {
            setElement(element);
            endpoints = (Vertex<V>[])new Vertex[]{v1, v2};
        }
        
        /**
         * This will set the element for the edge
         * @param element element to set
         */
        public void setElement(E element) {
            this.element = element;
        }
        
        /**
         * This will get an element from the edge
         * @return element element from the edge
         */
        public E getElement() {
            return element;
        }
        
        /**
         * This will get the endpoints of an edge
         * @return edges endpoints as an array of vertices with index 0 being the beginning 
         * 1 being the end
         */
        public Vertex<V>[] getEndpoints() {
            return endpoints;
        }
        
        /**
         * This will get the position of the edge
         * @return the posisitino of the edge
         */
        public Position<Edge<E>> getPosition(){
            return position;
        }
        
        /**
         * This will set the position of the edge
         * @param pos position to set
         */
        public void setPosition(Position<Edge<E>> pos) {
            position = pos;
        }
        
        @Override
        public String toString() {
            return "Edge[element=" + element + "]";
        }
    }
    
    /**
     * This will validate that an edge is a graph edge
     * @param e edge to validate
     * @return a GraphEdge cast of e
     */
    protected GraphEdge validate(Edge<E> e) {
        if (!(e instanceof AbstractGraph.GraphEdge)) {
            throw new IllegalArgumentException("Edge is not a valid graph edge.");
        }
        return (GraphEdge) e;
    }
}