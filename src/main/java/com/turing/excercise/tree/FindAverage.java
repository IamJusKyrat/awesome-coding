package com.turing.excercise.tree;

import com.turing.excercise.TestResultsHelper;

import java.util.LinkedList;
import java.util.Queue;

public class FindAverage {
    class TreeNode{
        int val;
        TreeNode left, right;

        public TreeNode(int val) {this.val = val;}
    }

    public int findAverage(TreeNode root, int low, int high) {
        int count = 0;
        int sum = 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()) {
            TreeNode node = q.poll();
            if(node == null) continue;
            if(node.val > high) {
                q.offer(node.left);
            } else if(node.val < low) {
                q.offer(node.right);
            } else {
                sum += node.val;
                count++;
                q.offer(node.left);
                q.offer(node.right);
            }
        }
        return sum/count;
    }

    void run() {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.left.left.left = new TreeNode(2);
        root.right.right = new TreeNode(9);
        root.right.left = new TreeNode(7);

        TestResultsHelper.verify("1", 3, findAverage(root, 1,5));
    }

    public static void main(String[] args) {
        new FindAverage().run();
    }
}
