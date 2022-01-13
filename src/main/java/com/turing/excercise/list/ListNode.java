package com.turing.excercise.list;

public class ListNode {
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

    public static ListNode arrayToList(int[] arr) {
        ListNode head , listNode; head = listNode = new ListNode(0);
        for(int element: arr) {
            listNode.next = new ListNode(element);
            listNode = listNode.next;
        }
        return head.next;
    }
}
