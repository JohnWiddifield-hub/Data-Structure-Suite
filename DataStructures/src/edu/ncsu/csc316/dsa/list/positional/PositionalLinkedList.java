package edu.ncsu.csc316.dsa.list.positional;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ncsu.csc316.dsa.Position;

/**
 * Positional linked list implementation of a list data structure.
 * 
 * Implemented using page 277-280 of Data Structures and Algorithms in 
 * Java 6th edition by Michael T. Goodrich

 * @author John Widdifield
 *
 * @param <E> generic param 
 */
public class PositionalLinkedList<E> implements PositionalList<E> {

	private PositionalNode<E> front;
	private PositionalNode<E> tail;
	private int size;

	/**
	 * Constructs a new list with front and back sentinal nodes
	 */
	public PositionalLinkedList() {
		front = new PositionalNode<E>(null);
		tail = new PositionalNode<E>(null, null, front);
		front.setNext(tail);
		size = 0;
	}

    @Override
    public Iterator<E> iterator() {
        return new ElementIterator(front.getNext());
    }

	/**
	 * adds a node with a value into the list after a specified position.
	 * Implemented using page 277-280 of Data Structures and Algorithms in 
	 * Java 6th edition by Michael T. Goodrich
	 * @param p position you would like to add after
	 * @param value value you would like to add
	 * @return position of the new node
	 * 
	 */
	@Override
	public Position<E> addAfter(Position<E> p, E value) {
		PositionalNode<E> node = validate(p);
		return addBetween(value, node.getNext(), node);
	}

	/**
	 * Adds a node before a given position
	 * Implemented using page 277-280 of Data Structures and Algorithms in 
	 * Java 6th edition by Michael T. Goodrich
	 * @param p position you would like to add before
	 * @param value you would like to add
	 * @return position which was added at
	 */
	@Override
	public Position<E> addBefore(Position<E> p, E value) {
		PositionalNode<E> node = validate(p);
		return addBetween(value, node, node.getPrevious());
		
	}

	/**
	 * adds to the beginning of the list
	 * Implemented using page 277-280 of Data Structures and Algorithms in 
	 * Java 6th edition by Michael T. Goodrich
	 * @param value value you would like to add
	 * @return position of the added value
	 */
	@Override
	public Position<E> addFirst(E value) {
		return addBetween(value, front.getNext(), front);
	}

	/**
	 * adds to the end of the list
	 * Implemented using page 277-280 of Data Structures and Algorithms in 
	 * Java 6th edition by Michael T. Goodrich
	 * @param value value you would like to add
	 * @return position that was added at
	 */
	@Override
	public Position<E> addLast(E value) {
		return addBetween(value, tail, tail.getPrevious());
	}

	/**
	 * Gets the position after a given position
	 * Implemented using page 277-280 of Data Structures and Algorithms in 
	 * Java 6th edition by Michael T. Goodrich
	 * @param p position you would like to receive the position after
	 * @throws NoSuchElementException if there isn't an element after
	 * @return the position after position p
	 */
	@Override
	public Position<E> after(Position<E> p) {
		PositionalNode<E> node = validate(p);
		if(position(node.getNext()) == tail) {
			throw new NoSuchElementException();
		}
		return position(node.getNext());
	}
	
	/**
	 * returns the given node as a position or null if it is a sentinel
	 * Implemented using page 277-280 of Data Structures and Algorithms in 
	 * Java 6th edition by Michael T. Goodrich
	 * @param node node to be given the position for
	 * @return position of a given node
	 */
	private Position<E> position(PositionalNode<E> node) {
		if(node == front || node == tail) {
			return null;
		}
		return node;
	}

	/**
	 * Gets the position before a given position
	 * Implemented using page 277-280 of Data Structures and Algorithms in 
	 * Java 6th edition by Michael T. Goodrich
	 * @param p position you would like to receive the position before
	 * @throws NoSuchElementException if there isn't an element before
	 * @return the position before position p
	 */
	@Override
	public Position<E> before(Position<E> p) {
		PositionalNode<E> node = validate(p);
		if(position(node.getPrevious()) == null) {
			throw new NoSuchElementException();
		}
		return position(node.getPrevious());
	}
	
	/**
	 * Gets the first position in the list
	 * 	Implemented using page 277-280 of Data Structures and Algorithms in 
	 *  Java 6th edition by Michael T. Goodrich
	 *  @throws NoSuchElementException if the list is empty
	 * @return the first position in the list
	 */
	@Override
	public Position<E> first() {
		if(size == 0) {
			throw new NoSuchElementException();
		}
		return position(front.getNext());
	}

	/**
	 * Checks to see if the list is empty
	 * @return true if the list is empty false if it is not empty
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Gets the last position in the list
	 * 	Implemented using page 277-280 of Data Structures and Algorithms in 
	 *  Java 6th edition by Michael T. Goodrich
	 *  @throws NoSuchElementException if the list is empty
	 * @return the last position in the list
	 */
	@Override
	public Position<E> last() {
		if(size == 0) {
			throw new NoSuchElementException();
		}
		return position(tail.getPrevious());
	}

	@Override
	public Iterable<Position<E>> positions() {
		return new PositionIterable();
	}

	/**
	 * Removes a position from the list
	 * 	
	 * Implemented using page 277-280 of Data Structures and Algorithms in 
	 * Java 6th edition by Michael T. Goodrich
	 * @param p position you would like to remove
	 * @return element which was removed
	 */
	@Override
	public E remove(Position<E> p) {
		PositionalNode<E> node = validate(p);
		PositionalNode<E> predecessor = node.getPrevious();
		PositionalNode<E> successor = node.getNext();
		predecessor.setNext(successor);
		successor.setPrevious(predecessor);
		
		size--;
		E answer = node.getElement();
		node.setElement(null);
		node.setNext(null);
		node.setPrevious(null);
		return answer;
	}

	/**
	 * sets a position to a specified value within the list
	 * 	
	 * Implemented using page 277-280 of Data Structures and Algorithms in 
	 * Java 6th edition by Michael T. Goodrich
	 * @param p position you would like to set
	 * @param value value you would like to set the position to
	 * @return element which was removed
	 */
	@Override
	public E set(Position<E> p, E value) {
		PositionalNode<E> node = validate(p);
		E answer = node.getElement();
		node.setElement(value);
		return answer;
	}

	/**
	 * gets the size of the list
	 * @return size of the list
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Determins if a position is valid
	 * @param p position to test
	 * @return the positionalNode representation of p
	 */
    private PositionalNode<E> validate(Position<E> p) {
        if (p instanceof PositionalNode) {
            return (PositionalNode<E>) p;
        }
        throw new IllegalArgumentException("Position is not a valid positional list node.");
    }
    
	/**
	 * adds between two positions in the list
	 * 	
	 * Implemented using page 277-280 of Data Structures and Algorithms in 
	 * Java 6th edition by Michael T. Goodrich
	 * @param value value you would like to add
	 * @param next the node which should be after the added value
	 * @param prev the node which should be before the added value
	 * @return position of the added element
	 */
    private Position<E> addBetween(E value, PositionalNode<E> next, PositionalNode<E> prev) {
		PositionalNode<E> temp = new PositionalNode<E>(value, next, prev);
		next.setPrevious(temp);
		prev.setNext(temp);
		size++;
    	return temp;
    }

    /**
     * Positional node for representing a position containing data
     * @author NCSU staff and John Widdifield
     *
     * @param <E> generic type
     */
	private static class PositionalNode<E> implements Position<E> {

        private E element;
        private PositionalNode<E> next;
        private PositionalNode<E> previous;

        public PositionalNode(E value) {
            this(value, null);
        }

        public PositionalNode(E value, PositionalNode<E> next) {
            this(value, next, null);
        }

        public PositionalNode(E value, PositionalNode<E> next, PositionalNode<E> prev) {
            setElement(value);
            setNext(next);
            setPrevious(prev);
        }

        public void setPrevious(PositionalNode<E> prev) {
            previous = prev;
        }

        public PositionalNode<E> getPrevious() {
            return previous;
        }
        
        public void setNext(PositionalNode<E> next) {
            this.next = next;
        }

        public PositionalNode<E> getNext() {
            return next;
        }

        @Override
        public E getElement() {
            return element;
        }
        
        public void setElement(E element) {
            this.element = element;
        }
    }

	/**
	 * This is a positional iterator class for traversal through the list
	 * Implemented using page 277-280 of Data Structures and Algorithms in 
	 * Java 6th edition by Michael T. Goodrich
	 * @author John Widdifield 
	 *
	 */
	private class PositionIterator implements Iterator<Position<E>> {

        private Position<E> current = null;
        private boolean removeOK = false;
        private Position<E> recent = null;

        public PositionIterator(PositionalNode<E> start) {
        	if(size > 0) {
        		current = first();
        	} else {
        		current = null;
        	}
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Position<E> next() {
            if(current == null) {
            	throw new NoSuchElementException();
            }
            recent = current;
            current = after(current);
            removeOK = true;
            return recent;
        }

        @Override
        public void remove() {
        	if(!removeOK) {
        		throw new IllegalStateException("Cannot remove right now");
        	}
        	PositionalLinkedList.this.remove(recent);
        	recent = null;
        	removeOK = false;
        }
    }
	
	/**
	 * This is a elemental iterator class for traversal through the positional list
	 * Implemented using page 277-280 of Data Structures and Algorithms in 
	 * Java 6th edition by Michael T. Goodrich
	 * @author John Widdifield 
	 *
	 */
	private class ElementIterator implements Iterator<E> {

        private Iterator<Position<E>> it;

        public ElementIterator(PositionalNode<E> start) {
            it = new PositionIterator(start);
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public E next() {
            return it.next().getElement();
        }

        @Override
        public void remove() {
            it.remove();
        }
    }
	
	/**
	 * This class delegates to the positionIterator object to iterate through positions
	 * @author John Widdifield
	 *
	 */
    private class PositionIterable implements Iterable<Position<E>> {
        
        @Override
        public Iterator<Position<E>> iterator() {
            return new PositionIterator(front.getNext());
        }
    }
	
}