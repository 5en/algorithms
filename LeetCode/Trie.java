// https://leetcode.com/submissions/detail/31501740/

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");

import java.util.HashMap;
import java.util.Map;

public class Trie {
    private static class TrieNode {
        public final Map<Character, TrieNode> ch2Child = new HashMap<Character, TrieNode>();
        public boolean isWord = false;
    }
    
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        if (word == null || word.length() == 0) {
            return;
        }
        
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!curr.ch2Child.containsKey(ch)) {
                curr.ch2Child.put(ch, new TrieNode());
            }
            curr = curr.ch2Child.get(ch);
        }
        curr.isWord = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!curr.ch2Child.containsKey(ch)) {
                return false;
            }
            curr = curr.ch2Child.get(ch);
        }
        
        return curr.isWord;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            if (!curr.ch2Child.containsKey(ch)) {
                return false;
            }
            curr = curr.ch2Child.get(ch);
        }
        
        return true;
    }
}
