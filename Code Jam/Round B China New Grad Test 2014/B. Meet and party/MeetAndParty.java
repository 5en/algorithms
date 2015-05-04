import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MeetAndParty {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        //Scanner sc = new Scanner(new File("B-small-practice.in.txt"));
        //Scanner sc = new Scanner(new File("B-large-practice.in.txt"));
        PrintWriter out = new PrintWriter(System.out);
        //PrintWriter out = new PrintWriter("output.txt");
        
        int T = sc.nextInt();
        for (int t=1; t<=T; t++) {
            System.out.println(t);
            
            int B = sc.nextInt();
            List<Rec> recs = new ArrayList<Rec>(B);
            for (int i=0; i<B; i++) {
                long x1 = sc.nextLong();
                long y1 = sc.nextLong();
                long x2 = sc.nextLong();
                long y2 = sc.nextLong();
                recs.add(new Rec(x1, y1, x2, y2));
            }
            
            out.printf("Case #%d: %s\n", t, process(recs));
        }
        
        out.flush();
        out.close();
    }
    
    private static String process(List<Rec> recs) {
        List<Person> persons = new LinkedList<Person>();
        for (Rec rec : recs) {
            for (long x=rec.x1; x<=rec.x2; x++) {
                for (long y=rec.y1; y<=rec.y2; y++) {
                    persons.add(new Person(x, y));
                }
            }
        }
        
        long opt_x = -1;
        long opt_y = -1;
        long opt_dist = Long.MAX_VALUE;
        
        for (Person p : persons) {
            long tmp_dist = 0;
            for (Rec rec : recs) {
                tmp_dist += rec.totalDist(p);
                if (tmp_dist > opt_dist) {
                    break;
                }
            }
            
            if (tmp_dist < opt_dist) {
                opt_dist = tmp_dist;
                opt_x = p.x;
                opt_y = p.y;
            }
            else if (tmp_dist == opt_dist) {
                if (p.x < opt_x) {
                    opt_x = p.x;
                    opt_y = p.y;
                }
                else if (p.x == opt_x) {
                    if (p.y < opt_y) {
                        opt_y = p.y;
                    }
                }
            }
        }
        
        return String.format("%d %d %d", opt_x, opt_y, opt_dist);
    }
}

class Person {
    public long x;
    public long y;
    
    public Person(long x, long y) {
        this.x = x;
        this.y = y;
    }
    
    public static long dist(Person p1, Person p2) {
        return Math.abs(p1.x-p2.x) + Math.abs(p1.y-p2.y);
    }
}

class Rec {
    public long x1;
    public long y1;
    public long x2;
    public long y2;
    public long N;
    public double centroid_x;
    public double centroid_y;
    
    public Rec(long x1, long y1, long x2, long y2) {
        if (x1 > x2) {
            throw new RuntimeException("x1 > x2");
        }
        
        if (y1 > y2) {
            throw new RuntimeException("y1 > y2");
        }
        
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        
        this.N = (x2-x1+1)*(y2-y1+1);
        
        this.centroid_x = ((double)(x1+x2)) / 2;
        this.centroid_y = ((double)(y1+y2)) / 2;
    }
    
    public long totalDist(Person p) {
        long num_row = y2-y1+1;
        long num_col = x2-x1+1;
        
        long total_dist = 0;
        
        // horizontal dist
        long tmp = 0;
        if (p.x<=x1 || p.x>=x2) {
            tmp = (Math.abs(x1-p.x) + Math.abs(x2-p.x)) * num_col / 2;
        }
        else {
            tmp = (p.x-x1 + 1) * (p.x - x1) / 2 + (1 + x2-p.x) * (x2 - p.x) / 2; 
        }
        total_dist += num_row * tmp;
        
        // vertical dist
        tmp = 0;
        if (p.y<=y1 || p.y>=y2) {
            tmp = (Math.abs(y1-p.y) + Math.abs(y2-p.y)) * num_row / 2;
        }
        else {
            tmp = (p.y-y1 + 1) * (p.y - y1) / 2 + (1 + y2-p.y) * (y2 - p.y) / 2; 
        }
        total_dist += num_col * tmp;
        
        return total_dist;
    }
}


