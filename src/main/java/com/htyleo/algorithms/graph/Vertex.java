package com.htyleo.algorithms.graph;

import java.util.HashMap;
import java.util.Map;

public class Vertex {
    public final Object            data;
    public final Map<Vertex, Edge> adjs  = new HashMap<Vertex, Edge>();

    public static int              time  = 0;                          // DFS, TPS (timestamp)

    public Vertex                  prev  = null;                       // MSTPrim, SP, DFS, BFS
    public int                     dist  = Integer.MAX_VALUE;          // MSTPrim, SP, BFS
    public Color                   color = Color.WHITE;                // DFS, BFS
    public int                     d     = -1;                         // DFS, TPS (timestamp when first discovered)
    public int                     f     = -1;                         // DFS, TPS (timestamp when finished discovering adjs)

    public Vertex(Object data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (this.getClass() != o.getClass()) {
            return false;
        }

        Vertex v = (Vertex) o;

        return this.data.equals(v.data);
    }

    @Override
    public int hashCode() {
        return this.data.hashCode();
    }

    @Override
    public String toString() {
        return data.toString();
    }

    public static enum Color {
        WHITE, GRAY, BALCK
    }
}
