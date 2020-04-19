package edu.ncsu.csc316.dsa.tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * This class tests the generalTree class for proper implementation of a general tree
 * @author John Widdifield and NCSU staff
 *
 */
public class GeneralTreeTest {

    private GeneralTree<String> tree;
    private GeneralTree<String> emptyTree;
    
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
    
    // An invalid Position to help test validate(p)
    /**
     * This class is for use when testing invalid positions in validate
     * @author John Widdifield
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
     * This will set up all of the tests for you
     */
    @Before
    public void setUp() {
        tree = new GeneralTree<String>();
        emptyTree = new GeneralTree<String>();
    }
    
    /**
     * Helper method to construct a sample tree
     *
     * One
     * -> Two
     *   -> Six
     *   -> Five
     *   -> Ten
     *     -> Seven
     * -> Three
     *   -> Four
     *     -> Eight
     *     -> Nine
     *
     * Or, visually:
     *                 one
     *            /            \
     *         two                three
     *      /   |     \             |   
     *   six   five   ten          four
     *                  |         /    \
     *                seven     eight  nine              
     */  
    private void createTree() {
        one = tree.addRoot("one");
        two = tree.addChild(one, "two");
        three = tree.addChild(one,  "three");
        six = tree.addChild(two, "six");
        five = tree.addChild(two, "five");
        ten = tree.addChild(two,  "ten");
        seven = tree.addChild(ten,  "seven");
        four = tree.addChild(three,  "four");
        eight = tree.addChild(four,  "eight");
        nine = tree.addChild(four,  "nine");
    }
    
    /**
     * This will test the set method
     */
    @Test
    public void testSet() {
        createTree();
        assertEquals("one", tree.set(one, "ONE"));
        
        try {
        	InvalidPosition<String> invPos = new InvalidPosition<String>();
        	assertNull(invPos.getElement());
            tree.set(invPos, "invalid");
            
            fail();
        } catch(Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
        
        assertEquals("eight", tree.set(eight, "eight2.0"));
        assertEquals("eight2.0", eight.getElement());
    }
    
    /**
     * This will test the size method
     */
    @Test
    public void testSize() {
        assertTrue(tree.isEmpty());
        createTree();
        assertEquals(10, tree.size());
        assertFalse(tree.isEmpty());
        
        tree.addChild(eight, "20");
        assertEquals(11, tree.size());
        
        tree.remove(tree.children(eight).iterator().next());
        assertEquals(10, tree.size());
    }
        
    /**
     * This will test the numChildren method
     */
    @Test
    public void testNumChildren() {
        createTree();
        
        assertEquals(3, tree.numChildren(two));
        assertEquals(2, tree.numChildren(one));
        assertEquals(0, tree.numChildren(eight));
    }

    /**
     * This will test the parent method
     */
    @Test
    public void testParent() {
        createTree();
        
        assertEquals(tree.parent(two), one);
    }
    
    /**
     * This will test the isInteranl method
     */
    @Test
    public void testIsInternal() {
        createTree();
        assertTrue(tree.isInternal(one));
        assertTrue(tree.isInternal(two));
        assertFalse(tree.isInternal(eight));
        
    }
    
    /**
     * This will test the isLeaf method
     */
    @Test
    public void isLeaf() {
        createTree();
        
        assertFalse(tree.isLeaf(one));
        assertFalse(tree.isLeaf(two));
        assertTrue(tree.isLeaf(eight));
    }
    
    /**
     * This will test the isRoot method
     */
    @Test
    public void isRoot() {
        createTree();
        assertTrue(tree.isRoot(one));
        assertFalse(tree.isRoot(two));
        
        assertFalse(tree.isRoot(eight));
    }
    
    /**
     * This will test the isEmpty method
     */
    @Test
    public void testIsEmpty() {
        assertTrue(emptyTree.isEmpty());
        
        createTree();
        assertFalse(tree.isEmpty());
        
        tree.addChild(eight, "twenty");
        assertFalse(tree.isEmpty());
    }
    
    /**
     * This will test the preOrder method
     */
    @Test
    public void testPreOrder() {
        createTree();
        Iterator<Position<String>> pre = tree.preOrder().iterator();
        assertEquals(one, pre.next());
        assertEquals(two, pre.next());
        assertEquals(six, pre.next());
        assertEquals(five, pre.next());
        assertEquals(ten, pre.next());
        assertEquals(seven, pre.next());
        assertEquals(three, pre.next());
        assertEquals(four, pre.next());
        assertEquals(eight, pre.next());
        assertEquals(nine, pre.next());
       
    }
    
    /**
     * This will test the iterator method
     */
    @Test
    public void testIterator() {
        createTree();
        Iterator<String> pre = tree.iterator();
        assertEquals(one.getElement(), pre.next());
        assertEquals(two.getElement(), pre.next());
        assertEquals(six.getElement(), pre.next());
        assertEquals(five.getElement(), pre.next());
        assertEquals(ten.getElement(), pre.next());
        assertEquals(seven.getElement(), pre.next());
        assertEquals(three.getElement(), pre.next());
        assertEquals(four.getElement(), pre.next());
        assertEquals(eight.getElement(), pre.next());
        assertEquals(nine.getElement(), pre.next());
        
        
    }
    
    /**
     * This will test the postOrder method
     */
    @Test
    public void testPostOrder() {
        createTree();
        Iterator<Position<String>> post = tree.postOrder().iterator();
        
        assertEquals(six, post.next());
        assertEquals(five, post.next());
        assertEquals(seven, post.next());
        assertEquals(ten, post.next());
        assertEquals(two, post.next());
        assertEquals(eight, post.next());
        assertEquals(nine, post.next());
        assertEquals(four, post.next());
        assertEquals(three, post.next());
        assertEquals(one, post.next());
    }
    
    /**
     * This will test the levelOrder method
     */
    @Test
    public void testLevelOrder() {
        createTree();
        Iterator<Position<String>> level = tree.levelOrder().iterator();
        
        assertEquals(one, level.next());
        assertEquals(two, level.next());
        assertEquals(three, level.next());
        assertEquals(six, level.next());
        assertEquals(five, level.next());
        assertEquals(ten, level.next());
        assertEquals(four, level.next());
        assertEquals(seven, level.next());
        assertEquals(eight, level.next());
        assertEquals(nine, level.next());
    }
    
    /**
     * This will test the addChild method
     */
    @Test
    public void testAddChild() {
        assertTrue(tree.isEmpty());
        Position<String> twenty = tree.addRoot("one");
        assertEquals(1, tree.size());
        assertNull(tree.parent(twenty));
        assertEquals("GeneralTree[\none\n]", tree.toString());
        
        tree.addChild(twenty, "two");
        assertEquals(1, tree.numChildren(tree.root()));
    }
    
    /**
     * This will test the remove method
     */
    @Test
    public void testRemove() {
        createTree();
        assertEquals(10, tree.size());
        assertEquals(2, tree.numChildren(four));
        tree.remove(nine);
        assertEquals("GeneralTree[\none\n two\n  six\n  five\n  ten\n   seven\n three\n  four\n   eight\n]", tree.toString());
        assertEquals(9, tree.size());
        assertEquals(1, tree.numChildren(four));
        
        assertEquals("nine", tree.remove(nine));
        assertEquals(1, tree.numChildren(four));
        
        try {
        	tree.remove(one);
        } catch (IllegalArgumentException e) {
        	assertEquals(e.getMessage(), "The node has more than 1 child.");
        }
        
        tree.remove(eight);
        tree.remove(four);
        tree.remove(nine);
        tree.remove(three);
        
        assertEquals(1, tree.numChildren(one));
        tree.remove(one);
        assertEquals(tree.root(), two);
    }
    
    /**
     * This will test the isEmpty method
     */
    @Test
    public void testEmptyTree() {
        GeneralTree<String> bTree = new GeneralTree<String>();
        assertTrue(bTree.isEmpty());
        
        bTree.addRoot("One");
        assertFalse(bTree.isEmpty());
    }
    
}