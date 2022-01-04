package com.turing.excercise.tree;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class IsValidBST {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public boolean isValidBST(TreeNode root) {
        if (root == null) return false;
        Stack<TreeNode> s = new Stack<>();
        TreeNode curr = root;

        // traverse the tree
        int prev = Integer.MIN_VALUE;
        while (curr != null || s.size() > 0) {
            while (curr != null) {
                s.push(curr);
                curr = curr.left;
            }

            /* Current must be NULL at this point */
            curr = s.pop();
            if(curr.val < prev) return false;
            prev = curr.val;

            /* we have visited the node and its left subtree.  Now, it's right subtree's turn */
            curr = curr.right;
        }
        return true;
    }

    public void run() {
        System.out.println(isValidBST(createRandomTree()));
    }

    public static void main(String[] args) {
        new IsValidBST().run();
    }

    private TreeNode createRandomTree() {
        TreeNode f = new TreeNode(6);
        TreeNode e = new TreeNode(3);
        TreeNode d = new TreeNode(1);
        TreeNode c = new TreeNode(5, null, f);
        TreeNode b = new TreeNode(2, d, e);
        return new TreeNode(4, b, c);
    }
}
