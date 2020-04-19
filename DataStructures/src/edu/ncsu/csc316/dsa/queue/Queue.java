package edu.ncsu.csc316.dsa.queue;


/**
 * This is an interface for a generic queue implementation
 * @author John Widdifield and NCSU staff
 *
 * @param <E> Generic param
 */
public interface Queue<E> {

	/**
	 * This adds a value to the queue
	 * 
	 * @param value  Value you would like to add to the queue
	 */
    void enqueue(E value);
    
	/**
	 * This removes the next value from the queue
	 * 
	 * @return The value which was removed from the queue
	 */
    E dequeue();
    
    /**
     * This shows you what is at the front of the queue.
     * @return The value at the front of the queue.
     */
    E front();
    /**
     * This gets the size of the queue
     * @return number of elements in the queue
     */
    int size();
    /**
     * This will tell you if the queue is empty
     * @return true if the queue is empty and false if it has atleast one value
     */
    boolean isEmpty();
}
