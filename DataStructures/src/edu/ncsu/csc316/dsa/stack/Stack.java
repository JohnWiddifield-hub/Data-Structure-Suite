package edu.ncsu.csc316.dsa.stack;

/**
 * Interface for a stack implementation 
 * 
 * @author John Widdifield
 *
 * @param <E> Generic param
 */
public interface Stack<E> {

	/**
	 * This pushes a value to the top of the stack
	 * 
	 * @param value Value you would like to add to the stack.
	 */
	void push(E value);
	/**
	 * This pops the top value off of the stack. Removing it and returning it.
	 * 
	 * @return The value at the top of the stack
	 */
	E pop();
	/**
	 * This peeks at the top value of the stack without removing it.
	 * @return The value at the top of the stack.
	 */
	E top();
	/**
	 * This gets the size of the stack
	 * @return The number of elements within the stack
	 */
	int size();
	/**
	 * This tells you if the stack is empty
	 * @return True if the stack is empty false if not
	 */
	boolean isEmpty();
}
