package com.turing.algorithm.pathfinding;

import com.turing.datastructure.priorityqueue.PriorityQueue;

import java.util.List;

public class Dijkstra {
    // Small epsilon value to comparing double values.
    private static final double EPS = 1e-6;

    static class Node implements Comparable<Node> {
        int identifier;
        double dist;

        public Node(int to, double dist) {
            this.identifier = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node compared) {
            if(this.dist - compared.dist < EPS) return 0;
            return (int) Math.floor(this.dist - compared.dist);
        }
    }

    public static double execute(List<List<Edge>> graph, int V, int start, int end) {
        boolean[] visited = new boolean[V];
        int[] prev = new int[V];
        PriorityQueue<Node> pQ = new PriorityQueue<>();
        double[] dist = new double[V];
        pQ.add(new Node(start,0.0));

        while (!pQ.isEmpty()) {
            Node node = pQ.poll();
            int from = node.identifier;
            visited[node.identifier] = true;
            if(dist[from] < node.identifier) continue;
            List<Edge> edges = graph.get(from);
            for (Edge edge : edges) {
                // You cannot get a shorter path by revisiting
                // a node you have already visited before.
                if (visited[edge.to]) continue;

                // Relax edge by updating minimum cost if applicable.
                double newDist = dist[edge.from] + edge.cost;
                if (newDist < dist[edge.to]) {
                    prev[edge.to] = edge.from;
                    dist[edge.to] = newDist;
                    pQ.add(new Node(edge.to, dist[edge.to]));
                }
            }
            // Once we've visited all the nodes spanning from the end
            // node we know we can return the minimum dist value to
            // the end node because it cannot get any better after this point.
            if (from == end) return dist[end];
        }
        return -1;
    }

    public void addEdge(List<List<Edge>> graph, int from, int to, int cost) {
        graph.get(from).add(new Edge(from, to, cost));
    }
}
