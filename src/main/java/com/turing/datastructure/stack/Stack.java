package com.turing.datastructure.stack;

public interface Stack<E> {
    int size();
    boolean isEmpty();
    void push(E element);
    E pop();
    E peek();
    boolean contains(E element);
}
