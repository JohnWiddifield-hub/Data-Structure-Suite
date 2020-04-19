package edu.ncsu.csc316.dsa.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An array based implementation of list
 * @author John Widdifield
 *
 * @param <E> generic param
 */
public class ArrayBasedList<E> extends AbstractList<E> {

	private final static int DEFAULT_CAPACITY = 10;
	private E[] data;

	private int size;

	/**
	 * Default constructor which uses 10 for capacity
	 */
	public ArrayBasedList() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Constructor which uses a custom starting capacity
	 * @param capacity capacity you wish to use
	 */
	@SuppressWarnings("unchecked")
	public ArrayBasedList(int capacity) {
		data = (E[]) (new Object[capacity]);
		size = 0;
	}

	@Override
	public void add(int index, E value) {
		checkIndexForAdd(index);
		ensureCapacity(size + 1);
		for(int i = size - 1; i >= index; i--) {
			data[i + 1] = data[i];
		}
		data[index] = value;
		size++;
		
	}

	@Override
	public E get(int index) {
		checkIndex(index);
		return data[index];
	}

	@Override
	public E remove(int index) {
		checkIndex(index);
		E temp = get(index);
		for(int i = index; i < size - 1; i++) {
			data[i] = data[i + 1];
		}
		set(size - 1, null);
		size--;
		return temp;
	}

	@Override
	public E set(int index, E value) {
		checkIndex(index);
		E temp = data[index];
		data[index] = value;
		return temp;
	}

	@Override
	public int size() {
		
		return size;
	}
 
	@Override
	public Iterator<E> iterator() {
	    return new ElementIterator();
	}
	
	/**
	 * Ensures that there is enough capacity to add to the data
	 * @param minCapacity Minimum capacity that is needed
	 */
    private void ensureCapacity(int minCapacity) {
        int oldCapacity = data.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = (oldCapacity * 2) + 1;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            data = Arrays.copyOf(data, newCapacity);
        }
    }
    
    /**
     * This is an iterator class which only promotes forward iteration through the
     * array based list
     * 
     * @author John Widdifield (using NCSU's CSC216 faculty lecture notes)
     *
     */
    private class ElementIterator implements Iterator<E> {
        private int position;
        private boolean removeOK;

        /**
         * Constructs the iterator
         */
        public ElementIterator() {
            position = 0;
            removeOK = false;
        }
        
        /**
         * Tells the user if there is another value to be returned from next()
         * @return false if there are no more values true if there are
         */
        public boolean hasNext() {
        	return position < size();
        }
        
        /**
         * Gets the next value in the list
         * @return E Value which is next in the list
         * @throws NoSuchElementException if there aren't any more values to be returned
         */
        public E next() {
        	if(!hasNext()) {
        		throw new NoSuchElementException();
        	} else {
        		position++;
        		removeOK = true;
        		return get(position - 1);
        	}
        }
        
        /**
         * Removes the value which was produced from the last call of next()
         * @throws IllegalStateException if removal is not possible at this time
         */
        public void remove() {
        	if(!removeOK) {
        		throw new IllegalStateException("Cannot remove until after another call to next()");
        	}
        	ArrayBasedList.this.remove(position - 1);
        	position--;
        	removeOK = false;
        }
    }
}