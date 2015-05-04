package graph;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class ToplogicalSort {
    public static void main(String[] args) {
        Vertex shirt = new Vertex("shirt");
        Vertex tie = new Vertex("tie");
        Vertex jacket = new Vertex("jacket");
        Vertex watch = new Vertex("watch");
        Vertex undershorts = new Vertex("undershorts");
        Vertex pants = new Vertex("pants");
        Vertex belt = new Vertex("belt");
        Vertex socks = new Vertex("socks");
        Vertex shoes = new Vertex("shoes");

        shirt.adjs.put(tie, null);
        shirt.adjs.put(belt, null);

        tie.adjs.put(jacket, null);

        belt.adjs.put(jacket, null);

        undershorts.adjs.put(pants, null);
        undershorts.adjs.put(shoes, null);

        pants.adjs.put(belt, null);
        pants.adjs.put(shoes, null);

        socks.adjs.put(shoes, null);

        List<Vertex> graph = new LinkedList<Vertex>();
        graph.add(shirt);
        graph.add(tie);
        graph.add(jacket);
        graph.add(watch);
        graph.add(undershorts);
        graph.add(pants);
        graph.add(belt);
        graph.add(socks);
        graph.add(shoes);

        tps(graph);
    }

    public static void tps(List<Vertex> graph) {
        for (Vertex v : graph) {
            DFS.dfs(v);
        }

        Collections.sort(graph, new Comparator<Vertex>() {
            @Override
            public int compare(Vertex v1, Vertex v2) {
                return v1.f >= v2.f ? -1 : 1;
            }
        });

        for (Vertex v : graph) {
            System.out.println(v.data + " " + v.d + "/" + v.f);
        }
    }
}
