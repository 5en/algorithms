// Given a list of tuples (in, out), 
// ‘in’ represents the time a person enters a office and ‘out’ represents the time leaves the office, 
// computes the longest busy time of this office.
//        For example,
//        A in:5 out:10
//        C in:12 out: 15
//        D in: 25 out:30
//        B in:7 out:13
//        return [5,15]

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LongestBusyTime {

    public static void main(String[] args) {
        Record[] records = new Record[4];
        records[0] = new Record(5, 10);
        records[1] = new Record(12, 15);
        records[2] = new Record(25, 30);
        records[3] = new Record(7, 13);

        int[] result = longestBusyTime(records);
        System.out.println(result[0] + " " + result[1]);

        result = longestBusyTime2(records);
        System.out.println(result[0] + " " + result[1]);
    }

    public static int[] longestBusyTime(Record[] records) {
        List<Action> actions = new ArrayList<Action>(records.length * 2);
        for (Record record : records) {
            actions.add(new Action(record.in, true));
            actions.add(new Action(record.out, false));
        }

        Collections.sort(actions, new Comparator<Action>() {
            @Override
            public int compare(Action a1, Action a2) {
                if (a1.time < a2.time) {
                    return -1;
                } else if (a1.time > a2.time) {
                    return 1;
                } else {
                    return a1.isIn ? -1 : 1;
                }
            }
        });

        int lbt = 0;
        int[] result = new int[2];

        int begin = 0;
        int pplCount = 0;
        for (Action action : actions) {
            if (pplCount == 0) {
                begin = action.time;
                pplCount++;
                continue;
            }

            if (action.isIn) {
                pplCount++;
            } else {
                pplCount--;
                if (pplCount == 0 && (action.time - begin > lbt)) {
                    lbt = action.time - begin;
                    result[0] = begin;
                    result[1] = action.time;
                }
            }
        }

        return result;
    }

    private static class Action {
        int     time;
        boolean isIn;

        public Action(int time, boolean isIn) {
            this.time = time;
            this.isIn = isIn;
        }
    }

    public static int[] longestBusyTime2(Record[] records) {
        Arrays.sort(records, new Comparator<Record>() {
            @Override
            public int compare(Record r1, Record r2) {
                if (r1.in < r2.in) {
                    return -1;
                } else if (r1.in > r2.in) {
                    return 1;
                } else {
                    return r1.out < r2.out ? -1 : (r1.out > r2.out ? 1 : 0);
                }
            }
        });

        List<Record> merged = new ArrayList<Record>();
        for (Record record : records) {
            if (merged.isEmpty()) {
                merged.add(record);
                continue;
            }
            Record last = merged.get(merged.size() - 1);

            if (record.in > last.out) {
                merged.add(record);
            } else {
                Record newRecord = new Record(last.in, Math.max(last.out, record.out));
                merged.remove(merged.size() - 1);
                merged.add(newRecord);
            }
        }

        int lbt = 0;
        int[] result = new int[2];
        for (Record record : merged) {
            if (record.out - record.in > lbt) {
                lbt = record.out - record.in;
                result[0] = record.in;
                result[1] = record.out;
            }
        }

        return result;
    }

    private static class Record {
        int in;
        int out;

        public Record(int in, int out) {
            this.in = in;
            this.out = out;
        }
    }
}
