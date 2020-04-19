package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * This is an interface for a binary tree data structure.  I used the sixth edition of
 * Data Structures and Algorithms in Java by Michael T. Goodrich to implement the code.
 * @author John Widdifield and NCSU staff
 *
 * @param <E> Generic type parameter
 */
public interface BinaryTree<E> extends Tree<E> {
	/**
	 * This gets the position of the left child of the given position
	 * @param p Position to get left child of
	 * @return position of the left child of p
	 */
    Position<E> left(Position<E> p);
    
    /**
	 * This gets the position of the right child of the given position
	 * @param p Position to get right child of
	 * @return position of the right child of p
	 */
    Position<E> right(Position<E> p);
    
    /**
	 * This gets the position of the sibling of the given position
	 * @param p Position to get sibling of
	 * @return position of the sibling of p
	 */
    Position<E> sibling(Position<E> p);
    
    /**
     * This will allow you to iterate through a tree in order
     * @return in order iterable list of positions
     */
    Iterable<Position<E>> inOrder();
}