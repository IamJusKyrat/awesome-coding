package com.turing.datastructure.tree;

public class AVLTreeNode<E extends Comparable<E>> {
    public E data;
    public int height = 0;
    public int balanceFactor = 0;
    public AVLTreeNode<E> left, right;

    public AVLTreeNode(E element, AVLTreeNode<E> left, AVLTreeNode<E> right) {
        this.data = element;
        this.left = left;
        this.right = right;
    }

    public AVLTreeNode(E element) {
        this.data = element;
        this.left = null;
        this.right = null;
    }
}
