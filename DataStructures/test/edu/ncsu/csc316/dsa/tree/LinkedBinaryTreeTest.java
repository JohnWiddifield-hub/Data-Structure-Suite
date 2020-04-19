package edu.ncsu.csc316.dsa.tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * This class tests the linkedbinarytree class for proper implementation of a 
 * linked binary tree data structure
 * 
 * @author John Widdifield  and NCSU staff
 *
 */
public class LinkedBinaryTreeTest {

    private LinkedBinaryTree<String> tree;
    private Position<String> one;
    private Position<String> two;
    private Position<String> three;
    private Position<String> four;
    private Position<String> five;
    private Position<String> six;
    private Position<String> seven;
    private Position<String> eight;
    private Position<String> nine;
    private Position<String> ten;
    
    /**
     * Helper class to create an invalid position to help test validate(p)
     * 
     * @param <E> Generic param 
     */
    private class InvalidPosition<E> implements Position<E> {

        @Override
        public E getElement() {
            return null;
        }
        
    }
    
    /**
     * This will set up the tests for you
     */
    @Before
    public void setUp() {
        tree = new LinkedBinaryTree<String>(); 
    }
    
    /**
     * Sample tree to help with testing
     *
     * One
     * -> Two
     *   -> Six
     *   -> Ten
     *     -> Seven
     *     -> Five
     * -> Three
     *   -> Four
     *     -> Eight
     *     -> Nine
     * 
     * Or, visually:
     *                    one
     *                /        \
     *             two          three
     *            /   \            /
     *         six   ten          four
     *              /   \        /     \
     *            seven  five  eight nine    
     */  
    private void createTree() {
        one = tree.addRoot("one");
        two = tree.addLeft(one, "two");
        three = tree.addRight(one, "three");
        six = tree.addLeft(two, "six");
        ten = tree.addRight(two, "ten");
        four = tree.addLeft(three, "four");
        seven = tree.addLeft(ten, "seven");
        five = tree.addRight(ten, "five");
        eight = tree.addLeft(four, "eight");
        nine = tree.addRight(four, "nine");
    }
    
    /**
     * This will test the set method
     */
    @Test
    public void testSet() {
        createTree();
        try {
        	InvalidPosition<String> invPos = new InvalidPosition<String>();
        	assertNull(invPos.getElement());
            tree.set(invPos, "invalid");
            
            fail();
        } catch(Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
        tree.set(seven, "newSeven");
        tree.set(three, "newThree");
        tree.set(two, "newTwo");
        tree.set(one, "newOne");
        assertEquals("newSeven", seven.getElement());
        assertEquals("newThree", three.getElement());
        assertEquals("newTwo", two.getElement());
        assertEquals("newOne", one.getElement());
        tree.remove(nine);
        tree.setRoot(nine);
        assertEquals(tree.root(), nine);
        
    }
    
    /**
     * This will test the size method
     */
    @Test
    public void testSize() {
        assertTrue(tree.isEmpty());
        createTree();
        assertEquals(tree.size(), 10);
        assertFalse(tree.isEmpty());
        
        tree.remove(five);
        assertEquals(tree.size(), 9);
        tree.addLeft(five, "eleven");
        assertEquals(tree.size(), 10);
    }
    
    /**
     * This will test the numChildren method
     */
    @Test
    public void testNumChildren() {
        createTree();
        assertEquals(tree.numChildren(one), 2);
        assertEquals(tree.numChildren(three), 1);
        assertEquals(tree.numChildren(eight), 0);
    }

    /**
     * This will test the parent method
     */
    @Test
    public void testParent() {
        createTree();
        assertEquals(tree.parent(eight), four);
        assertEquals(tree.parent(four), three);
        assertEquals(tree.parent(three), one);
        
    }
    
    /**
     * This will test the iterator method
     */
    @Test
    public void testIterator() {
        createTree();
        Iterator<String> it = tree.iterator();
        assertEquals(it.next(), six.getElement());
        assertEquals(it.next(), two.getElement());
        assertEquals(it.next(), seven.getElement());
        assertEquals(it.next(), ten.getElement());
        assertEquals(it.next(), five.getElement());
        assertEquals(it.next(), one.getElement());
        assertEquals(it.next(), eight.getElement());
        assertEquals(it.next(), four.getElement());
        assertEquals(it.next(), nine.getElement());
        try {
        	it.remove();
        } catch (UnsupportedOperationException e) {
        	assertEquals(e.getMessage(), "The remove operation is not supported yet.");
        }
        assertEquals(it.next(), three.getElement());
        assertFalse(it.hasNext());
        
    }
    
    /**
     * This will test the sibling method
     */
    @Test
    public void testSibling() {
        createTree();
        assertNull(tree.sibling(tree.root()));
        assertEquals(tree.sibling(seven), five);
        assertEquals(tree.sibling(six), ten);
        assertNull(tree.sibling(four));
    }
    
    /**
     * This will test the isInternal method
     */
    @Test
    public void testIsInternal() {
        createTree();
        assertTrue(tree.isInternal(four));
        assertTrue(tree.isInternal(two));
        assertTrue(tree.isInternal(ten));
        assertFalse(tree.isInternal(seven));
        assertTrue(tree.isInternal(one));
    }
    
    /**
     * This will test the isLeaf method
     */
    @Test
    public void isLeaf() {
        createTree();
        assertTrue(tree.isLeaf(seven));
        assertTrue(tree.isLeaf(five));
        assertTrue(tree.isLeaf(six));
        assertFalse(tree.isLeaf(one));
        assertFalse(tree.isLeaf(ten));
        assertFalse(tree.isLeaf(three));
        
    }
    
    /**
     * This will test the isRoot method
     */
    @Test
    public void isRoot() {
        createTree();
        assertTrue(tree.isRoot(one));
        assertFalse(tree.isRoot(eight));
        assertFalse(tree.isRoot(four));
    }
    
    /**
     * This will test the preorder method
     */
    @Test
    public void testPreOrder() {
        createTree();
        Iterator<Position<String>> it = tree.preOrder().iterator();
        
        assertEquals(it.next(), one);
        assertEquals(it.next(), two);
        assertEquals(it.next(), six);
        assertEquals(it.next(), ten);
        assertEquals(it.next(), seven);
        assertEquals(it.next(), five);
        assertEquals(it.next(), three);
        assertEquals(it.next(), four);
        assertEquals(it.next(), eight);
        assertEquals(it.next(), nine);
        assertFalse(it.hasNext());
    }
    
    /**
     * This will test the postOrder method
     */
    @Test
    public void testPostOrder() {
        createTree();
        Iterator<Position<String>> it = tree.postOrder().iterator();
        
        assertEquals(it.next(), six);
        assertEquals(it.next(), seven);
        assertEquals(it.next(), five);
        assertEquals(it.next(), ten);
        assertEquals(it.next(), two);
        assertEquals(it.next(), eight);
        assertEquals(it.next(), nine);
        assertEquals(it.next(), four);
        assertEquals(it.next(), three);
        assertEquals(it.next(), one);
        assertFalse(it.hasNext());
    }
    
    /**
     * This will test the inOrder method
     */
    @Test
    public void testInOrder() {
        createTree();
        Iterator<Position<String>> it = tree.inOrder().iterator();
        
        assertEquals(it.next(), six);
        assertEquals(it.next(), two);
        assertEquals(it.next(), seven);
        assertEquals(it.next(), ten);
        assertEquals(it.next(), five);
        assertEquals(it.next(), one);
        assertEquals(it.next(), eight);
        assertEquals(it.next(), four);
        assertEquals(it.next(), nine);
        assertEquals(it.next(), three);
        assertFalse(it.hasNext());
    }
    
    /**
     * This will test the isEmpty method
     */
    @Test
    public void testEmptyTree() {
    	LinkedBinaryTree<String> bTree = new LinkedBinaryTree<String>();
        assertTrue(bTree.isEmpty());
        
        bTree.addRoot("One");
        assertFalse(bTree.isEmpty());
    }
    
    /**
     * This will test the levelOrder method
     */
    @Test
    public void testLevelOrder() {
        createTree();
        Iterator<Position<String>> it = tree.levelOrder().iterator();
        
        assertEquals(it.next(), one);
        assertEquals(it.next(), two);
        assertEquals(it.next(), three);
        assertEquals(it.next(), six);
        assertEquals(it.next(), ten);
        assertEquals(it.next(), four);
        assertEquals(it.next(), seven);
        assertEquals(it.next(), five);
        assertEquals(it.next(), eight);
        assertEquals(it.next(), nine);
        assertFalse(it.hasNext());
    }
    
    /**
     * This will test the addRight addLeft and addRoot methods
     */
    @Test
    public void testAddChildren() {
        createTree();
        tree.addRight(seven, "eleven");
        tree.addLeft(seven, "twelve");
        
        try {
        	tree.addRight(seven, "thirteen");
        } catch (IllegalArgumentException e) {
        	assertEquals(e.getMessage(), "Node already has a right child.");
        }
        
        try {
        	tree.addLeft(seven, "thirteen");
        } catch (IllegalArgumentException e) {
        	assertEquals(e.getMessage(), "Node already has a left child.");
        }
        
        try {
        	tree.addRoot("thirteen");
        } catch (IllegalArgumentException e) {
        	assertEquals(e.getMessage(), "The tree already has a root.");
        }
        
        assertEquals(tree.right(seven).getElement(), "eleven");
        assertEquals(tree.left(seven).getElement(), "twelve");
    }
    
    /**
     * This will test the remove method
     */
    @Test
    public void testRemove() {
    	
        createTree();
        assertEquals(tree.remove(six), "six");
        assertEquals(tree.numChildren(two), 1);
        
        assertEquals(tree.remove(five), "five");
        assertEquals(tree.numChildren(ten), 1);
        
        
        try {
        	tree.remove(four);
        } catch (IllegalArgumentException e) {
        	assertEquals(e.getMessage(), "The node has two children");
        }
        
        tree.remove(three);
        assertEquals(tree.parent(four), one);
        assertEquals(tree.right(one), four);
        
        tree = new LinkedBinaryTree<String>(); 
        one = tree.addRoot("root");
        two = tree.addRight(one, "right");
        four = tree.addRight(two, "four");
        tree.remove(two);
        
    }
}