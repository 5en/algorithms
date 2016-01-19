// https://leetcode.com/problems/peeking-iterator/

package com.htyleo.algorithms;

import java.util.Iterator;

public class PeekingIterator<T> implements Iterator<T> {
    private Iterator<T> iterator;
    private T           nextCache;

    public PeekingIterator(Iterator<T> iterator) {
        // initialize any member here.
        this.iterator = iterator;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public T peek() {
        if (nextCache == null) {
            nextCache = iterator.next();
        }
        return nextCache;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public T next() {
        if (nextCache == null) {
            return iterator.next();
        } else {
            T result = nextCache;
            nextCache = null;
            return result;
        }
    }

    @Override
    public boolean hasNext() {
        if (nextCache == null) {
            return iterator.hasNext();
        } else {
            return true;
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove() method is not supported");
    }
}
