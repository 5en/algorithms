import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SudokuChecker {
    public static void main(String[] args) throws IOException {
        //Scanner sc = new Scanner(System.in);
        //Scanner sc = new Scanner(new File("A-small-practice.in.txt"));
        Scanner sc = new Scanner(new File("A-large-practice.in.txt"));
        //PrintWriter out = new PrintWriter(System.out);
        PrintWriter out = new PrintWriter("output.txt");
        
        int T = sc.nextInt();
        for (int t=1; t<=T; t++) {
            int N = sc.nextInt();
            int NN = N*N;
            int[][] m = new int[NN][NN];
            for (int i=0; i<NN; i++) {
                for (int j=0; j<NN; j++) {
                    m[i][j] = sc.nextInt();
                }
            }
            
            out.printf("Case #%d: %s\n", t, check(m, N));
        }
        
        out.flush();
        out.close();
    }
    
    private static String check(int[][] m, int N) {
        int NN = m.length;
        
        for (int row=0; row<NN; row++) {
            if (!checkRow(m, row)) {
                return "No";
            }
        }
        
        for (int col=0; col<NN; col++) {
            if (!checkCol(m, col)) {
                return "No";
            }
        }
        
        for (int row=0; row<=NN-N; row+=N) {
            for (int col=0; col<=NN-N; col+=N) {
                if (!checkGrid(m, N, row, col)) {
                    return "No";
                }
            }
        }
        
        return "Yes";
    }
    
    private static boolean checkRow(int[][] m, int row) {
        int NN = m.length;
        
        Set<Integer> all = new HashSet<Integer>(NN);
        for (int i=1; i<=NN; i++) {
            all.add(i);
        }
        
        for (int col=0; col<NN; col++) {
            if (!all.contains(m[row][col])) {
                return false;
            }
            all.remove(m[row][col]);
        }
        
        if (all.isEmpty()) {
            return true;
        }
        else {
            throw new RuntimeException("all is not empty.");
        }
    }
    
    private static boolean checkCol(int[][] m, int col) {
        int NN = m.length;
        
        Set<Integer> all = new HashSet<Integer>(NN);
        for (int i=1; i<=NN; i++) {
            all.add(i);
        }
        
        for (int row=0; row<NN; row++) {
            if (!all.contains(m[row][col])) {
                return false;
            }
            all.remove(m[row][col]);
        }
        
        if (all.isEmpty()) {
            return true;
        }
        else {
            throw new RuntimeException("all is not empty.");
        }
    }
    
    private static boolean checkGrid(int[][] m, int N, int row, int col) {
        int NN = m.length;
        
        Set<Integer> all = new HashSet<Integer>(NN);
        for (int i=1; i<=NN; i++) {
            all.add(i);
        }
        
        for (int row_inc=0; row_inc<N; row_inc++) {
            for (int col_inc=0; col_inc<N; col_inc++) {
                if (!all.contains(m[row+row_inc][col+col_inc])) {
                    return false;
                }
                all.remove(m[row+row_inc][col+col_inc]);
            }
        }
        
        if (all.isEmpty()) {
            return true;
        }
        else {
            throw new RuntimeException("all is not empty.");
        }
    }
}
