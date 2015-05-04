package graph;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class ShortestPath {
    public static void main(String[] args) {
        Map<String, Vertex> VMap = new HashMap<String, Vertex>();
        VMap.put("s", new Vertex("s"));
        VMap.put("t", new Vertex("t"));
        VMap.put("x", new Vertex("x"));
        VMap.put("y", new Vertex("y"));
        VMap.put("z", new Vertex("z"));
        Set<Edge> E = new HashSet<Edge>();
        E.add(new Edge(VMap.get("s"), VMap.get("t"), 10));
        E.add(new Edge(VMap.get("s"), VMap.get("y"), 5));
        E.add(new Edge(VMap.get("t"), VMap.get("x"), 1));
        E.add(new Edge(VMap.get("t"), VMap.get("y"), 2));
        E.add(new Edge(VMap.get("x"), VMap.get("z"), 4));
        E.add(new Edge(VMap.get("y"), VMap.get("t"), 3));
        E.add(new Edge(VMap.get("y"), VMap.get("x"), 9));
        E.add(new Edge(VMap.get("y"), VMap.get("z"), 2));
        E.add(new Edge(VMap.get("z"), VMap.get("s"), 7));
        E.add(new Edge(VMap.get("z"), VMap.get("x"), 6));

        System.out.println("Dijkstra");
        //dijkstra(new HashSet<Vertex>(VMap.values()), E, VMap.get("s"));

        System.out.println();

        System.out.println("Bellman-Ford");
        System.out.println(bellmanFord(new HashSet<Vertex>(VMap.values()), E, VMap.get("s")));

        System.out.println("dist(s,s) = " + VMap.get("s").dist);
        System.out.println("dist(s,t) = " + VMap.get("t").dist);
        System.out.println("dist(s,x) = " + VMap.get("x").dist);
        System.out.println("dist(s,y) = " + VMap.get("y").dist);
        System.out.println("dist(s,z) = " + VMap.get("z").dist);

        System.out.println();

        int[][] graph = new int[5][5]; // s(0), t(1), x(2), y(3), z(4)
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                graph[i][j] = -1;
            }
        }
        graph[0][1] = 10;
        graph[0][3] = 5;
        graph[1][2] = 1;
        graph[1][3] = 2;
        graph[2][4] = 4;
        graph[3][1] = 3;
        graph[3][2] = 9;
        graph[3][4] = 2;
        graph[4][0] = 7;
        graph[4][2] = 6;
        System.out.println("Dijkstra");
        System.out.println("dist(s,z) = " + dijkstra(graph, 0, 4));
    }

    // O(|E| + |V|*log|V|)
    public static int dijkstra(int[][] graph, int from, int to) {
        // init
        int V = graph.length;

        Set<Integer> Q = new HashSet<Integer>();

        int[] dist = new int[V];
        dist[from] = 0;
        for (int i = 0; i < V; i++) {
            if (i != from) {
                dist[i] = -1;
            }
            Q.add(i);
        }

        // main loop
        // could be replaced with PriorityQueue
        while (!Q.isEmpty()) {
            // find min dist, source node in the first iteration
            int minDist = Integer.MAX_VALUE;
            int optI = -1;
            for (int i : Q) {
                if (dist[i] != -1 && dist[i] < minDist) {
                    minDist = dist[i];
                    optI = i;
                }
            }

            if (optI == -1) {
                // some vertices may be isolated
                break;
            }

            Q.remove(optI);

            // for each node indexed j in Q that there is a path from optI to v, update
            for (int j : Q) {
                if (graph[optI][j] != -1) {
                    if (dist[j] == -1 || dist[optI] + graph[optI][j] < dist[j]) {
                        dist[j] = dist[optI] + graph[optI][j];
                    }
                }
            }
        }

        return dist[to];
    }

    // O(|E| + |V|*log|V|)
    public static void dijkstra(Set<Vertex> V, Set<Edge> E, Vertex from) {
        from.dist = 0;
        from.prev = null;

        PriorityQueue<Vertex> Q = new PriorityQueue<Vertex>(new Comparator<Vertex>() {
            @Override
            public int compare(Vertex v1, Vertex v2) {
                return v1.dist < v2.dist ? -1 : (v1.dist > v2.dist ? 1 : 0);
            }
        });
        for (Vertex v : V) {
            Q.add(v);
        }

        while (!Q.isEmpty()) {
            Vertex u = Q.remove();

            for (Map.Entry<Vertex, Edge> entry : u.adjs.entrySet()) {
                Vertex v = entry.getKey();
                Edge e = entry.getValue();

                if (Q.contains(v) && u.dist + e.w < v.dist) {
                    // v.dist is updated to the shortest dist to u
                    v.dist = u.dist + e.w;
                    v.prev = u;
                    Q.remove(v);
                    Q.add(v);
                }
            }
        }
    }

    // O(|E|*|V|), can handle negative weights
    public static boolean bellmanFord(Set<Vertex> V, Set<Edge> E, Vertex from) {
        from.dist = 0;
        from.prev = null;

        for (int i = 1; i <= V.size() - 1; i++) { // |V|-1 iterations, since each vertex can only be updated through |V|-1 vertices
            for (Edge e : E) {
                Vertex u = e.u;
                Vertex v = e.v;
                if (u.dist != Integer.MAX_VALUE && u.dist + e.w < v.dist) {
                    v.dist = u.dist + e.w;
                    v.prev = u;
                }
            }
        }

        for (Edge e : E) {
            Vertex u = e.u;
            Vertex v = e.v;
            if (u.dist + e.w < v.dist) {
                // indicate negative weights
                return false;
            }
        }

        return true;
    }
}
