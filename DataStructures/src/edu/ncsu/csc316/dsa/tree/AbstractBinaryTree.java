package edu.ncsu.csc316.dsa.tree;
import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.list.List;

/**
 * This is an abstract implementation for a binary tree. I used the 
 * sixth edition of Data Structures and Algorithms in Java by Michael T. Goodrich to implement the code.
 * 
 * @author John Widdifield and NCSU staff
 *
 * @param <E> Generic type param
 */
public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTreeCollection<E> {

    @Override
    public Iterable<Position<E>> inOrder() {
        List<Position<E>> traversal = new SinglyLinkedList<Position<E>>();

        if (!isEmpty()) {
            inOrderHelper(root(), traversal);
        }

        return traversal;
    }

    private void inOrderHelper(Position<E> node, List<Position<E>> traversal) {
        if(left(node) != null) {
        	inOrderHelper(left(node), traversal);
        }
        traversal.add(traversal.size(), node);
        if(right(node) != null) {
        	inOrderHelper(right(node), traversal);
        }
    }
    
    @Override
    public Position<E> sibling(Position<E> p) {
		AbstractNode<E> node = validate(p);
		if(isRoot(node)) {
			return null;
		}
		Position<E> parent = parent(p);
		if(numChildren(parent) == 1) {
			return null;
		}
		if(!right(parent).equals(p)) {
			return right(parent);
		} else {
			return left(parent);
		}
        
    }
    
    private AbstractNode<E> validate(Position<E> p) {
        if(!(p instanceof AbstractNode)) {
            throw new IllegalArgumentException("Position is not a valid binary tree node");
        }
        return (AbstractNode<E>)(p);
    }
    
    @Override
    public int numChildren(Position<E> p) {
        int children = 0;
        if(left(p) != null) {
        	children++;
        }
        if(right(p) != null) {
        	children++;
        }
        return children;
    }
    
    @Override
    public E set(Position<E> p, E value) {
        AbstractNode<E> node = validate(p);
        E original = node.getElement();
        node.setElement(value);
        return original;
    }
    
    @Override
    public Iterator<E> iterator() {
        return new ElementIterator(inOrder().iterator());
    }
    
    @Override
    public Iterable<Position<E>> children(Position<E> p) {
        AbstractNode<E> node = validate(p);
        List<Position<E>> ret = new SinglyLinkedList<Position<E>>();
        if(left(node) != null) {
            ret.addLast(left(node));
        }
        if(right(node) != null) {
            ret.addLast(right(node));
        }
        return ret;
    }
}