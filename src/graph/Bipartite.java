package graph;

import graph.Vertex.Color;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Bipartite {
    public static void main(String[] args) {
        Set<Vertex> V = new HashSet<Vertex>();
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");
        Vertex v5 = new Vertex("v5");
        Vertex v6 = new Vertex("v6");
        Vertex v7 = new Vertex("v7");
        Vertex v8 = new Vertex("v8");
        Vertex v9 = new Vertex("v9");
        v1.adjs.put(v6, null);
        v6.adjs.put(v1, null);

        v6.adjs.put(v2, null);
        v2.adjs.put(v6, null);

        v2.adjs.put(v7, null);
        v7.adjs.put(v2, null);

        v7.adjs.put(v3, null);
        v3.adjs.put(v7, null);

        v8.adjs.put(v3, null);
        v3.adjs.put(v8, null);

        v3.adjs.put(v9, null);
        v9.adjs.put(v3, null);

        v9.adjs.put(v5, null);
        v5.adjs.put(v9, null);

        v5.adjs.put(v6, null);
        v6.adjs.put(v5, null);

        //v1.adjs.put(v2, null);
        //v2.adjs.put(v1, null);

        V.add(v1);
        V.add(v2);
        V.add(v3);
        V.add(v4);
        V.add(v5);
        V.add(v6);
        V.add(v7);
        V.add(v8);
        V.add(v9);

        System.out.println(isBipartite(V));
    }

    public static boolean isBipartite(Set<Vertex> V) {
        for (Vertex v : V) {
            // default WHITE, set to GRAY or BLACK
            if (v.color == Color.WHITE) {
                v.color = Color.GRAY;
                if (bfsColor(V, v) == false) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean bfsColor(Set<Vertex> V, Vertex root) {
        // root has been assigned a color
        Queue<Vertex> queue = new LinkedList<Vertex>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Vertex u = queue.remove();
            for (Vertex v : u.adjs.keySet()) {
                if (v.color == u.color) {
                    return false;
                } else if (v.color == Color.WHITE) {
                    v.color = (u.color == Color.GRAY ? Color.BALCK : Color.GRAY);
                    queue.add(v);
                } // else, v has been traversed
            }
        }

        return true;
    }
}
