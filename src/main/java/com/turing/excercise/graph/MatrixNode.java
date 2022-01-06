package com.turing.excercise.graph;

public class MatrixNode {
        // (x, y) represents matrix cell coordinates, and
        // `dist` represents their minimum distance from the source
        int x, y, dist;

        public MatrixNode(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
}
