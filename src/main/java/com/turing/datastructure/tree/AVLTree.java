package com.turing.datastructure.tree;

public class AVLTree<E extends Comparable<E>>{
    private AVLTreeNode<E> root;
    private int count;

    public boolean isEmpty(){
        return root == null;
    }

    public boolean insert(E element) {
        if(element == null) return false;
        if(!contains(element))  {
            root = insert(element, root);
            count++;
            return true;
        }
        return false;
    }

    private AVLTreeNode<E> insert(E element, AVLTreeNode<E> root) {
        if(root == null) return new AVLTreeNode<>(element, null, null);
        int cmp = element.compareTo(root.data);
        if(cmp > 0) root.right = insert(element, root.right);
        else root.left = insert(element, root.left);

        update(root);
        return balance(root);
    }

    private void update(AVLTreeNode<E> root) {
        int lh =  -1, rh = -1;
        if(root.left != null) lh = root.left.height;
        if(root.right != null) rh = root.right.height;

        root.height = 1 + Math.max(lh,rh);
        root.balanceFactor = rh - lh;
    }

    private AVLTreeNode<E> balance(AVLTreeNode<E> root) {
        if(root.balanceFactor == -2) {
            if(root.left.balanceFactor <= 0) {//Left-left case
                return rotateRight(root);
            } else {//Left-Right Case
                root.left = rotateLeft(root.left);
                return rotateRight(root);
            }
        } else if(root.balanceFactor == 2) {
            if(root.right.balanceFactor >= 0) { //Right-Right Rotation
                return rotateLeft(root);
            } else { // Right-Left Rotation
                root.right = rotateRight(root.right);
                return rotateLeft(root.left);
            }
        }
        return root;
    }

    private AVLTreeNode<E> rotateRight(AVLTreeNode<E> root){
        if(root == null) return null;
        AVLTreeNode<E> left = root.left;
        root.left = left.right;
        left.right = root;
        update(root); update(left);
        return left;
    }

    private AVLTreeNode<E> rotateLeft(AVLTreeNode<E> root){
        if(root == null) return null;
        AVLTreeNode<E> right = root.right;
        root.right = right.left;
        right.left = root;
        update(root); update(right);
        return right;
    }

    public boolean contains(E element) {
        return find(element, root) != null;
    }

    public AVLTreeNode<E> get(E element) {
        return find(element, root);
    }

    private AVLTreeNode<E> find(E element, AVLTreeNode<E> root) {
        if(root == null) return null;
        int compare = element.compareTo(root.data);
        if(compare == 0) return root;
        if(compare > 0 && root.right != null) find(element, root.right);
        else if(root.left != null) find(element, root.left);
        return null;
    }

    public boolean remove(E element) {
        return false;
    }

    /*--------------------------------------------- Testing Code ----------------------------------------*/

    public static class TestAVL {
        public static void insertionTest() {
            AVLTree<Integer> avlTree = new AVLTree<>();
            avlTree.insert(10);
            avlTree.insert(20);
            avlTree.insert(5);
            avlTree.insert(-10);
            avlTree.insert(-5);
            avlTree.insert(7);
            avlTree.insert(-20);
            new TreeHelper().printLevelOrder(avlTree.root);
            new TreeHelper().printInOrder(avlTree.root);
        }
    }

    public static void main(String[] args) {
        TestAVL.insertionTest();
    }
}


