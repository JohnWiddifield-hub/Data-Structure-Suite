package edu.ncsu.csc316.dsa.graph;

/**
 * This interface lays out the functionality for a graph data structure
 * @author NCSU Staff and John Widdifield
 *
 * @param <V>	Generic vertex
 * @param <E>	Generic Edge
 */
public interface Graph<V, E> {
	/**
	 * This will tell you if the graph is directed or not
	 * @return true if the graph is directed false otherwise
	 */
    boolean isDirected();
    
    /**
     * This will tell you how many vertices there are in the graph
     * @return number of vertices
     */
    int numVertices();
    /**
     * this will give you an iterable list of vertices
     * @return a iterable list of vertices
     */
    Iterable<Vertex<V>> vertices();
    /**
     * This will tell you the number of edgse in the graph
     * @return the number of edges
     */
    int numEdges();
    /**
     * This will give you an iterable list of edges in the graph
     * @return an iterable list of edges
     */
    Iterable<Edge<E>> edges();
    /**
     * This will get an edge between two vertices for you
     * @param vertex1	the beginning vertex of the edge
     * @param vertex2	the ending vertex of the edge
     * @return	the edge which connnects the two vertices
     */
    Edge<E> getEdge(Vertex<V> vertex1, Vertex<V> vertex2);
    /**
     * This will get the vertices which are at the ends of an edge
     * @param edge edge to get the vertices for
     * @return an array of vertices with index 0 being the start and 1 being the finish
     */
    Vertex<V>[] endVertices(Edge<E> edge);
    /**
     * This will get the opposite vertex of the edge given the start vertex
     * @param vertex	vertex to start from
     * @param edge	edge to follow to get to the opposite vertex
     * @return	the vertex opposite of vertex following edge
     */
    Vertex<V> opposite(Vertex<V> vertex, Edge<E> edge);
    /**
     * This will get the number of edges leaving a vertex
     * @param vertex	vertex to get the outdegree of
     * @return	the number of edges leaving vertex
     */
    int outDegree(Vertex<V> vertex);
    /**
     * This will get the number of edges entering a vertex
     * @param vertex	vertex to get the in degree of
     * @return	the number of edges entering vertex
     */
    int inDegree(Vertex<V> vertex);
    /**
     * This will get an iterable list of the outgoing edges from a vertex
     * @param vertex	vertex to get the edges of 
     * @return an iterable list of outgoing edges leaving vertex
     */
    Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex);
    /**
     * This will get an iterable list of the incoming edges to a vertex
     * @param vertex	vertex to get the edges of 
     * @return an iterable list of incoming edges entering vertex
     */
    Iterable<Edge<E>> incomingEdges(Vertex<V> vertex);
    /**
     * This will insert a vertex into the graph
     * @param vertexData vertex to enter into the graph
     * @return the inserted vertex
     */
    Vertex<V> insertVertex(V vertexData);
    /**
     * This will insert an edge into the graph connecting two vertices
     * @param v1 vertex you want the edge to leave from
     * @param v2 vertex you want the edge to enter
     * @param edgeData data you wish the edge to contain (element)
     * @return  the inserted edge
     */
    Edge<E> insertEdge(Vertex<V> v1, Vertex<V> v2, E edgeData);
    /**
     * This will remove a vertex from the graph
     * @param vertex vertex you wish to remove
     * @return vertex which was removed
     */
    Vertex<V> removeVertex(Vertex<V> vertex);
    /**
     * This will remove an edge from the graph
     * @param edge edge you wish to remove
     * @return edge which was removed
     */
    Edge<E> removeEdge(Edge<E> edge);
    
    /**
     * This is the interface for an edge object in a graph
     * @author NCSU staff and John Widdifield
     *
     * @param <E> Generic element
     */
    interface Edge<E> {
    	/**
    	 * This will get the element from the edge
    	 * @return element within the edge
    	 */
        E getElement();
    }
    /**
     * This is the interface for an vertex object in a graph
     * @author NCSU staff and John Widdifield
     *
     * @param <V> Generic vertex
     */
    interface Vertex<V> {
    	/**
    	 * This will get the element from the vertex
    	 * @return vertex element
    	 */
        V getElement();
    }
}