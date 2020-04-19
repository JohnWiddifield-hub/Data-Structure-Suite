package edu.ncsu.csc316.dsa.queue;

import java.util.NoSuchElementException;

/**
* Array Based implementation of a Queue for generics. I used the sixth edition of
* Data Structures and Algorithms in Java by Michael T. Goodrich to implement the code (Page 243).
* 
* @author John Widdifield and NCSU Staff with help from the aforementioned textbook
* 
* @param <E> generic param
*/
public class ArrayBasedQueue<E> extends AbstractQueue<E> {

	/** List of data to be used as a queue */
	private E[] data;
	/** Index of the front of the queue */
	private int front = 0;
	/** The number of elements within the queue */
	private int size;
	
	private static final int DEFAULT_CAPACITY = 10;
	
	/**
	 * Constructs a Queue using a passed in capacity.
	 * @param initialCapacity Capacity you would like to use for the queue
	 */
	@SuppressWarnings("unchecked")
	public ArrayBasedQueue(int initialCapacity)
	{
		data = (E[])(new Object[initialCapacity]);
		size = 0;
	}
	
	/**
	 * Constructs a queue using a default capacity of 10
	 */
	public ArrayBasedQueue()
	{
		this(DEFAULT_CAPACITY);
	}

	@Override
	public void enqueue(E value) {
		ensureCapacity(size + 1);
		int available = (front + size) % data.length;
		data[available] = value;
		size++;
		
	}

	@Override
	public E dequeue() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		
		E answer = data[front];
		data[front] = null;
		front = (front + 1) % data.length;
		size--;
		return answer;
		
	}

	@Override
	public E front() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		return data[front];
	}

	@Override
	public int size() {
		return size;
	}
	
	private void ensureCapacity(int minCapacity) {
        int oldCapacity = data.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = (oldCapacity * 2) + 1;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            @SuppressWarnings("unchecked")
			E[] newArr = (E[])(new Object[newCapacity]);
            int i = 0;
            while(i < size) {
            	newArr[i] = data[front];
            	front = (front + 1) % data.length;
            	i++;
            }
            
            //Change the pointer of data to look at the bigger coppied array
            data = newArr;
            
            //The loop above sets the beginning of the queue to index 0 
            front = 0;
            
            
        }
	}
}
