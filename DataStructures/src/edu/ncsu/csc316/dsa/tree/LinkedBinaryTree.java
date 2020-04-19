package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * This is an implementation of a linked binary tree data structure. I used the 
 * sixth edition of Data Structures and Algorithms in Java by Michael T. Goodrich to implement the code.
 * @author John Widdifield and NCSU staff
 *
 * @param <E> Generic param 
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    private Node<E> root;
    private int size;

    /**
     * This is the linkedbinarytree default constructor 
     */
    public LinkedBinaryTree() {
        root = null;
        size = 0;
    }

    /**
     * This will validate if the given position is valid
     * @param p position to check
     * @return true if valid false if invalid
     */
    protected Node<E> validate(Position<E> p) {
        if (!(p instanceof Node)) {
            throw new IllegalArgumentException("Position is not a valid linked binary node");
        }
        return (Node<E>) p;
    }

    /**
     * This is the class for the nodes contained within the overarchnig tree structure
     * @author John WIddifield
     *
     * @param <E> Generic param type
     */
    public static class Node<E> extends AbstractNode<E> {
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;

        /**
         * This is the constructor for a node with an element
         * @param element element to keep contained by the node
         */
        public Node(E element) {
            this(element, null);
        }

        /**
         * This is the constructor for making a child node
         * @param element element for the node to hold
         * @param parent parent of the new node
         */
        public Node(E element, Node<E> parent) {
            super(element);
            setParent(parent);
        }

        /**
         * This gets the left child of the node
         * @return left child node
         */
        public Node<E> getLeft() {
            return left;
        }

        /**
         * This gets the right child of the node
         * @return right child node
         */
        public Node<E> getRight() {
            return right;
        }

        /**
         * This will set the left child of the node 
         * @param left desired left node to set
         */
        public void setLeft(Node<E> left) {
            this.left = left;
        }

        /**
         * This will set the right child of the node
         * @param right desired right node to set
         */
        public void setRight(Node<E> right) {
            this.right = right;
        }

        /**
         * This will get the parent node of the node
         * @return the parent node
         */
        public Node<E> getParent() {
            return parent;
        }

        /**
         * This will set the parent node of the current node
         * @param parent desired parent of the current node
         */
        public void setParent(Node<E> parent) {
            this.parent = parent;
        }
    }

    @Override
    public Position<E> left(Position<E> p) {
        Node<E> node = validate(p);
        return node.getLeft();
    }

    @Override
    public Position<E> right(Position<E> p) {
        Node<E> node = validate(p);
        return node.getRight();
    }

    @Override
    public Position<E> addLeft(Position<E> p, E value) {
        Node<E> node = validate(p);
        if (left(node) != null) {
            throw new IllegalArgumentException("Node already has a left child.");
        }
        node.setLeft(createNode(value, node, null, null));
        size++;
		return node.getLeft();
    }

    @Override
    public Position<E> addRight(Position<E> p, E value) {
        Node<E> node = validate(p);
        if (right(node) != null) {
            throw new IllegalArgumentException("Node already has a right child.");
        }
        node.setRight(createNode(value, node, null, null));
        size++;
		return node.getRight();
    }

    @Override
    public Position<E> root() {
        return root;
    }

    @Override
    public Position<E> parent(Position<E> p) {
        Node<E> node = validate(p);
        return node.getParent();
    }

    @Override
    public Position<E> addRoot(E value) {
        if (root() != null) {
            throw new IllegalArgumentException("The tree already has a root.");
        }
        this.root = createNode(value, null, null, null);
        size++;
        return root;
    }

    @Override
    public E remove(Position<E> p) {
    	Node<E> node = validate(p);
    	Node<E> childNode;
        if (numChildren(p) == 2){
            throw new IllegalArgumentException("The node has two children");
        }
        
        if(isRoot(p)) {
            if(node.getLeft() != null) {
            	childNode = node.getLeft();
            } else if(node.getRight() != null) {
            	childNode = node.getRight();
            } else {
            	childNode = null;
            }
            setRoot(childNode);
            size--;
            return node.getElement();
        }
        
        if(node.getLeft() != null) {
        	childNode = node.getLeft();
        	childNode.setParent(node.getParent());
        } else if(node.getRight() != null) {
        	childNode = node.getRight();
        	childNode.setParent(node.getParent());
        } else {
        	childNode = null;
        }
        if(node.getParent().getLeft() != null && node.getParent().getLeft().equals(node)) {
        	node.getParent().setLeft(childNode);
        } else if(node.getParent().getRight() != null && node.getParent().getRight().equals(node)) {
        	node.getParent().setRight(childNode);
        } 
        size--;
        return node.getElement();
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * This will create a node with the given params
     * @param e elemet you wish the ndoe to hold
     * @param parent parent of the new node
     * @param left left child of the new node
     * @param right right child of the new node
     * @return the new node
     */
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
        Node<E> newNode = new Node<E>(e);
        newNode.setParent(parent);
        newNode.setLeft(left);
        newNode.setRight(right);
        return newNode;
    }

    // setRoot is needed for a later lab...
    // ...but THIS DESIGN IS BAD! If a client arbitrarily changes
    // the root by using the method, the size may no longer be correct/valid.
    // Instead, the precondition for this method is that
    // it should *ONLY* be used when rotating nodes in 
    // balanced binary search trees. We could instead change
    // our rotation code to not need this setRoot method, but that
    // makes the rotation code messier. For the purpose of this lab,
    // we will sacrifice a stronger design for cleaner/less code.
    /**
     * This will set the root of the tree to a position
     * @param p position to set as the root of the tree
     * @return the position of the root of the tree
     */
    protected Position<E> setRoot(Position<E> p) {
        root = validate(p);
        return root;
    }
}