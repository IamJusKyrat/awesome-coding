package com.turing.datastructure.priorityqueue;

import com.turing.datastructure.array.DynamicArray;

/**
 * TODO: Pending documentation
 * @param <E>
 */
public class PriorityQueue<E extends Comparable<E>> {
    private DynamicArray<E> heap = new DynamicArray<>();
    private int heapCapacity;
    private int heapSize;

    public PriorityQueue() {
        this(1);
    }

    public PriorityQueue(int capacity) {
        heap = new DynamicArray<>(capacity);
        heapCapacity = capacity;
    }

    // Construct a priority queue using heapify in O(n) time, a great explanation can be found at:
    // http://www.cs.umd.edu/~meesh/351/mount/lectures/lect14-heapsort-analysis-part.pdf
    public PriorityQueue(E[] elements) {
        heapSize = elements.length;
        heap = new DynamicArray<>(heapSize);

        for(int i = 0; i < heapSize; i++) heap.add(elements[i]);

        // Heapify process, O(n)
        for (int i = Math.max(0, (heapSize / 2) - 1); i >= 0; i--) siftDown(i);
    }

    public int size() {return heapSize;}

    public boolean isEmpty() {return heapSize == 0;}

    public boolean contains(E element) { return heap.contains(element); }

    public void clear() { heap.clear(); }

    public E peek() {
        if(isEmpty()) throw new RuntimeException("Priority Queue is Empty");
        return heap.get(0);
    }

    public E poll() { return removeAt(0); }

    public void add(E element) {
        if (element == null) throw new IllegalArgumentException();
        heap.add(element);
        heapSize++;
        siftUp(heapSize - 1);
    }

    public E remove(E element) {
        if(isEmpty()) throw new RuntimeException("Priority Queue is Empty");
        int index = -1;
        for(int i = 0; i < heapSize; i++) if(element.equals(heap.get(i))) index = i;
        if(index == -1) throw new RuntimeException("Element not found");
        return removeAt(index);
    }
    public E removeAt(int index) {
        if(isEmpty()) throw new RuntimeException("Priority Queue is Empty");
        swap(index, heapSize - 1);
        final E element = heap.removeAt(heapSize - 1);
        heapSize--;
        siftDown(index);
        return element;
    }
    public boolean isMinHeap(int index) {
        int left = 2*index + 1;
        int right = 2*index + 2;

        if(less(left, index) && less(right, index)) return false;

        return isMinHeap(left) && isMinHeap(right);
    }

    private boolean less(int i, int j) {
        final E elementI = heap.get(i);
        final E elementJ = heap.get(j);

        return elementI.compareTo(elementJ) <= 0;
    }

    private void swap(int i, int j) {
        final E elementI = heap.get(i);
        final E elementJ = heap.get(j);

        heap.set(i, elementJ);
        heap.set(j, elementI);
    }

    private void siftDown(int index) {
        while (true) {
            int left = 2*index + 1;
            int right = 2*index + 2;
            int smallest = left;

            // Find which is smaller left or right
            // If right is smaller set smallest to be right
            if(right < heapSize && less(right, left)) smallest = right;

            // Stop if we're outside the bounds of the tree
            // or stop early if we cannot sink k anymore
            if(left >= heapSize || less(index, smallest)) break;

            swap(index, smallest);
            index = smallest;
        }


    }

    private void siftUp(int index) {
        int parent = (index -1)/2;

        while (index > 0 && less(index, parent)) {
            swap(index, parent);
            index = parent;
            parent = (index -1)/2;
        }
    }
}
