package edu.ncsu.csc316.dsa.sorter;

/**
 * Interface that defines the sorting behavior
 * @author Dr. King
 * 
 * @param <E> Generic type 
 */
public interface Sorter<E> {
	
	/**
	 * This function will sort the data for the array from lowest to highest and from left 
	 * to right.
	 * 
	 * @param data	List of Integers that you would like to have sorted.
	 */
	public void sort(E[] data);
}
