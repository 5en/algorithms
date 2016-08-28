// https://leetcode.com/contest/1/problems/longest-absolute-file-path/

package com.htyleo.algorithms;

import java.util.HashMap;
import java.util.Map;

public class LongestAbsoluteFilePath {

    // O(N) time
    public int lengthLongestPath(String input) {
        int maxLen = 0;
        Map<Integer, Integer> cumuLens = new HashMap<Integer, Integer>();

        StringParser sp = new StringParser(input);
        Element element = null;
        while ((element = sp.next()) != null) {
            int level = element.level;
            int len = element.name.length();
            if (element.isFile) {
                if (level == 0) {
                    maxLen = Math.max(maxLen, len);
                } else {
                    maxLen = Math.max(maxLen, cumuLens.get(level - 1) + len);
                }
            } else {
                // dir, len + 1 because of '/'
                if (level == 0) {
                    cumuLens.put(0, len + 1);
                } else {
                    cumuLens.put(level, cumuLens.get(level - 1) + len + 1);
                }
            }
        }

        return maxLen;
    }

    private static class StringParser {
        private final String input;

        private int          startIdx = 0;

        public StringParser(String input) {
            this.input = input;
        }

        public Element next() {
            if (startIdx == input.length()) {
                return null;
            }

            int level = 0;
            if (startIdx != 0) {
                // skip \n, level = # of \t
                startIdx++;
                while (input.charAt(startIdx) == '\t') {
                    level++;
                    startIdx++;
                }
            }
            int endIdx = input.indexOf("\n", startIdx);
            if (endIdx == -1) {
                endIdx = input.length();
            }

            String name = input.substring(startIdx, endIdx);
            boolean isFile = name.contains(".");

            startIdx = endIdx;

            return new Element(name, level, isFile);
        }
    }

    private static class Element {
        public final String  name;
        public final int     level;
        public final boolean isFile;

        public Element(String name, int level, boolean isFile) {
            this.name = name;
            this.level = level;
            this.isFile = isFile;
        }
    }
}
