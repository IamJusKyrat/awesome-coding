package com.turing.datastructure.queue;

public interface Queue<E> {
    int size();
    boolean isEmpty();
    void enqueue(E element);
    E dequeue();
    E peekFirst();
    E peekLast();
    boolean contains(E element);
}
