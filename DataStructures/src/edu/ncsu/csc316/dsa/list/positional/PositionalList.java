package edu.ncsu.csc316.dsa.list.positional;

import edu.ncsu.csc316.dsa.Position;

/**
 * This is simply an interface for a generic positional list
 * @author NCSU teaching staff implemented by John Widdifield
 *
 * @param <E> Generic type
 */
public interface PositionalList<E> extends Iterable<E> {
	
	/**
	 * Adds to the list after a specified position
	 * @param p Position to add after
	 * @param value Value you would like to add
	 * @return Position of the new value
	 */
	Position<E> addAfter(Position<E> p, E value);
	/**
	 * Adds to the list before a specified position
	 * @param p Position to add before
	 * @param value Value you would like to add
	 * @return Position of the new value
	 */
	Position<E> addBefore(Position<E> p, E value);
	
	/**
	 * Adds to the beginning of the list
	 * @param value Value you would like to add
	 * @return Position of the new value
	 */
	Position<E> addFirst(E value);
	
	/**
	 * Adds to the end of the list
	 * @param value Value you would like to add
	 * @return Position of the new value
	 */
	Position<E> addLast(E value);
	/**
	 * Gets the position after a given position
	 * @param p position you would like the position after
	 * @return position after the given position
	 */
	Position<E> after(Position<E> p);
	/**
	 * Gets the position before a given position
	 * @param p position you would like the position before
	 * @return position before the given position
	 */
	Position<E> before(Position<E> p);
	/**
	 * Gets the first position
	 * @return the first position
	 */
	Position<E> first();
	/**
	 * Tells if the list is empty
	 * @return true if the list is empty false if not
	 */
	boolean isEmpty();
	/**
	 * Gets the last position in the list
	 * @return last position in the list
	 */
	Position<E> last();
	/**
	 * gets the positional iterator
	 * @return positional iterator object
	 */
	Iterable<Position<E>> positions();
	/**
	 * removes a position from the list
	 * @param p position to remove
	 * @return the element within the removed position
	 */
	E remove(Position<E> p);
	/**
	 * sets a given position to have a given value
	 * @param p position to set
	 * @param value value you would like to set it to
	 * @return the element which was overridden
	 */
	E set(Position<E> p, E value);
	/**
	 * gets the size of the list
	 * @return the number of positions in the list
	 */
	int size();
}