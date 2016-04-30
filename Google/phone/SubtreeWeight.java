// Given a list of 3-number tuples (id,parent,weight), which describes a tree. print the weight of all subtrees.

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SubtreeWeight {

    public static void main(String[] args) {
        //          (0, 1)
        // (1, 5)   (2, 10)   (3, 4)
        //                  (4, 8) (5, 8)

        Node n0 = new Node(0, -1, 1);
        Node n1 = new Node(1, 0, 5);
        Node n2 = new Node(2, 0, 10);
        Node n3 = new Node(3, 0, 4);
        Node n4 = new Node(4, 3, 8);
        Node n5 = new Node(5, 3, 8);
        List<Node> nodes = new ArrayList<Node>();
        nodes.add(n0);
        nodes.add(n1);
        nodes.add(n2);
        nodes.add(n3);
        nodes.add(n4);
        nodes.add(n5);

        weight(nodes);
    }

    // O(N) time, O(N) space
    public static void weight(List<Node> nodes) {
        Map<Integer, Set<Node>> childMap = new HashMap<Integer, Set<Node>>();
        for (Node node : nodes) {
            if (node.parent == -1) {
                continue;
            }

            if (!childMap.containsKey(node.parent)) {
                childMap.put(node.parent, new HashSet<Node>());
            }
            childMap.get(node.parent).add(node);
        }

        Map<Integer, Integer> weightMap = new HashMap<Integer, Integer>();
        for (Node node : nodes) {
            int weight = calWeight(childMap, weightMap, node);
            System.out.println(String.format("id=%d, weight=%d", node.id, weight));
        }
    }

    private static int calWeight(Map<Integer, Set<Node>> childMap, Map<Integer, Integer> weightMap,
                                 Node node) {
        if (weightMap.containsKey(node.id)) {
            return weightMap.get(node.id);
        }

        int result = node.w;
        if (childMap.containsKey(node.id)) {
            for (Node child : childMap.get(node.id)) {
                result += calWeight(childMap, weightMap, child);
            }
        }

        weightMap.put(node.id, result);

        return result;
    }

    private static class Node {
        int id;
        int parent;
        int w;

        public Node(int id, int parent, int w) {
            this.id = id;
            this.parent = parent;
            this.w = w;
        }
    }
}
