import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GBusCount {
    public static void main(String[] args) throws IOException {
        //Scanner sc = new Scanner(System.in);
        //Scanner sc = new Scanner(new File("B-small.in.txt"));
        Scanner sc = new Scanner(new File("B-large.in.txt"));
        //PrintWriter out = new PrintWriter(System.out);
        PrintWriter out = new PrintWriter("output.txt");
        
        int T = sc.nextInt();
        sc.nextLine();
        for (int t=1; t<=T; t++) {
            int N = sc.nextInt();
            
            List<Interval> intervals = new ArrayList<Interval>(N);
            for (int n=0; n<N; n++) {
                int low = sc.nextInt();
                int high = sc.nextInt();
                intervals.add(new Interval(low, high));
            }
            int P = sc.nextInt();
            int[] cities = new int[P];
            for (int p=0; p<P; p++) {
                cities[p] = sc.nextInt();
            }
            
            out.printf("Case #%d: %s\n", t, process(intervals, cities));
        }
        
        out.flush();
        out.close();
    }
    
    private static String process(List<Interval> intervals, int[] cities) {
        StringBuilder sb = new StringBuilder();
        
        for (int city : cities) {
            int count = 0;
            
            for (Interval interval : intervals) {
                if (city>=interval.low && city<=interval.high) {
                    count++;
                }
            }
            
            sb.append(count).append(' ');
        }
        
        sb.delete(sb.length()-1, sb.length());
        return sb.toString();
    }
    
    private static class Interval {
        public final int low;
        public final int high;
        
        public Interval(int low, int high) {
            this.low = low;
            this.high = high;
        }
    }
}
