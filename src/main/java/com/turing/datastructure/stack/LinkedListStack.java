package com.turing.datastructure.stack;

import com.turing.datastructure.linkedlist.DoublyLinkedList;

import java.util.Iterator;

/**
 * TODO: Documentation
 * @param <E>
 */
public class LinkedListStack<E> implements Stack<E>, Iterable<E> {
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
    public void push(E element) {
        list.insertFirst(element);
    }

    @Override
    public E pop() {
        if(isEmpty()) { throw new RuntimeException("Stack is empty"); }
        return list.removeFirst();
    }

    @Override
    public E peek() {
        if(isEmpty()) { throw new RuntimeException("Stack is empty"); }
        return list.peekFirst();
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
