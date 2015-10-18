import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GFiles {
    public static void main(String[] args) throws IOException {
        //        Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(new File("B-small.in.txt"));
        //        Scanner sc = new Scanner(new File("B-large.in.txt"));
        //        PrintWriter out = new PrintWriter(System.out);
        PrintWriter out = new PrintWriter("B-output.txt");

        int T = sc.nextInt();
        sc.nextLine();
        for (int t = 1; t <= T; t++) {
            int N = sc.nextInt();
            int[] Ps = new int[N];
            long[] Ks = new long[N];
            for (int n = 0; n < N; n++) {
                Ps[n] = sc.nextInt();
                Ks[n] = sc.nextLong();
            }

            out.printf("Case #%d: %d\n", t, process(N, Ps, Ks));
        }

        out.flush();
        out.close();
    }

    private static long process(int N, int[] Ps, long[] Ks) {
        List<Interval> intervals = new ArrayList<Interval>(N);
        for (int n = 0; n < N; n++) {
            int P = Ps[n];
            long K = Ks[n];
            long start;
            long end;

            switch (P) {
                case 0:
                    start = (long) Math.floor(K / 0.01);
                    end = Long.MAX_VALUE; // unlimited
                    break;
                case 100:
                    return K;
                default:
                    start = (long) Math.floor(K / (((double) P + 1) / 100) + 1);
                    end = (long) Math.floor(K / (((double) P) / 100));
                    break;
            }

            intervals.add(new Interval(start, end));
        }

        Interval result = intersect(intervals);

        return result.start == result.end ? result.start : -1;
    }

    private static Interval intersect(List<Interval> intervals) {
        if (intervals.size() == 1) {
            return intervals.get(0);
        }

        Interval result = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            Interval interval = intervals.get(i);
            long start = Math.max(result.start, interval.start);
            long end = result.start == -1 ? interval.start : (interval.start == -1 ? result.start
                : Math.min(result.end, interval.end));

            result.start = start;
            result.end = end;
        }

        return result;
    }

    private static class Interval {
        public long start;
        public long end;

        public Interval(long start, long end) {
            this.start = start;
            this.end = end;
        }
    }
}
