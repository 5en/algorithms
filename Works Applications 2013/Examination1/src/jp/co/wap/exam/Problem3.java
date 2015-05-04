// Introduction To Algorithms 16.1

package jp.co.wap.exam;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import jp.co.wap.exam.lib.Interval;

public class Problem3 {
    // O(N*logN), greedy
    public int getMaxNumWorks(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) {
            return 0;
        } 
        
        // sort in order of end time
        Collections.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval thisInterval, Interval otherInterval) {
                return thisInterval.getEndMinuteUnit() - otherInterval.getEndMinuteUnit();
            }
        });

        int count = 1;
        int k = 0; // last selected interval index
        for (int i = 1; i < intervals.size(); i++) {
            if (intervals.get(i).getBeginMinuteUnit() > intervals.get(k).getEndMinuteUnit()) {
                count++;
                k = i;
            }
        }

        return count;
    }
}
