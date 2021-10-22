package com.turing.datastructure.queue;

import com.turing.datastructure.linkedlist.DoublyLinkedList;

import java.util.Iterator;

/**
 *  TODO: Documentation Pending
 * @param <E>
 */
public class LinkedListQueue<E> implements Queue<E>, Iterable<E> {
    private final DoublyLinkedList<E> list = new DoublyLinkedList<>();
    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void enqueue(E element) {
        list.insert(element);
    }

    @Override
    public E dequeue() {
        if(isEmpty()) throw new RuntimeException("Queue is empty");
        return list.removeFirst();
    }

    @Override
    public E peekFirst() {
        if(isEmpty()) throw new RuntimeException("Queue is empty");
        return list.peekFirst();
    }

    @Override
    public E peekLast() {
        if(isEmpty()) throw new RuntimeException("Queue is empty");
        return list.peekLast();
    }

    @Override
    public boolean contains(E element) {
        return list.contains(element);
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }
}
