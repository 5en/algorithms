// https://leetcode.com/problems/queue-reconstruction-by-height/

package com.htyleo.algorithms;

public class QueueReconstructionByHeight {

    // O(N^2) time, O(N) space
    public int[][] reconstructQueue(int[][] people) {
        int N = people.length;
        if (N == 0) {
            return new int[0][2];
        }

        Person[] persons = new Person[N];
        for (int i = 0; i < N; i++) {
            persons[i] = new Person(people[i][0], people[i][1]);
        }
        boolean[] used = new boolean[N];

        int[][] result = new int[N][2];
        for (int i = 0; i < N; i++) {
            // find the one with k - tmpK == 0
            // if there is a tie, choose the one with smaller h
            int opt = -1;
            for (int j = 0; j < N; j++) {
                if (used[j]) {
                    continue;
                }

                Person p = persons[j];
                if (p.k - p.tmpK == 0 && (opt == -1 || p.h < persons[opt].h)) {
                    opt = j;
                }
            }

            result[i][0] = persons[opt].h;
            result[i][1] = persons[opt].k;
            used[opt] = true;

            // inc p.tmpk for those persons[opt].h >= p.h
            for (int j = 0; j < N; j++) {
                if (persons[opt].h >= persons[j].h) {
                    persons[j].tmpK++;
                }
            }
        }

        return result;
    }

    private static class Person {
        public final int h;
        public final int k;
        public int       tmpK = 0;

        public Person(int h, int k) {
            this.h = h;
            this.k = k;
        }
    }

}
