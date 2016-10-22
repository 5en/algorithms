package com.htyleo.algorithms;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
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

    // order by score
    // no same hostId in the same page
    public static String[] paginate(int num, String[] results) {
        int N = results.length;
        if (N == 0) {
            return new String[0];
        }

        List<Entry> entries = new LinkedList<>();
        for (String result : results) {
            String[] items = result.split(",");
            entries.add(new Entry(Integer.parseInt(items[0]), result));
        }

        String[] paginatedResults = new String[results.length + (results.length - 1) / num];

        Set<Integer> hostIds = new HashSet<>(num);
        boolean reachEnd = false;
        for (int ri = 0, count = 0; !entries.isEmpty();) {
            for (ListIterator<Entry> it = entries.listIterator(); it.hasNext();) {
                Entry entry = it.next();

                if (reachEnd) {
                    paginatedResults[ri++] = entry.result;
                    if ((++count % num == 0) && ri < paginatedResults.length) {
                        paginatedResults[ri++] = "";
                    }
                    it.remove();
                    continue;
                }

                if (!hostIds.contains(entry.hostId)) {
                    hostIds.add(entry.hostId);
                    paginatedResults[ri++] = entry.result;
                    if ((++count % num == 0) && ri < paginatedResults.length) {
                        paginatedResults[ri++] = "";
                    }
                    it.remove();
                }

                if (!it.hasNext()) {
                    reachEnd = true;
                }

                if (hostIds.size() == num) {
                    hostIds.clear();
                    break;
                }
            }
        }

        return paginatedResults;
    }

    public static class Entry {
        public final int    hostId;
        public final String result;

        public Entry(int hostId, String result) {
            this.hostId = hostId;
            this.result = result;
        }
    }

}
