package com.htyleo.algorithms;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Paginator {

    public static void main(String[] args) {
        String[] results = { "1,28,300.6,San Francisco", "4,5,209.1,San Francisco",
                "20,7,203.4,Oakland", "6,8,202.9,San Francisco", "6,10,199.8,San Francisco",
                "1,16,190.5,San Francisco", "6,29,185.3,San Francisco", "7,20,180.0,Oakland",
                "6,21,162.2,San Francisco", "2,18,161.7,San Jose", "2,30,149.8,San Jose",
                "3,76,146.7,San Francisco", "2,14,141.8,San Jose" };
        System.out.println(Arrays.toString(paginate(5, results)));
    }

    public static String[] paginate(int num, String[] results) {
        int N = results.length;
        if (N == 0) {
            return new String[0];
        }

        Entry[] entries = new Entry[N];
        for (int i = 0; i < results.length; i++) {
            String result = results[i];
            String[] items = result.split(",");
            entries[i] = new Entry(Integer.parseInt(items[0]), Float.parseFloat(items[2]), result);
        }

        String[] paginatedResults = new String[results.length + (results.length - 1) / num];

        boolean[] used = new boolean[N];
        Set<Integer> hostIds = new HashSet<>(num);
        boolean scanned = false;
        int entryCount = 0;
        for (int curr = 0, ri = 0; ri < paginatedResults.length; curr++) {
            if (curr == N) {
                scanned = true;
                curr = 0;
            }

            if (used[curr]) {
                curr++;
                continue;
            }

            Entry entry = entries[curr];

            if (scanned) {
                paginatedResults[ri++] = entry.result;

                entryCount++;
                if (entryCount % num == 0 && entryCount != N) {
                    paginatedResults[ri++] = "";
                }

            } else {
                paginatedResults[ri++] = entry.result;
                used[curr] = true;

                entryCount++;
                if (entryCount % num == 0 && entryCount != N) {
                    paginatedResults[ri++] = "";
                }

                hostIds.add(entry.hostId);
                if (hostIds.size() == num) {
                    hostIds.clear();
                    curr = 0;
                }
            }
        }

        return paginatedResults;
    }

    public static class Entry {
        public final int    hostId;
        public final float  score;
        public final String result;

        public Entry(int hostId, float score, String result) {
            this.hostId = hostId;
            this.score = score;
            this.result = result;
        }
    }

}
