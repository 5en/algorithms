package graph;

import graph.Vertex.Color;

import java.util.LinkedList;
import java.util.Queue;

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
        System.out.println(source + ":" + source.color);
        source.prev = null;
        source.dist = 0;

        Queue<Vertex> Q = new LinkedList<Vertex>();
        Q.add(source);

        while (!Q.isEmpty()) {
            Vertex u = Q.remove();
            for (Vertex v : u.adjs.keySet()) {
                if (v.color == Color.WHITE) {
                    // v has not been accessed
                    v.color = Color.GRAY;
                    System.out.println(v + ":" + v.color);
                    v.dist = u.dist + 1;
                    v.prev = u;

                    Q.add(v);
                }
            }
            u.color = Color.BALCK;
            System.out.println(u + ":" + u.color);
        }
    }
}
