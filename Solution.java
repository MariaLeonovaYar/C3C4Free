package com.company;

import java.util.ArrayList;

public class Solution {
    public int n;
    public ArrayList<Edge> edges;
    public long weight;

    public Solution(int n) {
        this.n = n;
        this.weight = 0;
        this.edges = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("c Вес подграфа = " + weight + "\n");
        s.append("p edge " + n + " " + edges.size() + "\n");
        for (Edge edge : edges) {
            s.append("e " + (edge.u + 1) + " " + (edge.v + 1) + "\n");
        }
        return s.toString();
    }
}