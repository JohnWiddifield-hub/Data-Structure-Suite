package edu.ncsu.csc316.dsa.priority_queue;

import java.util.Comparator;

import edu.ncsu.csc316.dsa.list.ArrayBasedList;

/**
 * This class represents a heap priority queue data structure.
 * It was implemented using the sixth edition of Data Structures and 
 *   Algorithms in Java by Michael T. Goodrich 
 * @author John WIddifield and ncsu staff
 *
 * @param <K> Generic param for keys
 * @param <V> Generic param for values
 */
public class HeapPriorityQueue<K extends Comparable<K>, V> extends AbstractPriorityQueue<K, V> {

	/**
	 * This is a list for implementing the queue
	 */
    // Remember that heaps can be easily implemented using an internal array representation
    // versus a linked representation.
	protected ArrayBasedList<Entry<K, V>> list;

	/**
	 * This will construt a heap priority queue with a custom comparator
	 * @param comparator comparaotr you wish you use
	 */
	public HeapPriorityQueue(Comparator<K> comparator) {
		super(comparator);
		list = new ArrayBasedList<Entry<K, V>>();
	}

	/**
	 * This will construt a heap priority queue with a default comparator
	 */
	public HeapPriorityQueue() {
		this(null);
	}

    //////////////////////////////////////////
    // ADT Operations
    //////////////////////////////////////////

	@Override
	public Entry<K, V> insert(K key, V value) {
		Entry<K, V> temp = createEntry(key, value);
		list.addLast(temp);
		upHeap(list.size() - 1);
		return temp;
	}

	@Override
	public Entry<K, V> min() {
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public Entry<K, V> deleteMin() {
		if (list.isEmpty()) {
			return null;
		}
		Entry<K, V> answer = list.get(0);
		swap(0, list.size() - 1);
		list.remove(list.size() - 1);
		downHeap(0);
		return answer;
	}

	@Override
	public int size() {
		return list.size();
	}

	/**
	 * This will make sure that the index is in the correct position by
	 * iterating it up the heap into the right index.
	 * @param index index to upheap
	 */
	protected void upHeap(int index) {
		while(index > 0) {
			int p = parent(index);
			//This is assuming we are comparing keys and not values, if doesn't work try getValue()
			if (compare(list.get(index).getKey(), list.get(p).getKey()) >= 0) break;
			swap(index, p);
			index = p;
		}
        // The textbook has a non-recursive version of 
        //    the recursive algorithms presented in the lecture slides		
	}

	/**
	 * This will swap the index's of two locations
	 * @param index1 first index to swap
	 * @param index2 second index to swap 
	 */
	protected void swap(int index1, int index2) {
		Entry<K, V> temp = list.get(index1);
		list.set(index1, list.get(index2));
		list.set(index2, temp);
	}

	/**
	 * This will make sure that the index is in the correct position by
	 * iterating it down the heap into the right index.
	 * @param index index to downheap
	 */
	protected void downHeap(int index) {
		while(hasLeft(index)) {
			int leftIndex = left(index);
			int smallChildIndex = leftIndex;
			if(hasRight(index)) {
				int rightIndex = right(index);
				if(compare(list.get(leftIndex).getKey(), list.get(rightIndex).getKey()) > 0) {
					smallChildIndex = rightIndex;
				}
			}
			if(compare(list.get(smallChildIndex).getKey(), list.get(index).getKey()) >= 0) {
				break;
			}
			swap(index, smallChildIndex);
			index = smallChildIndex;
		}
        // The textbook has a non-recursive version of
        //    the recursive algorithms presented in the lecture slides
	}
	
    //////////////////////////////////////////////////
    // Convenience methods to help abstract the math
    // involved in jumping to parent or children
    //////////////////////////////////////////////////	

	/**
	 * This will get the parent of an index
	 * @param index index to get the parent of
	 * @return the parent of index
	 */
	protected int parent(int index) {
		return (index - 1) / 2;
	}

	/**
	 * This will get the left child of an index
	 * @param index index to get the left child of
	 * @return the left child of index
	 */
	protected int left(int index) {
		return 2 * index + 1;
	}

	/**
	 * This will get the right child of an index
	 * @param index index to get the right child of
	 * @return the right child of index
	 */
	protected int right(int index) {
		return 2 * index + 2;
	}

	/**
	 * This will tell you if the index has a left child
	 * @param index index to check
	 * @return true if there is a left child false otherwise
	 */
	protected boolean hasLeft(int index) {
		return left(index) < list.size();
	}

	/**
	 * This will tell you if the index has a right child
	 * @param index index to check
	 * @return true if there is a right child false otherwise
	 */
	protected boolean hasRight(int index) {
		return right(index) < list.size();
	}
}