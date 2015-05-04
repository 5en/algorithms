import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Q2_19_Overlap {
    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<Interval>();
        intervals.add(new Interval(2, 3));
        intervals.add(new Interval(3, 9));
        intervals.add(new Interval(1, 2));
        intervals.add(new Interval(11, 12));

        List<Interval> combined = preprocess(intervals);
        System.out.println(combined);

        System.out.println(find(combined, new Interval(1, 6)));
        System.out.println(find(combined, new Interval(0, 2)));
        System.out.println(find(combined, new Interval(9, 9)));
    }

    // O(N*logN)
    public static List<Interval> preprocess(List<Interval> intervals) {
        Collections.sort(intervals);

        List<Interval> combined = new ArrayList<Interval>();
        for (Interval interval : intervals) {
            if (combined.isEmpty()) {
                combined.add(interval);
            } else {
                Interval lastInterval = combined.get(combined.size()-1);
                if (interval.begin > lastInterval.end) {
                    combined.add(interval);
                } else if (interval.end > lastInterval.end) {
                    combined.remove(combined.size()-1);
                    combined.add(new Interval(lastInterval.begin, interval.end));
                }
            }
        }

        return combined;
    }

    // O(logN)
    public static boolean find(List<Interval> combined, Interval query) {
        int index = Collections.binarySearch(combined, query);

        if (index >= 0) {
            return query.end <= combined.get(index).end ;
        } else {
            index = (-index - 1) - 1;
            if (index < 0) {
                return false;
            } else {
                return query.end <= combined.get(index).end;
            }
        }
    }

    private static class Interval implements Comparable<Interval> {
        public final int begin;
        public final int end;

        public Interval(int begin, int end) {
            this.begin = begin;
            this.end = end;
        }

        @Override
        public int compareTo(Interval o) {
            return begin < o.begin ? -1 : (begin == o.begin ? 0 : 1);
        }

        @Override
        public String toString() {
            return new StringBuilder().append('[').append(begin).append(',').append(end).append(']').toString();
        }
    }
}
