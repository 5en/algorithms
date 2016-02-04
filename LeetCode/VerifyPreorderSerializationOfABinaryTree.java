// https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/

package com.htyleo.algorithms;

public class VerifyPreorderSerializationOfABinaryTree {
    // O(N) time
    public boolean isValidSerialization(String preorder) {
        // keep record of D = degree(out) - degree(in): 
        // when encounter a node (except for the root), D--, since it indicates an in-degree (parent -> node)
        // if the node is not null, D+=2, since it indicates two out-degree (node -> left, node -> right)
        // D should never be negative
        // at the end, D should be 0
        int D = 1;
        String[] nodes = preorder.split(",");
        for (String node : nodes) {
            D--;
            if (D < 0) {
                return false;
            }
            if (!node.equals("#")) {
                D += 2;
            }
        }

        return D == 0;
    }

    // worst case: biased tree O(N^2) time
    public boolean isValidSerialization2(String preorder) {
        String[] nodes = preorder.split(",");
        return verify(nodes, 0, nodes.length - 1);
    }

    private boolean verify(String[] nodes, int start, int end) {
        // if the tree is a single node, must be #
        if (start == end) {
            return nodes[start].equals("#");
        }

        // if the tree is more than one node,
        // the nodes[start] must not be #
        // scan from nodes[start+1], until count("#") == count(!"#") + 1, which is its left subtree
        if (nodes[start].equals("#")) {
            return false;
        }
        int countNull = 0;
        int countNotNull = 0;
        int i = start + 1;
        for (; i < end; i++) {
            if (nodes[i].equals("#")) {
                countNull++;
            } else {
                countNotNull++;
            }

            if (countNotNull + 1 == countNull) {
                break;
            }
        }

        return i < end && verify(nodes, start + 1, i) && verify(nodes, i + 1, end);
    }
}
