// https://leetcode.com/problems/clone-graph/

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CloneGraph {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }

        Map<UndirectedGraphNode, UndirectedGraphNode> old2new = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        return clone(old2new, node);
    }

    private UndirectedGraphNode clone(Map<UndirectedGraphNode, UndirectedGraphNode> old2new,
                                      UndirectedGraphNode node) {
        if (old2new.containsKey(node)) {
            return old2new.get(node);
        }

        UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
        old2new.put(node, newNode);
        for (UndirectedGraphNode neighbor : node.neighbors) {
            newNode.neighbors.add(clone(old2new, neighbor));
        }

        return newNode;
    }

    private static class UndirectedGraphNode {
        int                       label;
        List<UndirectedGraphNode> neighbors;

        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<UndirectedGraphNode>();
        }
    }
}
