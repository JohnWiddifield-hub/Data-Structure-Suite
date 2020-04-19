package edu.ncsu.csc316.dsa.disjoint_set;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * This class tests the UpTreeDisjointSetForest class for proper implementation
 * of a disjoint set implemented as a forest using a map.
 * @author John Widdifield
 *
 */
public class UpTreeDisjointSetForestTest {

    private DisjointSetForest<String> set;
    
    /**
     * This will set up the test cases with a new set each time
     */
    @Before
    public void setUp() {
        set = new UpTreeDisjointSetForest<>();
    }
    
    /**
     * This will test the makeSet method
     */
    @Test
    public void testMakeSet() {
        Position<String> one = set.makeSet("one");
        assertEquals("one", one.getElement());
        Position<String> two = set.makeSet("two");
        assertEquals("two", two.getElement());
        
        Position<String> three = set.makeSet("three");
        assertEquals("three", three.getElement());
        
        Position<String> four = set.makeSet("four");
        assertEquals("four", four.getElement());
    }
    
    /**
     * This will test the union and find methods
     */
    @Test
    public void testUnionFind() {
        Position<String> one = set.makeSet("one");
        Position<String> two = set.makeSet("two");
        Position<String> three = set.makeSet("three");
        Position<String> four = set.makeSet("four");
        Position<String> five = set.makeSet("five");
        Position<String> six = set.makeSet("six");
        Position<String> seven = set.makeSet("seven");
        Position<String> eight = set.makeSet("eight");
        Position<String> nine = set.makeSet("nine");
        Position<String> ten = set.makeSet("ten");
        
        assertEquals(one, set.find("one"));
        assertEquals(two, set.find("two"));
        assertEquals(three, set.find("three"));
        assertEquals(four, set.find("four"));
        assertEquals(five, set.find("five"));
        assertEquals(six, set.find("six"));
        assertEquals(seven, set.find("seven"));
        assertEquals(eight, set.find("eight"));
        assertEquals(nine, set.find("nine"));
        assertEquals(ten, set.find("ten"));
        
        set.union(nine, ten);
        assertEquals(ten, set.find("nine"));
        assertEquals(ten, set.find("ten"));
        
        set.union(ten, two);
        assertEquals(ten, set.find("two"));
        
        set.union(five, six);
        set.union(six, ten);
        assertEquals(ten, set.find("five"));
    }
}