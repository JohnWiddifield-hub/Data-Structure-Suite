package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * This class abstracts information for comparison based sorting algorithms.
 * @author John Widdifield
 *
 * @param <E> Generic type to be used
 */
public abstract class AbstractComparisonSorter<E extends Comparable<E>> implements Sorter<E> {

	/** Comparator to be used for comparison */
    private Comparator<E> comparator;
    
    /**
     * Constructs an instance of this class using a specified comparator
     * @param comparator	Comparator to be used in sorting
     */
    public AbstractComparisonSorter(Comparator<E> comparator) {
        setComparator(comparator);
    }
    
    /**
     * Setst the comparator to be used for comparisons
     * @param comparator	Comparator you would like to change to.
     */
    private void setComparator(Comparator<E> comparator) {
        if(comparator == null) {
           comparator = new NaturalOrder();
        }
        this.comparator = comparator;
    }     
    
    /**
     * This class determines the natural order of generic data
     * 
     * @author John Widdifield
     *
     */
    private class NaturalOrder implements Comparator<E> {
        public int compare(E first, E second) {
            return ((Comparable<E>) first).compareTo(second);
        }
    }
    
    /**
     * This method compares two generic data types.
     * @param data1	first object you would like to compare
     * @param data2 second object you would like to compare
     * @return positiv integer if data1 should be before data2, negative if data2 should be before data1
     * and 0 if they belong at the same position meaning they are the same.
     */
    public int compare(E data1, E data2) {
        return comparator.compare(data1,  data2);
    }
}