package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * SelectionSorter uses the selection sort algorithm to sort data
 * @author Dr. King, John Widdifield
 *
 * @param <E> the generic type of data to sort
 */
public class SelectionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

    /** default constructor */
    public SelectionSorter() {
        this(null);
    }
    
    /**
     * Constructor which creates an instance using a custom comparator
     * @param comparator Comparator you would like to use to compare
     */
    public SelectionSorter(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * Sorts the data using Selection Sort
     * 
     * @param data Data you would like sorted
     */
    public void sort(E[] data) {
        for(int i = 0; i < data.length; i++) {
        	int min = i;
        	for(int j = i + 1; j < data.length; j++) {
        		if(super.compare(data[j], data[min]) < 0) {
        			min = j;
        		}
        	}
        	if(i != min) {
        		E x = data[i];
        		data[i] = data[min];
        		data[min] = x;
        	}
        }
    }
}