package edu.ncsu.csc316.dsa.stack;

import java.util.EmptyStackException;

import edu.ncsu.csc316.dsa.list.SinglyLinkedList;

/**
 * LinkedStack implementation of a stack for generics. I used the sixth edition of
 * Data Structures and Algorithms in Java by Michael T. Goodrich to implement the code (Page 233).
 * 
 * @author John Widdifield
 *
 * @param <E> Generic param
 */
public class LinkedStack<E> extends AbstractStack<E> {
	
	private SinglyLinkedList<E> list;
	
	/**
	 * Constructs a SinglyLinkedList to be implemented by this class as a stack
	 */
	public LinkedStack()
	{
		list = new SinglyLinkedList<E>();
	}

	@Override
	public void push(E value) {
		list.addFirst(value);
	}

	@Override
	public E pop() {
		if(list.isEmpty()) {
			throw new EmptyStackException();
		}
		return list.removeFirst();
	}

	@Override
	public E top() {
		if(list.isEmpty()) {
			throw new EmptyStackException();
		}
		return list.first();
	}

	@Override
	public int size() {
		return list.size();
	}
	
}

