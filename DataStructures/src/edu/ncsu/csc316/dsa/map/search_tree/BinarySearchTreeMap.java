package edu.ncsu.csc316.dsa.map.search_tree;

import java.util.Comparator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.ArrayBasedList;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.AbstractSortedMap;
import edu.ncsu.csc316.dsa.map.Map.Entry;
import edu.ncsu.csc316.dsa.tree.BinaryTree;
import edu.ncsu.csc316.dsa.tree.LinkedBinaryTree;

/**
 * This class is a binary search tree map implementation * I used the sixth edition of Data Structures and Algorithms in Java by Michael T. Goodrich 
 * to implement the code.
 * @author John Widdifield and NCSU staff
 *
 * @param <K> generic key param
 * @param <V> generic value
 */
public class BinarySearchTreeMap<K extends Comparable<K>, V> extends AbstractSortedMap<K, V>
        implements BinaryTree<Entry<K, V>> {

    // The BalanceableBinaryTree class is an inner class below
    private BalanceableBinaryTree<K, V> tree;

    /**
     * This will construct a BST map
     */
    public BinarySearchTreeMap() {
        this(null);
    }

    /**
     * This will construct a BST map with a custom comparator
     * @param compare comparator you wish to use
     */
    public BinarySearchTreeMap(Comparator<K> compare) {
        super(compare);
        tree = new BalanceableBinaryTree<K, V>();
        tree.addRoot(null);
    }

    @Override
    public int size() {
        // Our search trees will all use dummy/sentinel leaf nodes,
        // so the actual number of elements in the tree will be (size-1)/2      
        return (tree.size() - 1) / 2;
    }

    // This method is used to add dummy/sentinel left and right children as leaves
    private void expandLeaf(Position<Entry<K, V>> p, Entry<K, V> entry) {
        // initially, p is a dummy/sentinel node,
        // so replace the null entry with the new actual entry      
        tree.set(p, entry);
        // Then add new dummy/sentinel children     
        tree.addLeft(p, null);
        tree.addRight(p, null);
    }

    // This helper method traces a path down the tree to locate the position
    // that contains an entry with the given key.
    // Think of "lookUp" as returning the last node visited when tracing
    // a path down the tree to find the given key
    private Position<Entry<K, V>> lookUp(Position<Entry<K, V>> p, K key) {
        // If we have reached a dummy/sentinel node (a leaf), return that sentinel node
        if (isLeaf(p)) {
            return p;
        }
        int comp = compare(key, p.getElement().getKey());
        if (comp == 0) {
            // Return the position that contains the entry with the key         
            return p;
        } else if (comp < 0) {
            return lookUp(left(p), key);
        } else {
            return lookUp(right(p), key);
        }
    }

    @Override
    public V get(K key) {
        Position<Entry<K, V>> p = lookUp(tree.root(), key);
        // actionOnAccess is a "hook" for our AVL, Splay, and Red-Black Trees to use        
        actionOnAccess(p);
        if (isLeaf(p)) {
            return null;
        }
        return p.getElement().getValue();
    }

    @Override
    public V put(K key, V value) {
        // Create the new map entry     
        Entry<K, V> newEntry = new MapEntry<K, V>(key, value);
        // Get the last node visited when looking for the key       
        Position<Entry<K, V>> p = lookUp(root(), key);
        // If the last node visited is a dummy/sentinel node        
        if (isLeaf(p)) {
            expandLeaf(p, newEntry);
            // actionOnInsert is a "hook" for our AVL, Splay, and Red-Black Trees to use            
            actionOnInsert(p);
            return null;
        } else {
            V original = p.getElement().getValue();
            set(p, newEntry);
            // actionOnAccess is a "hook" for our AVL, Splay, and Red-Black Trees to use            
            actionOnAccess(p);
            return original;
        }
    }

    @Override
    public V remove(K key) {
        // Get the last node visited when looking for the key       
        Position<Entry<K, V>> p = lookUp(root(), key);
        // If p is a dummy/sentinel node        
        if (isLeaf(p)) {
            // actionOnAccess is a "hook" for our AVL, Splay, and Red-Black Trees to use            
            actionOnAccess(p);
            return null;
        } else {
            V original = p.getElement().getValue();
            // If the node has two children (that are not dummy/sentinel nodes)         
            if (isInternal(left(p)) && isInternal(right(p))) {
                // Replace with the inorder successor               
                Position<Entry<K, V>> replacement = treeMin(right(p));
                set(p, replacement.getElement());
                // Move p to the replacement node in the right subtree              
                p = replacement;
            }
            // Get the dummy/sentinel node (in case the node has an actual entry as a child)...         
            Position<Entry<K, V>> leaf = (isLeaf(left(p)) ? left(p) : right(p));
            // ... then get its sibling (will be another sentinel or an actual entry node)          
            Position<Entry<K, V>> sib = sibling(leaf);
            // Remove the leaf NODE (this is your binary tree remove method)            
            remove(leaf);
            // Remove the NODE (this is your binary tree remove method)
            // which will "promote" the sib node to replace p           
            remove(p);
            // actionOnDelete is a "hook" for our AVL, Splay, and Red-Black Trees to use            
            actionOnDelete(sib);
            return original;
        }
    }

    // Returns the inorder successor (the minimum from the right subtree)
    private Position<Entry<K, V>> treeMin(Position<Entry<K, V>> node) {
        Position<Entry<K, V>> current = node;
        while (isInternal(current)) {
            current = left(current);
        }
        return parent(current);
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        List<Entry<K, V>> set = new ArrayBasedList<Entry<K, V>>(size());
        for (Position<Entry<K, V>> n : tree.inOrder()) {
        	if(n.getElement() != null) {
        		set.addLast(n.getElement());
        	}
        }
        return set;
    }

    @Override
    public String toString() {
        return tree.toString();
    }

    /** This is a "hook" method that will be overridden in 
     * your AVL, Splay, and Red-Black tree implementations
     * 
     * @param node Node to perform action on
     */
    protected void actionOnAccess(Position<Entry<K, V>> node) {
        // Do nothing for BST
    }

    /** This is a "hook" method that will be overridden in 
    * your AVL, Splay, and Red-Black tree implementations
    *
    *@param node node to perform on
    */
    protected void actionOnInsert(Position<Entry<K, V>> node) {
        // Do nothing for BST
    }

    // 
    /**
     * This is a "hook" method that will be overridden in 
     * your AVL, Splay, and Red-Black tree implementations
     * @param node node to perform on
     */
    protected void actionOnDelete(Position<Entry<K, V>> node) {
        // Do nothing for BST
    }

    /**
     * This is a class for a balanceablebinarytree
     * @author John Widdifield and NCSU staff
     *
     * @param <K> generic key param 
     * @param <V> generic value param 
     */
    protected static class BalanceableBinaryTree<K, V> extends LinkedBinaryTree<Entry<K, V>> {

        // Relink is a helper method for trinode restructuring and rotations
        private void relink(Node<Entry<K, V>> parent, Node<Entry<K, V>> child, boolean makeLeftChild) {
        	if(child == null) {
        		return;
        	}
            child.setParent( parent );
            if( makeLeftChild ) {
            	parent.setLeft( child );
            } else {
            	parent.setRight( child );
            }
        }

        /** Rotate is a helper method for handling rotations and relinking nodes
         * *
         * 
         * @param p the position to rotate around
         */
        public void rotate(Position<Entry<K, V>> p) {
            Node<Entry<K, V>> x = validate(p);
            Node<Entry<K, V>> y = x.getParent();
            Node<Entry<K, V>> z = y.getParent();
            
            if(z == null) {
            	setRoot(x);
            	x.setParent(null);
            } else {
            	relink(z, x, y == z.getLeft());
            }
            if(x == y.getLeft()) {
            	relink(y, x.getRight(), true);
            	relink(x, y, false);
            } else {
            	relink(y, x.getLeft(), false);
            	relink(x, y, true);
            }
        }

        /** Restructure is a helper method to perform a trinode restructuring
        // and trigger the appropriate rotations
         * 
         * @param x position to restructure
         * @return the new position of x
         */
        public Position<Entry<K, V>> restructure(Position<Entry<K, V>> x) {
            Position<Entry<K, V>> y = parent(x);
            Position<Entry<K, V>> z = parent(y);
            if((x == right(y)) == (y == right(z))) {
            	rotate(y);
            	return y;
            } else {
            	rotate(x);
            	rotate(x);
            	return x;
            }
        }

        @Override
        protected Node<Entry<K, V>> createNode(Entry<K, V> element, Node<Entry<K, V>> parent, Node<Entry<K, V>> left,
                Node<Entry<K, V>> right) {
            BSTNode<Entry<K, V>> newNode = new BSTNode<Entry<K, V>>(element);
            newNode.setParent(parent);
            newNode.setLeft(left);
            newNode.setRight(right);
            newNode.setProperty(0);
            return newNode;
        }

        /** A binary search tree node needs to keep track of some property.
        // For example, for AVL trees the "property" is the height of the node.
        // For Red-Black trees, the "property" is the color of the node.
         * 
         * @author John WIddifield and NCSU staff
         *
         * @param <E> Generic param 
         */
        protected static class BSTNode<E> extends Node<E> {

            private int property;

            /**
             * This constructs a BST node
             * @param element elemnt you wish the node to hold
             */
            public BSTNode(E element) {
                super(element);
                setProperty(0);
            }

            /**
             * sets the property
             * @param height height of the BST tree
             */
            public void setProperty(int height) {
                this.property = height;
            }

            /**
             * gets the property of the tree
             * @return property
             */
            public int getProperty() {
                return property;
            }
        }

        /**
         * gets the property of a specified position
         * @param p position to retrieve from 
         * @return the property of p
         */
        public int getProperty(Position<Entry<K, V>> p) {
            if (p == null) {
                return 0;
            }
            BSTNode<Entry<K, V>> node = (BSTNode<Entry<K, V>>) p;
            return node.getProperty();
        }

        /**
         * this will set the property for a position to a value
         * @param p	position to set
         * @param value value to set
         */
        public void setProperty(Position<Entry<K, V>> p, int value) {
            BSTNode<Entry<K, V>> node = (BSTNode<Entry<K, V>>) (p);
            node.setProperty(value);
        }
    }

    /////////////////////////////////////////////////////////////////////////////
    // All the methods below delegate to the BalanceableBinaryTree class, which extends 
    // your linked binary tree implementation
    /////////////////////////////////////////////////////////////////////////////

    @Override
    public Position<Entry<K, V>> root() {
        return tree.root();
    }

    @Override
    public Position<Entry<K, V>> parent(Position<Entry<K, V>> p) {
        return tree.parent(p);
    }

    @Override
    public Iterable<Position<Entry<K, V>>> children(Position<Entry<K, V>> p) {
        return tree.children(p);
    }

    @Override
    public int numChildren(Position<Entry<K, V>> p) {
        return tree.numChildren(p);
    }

    @Override
    public boolean isInternal(Position<Entry<K, V>> p) {
        return tree.isInternal(p);
    }

    /**
     * this will set a position to an entry
     * @param p position to set
     * @param entry entry to set
     * @return the entry which was replaced
     */
    public Entry<K, V> set(Position<Entry<K, V>> p, Entry<K, V> entry) {
        return tree.set(p, entry);
    }

    @Override
    public boolean isLeaf(Position<Entry<K, V>> p) {
        return tree.isLeaf(p);
    }

    @Override
    public boolean isRoot(Position<Entry<K, V>> p) {
        return tree.isRoot(p);
    }

    @Override
    public Iterable<Position<Entry<K, V>>> preOrder() {
        return tree.preOrder();
    }

    @Override
    public Iterable<Position<Entry<K, V>>> postOrder() {
        return tree.postOrder();
    }

    @Override
    public Iterable<Position<Entry<K, V>>> levelOrder() {
        return tree.levelOrder();
    }

    @Override
    public Position<Entry<K, V>> left(Position<Entry<K, V>> p) {
        return tree.left(p);
    }

    /**
     * this will remove a position from the map
     * @param p position to remove
     * @return entry of the removed position
     */
    protected Entry<K, V> remove(Position<Entry<K, V>> p) {
        return tree.remove(p);
    }

    @Override
    public Position<Entry<K, V>> right(Position<Entry<K, V>> p) {
        return tree.right(p);
    }

    @Override
    public Position<Entry<K, V>> sibling(Position<Entry<K, V>> p) {
        return tree.sibling(p);
    }

    @Override
    public Iterable<Position<Entry<K, V>>> inOrder() {
        return tree.inOrder();
    }

    /**
     * this will rotate p around 
     * @param p position to rotate
     */
    protected void rotate(Position<Entry<K, V>> p) {
        tree.rotate(p);
    }

    /**
     * this will restructure the position x
     * @param x position to restructure
     * @return the new position of x 
     */
    protected Position<Entry<K, V>> restructure(Position<Entry<K, V>> x) {
        return tree.restructure(x);
    }

    /**
     * this will get the property of p
     * @param p position to get from 
     * @return property of p
     */
    public int getProperty(Position<Entry<K, V>> p) {
        return tree.getProperty(p);
    }

    /**
     * this will set the property of p
     * @param p position to set
     * @param value value to set to
     */
    public void setProperty(Position<Entry<K, V>> p, int value) {
        tree.setProperty(p, value);
    }
}