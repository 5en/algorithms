package jp.co.wap.exam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import jp.co.wap.exam.lib.Interval;

public class Problem1 {
    // O(N*logN)
    public int getMaxIntervalOverlapCount(List<Interval> intervals) {
        if (intervals == null) {
            return 0;
        }
        
        switch (intervals.size()) {
            case 0:
                return 0;
            case 1:
                return 1;
            default:
                List<Point> points = new ArrayList<Point>();
                for (Interval interval : intervals) {
                    points.add(new Point(true, interval.getBeginMinuteUnit()));
                    points.add(new Point(false, interval.getEndMinuteUnit()));
                }
                Collections.sort(points, new Comparator<Point>(){
                    @Override
                    public int compare(Point p1, Point p2) {
                        if (p1.unit < p2.unit) {
                            return -1;
                        } else if (p1.unit > p2.unit) {
                            return 1;
                        } else if (p1.isBegin && !p2.isBegin) {
                            return -1;
                        } else if (!p1.isBegin && p2.isBegin) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }
                });

                int maxCount = 0;
                int currCount = 0;
                for (Point invervalPoint : points) {
                    if (invervalPoint.isBegin) {
                        currCount++;
                        if (currCount > maxCount) {
                            maxCount = currCount;
                        }
                    } else {
                        currCount--;
                    }
                }

                return maxCount;
        }
    }

    private static class Point {
        public final boolean isBegin;
        public final int unit;

        public Point(boolean isBegin, int unit) {
            this.isBegin = isBegin;
            this.unit = unit;
        }
    }
}
