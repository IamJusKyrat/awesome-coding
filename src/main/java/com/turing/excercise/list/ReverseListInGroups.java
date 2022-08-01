package com.turing.excercise.list;

import java.util.List;

public class ReverseListInGroups {

    public ListNode reverseKGroup(ListNode head, int k) {
        //start and end both at starting position, end moved to k-1th value
        //start reversing until start == end
        //head will be now be kth node.
        if(head == null) return head;

        ListNode currGrpLast = head, currGrpFirst = head, prevGrpLast = null;
        ListNode newHead = head; boolean headAssigned = false;
        int counter = k-1;
        while(currGrpFirst != null) {
            while(counter > 0) {
                if(currGrpLast.next == null) break;
                currGrpLast = currGrpLast.next;
                counter--;
            }

            //Reverse List
            if(counter != 0) break;
            ListNode curr = currGrpFirst.next; ListNode prev = currGrpFirst;
            ListNode nextGrp = currGrpLast.next;
            while(curr != nextGrp) {
                ListNode temp = curr.next;
                curr.next = prev;
                prev = curr; curr = temp;
            }
            if(!headAssigned) { newHead = currGrpLast; headAssigned = true;}
            if(prevGrpLast != null) prevGrpLast.next = currGrpLast;
            prevGrpLast = currGrpFirst;
            currGrpFirst = currGrpLast = curr;
            counter=k-1;
        }

        if(prevGrpLast != null) prevGrpLast.next = currGrpFirst;
        return newHead;
    }


    public ListNode reverseKGroupBetter(ListNode head, int k) {
        //start and end both at starting position, end moved to k-1th value
        //start reversing until start == end
        //head will be now be kth node.
        if(k == 1) return head;

        ListNode curr = head.next;//2
        ListNode prev = head; //1
        ListNode newHead = head; boolean isAssigned = false;
        ListNode prevLast = null;
        int counter = k - 1;
        while(curr != null) {
            ListNode localHead = prev; //3
            while(counter > 0 && curr != null) {
                ListNode temp = curr.next; //5
                curr.next = prev; prev = curr; curr = temp; //4 -> 3, 4, 5
                counter--;
            }
            if(!isAssigned) {isAssigned = true; newHead = prev;} //2
            if(prevLast != null) {
                prevLast.next = prev; //3 -> 5
            }
            prevLast = localHead;
            prev = curr;
            if(curr != null) curr = curr.next;
            counter = k - 1;
        }

        prevLast.next = prev;

        return newHead;
    }

    public void run() {
        ListNode l1 = new ListNode(1); ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3); ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);

        ListNode l6 = new ListNode(6);

        l1.next = l2; l2.next = l3; l3.next = l4; l4.next = l5;
        ListNode resp = reverseKGroup(l6, 3);
        while(resp != null) {
            System.out.print(resp.val + ",");
            resp = resp.next;
        }
    }

    public static void main(String[] agrs) {
        ReverseListInGroups rlg = new ReverseListInGroups();
        rlg.run();
    }
}
