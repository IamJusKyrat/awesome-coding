package com.turing.excercise.graph;

import com.turing.excercise.TestResultsHelper;

import java.util.*;

/**
 * Category: Medium
 * <p>
 * There are n cities connected by some number of flights. You are given an array flights where flights[i] =
 * [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.
 * <p>
 * You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops.
 * If there is no such route, return -1.
 * <p>
 * Reference: https://leetcode.com/problems/cheapest-flights-within-k-stops/
 */
public class FindCheapestFlights {
    class Node {
        int from;
        int to;
        int cost;
        int totalCost = 0;
        int pathLength = 0;

        public Node(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        //We cannot use djikstra as the graph may contain a cycle
        //BFS for cheapest path from a to b
        //k iterations we can do
        //Create an adjacency List;
        Map<Integer, List<Node>> adjList = new HashMap<>(n);

        for (int i = 0; i < flights.length; i++) {
            int from = flights[i][0];
            int to = flights[i][1];
            int cost = flights[i][2];
            List<Node> adj = adjList.getOrDefault(from, new ArrayList<>());
            adj.add(new Node(from, to, cost));
            adjList.put(from, adj);
        }

        int minCost = Integer.MAX_VALUE;

        Queue<Node> q = new LinkedList<>();
        if(adjList.get(src) == null) return -1;
        q.addAll(adjList.get(src));
        while (!q.isEmpty()) {
            Node curr = q.poll();
            if (curr.pathLength <= k) {
                List<Node> adj = adjList.get(curr.to);
                if(adj != null) {
                    adj.forEach(adjI -> {
                        Node copy = new Node(adjI.from, adjI.to, adjI.cost);
                        copy.totalCost = curr.totalCost + curr.cost;
                        copy.pathLength = curr.pathLength + 1;
                        q.offer(copy);
                    });
                }
                if (curr.to == dst) minCost = Math.min(minCost, curr.totalCost + curr.cost);
            }


        }

        return minCost == Integer.MAX_VALUE ? -1: minCost;
    }

    private void run() {
        int[][] flights1 = new int[][] {{0,1,100}, {0,2,500}, {1,2,100}};
        TestResultsHelper.verify("1", 200, findCheapestPrice(flights1.length, flights1, 0, 2, 1));
        TestResultsHelper.verify("2", 500, findCheapestPrice(flights1.length, flights1, 0, 2, 0));
        int[][] flights2 =  new int[][] {{0,1,2},{1,2,1},{2,0,10}};
        TestResultsHelper.verify("3", 1, findCheapestPrice(flights2.length, flights2, 1, 2, 1));
    }

    public static void main(String[] args) {
        new FindCheapestFlights().run();
    }
}
