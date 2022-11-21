package com.turing.excercise.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {
    public List<List<Integer>> levelOrder(Node root) {
        //Create a queue start with root, add a sentinal value in between
        //Keep adding elements of each row  of the tree to the list,
        //once sentinal is acheived add the list to linked list or tree map list.

        Node SENTINAL = new Node();

        List<List<Integer>> response = new LinkedList<>();
        List<Integer> currRow = new LinkedList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(SENTINAL);

        Node prev = null;

        while(queue.peek() != null) {
            Node curr = queue.poll();
            if(curr == SENTINAL) {
                if(prev == SENTINAL) break;
                response.add(currRow);
                currRow = new LinkedList<>();
                queue.offer(SENTINAL);
            }
            if(curr != SENTINAL) {
                currRow.add(curr.val);
                if(curr.children != null) {
                    curr.children.forEach(queue::offer);
                }
            }

            prev = curr;
        }

        return response;
    }
}

class Node {
    public int val;
    public List<Node> children;
    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
