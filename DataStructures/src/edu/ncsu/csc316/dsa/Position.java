package edu.ncsu.csc316.dsa;

/**
 * This interface is for a position so that you may retrieve an element
 * @author John Widdifield
 *
 * @param <E> generic param 
 */
public interface Position<E> {
	/**
	 * This allows you to retrieve an element from a position
	 * @return element from the instance position
	 */
	E getElement();
}