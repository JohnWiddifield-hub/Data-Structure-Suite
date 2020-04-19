package edu.ncsu.csc316.dsa.disjoint_set;

import edu.ncsu.csc316.dsa.Position;

/**
 * This is the interface for a disjoint set forest.
 * @author John Widdifield and NCSU staff
 *
 * @param <E>	generic param
 */
public interface DisjointSetForest<E> {

	/**
	 * This will make a new set with the given value at the root node
	 * @param value	value to set as root node
	 * @return position of the new root node
	 */
    Position<E> makeSet(E value);
    /**
     * This will find a value within the forest
     * @param value value to find
     * @return	position of the root of the tree which contains that value
     */
    Position<E> find(E value);
    
    /**
     * This performs the union operation to combine two sets.
     * @param s	root position of first set to combine
     * @param t root position of second set to combine
     */
    void union(Position<E> s, Position<E> t);

}