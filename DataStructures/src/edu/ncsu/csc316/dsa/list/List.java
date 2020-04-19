package edu.ncsu.csc316.dsa.list;

/**
 * This is an interface for a generic list
 * @author John Widdifield
 *
 * @param <E>  Generic Parameter
 */
public interface List<E> extends Iterable<E> {
	/**
	 * This adds a value to an index within the list
	 * @param index index to add at
	 * @param value value you wish to add
	 */
	void add(int index, E value);
	/**
	 * Adds to the beginning of the list
	 * 
	 * @param value Value you wish to add
	 */
	void addFirst(E value);
	
	/**
	 * Adds to the end of the list
	 * 
	 * @param value Value you wish to add
	 */
	void addLast(E value);
	
	/**
	 * Gets the first element in the list
	 * 
	 * @return returns the element at the first position
	 */
	E first();
	/**
	 * Gets the element at an index within the list
	 * @param index index you would like to retrieve from
	 * @return the element at the given index
	 */
	E get(int index);
	/**
	 * Checks to see if the list is empty
	 * @return false if it is not empty true if it is empty
	 */
	boolean isEmpty();
	/**
	 * gets the last element in the list
	 * @return last element within the list
	 */
	E last();
	/**
	 * Removes an element from the list at the given index
	 * @param index index you would like removed from
	 * @return the element at the given index which was removed
	 */
	E remove(int index);
	/**
	 * Removes the first element in the list
	 * 
	 * @return The element that was removed from the beginning
	 */
	E removeFirst();
	/**
	 * Removes the last element from the list
	 * @return the last element from the list which was removed
	 */
	E removeLast();
	/**
	 * Sets a value at a given index 
	 * @param index Index you would like to set at
	 * @param value Value you would like to replace the old value with
	 * @return The value which was removed during the set
	 */
	E set(int index, E value);
	/**
	 * Returns the number of indexes in the list
	 * @return Size of the list 
	 */
	int size();
}