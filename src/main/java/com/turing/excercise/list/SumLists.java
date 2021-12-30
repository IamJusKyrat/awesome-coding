package com.turing.excercise.list;

/**
 * Category: Medium
 * <p>
 * Question: You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Reference: https://leetcode.com/problems/add-two-numbers/
 */

public class SumLists {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode head = l1;
        ListNode prev = null;
        while (l1 != null || l2 != null) {
            int l1Val = l1 != null ? l1.val : 0;
            int l2Val = l2 != null ? l2.val : 0;
            int currentValue = l1Val + l2Val + carry;
            if (l1 == null) {
                l1 = new ListNode(0);
                prev.next = l1;
            }
            l1.val = currentValue % 10;
            carry = currentValue / 10;
            prev = l1;l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        return head;
    }
}

