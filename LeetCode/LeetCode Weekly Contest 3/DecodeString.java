// https://leetcode.com/contest/3/problems/decode-string/

package com.htyleo.algorithms;

import java.util.Deque;
import java.util.LinkedList;

public class DecodeString {

    public static void main(String[] args) {
        System.out.println(new DecodeString().decodeString("3[bb]100[a]"));
        System.out.println(new DecodeString().decodeString("3[a]2[bc]"));
        System.out.println(new DecodeString().decodeString("3[a2[c]]"));
        System.out.println(new DecodeString().decodeString("2[abc]3[cd]ef"));
    }

    public String decodeString(String s) {
        Deque<String> stack = new LinkedList<String>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch != ']') {
                stack.push(ch + "");
                continue;
            }

            // meet ']'
            StringBuilder seqSb = new StringBuilder();
            while (true) {
                String token = stack.pop();
                if (!token.equals("[")) {
                    seqSb.insert(0, token);
                } else {
                    break;
                }
            }
            String seq = seqSb.toString();

            StringBuilder countSb = new StringBuilder();
            while (!stack.isEmpty()) {
                String token = stack.peek();
                if (token.charAt(0) >= '0' && token.charAt(0) <= '9') {
                    token = stack.pop();
                    countSb.insert(0, token);
                } else {
                    break;
                }
            }
            int count = Integer.parseInt(countSb.toString());

            for (int j = 1; j < count; j++) {
                seqSb.append(seq);
            }
            stack.push(seqSb.toString());
        }

        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.insert(0, stack.pop());
        }

        return result.toString();
    }

}
