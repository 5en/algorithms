package jp.co.wap.exam;

import java.util.ArrayList;
import java.util.List;

import jp.co.wap.exam.lib.Interval;

public class Test {

    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<Interval>();
        intervals.add(new Interval("06:00", "08:30"));
        intervals.add(new Interval("08:00", "09:00"));
        intervals.add(new Interval("09:00", "11:00"));
        intervals.add(new Interval("09:00", "11:30"));
        intervals.add(new Interval("10:30", "14:00"));
        intervals.add(new Interval("12:30", "14:00"));

        Problem1 problem1 = new Problem1();
        System.out.println(problem1.getMaxIntervalOverlapCount(intervals));

        Problem2 problem2 = new Problem2();
        System.out.println(problem2.getMaxWorkingTime(intervals));

        Problem3 problem3 = new Problem3();
        System.out.println(problem3.getMaxNumWorks(intervals));
    }

}
