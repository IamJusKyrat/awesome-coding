package com.turing.excercise.list;

/**
 * Category: Medium
 * <p>
 * Question: You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Reference: https://leetcode.com/problems/add-two-numbers/
 */

public class MergeSortedLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result, head;
        result = head = new ListNode(0);
        while (l1 != null || l2 != null) {
            int value = 0;
            int l1Val = l1 != null ? l1.val : 101;
            int l2Val = l2 != null ? l2.val : 101;
            if (l1 != null && l1Val <= l2Val) {
                value = l1Val;
                l1 = l1.next;
            } else if (l2 != null) {
                value = l2Val;
                l2 = l2.next;
            }
            result.next = new ListNode(value);
            result = result.next;
        }
        return head.next;
    }

    public static void main(String[] args) {
        MergeSortedLists msl = new MergeSortedLists();
        ListNode node1 = msl.mergeTwoLists(ListNode.arrayToList(new int[]{1,2,3}), ListNode.arrayToList(new int[]{1,3,4}));
        ListNode node2 = msl.mergeTwoLists(ListNode.arrayToList(new int[]{}), ListNode.arrayToList(new int[]{0}));
        ListNode node3 = msl.mergeTwoLists(ListNode.arrayToList(new int[]{}), ListNode.arrayToList(new int[]{}));
    }
}
