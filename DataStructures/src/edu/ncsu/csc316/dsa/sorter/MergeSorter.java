package edu.ncsu.csc316.dsa.sorter;

import java.util.Arrays;
import java.util.Comparator;

/**
 * This class uses a merge sort algorithm to sort abstract data in ascending order
 * This class was implemented using the book Data Structures and Algorithms in Java by 
 * Michael T. Goodrich
 * @author John Widdifield jfwiddif
 * @param <E> Generic type
 */

public class MergeSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/**
	 * This is the constructor for merge sorter to use when you desire a custom comparator
	 * @param comparator	comparator you wish to use
	 */
	public MergeSorter(Comparator<E> comparator) {
		super(comparator);
	}
	
	/**
	 * This is the constructor to use for a default comparator
	 */
	public MergeSorter() {
		this(null);
	}

	@Override
	public void sort(E[] arr) {
		int n = arr.length;
		if(n < 2) {
			return;
		}
		int mid = n / 2;
		E[] left = Arrays.copyOfRange(arr, 0, mid);
		E[] right = Arrays.copyOfRange(arr, mid, n);
		
		sort(left);
		sort(right);
		
		merge(left, right, arr);
		
	}
	
	/**
	 * This merges two arrays into sorted order to be used in conjuction with sort()
	 * @param left	Left array you wish to merge
	 * @param right  Right array you wish to merge
	 * @param arr	Final output array
	 */
	private void merge(E[] left, E[] right, E[] arr) {
		int i = 0;
		int j = 0;
		
		while(i + j < arr.length) {
			if(j == right.length || (i < left.length && compare(left[i], right[j]) < 0)) {
				arr[i + j] = left[i++];
			} else {
				arr[i + j] = right[j++];
			}
		}
	}
}