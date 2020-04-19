package edu.ncsu.csc316.dsa.tree;
import edu.ncsu.csc316.dsa.Position;

/**
 * This is an interface for a tree data structure.  I used the sixth edition of
 * Data Structures and Algorithms in Java by Michael T. Goodrich to implement the code.
 * @author John Widdifield and NCSU staff
 *
 * @param <E> Generic paramter type
 */
public interface Tree<E> {

	/**
	 * This gets the root position in the tree
	 * @return the position of the root
	 */
    Position<E> root();
    /**
     * This gets the parent of the position p
     * @param p position you wish to know the parent of
     * @return the parents position
     */
    Position<E> parent(Position<E> p);
    /**
     * This gets all of the children of a node
     * @param p position you wish to know the children of
     * @return an iterable list of the positions children
     */
    Iterable<Position<E>> children(Position<E> p);
    /**
     * This gets the number of children owned by a parent p
     * @param p position you wish to know how many children it has
     * @return number of children p has
     */
    int numChildren(Position<E> p);
    /**
     * This will let you know if a position is internal meaning it is not a root or leaf node
     * @param p position you wish to inspect
     * @return false if the node is a root or leaf node true if otherwise
     */
    boolean isInternal(Position<E> p);
    /**
     * This will let you know if a position is a leaf node in the tree
     * @param p position of the node to inspect
     * @return true if p is a leaf false if not
     */
    boolean isLeaf(Position<E> p);
    /**
     * This will let you know if a position is a root node in the tree
     * @param p position of the node to inspect
     * @return true if p is a root false if not
     */
    boolean isRoot(Position<E> p);
    /**
     * This will tell you the size of the tree
     * @return the size of the tree, how many nodes it contains
     */
    int size();
    /**
     * This will tell you if the tree is empty or not
     * @return true if the tree is empty false if the tree is not empty
     */
    boolean isEmpty();

    /**
     * This will give you an iterable list of positions held by the tree in pre-Order
     * @return iterable list of positions in pre-order
     */
    Iterable<Position<E>> preOrder();
    /**
     * This will give you an iterable list of positions held by the tree in post-Order
     * @return iterable list of positions in post-order
     */
    Iterable<Position<E>> postOrder();
    /**
     * This will give you an iterable list of positions held by the tree in level-Order
     * @return iterable list of positions in level-order
     */
    Iterable<Position<E>> levelOrder();
}