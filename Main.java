package com.company;

import java.io.IOException;

import static com.company.FileUtils.writeAnswerToFile;

public class Main {
    public static void main(String[] args) throws IOException {
        solve();
    }

    public static void solve() throws IOException {
        int n = 64;
        long[][] dist = Graph.readGraph(n);
        writeAnswerToFile(Graph.postProcessing(dist, Graph.maxMST(dist)));
    }
}
