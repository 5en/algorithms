// https://leetcode.com/problems/min-stack/

package com.htyleo.algorithms;

import java.util.Deque;
import java.util.LinkedList;

public class MinStack {

    private Node        min;
    private Deque<Node> stack = new LinkedList<Node>();

    private static class Node {
        public int  val;
        public Node nextMin; // point to the min Node below this node

        public Node(int val, Node nextMin) {
            this.val = val;
            this.nextMin = nextMin;
        }
    }

    public void push(int x) {
        Node node = new Node(x, min);
        stack.push(node);

        if (min == null || node.val < min.val) {
            min = node;
        }
    }

    public void pop() {
        Node node = stack.pop();
        min = node.nextMin;
    }

    public int top() {
        return stack.peek().val;
    }

    public int getMin() {
        return min.val;
    }

}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
