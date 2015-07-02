package graph;

import graph.Vertex.Color;

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
        
        // cycle
//        shirt.adjs.put(tie, null);
//        tie.adjs.put(jacket, null);
//        jacket.adjs.put(shirt, null);

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

        System.out.println(tps(graph));
    }

    public static List<Vertex> tps(List<Vertex> graph) {
        List<Vertex> order = new LinkedList<Vertex>();
        for (Vertex v : graph) {
            if (dfs(v, order) == false) {
                return new LinkedList<Vertex>();
            }
        }
        
        return order;
    }
    
    private static boolean dfs(Vertex v, List<Vertex> order) {
        if (v.color == Color.GRAY) {
            return false;
        }
        if (v.color == Color.BALCK) {
            return true;
        }
        
        v.color = Color.GRAY;
        for (Vertex adj : v.adjs.keySet()) {
            if (dfs(adj, order) == false) {
                return false;
            }
        }
        v.color = Color.BALCK;
        order.add(0, v);
        
        return true;
    }
}
