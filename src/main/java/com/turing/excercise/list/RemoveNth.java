package com.turing.excercise.list;

import java.util.List;

public class RemoveNth {
    class ListNode{
        int val;
        ListNode next;

        public ListNode(int val) {this.val = val;}
    }

    public ListNode removeNth(ListNode root, int k) {
        if(root == null) throw new IllegalArgumentException();
        ListNode i = root;
        ListNode j = root;

        for(int x = 0; x < k; x++) {
            if(j == null) throw new IllegalArgumentException();
            j  = j.next;
        }

        if(j == null)  return i.next;

        while(j.next != null) {
            i = i.next; j=j.next;
        }

        ListNode tmp = i.next;
        if(tmp != null) {
            i.next = tmp.next;
        }
        tmp = null;
        return root;
    }

    void run(){
        ListNode root = new ListNode(1);root.next = new ListNode(2); root.next.next = new ListNode(3);
        root.next.next.next = new ListNode(4); root.next.next.next.next = new ListNode(5);
        ListNode result = removeNth(root, 1);
        System.out.println(result.val);
    }

    public static void main(String[] args) {
        new RemoveNth().run();
    }
}
