package edu.ncsu.csc316.dsa.set;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.search_tree.BinarySearchTreeMap;
//import edu.ncsu.csc316.dsa.map.search_tree.RedBlackTreeMap;

// Remember that search trees are ordered, so our elements must be Comparable
/**
 * This class represents a set using a tree data structure.
 * @author John Widdifield and NCSU staff
 *
 * @param <E> Generic param
 */
public class TreeSet<E extends Comparable<E>> extends AbstractSet<E> {

    private Map<E, E> tree;
    
    /**
     * This will construcg a new set 
     */
    public TreeSet() {
        tree = new BinarySearchTreeMap<E, E>();
    }
    
    @Override
    public Iterator<E> iterator() {
        return tree.iterator();
    }

    @Override
    public void add(E value) {
        tree.put(value, value);
    }

    @Override
    public boolean contains(E value) {
        return tree.get(value) != null;
    }

    @Override
    public E remove(E value) {
        return tree.remove(value);
    }

    @Override
    public int size() {
        return tree.size();
    }
}