package com.htyleo.algorithms.graph;

public class Edge {
    public final Vertex u;
    public final Vertex v;
    public final int    w;

    public Edge(Vertex u, Vertex v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
        u.adjs.put(v, this);
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

        Edge oe = (Edge) o;

        return this.u.equals(oe.u) && this.v.equals(oe.v) && this.w == oe.w;
    }

    @Override
    public int hashCode() {
        return this.u.hashCode() + this.v.hashCode() + this.w;
    }

    @Override
    public String toString() {
        return "(" + this.u + ", " + this.v + ") w = " + this.w;
    }
}
