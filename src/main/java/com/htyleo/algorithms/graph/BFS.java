package com.htyleo.algorithms.graph;

import java.util.LinkedList;
import java.util.Queue;

import com.htyleo.algorithms.graph.Vertex.Color;

public class BFS {
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
        bfs(v1);
    }

    // O(|V|+|E|)
    public static void bfs(Vertex source) {
        source.color = Color.GRAY;
        source.prev = null;
        source.dist = 0;

        Queue<Vertex> Q = new LinkedList<>();
        Q.add(source);
        Queue<Vertex> auxQ = new LinkedList<>();

        while (!Q.isEmpty()) {
            while (!Q.isEmpty()) {
                Vertex u = Q.remove();
                System.out.println("START: " + u + "-" + u.color);

                for (Vertex v : u.adjs.keySet()) {
                    if (v.color == Color.WHITE) {
                        // v has not been accessed
                        v.color = Color.GRAY;
                        v.dist = u.dist + 1;
                        v.prev = u;

                        System.out.println("ENQUEUE: " + v + "-" + v.color);

                        auxQ.add(v);
                    }
                }

                u.color = Color.BALCK;
                System.out.println("END: " + u + "-" + u.color);
            }

            Queue<Vertex> tmpQ = Q;
            Q = auxQ;
            auxQ = tmpQ;
        }
    }
}
