package com.turing.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;

public class TreeHelper {
    public void printPreOrder(TreeNode<?> root) {
        System.out.print(root + ",");
        if(root.left != null) printPreOrder(root.left);
        if(root.right != null) printPreOrder(root.right);
    }

    public void printInOrder(TreeNode<?> root) {
        if(root.left != null) printInOrder(root.left);
        System.out.print(root.data + ",");
        if(root.right != null) printInOrder(root.right);
    }

    public void printPostOrder(TreeNode<?> root) {
        if(root.left != null) printPostOrder(root.left);
        if(root.right != null) printPostOrder(root.right);
        System.out.print(root.data + ",");
    }

    public void printLevelOrder(TreeNode<?> root) {
        TreeNode<?> sentinal = new TreeNode<>(null);
        Queue<TreeNode<?>> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(sentinal);
        while(!queue.isEmpty()) {
            TreeNode<?> node = queue.poll();
            if(node == sentinal) {
                System.out.println(""); if(queue.isEmpty()) break;
                queue.offer(sentinal);
                continue;
            }
            System.out.print(node.data + ",");
            if(node.left != null) queue.offer(node.left);
            if(node.right != null) queue.offer(node.right);
        }
    }

    public void printLevelOrder(AVLTreeNode<?> root) {
        AVLTreeNode<?> sentinal = new AVLTreeNode<>(null);
        Queue<AVLTreeNode<?>> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(sentinal);
        while(!queue.isEmpty()) {
            AVLTreeNode<?> node = queue.poll();
            if(node == sentinal) {
                System.out.println(""); if(queue.isEmpty()) break;
                queue.offer(sentinal);
                continue;
            }
            System.out.print(node.data + ",");
            if(node.left != null) queue.offer(node.left);
            if(node.right != null) queue.offer(node.right);
        }
    }

    public void printInOrder(AVLTreeNode<?> root) {
        if(root.left != null) printInOrder(root.left);
        System.out.print(root.data + ",");
        if(root.right != null) printInOrder(root.right);
    }
}
