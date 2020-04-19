package edu.ncsu.csc316.dsa.stack;

/**
 * Abstract class for implementing isEmpty across all stack implementations
 * @author John Widdifield
 *
 * @param <E> Generic param
 */
public abstract class AbstractStack<E> implements Stack<E> {

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}
}