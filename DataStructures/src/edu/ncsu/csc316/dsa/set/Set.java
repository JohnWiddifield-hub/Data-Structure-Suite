package edu.ncsu.csc316.dsa.set;

/**
 * This interface displays all of the functionality of a set
 * @author John Widdifield and ncsu staff
 *
 * @param <E>	Generic param 
 */
public interface Set<E> extends Iterable<E> {
	/**
	 * This will add a value to the set
	 * @param value	value to add
	 */
    void add(E value);
    
    /**
     * This will tell you if the set contains a specified value or not
     * @param value	value you wish to check to see if the set contains
     * @return	true if the set contains the value false otherwise
     */
    boolean contains(E value);
    
    /**
     * This will remove a value from the set.
     * @param value	value you wish to remove from the set
     * @return	the element which was removed
     */
    E remove(E value);
    
    /**
     * This will tell you if the set is empty or not
     * @return	true if the set is empty false otherwise
     */
    boolean isEmpty();
    /**
     * This will tell you the size of the set
     * @return number of elements within the set
     */
    int size();
    
    /**
     * This will perform the union operation on two sets
     * @param other	set to union to the "this" set
     */
    void addAll(Set<E> other);
    
    /**
     * This will perform the intersection operation on two sets
     * @param other other set to intersect to the "this" set
     */
    void retainAll(Set<E> other);
    
    /**
     * This will perform the subtraction operation on two sets
     * @param other	other set to subtract from the "this" set
     */
    void removeAll(Set<E> other);
}