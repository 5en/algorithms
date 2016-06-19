// http://stackoverflow.com/questions/19850580/maximum-non-overlapping-intervals-in-a-interval-tree

package jp.co.wap.exam;

import java.util.List;
import java.util.Collections;
import java.util.Comparator;

import jp.co.wap.exam.lib.Interval;

public class Problem2 {
    public int getMaxWorkingTime(List<Interval> intervals) {
        int result = 0;

        if (intervals == null) {
            result = 0;
        } else {
            int N = intervals.size();
            switch (N) {
                case 0:
                    result = 0;
                    break;
                case 1:
                    result = intervals.get(0).getIntervalMinute();
                    break;
                default:
                    // sort in order of begin time
                    Collections.sort(intervals, new Comparator<Interval>() {
                        public int compare(Interval i1, Interval i2) {
                            return i1.getBeginMinuteUnit() - i2.getBeginMinuteUnit();
                        }
                    });
                    int[] begin = new int[N];
                    int[] end = new int[N];
                    int[] period = new int[N];
                    for (int i = 0; i < N; i++) {
                        Interval tmpInterval = intervals.get(i);
                        begin[i] = tmpInterval.getBeginMinuteUnit();
                        end[i] = tmpInterval.getEndMinuteUnit();
                        period[i] = tmpInterval.getIntervalMinute();
                    }

                    // for each interval i, find its next one j (j has smallest begin such that j.begin>i.end). if none exists, j=-1;
                    int[] next = new int[N];
                    for (int i = 0; i < N - 1; i++) {
                        next[i] = findNext(end[i], begin, i + 1, N - 1); // find in range begin[(i+1)...(N-1)];
                    }
                    next[N - 1] = -1;

                    // results[i] is max working time for intervals i, i+1, i+2, ..., N-1
                    // results[i] = max{choosing interval i, not choosing interval i} = max{period[i]+results[next[i]], results[i+1]}
                    int[] results = new int[N];
                    results[N - 1] = period[N - 1];
                    for (int i = N - 2; i >= 0; i--) {
                        int maxNextPeriod = 0;
                        if (next[i] != -1) {
                            maxNextPeriod = results[next[i]];
                        }
                        results[i] = Math.max(period[i] + maxNextPeriod, results[i + 1]);
                    }
                    result = results[0];
                    break;
            }
        }

        return result;
    }

    private int findNext(int endTime, int[] begin, int left, int right) {
        int limit = right;
        
        while (left <= right) {
            int mid = (left + right) / 2;
            if (begin[mid] > endTime) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        if (left <= limit) {
            return left;
        } else {
            return -1;
        }
    }
}
