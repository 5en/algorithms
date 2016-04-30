// Given a string and array of strings, find whether the array contains a string with one character difference from the given string.
// Array may contain string of different lengths.
//        Ex: Given string
//        banana
//        and array is
//        [bana, apple, banaba, bonanza, banamf]
//        and the outpost should be true as banana and banaba are one character difference.

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringOneCharDiff {

    public static void main(String[] args) {
        List<String> strs = Arrays.asList("bana", "apple", "banaba", "bonanza", "banamf");
        System.out.println(find(strs, "banana"));
    }

    public static boolean find(List<String> strs, String s) {
        Node root = constructTrie(strs);
        return search(root, s, 0, 0);
    }

    // O(N * L), L is the max string length
    private static Node constructTrie(List<String> strs) {
        Node root = new Node("");
        for (String s : strs) {
            Node curr = root;
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (!curr.childs.containsKey(ch)) {
                    curr.childs.put(ch, new Node(curr.val + ch));
                }
                curr = curr.childs.get(ch);
            }
            curr.isLeaf = true;
        }

        return root;
    }

    private static boolean search(Node root, String s, int i, int misMatchCount) {
        if (misMatchCount >= 2) {
            return false;
        }

        if (i == s.length()) {
            return root.isLeaf && misMatchCount == 1;
        }

        char ch = s.charAt(i);
        for (char key : root.childs.keySet()) {
            if (search(root.childs.get(key), s, i + 1, key == ch ? misMatchCount
                : misMatchCount + 1)) {
                return true;
            }
        }

        return false;
    }

    private static class Node {
        String               val;
        Map<Character, Node> childs = new HashMap<Character, Node>();
        boolean              isLeaf;

        public Node(String val) {
            this.val = val;
        }
    }
}
