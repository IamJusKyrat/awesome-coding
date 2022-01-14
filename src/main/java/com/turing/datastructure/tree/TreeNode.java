package com.turing.datastructure.tree;

public class TreeNode<E extends Comparable<E>>{
    public E data;
    public TreeNode<E> left, right;

    public TreeNode(E element, TreeNode<E> left, TreeNode<E> right) {
        this.data = element;
        this.left = left;
        this.right = right;
    }


    public TreeNode(E element) {
        this.data = element;
        this.left = null;
        this.right = null;
    }
}