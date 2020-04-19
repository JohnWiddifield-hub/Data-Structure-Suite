package edu.ncsu.csc316.dsa.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * This class is an implementation of a Singly linked list data structure using a sentinel
 * front pointer and a regular tail pointer
 * 
 * @author John Widdifield
 *
 * @param <E> Generic param
 */
public class SinglyLinkedList<E> extends AbstractList<E> {

	private LinkedListNode<E> front;
	private LinkedListNode<E> tail;
	
	private int size;
	
	/**
	 * Default constructor which sets up an empty list
	 */
	public SinglyLinkedList() {
		front = new LinkedListNode<E>(null);
		tail = null;
		size = 0;
	}

	@Override
	public void add(int index, E value) {
		checkIndexForAdd(index);
		if(index == 0) {
			LinkedListNode<E> newFront = new LinkedListNode<E>(value, front.getNext());
			front.setNext(newFront);
			if(size == 0) {
				tail = newFront;
			}
		} else if(index == size) {
			LinkedListNode<E> newTail = new LinkedListNode<E>(value, null);
			tail.setNext(newTail);
			tail = newTail;
		} else {
			LinkedListNode<E> newNode = new LinkedListNode<E>(value, null);
			LinkedListNode<E> current = front;
			for(int i = 0; i < index; i++) {
				current = current.getNext();
			}
			newNode.setNext(current.getNext());
			current.setNext(newNode);
		}
		size++;
	}

	@Override
	public E get(int index) {
		checkIndex(index);
		if(index == 0) {
			return front.getNext().getElement();
		} else if(index == size - 1) {
			return tail.getElement();
		} else {
			LinkedListNode<E> current = front;
			for(int i = 0; i <= index; i++) {
				current = current.getNext();
			}
			return current.getElement();
		}
	}

	@Override
	public E remove(int index) {
		checkIndex(index);
		if(index == 0) {
			E temp = front.getNext().getElement();
			front.setNext(front.getNext().getNext());
			size--;
			return temp;
		} else if(index == size - 1) {
			LinkedListNode<E> current = front;
			for(int i = 0; i < size - 1; i++) {
				current = current.getNext();
			}
			E temp = current.getNext().getElement();
			current.setNext(null);
			tail = current;
			size--;
			return temp;
		} else {
			LinkedListNode<E> current = front.getNext();
			for(int i = 0; i < index - 1; i++) {
				current = current.getNext();
			}
			E temp = current.getNext().getElement();
			current.setNext(current.getNext().getNext());
			size--;
			return temp;
		}
		
	}

	@Override
	public E set(int index, E value) {
		checkIndex(index);
		LinkedListNode<E> current = front;
		for(int i = 0; i < index; i++) {
			current = current.getNext();
		}
		E temp = current.getNext().getElement();
		current.getNext().setElement(value);
		return temp;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<E> iterator() {
	    // We need to tell the iterator to skip the dummy/sentinel node
	    return new ElementIterator(front.getNext());
	}
	
	@Override
    public E last() {
        return tail.getElement();
    }
	    
    @Override
    public void addLast(E value) {
    	if(front.getNext() == null) {
    		front.setNext(new LinkedListNode<E>(value, null));
    		tail = front.getNext();
    		size++;
    		return;
    	}
        tail.setNext(new LinkedListNode<E>(value, null));
        tail = tail.getNext();
        size++;
    }
	
    /**
     * This class allows for the creation of seperate nodes to be linked
     * @author John Widdifield
     *
     * @param <E> generic param
     */
private static class LinkedListNode<E> {
        
        private E data;
        private LinkedListNode<E> next;
        
        public LinkedListNode(E data){
        	this(data, null);
        }
        
        public LinkedListNode(E data, LinkedListNode<E> next) {
        	this.data = data;
        	this.next = next;
        }
        
        public LinkedListNode<E> getNext() {
			return next;
        }
        
        public E getElement() {
			return data;
        }
        
        public void setNext(LinkedListNode<E> node) {
			this.next = node;
        }
        
        public void setElement(E element) {
        	this.data = element;
        }
    }

/**
 * Element iterator for traversing through elements
 * @author John Widdifield
 *
 */
private class ElementIterator implements Iterator<E> {
    // Keep track of the next node that will be processed
    private LinkedListNode<E> current;
    // Keep track of the node that was processed on the last call to 'next'
    private LinkedListNode<E> previous = new LinkedListNode<E>(null, null);
    // Keep track of the previous-previous node that was processed
    // so that we can update 'next' links when removing
    private LinkedListNode<E> previousPrevious = new LinkedListNode<E>(null, null);
    private boolean removeOK;
    private int index = -1;

    public ElementIterator(LinkedListNode<E> start) {
        removeOK = false;
        current = start;
    }

    public boolean hasNext() {
        return current != null;
    }

    public E next() {
    	if(!hasNext()) {
    		throw new NoSuchElementException();
    	}
    	E temp = current.getElement();
    	if(previous != null) {
    		previousPrevious = previous;
    	}
    	previous = current;
        current = current.getNext();
        
        removeOK = true;
        index++;
        return temp;
    }
        
    public void remove() {
        if(!removeOK) {
        	throw new IllegalStateException("Cannot remove until after another call to next()");
        }
        previous = previousPrevious;
        removeOK = false;
        SinglyLinkedList.this.remove(index);
        index--;
        
    }
}
}