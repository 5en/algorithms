//Given an infinite stream of characters and a list L of strings, create a function that calls an external API when a word in L is recognized during the processing of the stream.
//
//        Example:
//        L = ["ok","test","one","try","trying"]
//        stream = a,b,c,o,k,d,e,f,t,r,y,i,n,g.............
//
//        the call to external API (let's call it some function callAPI()) would be called when the 'k' is encountered, again when the 'y' is encountered, and again at 'g'.

package com.htyleo.algorithms;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class StreamWordDictionary {

    public static void main(String[] args) {
//        char[] chs = { 'a', 'b', 'c', 'o', 'k', 'd', 'e', 'f', 't', 'r', 'y', 'i', 'n', 'g' };
//        String[] dicts = new String[] { "ok", "test", "one", "try", "trying" };
//
//        char[] chs = { 't', 'e', 's', 't', 'e', 's', 't' };
//        String[] dicts = new String[] { "test" };
//
//        char[] chs = { 't', 't', 't', 'e', 't', 't', 't' };
//        String[] dicts = new String[] { "t", "tt" };

//        check(chs, dicts);
//        check2(chs, dicts);
    }

    // O(N * MAX_WORD_LEN * MAX_WORD_LEN)
    //
    // trie
    public static void check(char[] chs, String[] dicts) {
        Node root = constructTrie(dicts);

        int maxWordLen = 0;
        for (String word : dicts) {
            maxWordLen = Math.max(maxWordLen, word.length());
        }

        // for each chs[i] scan ast most maxWordLen strings: chs[i-maxWordLen+1...i], ..., chs[i-1...i], chs[i]
        for (int i = 0; i < chs.length; i++) {
            for (int len = 1; len <= maxWordLen && i-len+1 >= 0; len++) {
                // search in the trie
                Node curr = root;
                int ci;
                for (ci = i - len + 1; ci <= i; ci++) {
                    char ch = chs[ci];
                    if (!curr.childs.containsKey(ch)) {
                        break;
                    }
                    curr = curr.childs.get(ch);
                }

                if (ci == i + 1 && curr.isWord) {
                    System.out.println(String.format("stream[%d]=%s: %s", i, chs[i], curr.val));
                }
            }
        }
    }

    private static Node constructTrie(String[] dicts) {
        Node root = new Node("");
        for (String word : dicts) {
            Node curr = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (!curr.childs.containsKey(ch)) {
                    curr.childs.put(ch, new Node(curr.val + ch));
                }
                curr = curr.childs.get(ch);
            }

            // last node for this word
            curr.isWord = true;
        }

        return root;
    }

    private static class Node {
        String val;

        boolean isWord = false;

        Map<Character, Node> childs = new HashMap<Character, Node>();

        public Node(String val) {
            this.val = val;
        }
    }

    // O(N * M * MAX_WORD_LEN)
    //
    // M state machines
    public static void check2(char[] chs, String[] dicts) {
        int M = dicts.length;
        WordStateMachine[] sms = new WordStateMachine[M];
        for (int i = 0; i < M; i++) {
            sms[i] = new WordStateMachine(dicts[i]);
        }

        int N = chs.length;
        for (int i = 0; i < N; i++) {
            for (WordStateMachine sm : sms) {
                sm.check(chs[i], i);
            }
        }
    }

    private static class WordStateMachine {
        private String word;

        // the char that is required to proceed to the next state
        // s0
        // (proceedChars[0]) =>
        // s1
        // (proceedChars[1]) =>
        // s2
        // ...
        // s(n-1)
        // (proceedChars[n-1]) => reached
        private char[] proceedChars;

        // current states
        private Queue<Integer> states = new LinkedList<Integer>();

        private final int FINAL_STATE;

        public WordStateMachine(String word) {
            this.word = word;
            proceedChars = new char[word.length()];
            for (int i = 0; i < word.length(); i++) {
                proceedChars[i] = word.charAt(i);
            }

            // initial: s0
            states.add(0);

            FINAL_STATE = word.length();
        }

        public void check(char ch, int streamIndex) {
            Queue<Integer> nextStates = new LinkedList<Integer>();

            while (!states.isEmpty()) {
                int state = states.remove();
                if (state != 0 && ch == proceedChars[0]) {
                    nextStates.add(1);
                }

                if (ch != proceedChars[state]) {
                    continue;
                }

                state++;

                if (state == FINAL_STATE) {
                    System.out.println(String.format("stream[%d]=%s: %s", streamIndex, ch, word));
                    continue;
                }

                nextStates.add(state);
            }

            if (nextStates.isEmpty()) {
                nextStates.add(0);
            }

            states = nextStates;
        }
    }
}
