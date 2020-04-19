/**
 * 
 */
package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;


/**
 * Sorts a list of data based on the bubble sorter algorithm
 *  
 * @author John Widdifield
 * @param <E> The generic type to be used as input
 */
public class BubbleSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

    /** default constructor */
    public BubbleSorter() {
        this(null);
    }
    /**
     * Constructor using a specified comparator.
     * @param comparator Comparator you would like to use.
     */
	public BubbleSorter(Comparator<E> comparator) {
		super(comparator);
	}

	@Override
	public void sort(E[] data) {
		Boolean r = true;
		while(r) {
			r = false;
			for(int i = 1; i < data.length; i++) {
				if(super.compare(data[i], data[i - 1]) < 0) {
					E x = data[i - 1];
					data[i - 1] = data[i];
					data[i] = x;
					r = true;
				}
			}
		}
	}

}
