// https://leetcode.com/problems/merge-intervals/

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {
    public static class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }
    
    public static List<Interval> merge(List<Interval> intervals) {
        Collections.sort(intervals, new Comparator<Interval>(){
            @Override
            public int compare(Interval i1, Interval i2) {
                return i1.start < i2.start ? -1 : (i1.start == i2.start ? 0 : 1);
            }
        });
        
        List<Interval> result = new ArrayList<Interval>();
        for (Interval interval : intervals) {
            if (result.isEmpty()) {
                result.add(interval);
                continue;
            }
            
            Interval lastInterval = result.get(result.size()-1);
            if (lastInterval.end >= interval.start) {
                result.remove(result.size()-1);
                result.add(new Interval(lastInterval.start, Math.max(lastInterval.end, interval.end)));
            } else {
                result.add(new Interval(interval.start, interval.end));
            }
        }
        
        return result;
    }
}
