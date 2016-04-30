// We have a long string. We label some substrings with tags.
// A tag entry is [startIndex, endIndex, tag].
//        - Query: 1 or more tags
//        - Output: all blocks/ranges with all queried tags.
//
//        Example tag entries:
//        [23, 72, 0]    // label [23, 72) with tag 0
//        [34, 53, 1]    // label [34, 53) with tag 1
//        [100, 128, 0]
//
//        Query and Output:
//        0 => [23, 72], [100, 128]
//        0,1 => [34,53] // [34, 53) matches both tag 0 and 1

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class IntervalIntersection {

    public static void main(String[] args) {
        // [23, 72, 0]
        // [100, 128, 0]
        // [34, 53, 1]
        List<Interval> intervals = new ArrayList<Interval>();
        intervals.add(new Interval(23, 72, 0));
        intervals.add(new Interval(100, 128, 0));
        intervals.add(new Interval(34, 53, 1));
        Set<Integer> tags = new HashSet<Integer>(Arrays.asList(new Integer[] { 0, 1 }));
        System.out.println(calculateIntervals(intervals, tags));

        // [23, 72, 0]
        // [72, 128, 0]
        // [34, 53, 1]
        intervals = new ArrayList<Interval>();
        intervals.add(new Interval(23, 72, 0));
        intervals.add(new Interval(72, 128, 0));
        intervals.add(new Interval(34, 53, 1));
        tags = new HashSet<Integer>(Arrays.asList(new Integer[] { 0 }));
        System.out.println(calculateIntervals(intervals, tags));

        // [10, 30, 0]
        // [25, 40, 0]
        // [50, 100, 0]
        // [20, 80, 1]
        intervals = new ArrayList<Interval>();
        intervals.add(new Interval(10, 30, 0));
        intervals.add(new Interval(25, 40, 0));
        intervals.add(new Interval(50, 100, 0));
        intervals.add(new Interval(20, 80, 1));
        tags = new HashSet<Integer>(Arrays.asList(new Integer[] { 0, 1 }));
        System.out.println(calculateIntervals(intervals, tags));
    }

    private static class Interval {
        int s;
        int e;
        int tag;

        Interval(int s, int e, int tag) {
            this.s = s;
            this.e = e;
            this.tag = tag;
        }

        Interval(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public String toString() {
            return String.format("[%s, %s)", s, e);
        }
    }

    public static List<Interval> calculateIntervals(List<Interval> intervals, Set<Integer> tags) {
        List<Interval> result = new ArrayList<Interval>();

        List<Point> sortedPoints = new ArrayList<Point>(intervals.size() * 2);
        for (Interval interval : intervals) {
            sortedPoints.add(new Point(interval.s, true, interval.tag));
            sortedPoints.add(new Point(interval.e, false, interval.tag));
        }
        Collections.sort(sortedPoints);

        Map<Integer, Integer> tagCount = new HashMap<Integer, Integer>();
        int start = 0;
        for (Point point : sortedPoints) {
            if (!tags.contains(point.tag)) {
                continue;
            }

            if (point.isStart) {
                if (!tagCount.containsKey(point.tag)) {
                    tagCount.put(point.tag, 0);
                }
                tagCount.put(point.tag, tagCount.get(point.tag) + 1);

                if (tagCount.size() == tags.size() && tagCount.get(point.tag) == 1) {
                    // enter the common part of all tags
                    start = point.val;
                }
            } else {
                tagCount.put(point.tag, tagCount.get(point.tag) - 1);
                if (tagCount.get(point.tag) == 0) {
                    tagCount.remove(point.tag);
                }

                if (tagCount.size() == tags.size() - 1 && !tagCount.containsKey(point.tag)) {
                    // exit the common part of all tags
                    result.add(new Interval(start, point.val));
                }
            }
        }

        return result;
    }

    private static class Point implements Comparable<Point> {
        int     val;
        boolean isStart;
        int     tag;

        Point(int val, boolean isStart, int flag) {
            this.val = val;
            this.isStart = isStart;
            this.tag = flag;
        }

        @Override
        public int compareTo(Point p) {
            return val < p.val ? -1 : (val > p.val ? 1 : (isStart ? -1 : 1));
        }
    }
}
