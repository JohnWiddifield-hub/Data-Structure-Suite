package edu.ncsu.csc316.dsa.tree;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.queue.ArrayBasedQueue;
/**
 * This class is an abstract implementation of a tree data type. I used the 
 * sixth edition of Data Structures and Algorithms in Java by Michael T. Goodrich to implement the code.
 * @author John Widdifield and NCSU staff
 *
 * @param <E> Generic param 
 */
public abstract class AbstractTree<E> implements Tree<E> {
    
    @Override
    public boolean isInternal(Position<E> p) {
        return numChildren(p) > 0;
    }
    
    @Override
    public boolean isLeaf(Position<E> p) {
        return numChildren(p) == 0;
    }
    
    @Override
    public boolean isRoot(Position<E> p) {
        return p == root();
    }
    
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
    
    /**
     * This gives the preOrder traversal of the tree
     * @return iterable list of positions in preOrder
     */
	public Iterable<Position<E>> preOrder()
    {
        // You can use any list data structure here that supports
        // O(1) addLast
        List<Position<E>> traversal = new SinglyLinkedList<Position<E>>();
        if (!isEmpty()) {
            preOrderHelper(root(), traversal);
        }
        return traversal;
    }
    
    private void preOrderHelper(Position<E> p, List<Position<E>> traversal) {
        traversal.addLast(p);
        for(Position<E> c : children(p)) {
            preOrderHelper(c, traversal);
        }
    }  

    @Override
    public Iterable<Position<E>> postOrder() {
        // You can use any list data structure here that supports
        // O(1) addLast
        List<Position<E>> list = new SinglyLinkedList<Position<E>>();
        if(!isEmpty()) {
            postOrderHelper(root(), list);
        }
        return list;
    }
    
    private void postOrderHelper(Position<E> p, List<Position<E>> list) {
        for(Position<E> c : children(p)) {
            postOrderHelper(c, list);
        }
        list.addLast(p);
    }
    
    @Override
    public Iterable<Position<E>> levelOrder()
    {
    	List<Position<E>> list = new SinglyLinkedList<Position<E>>();
        if(!isEmpty()) {
        	ArrayBasedQueue<Position<E>> fringe = new ArrayBasedQueue<>();
        	fringe.enqueue(root());
        	while(!fringe.isEmpty()) {
        		Position<E> p = fringe.dequeue();
        		list.add(list.size(), p);
        		for(Position<E> c : children(p)) {
        			fringe.enqueue(c);
        		}
        	}
        }
    	return list;
    }
    
    /**
     * This class is for the nodes of a tree
     * @author John Widdifield
     *
     * @param <E> generic param type
     */
    protected abstract static class AbstractNode<E> implements Position<E> {

        private E element;
        
        /**
         * This constructs an abstract node object
         * @param element elemetn to set the node object to
         */
        public AbstractNode(E element) {
            setElement(element);
        }
        
        @Override
        public E getElement() {
            return element;
        }
        
        /**
         * This method allows you to set the element of a node
         * @param element element you wish to set the node to
         */
        public void setElement(E element) {
            this.element = element;
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "[\n");
        toStringHelper(sb, "", root());
        sb.append("]");
        return sb.toString();
    }
    
    private void toStringHelper(StringBuilder sb, String indent, Position<E> root) {
        if(root == null) {
            return;
        }
        sb.append(indent).append(root.getElement()).append("\n");
        for(Position<E> child : children(root)) {
            toStringHelper(sb, indent + " ", child);
        }
    }
    
    /**
     * This class is for iterating through the elements of a tree.
     * @author John Widdifield and NCSU staff
     *
     */
    protected class ElementIterator implements Iterator<E> {
        private Iterator<Position<E>> it;
        
        /**
         * This constructs an object which allows you to iterate through the elements of the tree
         * @param iterator iterator you wish to use (traversal type)
         */
        public ElementIterator(Iterator<Position<E>> iterator) {
            it = iterator;
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
            throw new UnsupportedOperationException("The remove operation is not supported yet.");
        }
    }  
}