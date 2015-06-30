// https://leetcode.com/problems/add-and-search-word-data-structure-design/

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");

import java.util.HashMap;
import java.util.Map;

public class AddAndSearchWord {
    TrieNode root = new TrieNode();

    // Adds a word into the data structure.
    public void addWord(String word) {
        root.insert(word);
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return root.search(word, 0);
    }

    private static class TrieNode {
        private Map<Character, TrieNode> ch2Child = new HashMap<Character, TrieNode>();
        private boolean isWord = false;

        // Inserts a word into the trie.
        public void insert(String word) {
            if (word == null || word.length() == 0) {
                return;
            }

            TrieNode curr = this;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (!curr.ch2Child.containsKey(ch)) {
                    curr.ch2Child.put(ch, new TrieNode());
                }
                curr = curr.ch2Child.get(ch);
            }
            curr.isWord = true;
        }

        // Returns if the (regex) word is in the trie.
        public boolean search(String word, int start) {
            if (start >= word.length()) {
                return isWord;
            }

            char ch = word.charAt(start);
            if (ch != '.') {
                if (!ch2Child.containsKey(ch)) {
                    return false;
                }
                return ch2Child.get(ch).search(word, start+1);
            }

            // ch == '.'
            for (TrieNode node : ch2Child.values()) {
                if (node.search(word, start+1)) {
                    return true;
                }
            }
            return false;
        }
    }
}
