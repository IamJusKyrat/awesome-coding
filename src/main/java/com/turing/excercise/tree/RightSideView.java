package com.turing.excercise.tree;

import java.util.*;

public class RightSideView {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}
        TreeNode(int val) {this.val = val;}

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> output = new ArrayList<>();
        Queue<TreeNode> stack = new LinkedList<>();
        TreeNode SENTINAL = new TreeNode(-101);
        stack.add(root);
        stack.add(SENTINAL);
        TreeNode prev = null;
        while (stack.peek() != null) {
            TreeNode node = stack.remove();
            if (node == SENTINAL) {
                if (prev != SENTINAL) {
                    output.add(prev.val);
                    stack.add(SENTINAL);
                }
            }

            if (node.left != null) stack.add(node.left);
            if (node.right != null) stack.add(node.right);
            prev = node;
        }

        return output;
    }

    public void run() {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        t1.left = t2; t1.right = t3;
        t3.left = t4; t2.right = t5;

        System.out.println(rightSideView(t1).toString());
    }

    public static void main(String[] agrs) {
         RightSideView  rsv = new RightSideView();
         rsv.run();
    }
}
