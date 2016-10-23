// https://leetcode.com/problems/lru-cache/

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private final int          capacity;
    private Map<Integer, Node> key2Node = new HashMap<>();
    private Node               head;
    private Node               tail;

    private static class Node {
        int  key;
        int  value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!key2Node.containsKey(key)) {
            return -1;
        }

        Node node = key2Node.get(key);
        removeNode(node);
        addToHead(node);

        return node.value;
    }

    public void set(int key, int value) {
        Node node = key2Node.get(key);
        if (node != null) {
            // the node corresponding to this key exists
            node.value = value;
            removeNode(node);
            addToHead(node);
            return;
        }

        // the node corresponding to this key does not exist
        if (key2Node.size() == capacity) {
            removeNode(tail);
        }
        node = new Node(key, value);
        addToHead(node);
    }

    private void removeNode(Node node) {
        if (node == null) {
            return;
        }

        Node prevNode = node.prev;
        Node nextNode = node.next;

        if (prevNode != null) {
            prevNode.next = nextNode;
        } else {
            // prevNode == null => node is the head
            head = node.next;
        }

        if (nextNode != null) {
            nextNode.prev = prevNode;
        } else {
            // nextNode == null => node is the tail
            tail = node.prev;
        }

        key2Node.remove(node.key);
    }

    private void addToHead(Node node) {
        if (head != null) {
            head.prev = node;
        } else {
            // head == null => empty
            tail = node;
        }

        node.prev = null;
        node.next = head;
        head = node;

        key2Node.put(node.key, node);
    }

}
