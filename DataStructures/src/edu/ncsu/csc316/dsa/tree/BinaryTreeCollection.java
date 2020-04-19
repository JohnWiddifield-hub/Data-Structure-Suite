package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * This is an interface for the collection of functions to be used with binary trees. I used the 
 * sixth edition of Data Structures and Algorithms in Java by Michael T. Goodrich to implement the code.
 * @author John Widdifield and NCSU staff
 *
 * @param <E> Generic type param
 */
public interface BinaryTreeCollection<E> extends BinaryTree<E>, Iterable<E> {
	/**
	 * This adds a root to an empty tree
	 * @param value Value you wish to be the root of your tree
	 * @return the position of the new root
	 */
    Position<E> addRoot(E value);
    
    /**
     * This adds a left child to the position given 
     * @param p position to add a child to
     * @param value value you wish to be added as a child
     * @return the position of the new child
     */
    Position<E> addLeft(Position<E> p, E value);
    /**
     * This adds a right child to the position given 
     * @param p position to add a child to
     * @param value value you wish to be added as a child
     * @return the position of the new child
     */
    Position<E> addRight(Position<E> p, E value);
    
    /**
     * This removes a node from the position given 
     * @param p position to remove a node from
     * @return the element of the old node
     */
    E remove(Position<E> p);
    
    /**
     * This sets the value at a position in the tree
     * @param p position you wish to alter
     * @param value Value you wish to set the position to
     * @return the old value held within the position
     */
    E set(Position<E> p, E value);
}