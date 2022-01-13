package com.turing.datastructure.array;

import java.util.Iterator;

/**
 * Implementation of a generic dynamic array. Similar to java arraylist.
 * @param <E> Contains the elements of type E
 * @author Jaskirat Uppal
 */
@SuppressWarnings("unchecked")
public class DynamicArray<E> implements Iterable<E> {

    private E[] backing;
    private int capacity = 0; /* Internally assigned capacity (not visible to the user) */
    private int len = 0; /* Length visible to the user */

    public DynamicArray() {
        this(16);
    }

    public DynamicArray(int capacity) {
        if(capacity<0) throw new IllegalArgumentException("Illegal Capacity : " + capacity);
        this.capacity = capacity;
        backing = (E[]) new Object[capacity];
    }

    public int size() { return len; }
    public boolean isEmpty(){ return len == 0; }

    public boolean contains(E element) {
        return indexOf(element) != -1;

    }

    public void clear () {
        for (int i = 0; i < len; i++) backing[i] = null;
        len = 0;
    }

    public int indexOf(E element) {
        for(int index=0; index < len; index++) {
            if(backing[index] == element) {
                return index;
            }
        }
        return -1;
    }

    public E get(int index) {
        if(index >= size() || index < 0) throw new IndexOutOfBoundsException();
        return backing[index];
    }

    public void set(int index, E element) {
        if(index >= size() || index < 0) throw new IndexOutOfBoundsException();
        backing[index] = element;
    }

    public void add(E element) {
        if(size() == capacity) resize();
        backing[len] = element;
        len++;
    }

    public void addAll(DynamicArray<E> elements) {
        if(size()+elements.size() > capacity) resize(elements.size());
        for(int index = 0; index < elements.size(); index++) {
            backing[index+len] = elements.get(index);
        }
        len = len + elements.size();
    }

    public boolean remove(E element) {
        int index = indexOf(element);
        if(index == -1) return false;
        removeAt(index);
        return true;
    }

    public E removeAt(int index) {
        if(index >= len || index < 0) throw new IndexOutOfBoundsException();
        E element = backing[index];
        if (len - index > 1 && capacity != len) System.arraycopy(backing, index + 1, backing, index, len - (index+1));
        backing[len-1] = null;
        len--;
        return element;
    }

    private void resize(){
        resize(1);
    }

    /**
     * Resizing to minimum of a two times multiplier of the current size.
     * Resizing with a two times multiplier reduces the amortized time to O(capacity) time complexity
     * @param addSize The number of new elements to be added
     */
    private void resize(int addSize) {
        int multiplier = addSize/capacity + 2;
        int replacementCapacity = capacity*multiplier;
        final E[] replacementArr = (E[]) new Object[replacementCapacity];
        if (len >= 0) System.arraycopy(backing, 0, replacementArr, 0, len);
        backing = replacementArr;
        capacity = replacementCapacity;
        System.out.println("Resizing | Elements Added: " + addSize + " | New Capacity : " + capacity);
    }


    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < len;
            }

            @Override
            public E next() {
                return backing[index++];
            }
        };
    }
}
