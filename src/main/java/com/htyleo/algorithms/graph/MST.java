package com.htyleo.algorithms.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class MST {
    public static void main(String[] args) {
        Set<Vertex> V = new HashSet<Vertex>();
        Vertex a = new Vertex("a");
        Vertex b = new Vertex("b");
        Vertex c = new Vertex("c");
        Vertex d = new Vertex("d");
        Vertex e = new Vertex("e");
        Vertex f = new Vertex("f");
        Vertex g = new Vertex("g");
        Vertex h = new Vertex("h");
        Vertex i = new Vertex("i");
        V.add(a);
        V.add(b);
        V.add(c);
        V.add(d);
        V.add(e);
        V.add(f);
        V.add(g);
        V.add(h);
        V.add(i);

        Set<Edge> E = new HashSet<Edge>();
        E.add(new Edge(a, b, 4));
        E.add(new Edge(a, h, 8));
        E.add(new Edge(b, h, 11));
        E.add(new Edge(b, c, 8));
        E.add(new Edge(h, i, 7));
        E.add(new Edge(i, c, 2));
        E.add(new Edge(h, g, 1));
        E.add(new Edge(i, g, 6));
        E.add(new Edge(g, f, 2));
        E.add(new Edge(c, f, 4));
        E.add(new Edge(c, d, 7));
        E.add(new Edge(d, f, 14));
        E.add(new Edge(f, e, 10));
        E.add(new Edge(d, e, 9));
        for (Edge edge : E) {
            edge.u.adjs.put(edge.v, edge);
            edge.v.adjs.put(edge.u, edge);
        }

        System.out.println("Kruskal");
        for (Edge edge : mstKruskal(V, E)) {
            System.out.println(edge);
        }

        System.out.println("Prim");
        for (Edge edge : mstPrim(V, E)) {
            System.out.println(edge);
        }
    }

    // O(|V|+|E|)
    public static Set<Edge> mstKruskal(Set<Vertex> V, Set<Edge> E) {
        // make each vertex its own group
        int setId = 0;
        Map<Vertex, Integer> v2groupId = new HashMap<Vertex, Integer>();
        for (Vertex v : V) {
            v2groupId.put(v, setId++);
        }
        Map<Integer, Set<Vertex>> groupId2v = new HashMap<Integer, Set<Vertex>>();
        for (Vertex v : v2groupId.keySet()) {
            groupId2v.put(v2groupId.get(v), new HashSet<Vertex>());
            groupId2v.get(v2groupId.get(v)).add(v);
        }

        // sort edges in nondecreasing order
        List<Edge> sortedEdges = new ArrayList<Edge>(E);
        Collections.sort(sortedEdges, new Comparator<Edge>() {
            @Override
            public int compare(Edge e1, Edge e2) {
                return (e1.w == e2.w ? 0 : (e1.w < e2.w ? -1 : 1));
            }
        });

        Set<Edge> selectedE = new HashSet<Edge>(V.size() - 1);

        for (Edge e : sortedEdges) {
            if (!v2groupId.get(e.u).equals(v2groupId.get(e.v))) {
                // u and v are not in the same group

                // merge the two groups
                int groupIdU = v2groupId.get(e.u);
                int groupIdV = v2groupId.get(e.v);
                for (Vertex v : groupId2v.get(groupIdV)) {
                    v2groupId.put(v, groupIdU); // update 
                    groupId2v.get(groupIdU).add(v);
                }
                groupId2v.remove(groupIdV);

                selectedE.add(e);
                if (selectedE.size() == V.size() - 1) {
                    break;
                }
            }
        }

        return selectedE;
    }

    // O(|V|+|E|)
    public static Set<Edge> mstPrim(Set<Vertex> V, Set<Edge> E) {
        // initial node
        Vertex root = V.iterator().next();
        root.dist = 0;
        root.prev = null;

        // min-heap
        PriorityQueue<Vertex> Q = new PriorityQueue<Vertex>(V.size(), new Comparator<Vertex>() {
            @Override
            public int compare(Vertex v1, Vertex v2) {
                return v1.dist < v2.dist ? -1 : (v1.dist > v2.dist ? 1 : 0);
            }
        });
        for (Vertex v : V) {
            Q.add(v);
        }

        Set<Edge> selectedE = new HashSet<Edge>(V.size() - 1);

        while (!Q.isEmpty()) {
            Vertex u = Q.remove(); // u is the node that has the min dist to a node currently in MST

            if (u.prev != null) {
                selectedE.add(u.adjs.get(u.prev));
            }

            for (Map.Entry<Vertex, Edge> entry : u.adjs.entrySet()) {
                Vertex v = entry.getKey();
                Edge e = entry.getValue();

                if (Q.contains(v) && e.w < v.dist) {
                    v.dist = e.w;
                    v.prev = u;
                    Q.remove(v);
                    Q.add(v);
                }
            }
        }

        return selectedE;
    }
}
