package com.turing.algorithm;

import java.awt.*;
import java.util.*;
import java.util.List;


class Node<E extends Comparable<E>> {
    final E data;
    final Node<E> left;
    final Node<E> right;

    Node(E element, Node<E> left, Node<E> right) {
        this.data = element;
        this.left = left;
        this.right = right;
    }
}

public class TreeIterations<E extends Comparable<E>> {

    //Depth First Traversal
    public static <E extends Comparable<E>> List<E> depthFirstTraversal(Node<E> root) {
        if(root == null) return new ArrayList<>();
        final Stack<Node<E>> stack = new Stack<>();
        final List<E> result = new ArrayList<>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node<E> currentNode = stack.pop();
            result.add(currentNode.data);
            if(currentNode.right != null) stack.push(currentNode.right);
            if(currentNode.left != null) stack.push(currentNode.left);
        }
        return result;
    }

    //Depth First Search returns path to the element
    public static <E extends Comparable<E>> boolean depthFirstSearch(Node<E> root, E find) {
        if(root == null) return false;
        final Stack<Node<E>> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            final Node<E> currentNode = stack.pop();
            if(currentNode.data == find) return true;
            if(currentNode.right != null) stack.push(currentNode.right);
            if(currentNode.left != null) stack.push(currentNode.left);
        }
        return false;
    }

    //Level Order Traversal or BreadthFirstTraversal
    public static <E extends Comparable<E>> List<E> breadthFirstTraversal(Node<E> root) {
        if(root == null) return new ArrayList<>();
        final Queue<Node<E>> queue = new ArrayDeque<>();
        final List<E> result = new ArrayList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            final Node<E> currentNode = queue.poll();
            result.add(currentNode.data);
            if(currentNode.left != null) queue.add(currentNode.left);
            if(currentNode.right != null) queue.add(currentNode.right);
        }
        return result;
    }

    private static void run() {
        final Node<Integer> root = createRandomTree();
        final List<Integer> resultDFT = depthFirstTraversal(root);
        System.out.println("\n ====Depth First Traversal======");
        resultDFT.forEach(e -> System.out.print(e + ", "));

        final List<Integer> resultBFT = breadthFirstTraversal(root);
        System.out.println("\n ====Breadth First Traversal======");
        resultBFT.forEach(e -> System.out.print(e + ", "));

        System.out.println("\n ====Depth First Search======");
        System.out.println("3 : " + depthFirstSearch(root, 3));
        System.out.println("6 : " + depthFirstSearch(root, 6));
        System.out.println("7 : " + depthFirstSearch(root, 7));
    }

    private static Node<Integer> createRandomTree() {
        Node<Integer> f = new Node<>(6, null, null);
        Node<Integer> e = new Node<>(5, null, null);
        Node<Integer> d = new Node<>(4, null, null);
        Node<Integer> c = new Node<>(3, null, f);
        Node<Integer> b = new Node<>(2, d, e);
        return new Node<>(1, b, c);
    }

    public static void main(String[] args) {
        TreeIterations.run();
    }
}
