package com.turing.datastructure;

import java.util.Arrays;

public class TestBasicDS {
    final static String DEFAULT_SEPARATOR = ",";

    private void run() {
        int[] arr = new int[]{1,1,5,7};
        heapsort(arr);
        System.out.println("Final Result : " + printIntArray(arr));
    }

    public static void main(String[] args) {
        new TestBasicDS().run();
    }

    private static void heapsort(int[] ar) {
        if (ar == null) return;
        int n = ar.length;

        // Heapify, converts array into binary heap O(n), see:
        // http://www.cs.umd.edu/~meesh/351/mount/lectures/lect14-heapsort-analysis-part.pdf
        for (int i = n / 2 - 1; i >= 0; i--) {
            sink(ar, n, i);
        }

        // Sorting bit
        for (int i = n - 1; i >= 0; i--) {
            swap(ar, 0, i);
            sink(ar, i, 0);
        }
    }

    private static void sink(int[] ar, int n, int i) {
        while (true) {
            int left = 2 * i + 1; // Left  node
            int right = 2 * i + 2; // Right node
            int smallest = i;

            // Right child is larger than parent
            if (right < n && ar[right] < ar[smallest]) smallest = right;

            // Left child is larger than parent
            if (left < n && ar[left] < ar[smallest]) smallest = left;

            // Move down the tree following the largest node
            if (smallest != i) {
                swap(ar, smallest, i);
                i = smallest;
            } else break;
        }
    }

    private static void swap(int[] ar, int i, int j) {
        int tmp = ar[i];
        ar[i] = ar[j];
        ar[j] = tmp;
    }

    private static String printIntArray(int[] arr) {

        StringBuilder sb = new StringBuilder();
        Arrays.stream(arr).forEach(item -> sb.append(item).append(DEFAULT_SEPARATOR));
        return sb.substring(0, sb.length() -1).toString();
    }
}
