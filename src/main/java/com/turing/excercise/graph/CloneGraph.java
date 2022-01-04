package com.turing.excercise.graph;

import java.util.*;

public class CloneGraph {
    public Node cloneGraphIterative(Node node) {
        if(node== null) return null;
        Map<Integer, Node> visited = new HashMap<>();
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        Node base = null;
        while(!stack.isEmpty()) {
            Node trav = stack.pop();
            if(!visited.containsKey(trav.val)) {
                Node curr;
                if(base == null) curr = base = new Node(trav.val);
                else curr = new Node(trav.val);
                for(Node neighbor: trav.neighbors) {
                    if(visited.containsKey(neighbor.val)) {
                        Node connected = visited.get(neighbor.val);
                        connected.neighbors.add(curr);
                        curr.neighbors.add(connected);
                    } else {
                        stack.push(neighbor);
                    }
                }
                visited.put(curr.val, curr);
            }
        }
        return base;
    }

    private static Map<Integer, Node> visited = new HashMap<>();
    public Node cloneGraphRecursive(Node node) {
        if(node == null) return null;
        if(visited.containsKey(node.val)) return visited.get(node.val);

        Node newNode = new Node(node.val);
        visited.put(newNode.val, newNode);

        for(Node neighbor: node.neighbors) {
            newNode.neighbors.add(cloneGraphRecursive(neighbor));
        }
        return newNode;
    }


    public void run() {
        Node first = new Node(1);
        Node second = new Node(2);
        Node third = new Node(3);
        Node fourth = new Node(4);
        first.neighbors = Arrays.asList(second, fourth);
        second.neighbors = Arrays.asList(first, third);
        third.neighbors = Arrays.asList(second, fourth);
        fourth.neighbors = Arrays.asList(first, third);
        printGraph(cloneGraphRecursive(first));
    }

    private void printGraph(Node base) {
        Set<Integer> visited = new HashSet<>();
        Stack<Node> nodes = new Stack<>();
        nodes.push(base);
        while(!nodes.isEmpty()) {
            Node curr = nodes.pop();
            if(!visited.contains(curr.val)) {
                System.out.print(curr.val + " => ");
                for (Node neighbor : curr.neighbors) {
                    System.out.print(neighbor.val + " ");
                    nodes.push(neighbor);
                }
                visited.add(curr.val);
                System.out.println("");
            }
        }
    }

    public static void main(String[] args) {
        new CloneGraph().run();
    }
}
