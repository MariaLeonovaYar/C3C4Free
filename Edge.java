package com.company;

import java.util.Objects;

public class Edge {
    public int u, v;
    public long cost;

    public Edge(int u, int v, long cost) {
        this.u = u;
        this.v = v;
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return u == edge.u && v == edge.v && cost == edge.cost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(u, v, cost);
    }
}

