package edu.ncsu.csc316.dsa.disjoint_set;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;

/**
 * This class implements a disjoint set forest data structure using up trees.
 * 
 * Implemented using Data Structures and Algorithms in 
 * Java 6th edition by Michael T. Goodrich
 * @author John Widdifield
 *
 * @param <E> Generic param 
 */
public class UpTreeDisjointSetForest<E> implements DisjointSetForest<E> {

    // We need a secondary map to quickly locate an entry within the forest of up-trees
    // NOTE: The textbook implementation does not include this
    //       functionality; instead, the textbook implementation leaves
    //       the responsibility of tracking positions to the client in a
    //       separate map structure
    private Map<E, UpTreeNode<E>> map;
    
    /**
     * This will construct a new forest
     */
    public UpTreeDisjointSetForest() {
        // Use an efficient map!
        map = new LinearProbingHashMap<E, UpTreeNode<E>>();
    }

	@Override
    public Position<E> makeSet(E value) {
        map.put(value, new UpTreeNode<E>(value));
        return map.get(value);
    }

    @Override
    public Position<E> find(E value) {
        
    	return findHelper(map.get(value));
        // NOTE: The textbook solution requires the client to keep
        //       track of the location of each position in the forest.
        //       Our implementation includes a Map to handle this for the 
        //       client, so we should allow the client to find the set
        //       that contains a node by specifying the element
    }
    
    private UpTreeNode<E> findHelper(UpTreeNode<E> current) {
        if(current != current.getParent()) {
        	current.setParent(findHelper(current.getParent()));
        }
        return current.getParent();
    }

    @Override
    public void union(Position<E> s, Position<E> t) {
        // Instead of trusting the client to give us the roots
        // of two up-trees, we will verify by finding the root 
        // of the up-tree that contains the element in positions s and t
        UpTreeNode<E> a = validate(find(s.getElement()));
        UpTreeNode<E> b = validate(find(t.getElement()));
        if(a != b) {
        	if(a.getCount() > b.getCount()) {
        		b.setParent(a);
        		a.setCount(a.getCount() + b.getCount());
        	} else {
        		a.setParent(b);
        		b.setCount(b.getCount() + a.getCount());
        	}
        }
    }
    
    private UpTreeNode<E> validate(Position<E> p) {
        if(!(p instanceof UpTreeNode)) {
            throw new IllegalArgumentException("Position is not a valid up tree node.");
        }
        return (UpTreeNode<E>)p;
    }
    
    /**
     * This class is to represent a node in an uptree 
     * @author John Widdifield and NCSU staff
     *
     * @param <E>	Generic param 
     */
 private static class UpTreeNode<E> implements Position<E> {
        
        private E element;
        private UpTreeNode<E> parent;
        private int count;
        
        public UpTreeNode(E element) {
            setElement(element);
            setParent(this);
            setCount(1);
        }
        
        public void setElement(E element) {
            this.element = element;
        }
        
        @Override
        public E getElement() {
            return element;
        }
        
        public void setParent(UpTreeNode<E> parent) {
            this.parent = parent;
        }
        
        public UpTreeNode<E> getParent() {
            return parent;
        }
        
        public void setCount(int count) {
            this.count = count;
        }
        
        public int getCount() {
            return count;
        }
    }
}