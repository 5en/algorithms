package com.htyleo.algorithms.graph;

import com.htyleo.algorithms.graph.Vertex.Color;

public class DFS {
    public static void main(String[] args) {
        //      v1
        //    /    \
        //  v2------v3
        //  / \      |
        // v4 v5    v6
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");
        Vertex v5 = new Vertex("v5");
        Vertex v6 = new Vertex("v6");
        v1.adjs.put(v2, null);
        v1.adjs.put(v3, null);
        v2.adjs.put(v4, null);
        v2.adjs.put(v5, null);
        v2.adjs.put(v3, null);
        v3.adjs.put(v6, null);
        dfs(v1);
    }

    // O(|V|+|E|)
    public static void dfs(Vertex source) {
        if (source.color == Color.WHITE) {
            visit(source);
        }
    }

    private static void visit(Vertex u) {
        Vertex.time++;

        u.d = Vertex.time;
        u.color = Color.GRAY;

        System.out.println("START: " + u + "-" + u.color + "-" + u.d);

        for (Vertex v : u.adjs.keySet()) {
            if (v.color == Color.WHITE) {
                v.prev = u;
                visit(v);
            }
        }

        u.f = Vertex.time;
        u.color = Color.BALCK;

        System.out.println("END: " + u + "-" + u.color + "-" + u.f);
    }
}
