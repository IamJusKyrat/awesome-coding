package com.turing.datastructure.linkedlist;

import java.util.Iterator;

/**
 * Implementation of a double linked list.
 * @param <E> Contains the elements of type E
 * @author Jaskirat Uppal
 */
@SuppressWarnings("unchecked")
public class DoublyLinkedList<E> implements Iterable<E>{
    private class Node<E> {
        private Node<E> previous, next;
        private E data;

        public Node(final E data, final Node<E> previous, final Node<E> next) {
            this.data = data;
            this.previous = previous;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    private Node<E> head, tail;
    private int size = 0;

    public int size() { return size; }
    public boolean isEmpty() {return size == 0;}

    /**
     * Clears all of the elements from the linked list
     */
    public void clear() {
        Node<E> trav = head;
        while (trav != null) {
            Node<E> next = trav.next;
            trav.previous = trav.next = null;
            trav.data= null;
            trav = next;
        }
        head = tail = null;
        size = 0;
    }

    /**
     * Insert at the end of the link list
     * @param element Element Data
     */
    public void insert(final E element) {insertLast(element);}

    public void insertLast(final E element) {
        final Node<E> newNode = new Node<>(element, tail, null);
        if(isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void insertFirst(final E element) {
        final Node<E> newNode = new Node<>(element, null, head);
        if(isEmpty()) {
            head = tail = newNode;
        } else {
            head.previous = newNode;
            head = newNode;
        }
        size++;
    }

    /**
     * Return the value of the head if the node exists
     * @return E data
     */
    public E peekFirst() {
        if(isEmpty()) throw new RuntimeException("List is empty.");
        return head.data;
    }

    /**
     * Return the value of the tail if the node exists
     * @return E data
     */
    public E peekLast() {
        if(isEmpty()) throw new RuntimeException("List is empty.");
        return tail.data;
    }

    public E peek() {return null;}  // TODO: Implementation Pending

    public E removeFirst() {
        if(isEmpty()) throw new RuntimeException("List is empty");
        final Node<E> node = head;
        if(head.next == null) { head = tail = null; }
        else { head = head.next; }

        final E data = node.data;
        clearCurrentNode(node);
        size--;
        return data;
    }

    public E removeLast() {
        if(isEmpty()) throw new RuntimeException("List is empty");
        final Node<E> node = tail;
        if(tail.previous == null) { head = tail = null; }
        else { tail = tail.previous; }

        final E data = node.data;
        clearCurrentNode(node);
        size--;
        return data;
    }

    public E remove(final Node<E> node) {
        if(node.previous == null) return removeFirst();
        if(node.next == null) return removeLast();

        node.previous.next = node.next;
        node.next.previous = node.previous;

        final E data = node.data;
        clearCurrentNode(node);
        size--;

        return data;
    }

    public E removeAt(int index) {return null;}  // TODO: Implementation Pending
    public E remove(E element) {return null;}  // TODO: Implementation Pending
    public boolean contains(E element) {return false;}  // TODO: Implementation Pending

    private void clearCurrentNode(Node<E> node) {
        node.data = null;
        node = node.previous = node.next = null;
    }

    @Override
    public Iterator<E> iterator() {
        return null; // TODO: Implementation Pending
    }
}


