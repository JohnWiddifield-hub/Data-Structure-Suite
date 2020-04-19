package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * This is the quicksorter class which is to be used for sorting lists of generic types 
 * in ascending order with a quicksorting algorithm.
 * @author John Widdifield
 *
 * @param <E> Generic param 
 */
public class QuickSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/** For using the first element as a pivot */
    public static final PivotSelector FIRST_ELEMENT_SELECTOR = new FirstElementSelector();
    /** For using the last element as a pivot */
    public static final PivotSelector LAST_ELEMENT_SELECTOR = new LastElementSelector();
    /** For using the middle element as a pivot */
    public static final PivotSelector MIDDLE_ELEMENT_SELECTOR = new MiddleElementSelector();
    /** For using a random element as a pivot */
    public static final PivotSelector RANDOM_ELEMENT_SELECTOR = new RandomElementSelector();
    
    private PivotSelector selector;
    
    /**
     * This is the constructor for using a custom comparator and selector
     * @param comparator Comparator you wish to use to compare objects
     * @param selector	Pivot Selector you wish to use
     */
    public QuickSorter(Comparator<E> comparator, PivotSelector selector) {
        super(comparator);
        setSelector(selector);
    }
    
    /**
     * This is the constructor for using a custom comparator
     * @param comparator Comparator you wish to use to compare objects
     */
	public QuickSorter(Comparator<E> comparator) {
		this(comparator, null);
	}    
    
	/**
	 * This is the constructor for use with a custom selector
	 * @param selector	Pivot Selector you wish to use
	 */
    public QuickSorter(PivotSelector selector) {
        this(null, selector);
    }
    
    /**
     * This is the default constructor
     */
    public QuickSorter() {
        this(null, null);
    }
    
    /**
     * This sets the selector of the pivot location
     * @param selector PivotSelector you would like to use to partition
     */
    private void setSelector(PivotSelector selector) {
        if(selector == null) {
            selector = new RandomElementSelector();
        }
        this.selector = selector;
    }
    
	@Override
	public void sort(E[] data) {
		quickSort(data, 0, data.length - 1);
		
	}
	
	/**
	 * This sorts the array from low to high using a quicksort algorithm
	 * @param arr	Array to partition	
	 * @param low Low element of the array
	 * @param high High element of the array
	 */
	private void quickSort(E[] arr, int low, int high) {
		if(low < high) {
			int pivotLocation = partition(arr, low, high);
			quickSort(arr, low, pivotLocation - 1);
			quickSort(arr, pivotLocation + 1, high);
		}
	}
	
	/**
	 * This partitions the array for use with quicksort
	 * @param arr	Array to partition	
	 * @param low Low element of the array
	 * @param high High element of the array
	 * @return	The location of the pivot element 
	 */
	private int partition(E[] arr, int low, int high) {
		int pivotIndex = selector.selectPivot(low, high);
		swap(arr, high, pivotIndex);
		return partitionHelper(arr, low, high);
	}
	
	/**
	 * This helps the partition perform the swaps 
	 * @param arr		array to help partition
	 * @param low	Low index of the array
	 * @param high  high index of the array
	 * @return	The location of the pivot element within the array
	 */
	private int partitionHelper(E[] arr, int low, int high) {
		E pivot = arr[high];
		int index = low;
		for(int j = low; j <= (high - 1); j++) {
			if(compare(arr[j], pivot) <= 0) {
				swap(arr, j, index);
				index++;
			}
		}
		swap(arr, high, index);
		return index;
	}
	
	/**
	 * This swaps two indexes within an array
	 * @param arr				Array to perform the swap	
	 * @param low			low index to swap
	 * @param high			high index to swap
	 */
	private void swap(E[] arr, int low, int high) {
		E temp = arr[low];
		arr[low] = arr[high];
		arr[high] = temp;
	}
	
	/**
	 * This is the interface for selecting a pivot
	 * @author John Widdifield
	 *
	 */
	private interface PivotSelector {
        /**
         * Returns the index of the selected pivot element
         * @param low - the lowest index to consider
         * @param high - the highest index to consider
         * @return the index of the selected pivot element
         */
        int selectPivot(int low, int high);
    }

	/**
	 * This makes the pivot the first element in the array
	 * @author John Widdifield
	 *
	 */
	public static class FirstElementSelector implements PivotSelector {
		
		/**
		 * This selects the pivot based on the class description
		 * @param low lowest index of the pivot section
		 * @param high highest index of teh pivot section
		 * @return low lowest index for pivoting
		 */
		public int selectPivot(int low, int high) {
			return low;
		}
	}
	
	/**
	 * This makes the pivot the last element in the array
	 * @author John Widdifield
	 *
	 */
	public static class LastElementSelector implements PivotSelector {
		
		/**
		 * This selects the pivot based on the class description
		 * @param low lowest index of the pivot section
		 * @param high highest index of the pivot section
		 * @return highest index for pivoting
		 */
		public int selectPivot(int low, int high) {
			return high;
		}
	}
	
	/**
	 * This makes the pivot the middle element in the array
	 * @author John Widdifield
	 *
	 */
	public static class MiddleElementSelector implements PivotSelector {
		
		/**
		 * This selects the pivot based on the class description
		 * @param low lowest index of the pivot section
		 * @param high highest index of teh pivot section
		 * @return middle index for pivoting
		 */
		public int selectPivot(int low, int high) {
			return (low + high) / 2;
		}
	}
	
	/**
	 * This makes the pivot a random element in the array
	 * @author John Widdifield
	 *
	 */
	public static class RandomElementSelector implements PivotSelector {
		
		/**
		 * This selects the pivot based on the class description
		 * @param low lowest index of the pivot section
		 * @param high highest index of teh pivot section
		 * @return middle index for pivoting
		 */
		public int selectPivot(int low, int high) {
			int randomNum = (int)(Math.random() * ((high - low) + 1)) + low;
			return randomNum;
		}
	}
}
