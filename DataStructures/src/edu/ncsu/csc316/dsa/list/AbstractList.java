package edu.ncsu.csc316.dsa.list;

/**
 * This class is an abstract class which delegates heavily to List but includes some additional functionality
 * 
 * @author John Widdifield
 *
 * @param <E> Generic param
 */
public abstract class AbstractList<E> implements List<E> {
	
	@Override
	public void addFirst(E value) {
		add(0, value);
	}
	
	@Override
	public void addLast(E value) {
		add(size(), value);
	}

	/**
	 * Checks to see if an index is valid
	 * @param index index to check for
	 * @throws IndexOutOfBoundsException if the index is invalid
	 */
	protected void checkIndex(int index)
	{
		if(index < 0 || index >= size())
		{
			throw new IndexOutOfBoundsException("Index is invalid: " + index + " (size=" + size() + ")");
		}
	}
	
	/**
	 * Checks to see if an index is valid to add at
	 * @param index index to check for adding
	 * @throws IndexOutOfBoundsException if the index is invalid
	 */
	protected void checkIndexForAdd(int index)
	{
		if(index < 0 || index > size())
		{
			throw new IndexOutOfBoundsException("Index is invalid: " + index + " (size=" + size() + ")");
		}
	}
	
	@Override
	public E first() {
		return get(0);
	}
	
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public E last() {
		return get(size() - 1);
	}
	
	@Override
	public E removeFirst() {
		return remove(0);
	}
	
	@Override
	public E removeLast() {
		return remove(size() - 1);
	}
}