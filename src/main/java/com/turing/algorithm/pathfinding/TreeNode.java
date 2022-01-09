package com.turing.algorithm.pathfinding;

public class TreeNode<E extends Comparable<E>> {
    final E data;
    final TreeNode<E> left;
    final TreeNode<E> right;

    TreeNode(E element, TreeNode<E> left, TreeNode<E> right) {
        this.data = element;
        this.left = left;
        this.right = right;
    }
}