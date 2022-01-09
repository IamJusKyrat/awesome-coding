package com.turing.algorithm.pathfinding;

public class Edge {
    double cost;
    int from, to;

    public Edge(int from, int to, double cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }
}