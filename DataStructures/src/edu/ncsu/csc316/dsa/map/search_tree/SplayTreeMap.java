package edu.ncsu.csc316.dsa.map.search_tree;

import java.util.Comparator;

import edu.ncsu.csc316.dsa.Position;
/**
 * This is an implementation of a splay tree map * I used the sixth edition of Data Structures and Algorithms in Java by Michael T. Goodrich 
 * to implement the code.
 * @author John Widdifield  and NCSU staff
 *
 * @param <K> Generic key param 
 * @param <V> generic value param 
 */
public class SplayTreeMap<K extends Comparable<K>, V> extends BinarySearchTreeMap<K, V> {

	/**
	 * This will construct a splay tree map
	 */
	public SplayTreeMap() {
		super(null);
	}

	/**
	 * This will construct a spaly tree map with a custom comparator
	 * @param compare comparator to use
	 */
	public SplayTreeMap(Comparator<K> compare) {
		super(compare);
	}

	private void splay(Position<Entry<K, V>> p) {
		while(!isRoot(p)) {
			Position<Entry<K, V>> parent = parent(p);
			Position<Entry<K, V>> grand = parent(parent);
			if(grand == null) {
				rotate(p);
			} else if((parent == left(grand)) == (p == left(parent))) {
				rotate(parent);
				rotate(p);
			} else {
				rotate(p);
				rotate(p);
			}
		}
	}

	@Override
	protected void actionOnAccess(Position<Entry<K, V>> p) {
		// If p is a dummy/sentinel node, move to the parent
		if(isLeaf(p)) {
			p = parent(p);
		}
		if(p != null) {
			splay(p);
		}
	}

	@Override
	protected void actionOnInsert(Position<Entry<K, V>> node) {
		splay(node);
	}

	@Override
	protected void actionOnDelete(Position<Entry<K, V>> p) {
		if(!isRoot(p)) {
			splay(parent(p));
		}
	}
}