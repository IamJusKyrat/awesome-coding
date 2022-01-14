package com.turing.algorithm.pathfinding;

import com.turing.datastructure.tree.TreeNode;

import java.util.*;
import java.util.List;
public class TreeIterations<E extends Comparable<E>> {

    //Depth First Traversal
    public static <E extends Comparable<E>> List<E> depthFirstTraversal(TreeNode<E> root) {
        if(root == null) return new ArrayList<>();
        final Stack<TreeNode<E>> stack = new Stack<>();
        final List<E> result = new ArrayList<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode<E> currentNode = stack.pop();
            result.add(currentNode.data);
            if(currentNode.right != null) stack.push(currentNode.right);
            if(currentNode.left != null) stack.push(currentNode.left);
        }
        return result;
    }

    //Depth First Search returns path to the element
    public static <E extends Comparable<E>> boolean depthFirstSearch(TreeNode<E> root, E find) {
        if(root == null) return false;
        final Stack<TreeNode<E>> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            final TreeNode<E> currentNode = stack.pop();
            if(currentNode.data == find) return true;
            if(currentNode.right != null) stack.push(currentNode.right);
            if(currentNode.left != null) stack.push(currentNode.left);
        }
        return false;
    }

    //Level Order Traversal or BreadthFirstTraversal
    public static <E extends Comparable<E>> List<E> breadthFirstTraversal(TreeNode<E> root) {
        if(root == null) return new ArrayList<>();
        final Queue<TreeNode<E>> queue = new ArrayDeque<>();
        final List<E> result = new ArrayList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            final TreeNode<E> currentNode = queue.poll();
            result.add(currentNode.data);
            if(currentNode.left != null) queue.add(currentNode.left);
            if(currentNode.right != null) queue.add(currentNode.right);
        }
        return result;
    }

    private static void run() {
        final TreeNode<Integer> root = createRandomTree();
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

    private static TreeNode<Integer> createRandomTree() {
        TreeNode<Integer> f = new TreeNode<>(6, null, null);
        TreeNode<Integer> e = new TreeNode<>(5, null, null);
        TreeNode<Integer> d = new TreeNode<>(4, null, null);
        TreeNode<Integer> c = new TreeNode<>(3, null, f);
        TreeNode<Integer> b = new TreeNode<>(2, d, e);
        return new TreeNode<>(1, b, c);
    }

    public static void main(String[] args) {
        TreeIterations.run();
    }
}
