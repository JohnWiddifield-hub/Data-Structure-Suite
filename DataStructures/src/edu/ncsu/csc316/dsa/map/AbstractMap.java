package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;

/**
 * This is a class which is a partial implementation for a map data structure.  It includes the 
 * format for Entry's into the map as well.
 * @author John Widdifield
 *
 * @param <K> Generic param for Keys
 * @param <V> Generic param for values
 */
public abstract class AbstractMap<K, V> implements Map<K, V> {

	/**
	 * This is an inner class for creating entries for the map.
	 * @author John Widdifield
	 *
	 * @param <K>	Generic param for keys
	 * @param <V>	Generic param for values
	 */
	protected static class MapEntry<K, V> implements Entry<K, V> {

		private K key;
		private V value;
		
		/**
		 * This is the constructor to create an entry for a map.
		 * @param key	Key of the entry	
		 * @param value	Value for the key of the entry
		 */
		public MapEntry(K key, V value) {
			setKey(key);
			setValue(value);
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		/**
		 * This method sets the key for an entry
		 * @param key Key you would like to set
		 * @return K key which has been set.
		 */
		public K setKey(K key) {
			this.key = key;
			return this.key;
		}

		@Override
		public V setValue(V value) {
			V original = this.value;
			this.value = value;
			return original;
		}
	}

	/**
	 * This class allows the map to be iterated through returning keys on calls to .next()
	 * @author John Widdifield
	 *
	 */
    protected class KeyIterator implements Iterator<K> {

        private Iterator<Entry<K, V>> it;
        
        /**
         * This is the constructor for an iterator which iterates through the 
         * keys of a map.
         * @param iterator iterator of entry's to then retrieve the keys from
         */
        public KeyIterator(Iterator<Entry<K, V>> iterator) {
            it = iterator;
        }
        
        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public K next() {
            return it.next().getKey();
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException("The remove operation is not supported yet.");
        }
    }
	
    /**
	 * This class allows the map to be iterated through returning values on calls to .next()
	 * @author John Widdifield
	 *
	 */
	protected class ValueIterator implements Iterator<V> {
        private Iterator<Entry<K, V>> it;
        
        /**
         * This is the constructor for an iterator which iterates through the 
         * values of a map.
         * @param iterator iterator of values to then retrieve the keys from
         */
        public ValueIterator(Iterator<Entry<K, V>> iterator) {
            it = iterator;
        }
        
        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public V next() {
            return it.next().getValue();
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException("The remove operation is not supported yet.");
        }
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}
	
	@Override
	public Iterator<K> iterator() {
		return new KeyIterator(entrySet().iterator());
	}

	@Override
	public Iterable<V> values() {
		return new ValueIterable();
	}

	/**
	 * This class allows the map to be iterated through returning keys on calls to .next()
	 * It is to be used as delegation.
	 * @author John Widdifield & NCSU Staff
	 *
	 */
	private class ValueIterable implements Iterable<V> {
        @Override
        public Iterator<V> iterator() {
            return new ValueIterator(entrySet().iterator());
        }
    }

	
	
}