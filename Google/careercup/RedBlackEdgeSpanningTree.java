// You are given a graph, some edges are black, some are red.
// Find a spanning tree with one restriction:
// if we take some node as root, every path from it to some leaf node must consist of alternating red-black-red-black edges.
// That is, no path from root to leaf must contain sequential black-black edges or red-red edges.
// You are guaranteed that such spanning tree exists.

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RedBlackEdgeSpanningTree {

    public static void main(String[] args) {
        int[][] adj = new int[8][8];
        adj[0][2] = 1;
        adj[2][0] = 1;
        adj[1][2] = 1;
        adj[2][1] = 1;

        adj[2][3] = 1;
        adj[3][2] = 1;

        adj[3][4] = -1;
        adj[4][3] = -1;
        adj[4][5] = 1;
        adj[5][4] = 1;
        adj[5][6] = 1;
        adj[6][5] = 1;
        adj[6][7] = -1;
        adj[7][6] = -1;
        adj[4][7] = 1;
        adj[7][4] = 1;

        spanningTree(adj);
    }

    // adj[u][v]
    // 0: no edge
    // -1: red
    // 1: black
    public static void spanningTree(int[][] adj) {
        int N = adj.length;
        for (int root = 0; root < N; root++) {
            List<Edge> result = spanningTreeSub(adj, root);
            if (result != null) {
                System.out.println("root = " + root);
                System.out.println(result);
            }
        }
    }

    private static List<Edge> spanningTreeSub(int[][] adj, int root) {
        int N = adj.length;
        List<Edge> result = new ArrayList<Edge>();
        Set<Integer> visited = new HashSet<Integer>();
        dfs(result, visited, adj, root, 0);

        return result.size() == N - 1 ? result : null;
    }

    private static void dfs(List<Edge> result, Set<Integer> visited, int[][] adj, int u,
                            int prevColor) {
        int N = adj.length;

        visited.add(u);

        for (int v = 0; v < N; v++) {
            if (adj[u][v] == 0) {
                continue;
            }

            if (visited.contains(v)) {
                continue;
            }

            Edge edge = new Edge(u, v);

            // choose (u, v), if alternating color
            if (prevColor != adj[u][v]) {
                visited.add(v);
                result.add(edge);

                dfs(result, visited, adj, v, adj[u][v]);
            }
        }
    }

    private static class Edge {
        int u;
        int v;

        public Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }

        @Override
        public String toString() {
            return String.format("(%d, %d)", u, v);
        }
    }

}
