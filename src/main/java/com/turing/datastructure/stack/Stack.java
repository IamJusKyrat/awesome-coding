package com.turing.datastructure.stack;

public interface Stack<E> {
    public int size();
    public boolean isEmpty();
    public void push(E element);
    public E pop();
    public E peek();
    public boolean contains(E element);
}
