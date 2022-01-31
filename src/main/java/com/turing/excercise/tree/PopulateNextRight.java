package com.turing.excercise.tree;

import com.turing.excercise.TestResultsHelper;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Category: Medium
 * <p>
 * Given a binary tree
 * <p>
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 * <p>
 * Initially, all next pointers are set to NULL.
 * <p>
 * Reference: https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/
 */
public class PopulateNextRight {
    // Definition for a Node.
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    ;

    public Node connect(Node root) {
        //Put all entries in a queue
        //Set next pointers to next value in the queue
        //Need a sentinal to differentiate levels
        if (root == null) return null;
        Queue<Node> q = new LinkedList<>();
        final Node SENTINAL = new Node(Integer.MAX_VALUE);
        q.offer(root);
        q.offer(SENTINAL);

        Node prev = null;
        while (!q.isEmpty()) {
            Node curr = q.poll();
            if (curr == SENTINAL) {
                if(q.isEmpty()) break;
                q.offer(SENTINAL);
                prev = null;
                continue;
            }
            if (prev != null) prev.next = curr;
            if (curr.left != null) q.offer(curr.left);
            if (curr.right != null) q.offer(curr.right);
            prev = curr;
        }

        return root;
    }

    private void run() {
        Node root = new Node(1);
        root.left = new Node(2); root.right = new Node(3);
        root.left.left = new Node(4); root.left.right = new Node(5);
        root.right.right = new Node(7);
        connect(root);
    }

    public static void main(String[] args) {
        new PopulateNextRight().run();
    }
}
