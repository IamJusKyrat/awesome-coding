package com.turing.datastructure;

import java.util.Iterator;

/**
 * Implementation of a generic dynamic array. Similar to java arraylist.
 * @param <E> Contains the elements of type E
 * @author Jaskirat Uppal
 */
@SuppressWarnings("unchecked")
public class DynamicArray<E> implements Iterable<E> {

    private E[] arr;
    private int capacity = 0; /* Internally assigned capacity (not visible to the user) */
    private int len = 0; /* Length visible to the user */

    public DynamicArray() {
        this(16);
    }

    public DynamicArray(int capacity) {
        if(capacity<0) throw new IllegalArgumentException("Illegal Capacity : " + capacity);
        this.capacity = capacity;
        arr = (E[]) new Object[capacity];
    }

    public int size() { return len; }
    public boolean isEmpty(){ return len == 0; }

    public int indexOf(E element) {
        for(int index=0; index < len; index++) {
            if(arr[index] == element) {
                return index;
            }
        }
        return -1;
    }

    public boolean contains(E element) {
        return indexOf(element) != -1;

    }

    public void add(E element) {
        if(len+1 >= capacity) {
            resize();
        }
        arr[len] = element;
        len++;
    }

    public void addAll(DynamicArray<E> elements) {
        if(len+elements.size() > capacity) {
            resize(elements.size());
        }
        for(int index = 0; index < elements.size(); index++) {
            arr[index+len] = elements.get(index);
        }
        len = len + elements.size();
    }

    public E get(int index) {
        if(index >= len && index < 0) throw new IndexOutOfBoundsException();
        return arr[index];
    }

    public void set(int index, E element) {
        if(index >= len && index < 0) throw new IndexOutOfBoundsException();
        arr[index] = element;
    }

    public boolean remove(E element) {
        int index = indexOf(element);
        if(index == -1) return false;
        removeAt(index);
        return true;
    }

    public E removeAt(int index) {
        if(index >= len && index < 0) throw new IndexOutOfBoundsException();
        E element = arr[index];
        if (len - index > 1 && capacity != len) System.arraycopy(arr, index + 1, arr, index, len - (index+1));
        len--;
        return element;
    }

    private void resize(){
        resize(0);
    }

    private void resize(int addSize) {
        // TODO: fina a better way to add size to an array
        int multiplier = addSize/capacity + 2;
        capacity = capacity*multiplier;
        final E[] replacementArr = (E[]) new Object[capacity];
        if (len >= 0) System.arraycopy(arr, 0, replacementArr, 0, len);
        arr = replacementArr;
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
                return arr[index++];
            }
        };
    }
}
