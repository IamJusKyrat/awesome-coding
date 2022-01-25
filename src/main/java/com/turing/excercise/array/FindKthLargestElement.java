package com.turing.excercise.array;

import com.turing.excercise.TestResultsHelper;

public class FindKthLargestElement {
    public int findKthLargest(int[] nums, int k) {
        MinHeap mh  = new MinHeap(nums, k);
        for(int i=k; i < nums.length; i++) {
            if(nums[i] > mh.heap[0]) {
                mh.replaceMax(nums[i]);
            }
        }

        return mh.heap[0];
    }

    private void run() {
        TestResultsHelper.verify("1", 6, findKthLargest(new int[]{1,2,3,4,5,6,7,8}, 3));
        TestResultsHelper.verify("2", 3, findKthLargest(new int[]{11,-2,3,-4,-5,6,0,-8}, 3));
    }

    public static void main(String[] args) {
        new FindKthLargestElement().run();
    }
}

class MinHeap {
    int[] heap;
    int size;
    int capacity;

    int parent(int i) {return (i-1)/2;}
    int left(int i) {return 2*i+1;}
    int right(int i) {return 2*i+2;}

    void replaceMax(int value) {
        heap[0] = value;
        minHeapify(0);
    }

    MinHeap(int[] arr, int capacity) {
        this.heap = new int[capacity]; this.capacity = this.size = capacity;
        for(int in = 0; in < capacity; in++) {
            heap[in] = arr[in];
        }
        int i = (size-1)/2;
        while(i >= 0) {
            minHeapify(i);
            i--;
        }
    }

    void swap(int i, int j) {
        int swap = heap[i];
        heap[i] = heap[j];
        heap[j] = swap;
    }

    void minHeapify(int index) {
        int smallest = index;
        if(left(index) < size && heap[smallest] > heap[left(index)]) smallest = left(index);
        if(right(index) < size && heap[smallest] > heap[right(index)]) smallest = right(index);

        if(smallest != index) {
            swap(smallest, index);
            minHeapify(smallest);
        }
    }
}