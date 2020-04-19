package edu.ncsu.csc316.dsa.queue;

/**
 * This is an abstract queue class in order to add a common isEmpty function
 * @author John Widdifield
 *
 * @param <E> Generic param
 */
public abstract class AbstractQueue<E> implements Queue<E> {

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}
}