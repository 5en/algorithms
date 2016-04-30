package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {
    public static void main(String[] args) {
        Interval[] intervals = new Interval[5];
        intervals[0] = new Interval(2, 10);
        intervals[1] = new Interval(0, 1);
        intervals[2] = new Interval(1, 2);
        intervals[3] = new Interval(5, 8);
        intervals[4] = new Interval(11, 18);
        System.out.println(merge(intervals));
    }

    public static List<Interval> merge(Interval[] intervals) {
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval i1, Interval i2) {
                if (i1.left < i2.left) {
                    return -1;
                } else if (i1.left > i2.left) {
                    return 1;
                } else {
                    return i1.right < i2.right ? -1 : (i1.right > i2.right ? 1 : 0);
                }
            }
        });

        List<Interval> merged = new ArrayList<Interval>();
        for (Interval interval : intervals) {
            if (merged.isEmpty()) {
                merged.add(interval);
                continue;
            }
            Interval last = merged.get(merged.size() - 1);

            if (interval.left > last.right) {
                // [i1.left, i1.right]
                //			[i2.left, i2.right]
                merged.add(interval);
            } else {
                // [i1.left, i1.right]
                //	    [i2.left, i2.right]
                Interval newInterval = new Interval(last.left, Math.max(last.right, interval.right));
                merged.remove(merged.size() - 1);
                merged.add(newInterval);
            }
        }

        return merged;
    }

    private static class Interval {
        int left;
        int right;

        public Interval(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return String.format("[%d, %d]", left, right);
        }
    }
}
