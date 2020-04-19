package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * This is an interface for a collection data structure of general tree
 * @author John Widdifield
 *
 * @param <E> generic param 
 */
public interface GeneralTreeCollection<E> extends Tree<E>, Iterable<E> {
	/**
	 * This will add a root to the tree
	 * @param value value you wish to add
	 * @return the position of the new root
	 */
    Position<E> addRoot(E value);
    /**
     * This will add a child to a position with a given value
     * @param p position to add a  child to
     * @param value value to add to the new node
     * @return position of the new child
     */
    Position<E> addChild(Position<E> p, E value);
    /**
     * This will remove a node from the tree
     * @param p position to remove from
     * @return the element at the removed position
     */
    E remove(Position<E> p);
    /**
     * This will set a value of a node in a tree
     * @param p positino to set
     * @param value new value you wish to set the node to
     * @return the old value contained by the node
     */
    E set(Position<E> p, E value);
}