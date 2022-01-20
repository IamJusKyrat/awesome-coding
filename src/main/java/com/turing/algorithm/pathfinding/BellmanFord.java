package com.turing.algorithm.pathfinding;

import java.util.Arrays;
import java.util.Comparator;

public class BellmanFord {
    /**
     * Bellman-Ford-Moore relaxes each edge by iterating over each vertices and directed edge and then reducing the actual cost from the start node.
     * If secondly the edges are in a negative loop this will find out as anything within a negative loop will be replaced with negative infinity.
     * @param edges
     * @param V
     * @param start
     * @return
     */
    public static double[] execute(Edge[] edges, int V, int start) {
        double[] dist = new double[V];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        dist[start] = 0;
        for (int v = 0; v < V-1; v++) {
            for (Edge edge : edges) {
                if (dist[edge.from] + edge.cost < dist[edge.to]) {
                    dist[edge.to] = dist[edge.from] + edge.cost;
                }
            }
        }

        for (int v = 0; v < V - 1; v++) {
            for (Edge edge : edges) {
                if (dist[edge.from] + edge.cost < dist[edge.to]) {
                    dist[edge.to] = Double.NEGATIVE_INFINITY;
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        Edge e1 = new Edge(0,1,3.0);
        Edge e2 = new Edge(0,2,4.0);
        Edge e3 = new Edge(1,3,1.0);
        Edge e4 = new Edge(2,1,-2.0);
        Edge e5 = new Edge(3,4,2.0);
        Edge e6 = new Edge(4,2,-1.0);
        Edge[] edges = new Edge[]{e1, e2, e3, e4, e5, e6};
        String str = "Hello";
        Arrays.sort(edges, Comparator.comparingInt(o -> o.from));
        System.out.println(str.substring(0,5));
        double[] result = BellmanFord.execute(new Edge[]{e1,e2,e3,e4,e5,e6}, 5, 0);
        System.out.println(Arrays.toString(result));
    }
}
