import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CubeIV {
    public static void main(String[] args) throws IOException {
        //Scanner sc = new Scanner(System.in);
        //Scanner sc = new Scanner(new File("A-small.in.txt"));
        Scanner sc = new Scanner(new File("A-large.in.txt"));
        //PrintWriter out = new PrintWriter(System.out);
        PrintWriter out = new PrintWriter("output.txt");
        
        int T = sc.nextInt();
        sc.nextLine();
        for (int t=1; t<=T; t++) {
            int S = sc.nextInt();
            int[][] m = new int[S][S];
            for (int x=0; x<S; x++) {
                for (int y=0; y<S; y++) {
                    m[x][y] = sc.nextInt();
                }
            }
            
            out.printf("Case #%d: %s\n", t, process(S, m));
        }
        
        out.flush();
        out.close();
    }
    
    private static String process(int S, int[][] m) {
        int minId = 1;
        int maxSteps = 1;
        
        for (int x=0; x<S; x++) {
            for (int y=0; y<S; y++) {
                Person p = new Person(m[x][y], x, y);
                
                if (maxSteps==S*S-1 && p.id>minId) {
                    continue;
                }
                
                while(true) {
                    boolean progress = false;
                    
                    for (int x_inc = -1; x_inc <= 1; x_inc++) {
                        for (int y_inc = -1; y_inc <= 1; y_inc++) {
                            if (x_inc != 0 && y_inc != 0) {
                                continue;
                            }

                            if (x_inc == 0 && y_inc == 0) {
                                continue;
                            }

                            if (p.x + x_inc < 0 || p.x + x_inc >= S || p.y + y_inc < 0 || p.y + y_inc >= S) {
                                continue;
                            }

                            if (m[p.x+x_inc][p.y+y_inc] == p.roomId+1) {
                                p.x += x_inc;
                                p.y += y_inc;
                                p.roomId++;
                                p.steps++;
                                progress = true;
                                break;
                            }
                        }
                        
                        if (progress) {
                            break;
                        }
                    }
                    
                    if (!progress) {
                        break;
                    }
                }
                
                if (p.steps > maxSteps || (p.steps==maxSteps && p.id < minId)) {
                    minId = p.id;
                    maxSteps = p.steps;
                }
            }
        }
        
        return String.format("%d %d", minId, maxSteps);
    }
    
    private static class Person {
        public final int id;
        public int x;
        public int y;
        public int steps = 1;
        public int roomId;
        
        public Person(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.roomId = id;
        }
    }
}
