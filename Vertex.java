package com.company;

public class Vertex {
    public int x, y;

    public Vertex(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int dist(Vertex v) {
        return Math.abs(v.x - x) + Math.abs(v.y - y);
    }
}
