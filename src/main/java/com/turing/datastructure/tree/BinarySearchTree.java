package com.turing.datastructure.tree;

import com.turing.datastructure.queue.LinkedListQueue;
import com.turing.datastructure.queue.Queue;
import com.turing.datastructure.stack.LinkedListStack;
import com.turing.datastructure.stack.Stack;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * TODO: Add Documentation
 * @param <T>
 */
public class BinarySearchTree<T extends Comparable<T>> {
    private int nodeCount;
    private Node root = null;

    public boolean isEmpty() {
        return nodeCount == 0;
    }

    public int size() {
        return nodeCount;
    }

    public boolean add(T element) {
        //Not adding duplicate elements.
        if (contains(element)) return false;
        root = add(element, root);
        nodeCount++;
        return true;
    }

    private Node add(T element, Node node) {
        if (node == null) {
            node = new Node(null, null, element);
        } else {
            if (element.compareTo(node.data) > 0) {
                node.right = add(element, node.right);
            } else {
                node.left = add(element, node.left);
            }
        }
        return node;
    }

    public boolean remove(T element) {
        if (contains(element)) {
            root = remove(element, root);
            nodeCount--;
            return true;
        }
        return false;
    }

    private Node remove(T element, Node node) {
        if (node == null) return null;
        int cmp = element.compareTo(node.data);
        if (cmp < 0) {
            node.left = remove(element, node.left);
        } else if (cmp > 0) {
            node.right = remove(element, node.right);
        } else {
            if (node.left == null) {
                Node rightChild = node.right;
                node.data = null;
                node = null;
                return rightChild;
            } else if (node.right == null) {
                Node leftChild = node.left;
                node.data = null;
                node = null;
                return leftChild;
            } else {
                Node tmp = digLeft(node.right);
                node.data = tmp.data;
                node.right = remove(tmp.data, node.right);
            }
        }
        return node;
    }

    private Node digLeft(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private Node digRight(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    public boolean contains(T element) {
        return contains(element, root);
    }

    private boolean contains(T element, Node node) {
        if (node == null) return false;
        int cmp = element.compareTo(node.data);
        if (cmp > 0) {
            contains(element, node.right);
        } else {
            contains(element, node.left);
        }
        return true;
    }

    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) return 0;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    public Iterator<T> traverse(TreeTraversalOrder order) {
        switch (order) {
            case PRE_ORDER:
                return preOrderTraversal();
            case IN_ORDER:
                return inOrderTraversal();
            case POST_ORDER:
                return postOrderTraversal();
            case LEVEL_ORDER:
                return levelOrderTraversal();
        }
        return null;
    }

    private Iterator<T> preOrderTraversal() {
        final int expectedNodeCount = nodeCount;
        final Stack<Node> stack = new LinkedListStack<>();
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                return root != null && !stack.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                Node node = stack.pop();
                if (node.right != null) stack.push(node.right);
                if (node.left != null) stack.push(node.left);
                return node.data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private Iterator<T> inOrderTraversal() {
        final int expectedNodeCount = nodeCount;
        final Stack<Node> stack = new LinkedListStack<>();
        return new Iterator<T>() {
            Node trav = root;

            @Override
            public boolean hasNext() {
                if (expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                return root != null && !stack.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                while (trav != null && trav.left != null) {
                    stack.push(trav.left);
                    trav = trav.left;
                }

                Node node = stack.pop();

                if (node.right != null) {
                    stack.push(node.right);
                    trav = node.right;
                }
                return node.data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private Iterator<T> postOrderTraversal() {
        final int expectedNodeCount = nodeCount;
        final Stack<Node> tempStack = new LinkedListStack<>();
        final Stack<Node> stack = new LinkedListStack<>();
        tempStack.push(root);
        while (!tempStack.isEmpty()) {
            Node node = stack.pop();
            if (node != null) {
                stack.push(node);
                if (node.right != null) tempStack.push(node.right);
                if (node.left != null) tempStack.push(node.left);
            }
        }
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                return root != null && !stack.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                return stack.pop().data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private Iterator<T> levelOrderTraversal() {
        final int expectedNodeCount = nodeCount;
        final Queue<Node> queue = new LinkedListQueue<>();
        queue.enqueue(root);
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                return root != null && !queue.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNodeCount != nodeCount) throw new ConcurrentModificationException();
                Node node = queue.dequeue();
                if (node.left != null) queue.enqueue(node.left);
                if (node.right != null) queue.enqueue(node.right);
                return node.data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public enum TreeTraversalOrder {
        PRE_ORDER,
        IN_ORDER,
        POST_ORDER,
        LEVEL_ORDER;
    }

    private class Node {
        private T data;
        private Node left, right;

        public Node(Node left, Node right, T element) {
            this.data = element;
            this.left = left;
            this.right = right;
        }
    }
}
