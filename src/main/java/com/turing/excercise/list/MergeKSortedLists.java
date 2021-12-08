package com.turing.excercise.list;

/**
 * Category: Hard
 *
 * Question: You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 * Merge all the linked-lists into one sorted linked-list and return it.
 *
 * Reference: https://leetcode.com/problems/merge-k-sorted-lists/
 */
public class MergeKSortedLists {
    //Definition for singly-linked list.
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    static class Solution {

        public ListNode mergeKLists(ListNode[] lists) {
            /**
             * 1. Insert into a minHeap (PriorityQueue) and pop-out into a list.
             * 2. Insert in place something like merge sort but we can do it parallely.
             */

            return null;
        }

    }
}
