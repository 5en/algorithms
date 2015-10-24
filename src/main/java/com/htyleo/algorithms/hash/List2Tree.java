package com.htyleo.algorithms.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class List2Tree {
    public static void main(String[] args) {
        //				1
        //		2				3
        //					4	5	7
        //					6
        List<PlainNode> plainNodes = new ArrayList<PlainNode>();
        plainNodes.add(new PlainNode(4, 3));
        plainNodes.add(new PlainNode(2, 1));
        plainNodes.add(new PlainNode(1, -1));
        plainNodes.add(new PlainNode(6, 4));
        plainNodes.add(new PlainNode(5, 3));
        plainNodes.add(new PlainNode(7, 3));
        plainNodes.add(new PlainNode(3, 1));

        TreeNode root = parse(plainNodes);

        System.out.println(root.id);
        System.out.println(root.c.get(0).id + " " + root.c.get(1).id);
        System.out.println(root.c.get(1).c.get(0).id + " " + root.c.get(1).c.get(1).id + " "
                           + root.c.get(1).c.get(2).id);
        System.out.println(root.c.get(1).c.get(0).c.get(0).id);
    }

    public static TreeNode parse(List<PlainNode> plainNodes) {
        Map<Integer, TreeNode> nodeMap = new HashMap<Integer, TreeNode>();
        for (PlainNode pn : plainNodes) {
            nodeMap.put(pn.id, new TreeNode(pn.id));
        }

        TreeNode root = null;
        for (PlainNode pn : plainNodes) {
            TreeNode node = nodeMap.get(pn.id);

            if (pn.pid == -1) {
                root = node;
            } else {
                TreeNode parent = nodeMap.get(pn.pid);
                node.p = parent;
                parent.c.add(node);
            }
        }

        return root;
    }

    private static class PlainNode {
        public final int id;
        public final int pid; // -1 means no parent

        public PlainNode(int id, int pid) {
            this.id = id;
            this.pid = pid;
        }
    }

    private static class TreeNode {
        public final int            id;
        public TreeNode             p = null;
        public final List<TreeNode> c = new ArrayList<TreeNode>();

        public TreeNode(int id) {
            this.id = id;
        }
    }
}
