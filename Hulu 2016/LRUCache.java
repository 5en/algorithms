package com.htyleo.algorithms;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    Map<Integer, Node> pageNum2Node = new HashMap<Integer, Node>();
    Node               head;
    Node               tail;
    final int          CAPACITY     = 100;

    public Page get(int pageNum) {
        if (pageNum2Node.containsKey(pageNum)) {
            Node curr = pageNum2Node.get(pageNum);

            // remove curr
            Node currPrev = curr.prev;
            Node currNext = curr.next;
            if (currPrev != null) {
                currPrev.next = currNext;
            }
            if (currNext != null) {
                currNext.prev = currPrev;
            }

            // add node to head
            addToHead(curr);

            return curr.page;
        }

        if (pageNum2Node.size() == CAPACITY) {
            Node tailPrev = tail.prev;
            tailPrev.next = null;

            tail.prev = null;
            pageNum2Node.remove(tail.pageNum);
        }

        Page page = getPageFromMem(pageNum);
        Node newNode = new Node(pageNum, page);
        addToHead(newNode);

        return page;
    }

    private void addToHead(Node curr) {
        // add curr to head
        curr.prev = null;
        curr.next = head;

        if (head != null) {
            head.prev = curr;
        } else {
            tail = curr;
        }
        head = curr;
    }

    private Page getPageFromMem(int pageNum) {
        return null;
    }
}

class Node {
    int  pageNum;
    Page page;
    Node prev;
    Node next;

    public Node(int pageNum, Page page) {
        this.pageNum = pageNum;
        this.page = page;
    }
}

class Page {
}
