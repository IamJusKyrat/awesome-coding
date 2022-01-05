package com.turing.excercise.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class LowestCommonAncestor {
    private boolean getNodeStack(TreeNode node, TreeNode value, Stack<TreeNode> stack) {
        if (node == null) return false;
        if (node == value || getNodeStack(node.left, value, stack) || getNodeStack(node.right, value, stack)) {
            stack.push(node);
            return true;
        }
        return false;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Stack<TreeNode> first = new Stack<>();
        Stack<TreeNode> second = new Stack<>();

        getNodeStack(root, p, first);
        getNodeStack(root, q, second);
        TreeNode prev = null;
        while(!first.isEmpty() && !second.isEmpty()) {
            second.pop();
            prev = first.pop();
        }

        return prev;
    }

    public void run() {
        List<TreeNode> nodes = testTree1();
        TreeNode lca = lowestCommonAncestor(nodes.get(0), nodes.get(1), nodes.get(2));
        if(lca.val == 5) System.out.println("Test 1 passed");
    }

    public static void main(String[] args) {
        new LowestCommonAncestor().run();
    }

    private List<TreeNode> testTree1() {
        TreeNode node_7 = new TreeNode(7);
        TreeNode node_4 = new TreeNode(4);
        TreeNode node_2 = new TreeNode(2, node_4, node_7);
        TreeNode node_6 = new TreeNode(6);
        TreeNode node_5 = new TreeNode(5, node_6, node_2);
        TreeNode node_0 = new TreeNode(0);
        TreeNode node_8 = new TreeNode(8);
        TreeNode node_1 = new TreeNode(1, node_0, node_8);
        TreeNode node_3 = new TreeNode(3, node_5, node_1);

        return Arrays.asList(node_3, node_5, node_4);
    }
}
