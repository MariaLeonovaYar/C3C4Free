package com.company;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Graph {
    public static long[][] readGraph(int n) throws FileNotFoundException {
        Scanner in = new Scanner(new FileReader("./tests/test" + n + ".txt"));
        long[][] dist = new long[n][n];
        in.nextLine();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            int tx = in.nextInt();
            int ty = in.nextInt();
            x[i] = tx;
            y[i] = ty;
        }
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                dist[i][j] = new Vertex(x[i], y[i]).dist(new Vertex(x[j], y[j]));
        return dist;
    }


    public static Solution maxMST(long[][] dist) {
        int n = dist.length;
        ArrayList<Edge> edges = new ArrayList<>();
        long maxCost = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                edges.add(new Edge(i, j, dist[i][j]));
                maxCost = Math.max(maxCost, dist[i][j]);
            }
        }
        edges.sort((o1, o2) -> (int) (o2.cost - o1.cost));
        boolean[][] g = new boolean[n][n];
        Solution solution = new Solution(n);
        long cost = 0;
        for (Edge curEdge : edges) {
            if (peekEdge(curEdge, maxCost) && isC3C4Free(g, curEdge)) {
                g[curEdge.u][curEdge.v] = true;
                g[curEdge.v][curEdge.u] = true;
                solution.edges.add(new Edge(curEdge.u, curEdge.v, curEdge.cost));
                cost += curEdge.cost;
            }
        }
        solution.weight = cost;
        return solution;
    }

    private static boolean peekEdge(Edge curEdge, long maxCost) {
        long r = Math.round(Math.random() * maxCost);
        return r <= curEdge.cost;
    }

    private static boolean isC3C4Free(boolean[][] g, Edge addedEdge) {
        return isC3Free(g, addedEdge) && isC4Free(g, addedEdge);
    }

    private static boolean isC3Free(boolean[][] g, Edge addedEdge) {
        int n = g.length;
        int u = addedEdge.u;
        int v = addedEdge.v;
        for (int i = 0; i < n; i++) {
            if (i != u && i != v) {
                if (g[u][i] && g[v][i]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isC4Free(boolean[][] g, Edge addedEdge) {
        int n = g.length;
        int u = addedEdge.u;
        int v = addedEdge.v;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (i != u && i != v && j != u && j != v) {
                    if ((g[v][j] && g[j][i] && g[u][i])
                            || (g[v][i] && g[j][i] && g[u][j])) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static Solution postProcessing(long[][] dist, Solution curSolution) {
        int n = dist.length;
        ArrayList<Edge> edges = new ArrayList<>();
        long maxCost = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                edges.add(new Edge(i, j, dist[i][j]));
                maxCost = Math.max(maxCost, dist[i][j]);
            }
        }
        edges.sort((o1, o2) -> (int) (o2.cost - o1.cost));
        boolean[][] g = new boolean[n][n];
        Solution solution = new Solution(n);
        solution.weight = curSolution.weight;
        for (Edge e : curSolution.edges) {
            g[e.u][e.v] = true;
            g[e.v][e.u] = true;
            solution.edges.add(new Edge(e.u, e.v, -1));
        }
        long incr = 0;
        for (Edge curEdge : edges) {
            if (!g[curEdge.u][curEdge.v] && !g[curEdge.v][curEdge.u] && isC3C4Free(g, curEdge)) {
                g[curEdge.u][curEdge.v] = true;
                g[curEdge.v][curEdge.u] = true;
                solution.edges.add(new Edge(curEdge.u, curEdge.v, curEdge.cost));
                incr += curEdge.cost;
            }
        }
        solution.weight += incr;
        return solution;
    }
}
