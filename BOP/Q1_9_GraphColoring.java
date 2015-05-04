import java.util.Arrays;
import java.util.Scanner;

public class Q1_9_GraphColoring {
    public static void main(String[] args) {
        /*
        10
        0 1 0 0 0 1 0 0 0 0
        1 0 1 0 0 0 1 0 0 0
        0 1 0 1 0 0 0 1 0 0
        0 0 1 0 1 0 0 0 1 0
        1 0 0 1 0 0 0 0 0 1
        1 0 0 0 0 0 0 1 1 0
        0 1 0 0 0 0 0 0 1 1
        0 0 1 0 0 1 0 0 0 1
        0 0 0 1 0 1 1 0 0 0
        0 0 0 0 1 0 1 1 0 0
        
        solution:
        3
        [1 2 1 2 3 2 1 3 3 2]
        */

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        new Problem(graph).color();

        sc.close();
    }

    private static class Problem {
        public final int N;
        public final int[][] graph; // adjacency matrix 
        public final int[] c; // stores color for each vertex

        public Problem(int[][] graph) {
            this.N = graph.length;
            this.graph = graph;
            this.c = new int[graph.length];
        }

        public void color() {
            for (int K = 1; K <= N; K++) {
                // try using K colors
                if (colorK(K)) {
                    System.out.println(K + ":" + Arrays.toString(c));
                    break;
                }
            }
        }

        private boolean colorK(int K) {
            c[0] = 1;
            boolean result = solve(1, K);
            c[0] = 0; // back track

            return result;
        }

        // color vertex[index...N-1] using color 1,2,...,K
        private boolean solve(int index, int K) {
            if (index == N) {
                return true;
            }

            for (int k = 1; k <= K; k++) {
                c[index] = k;

                if (noConflict(index, k) && solve(index + 1, K)) {
                    return true;
                }

                c[index] = 0; // back track
            }

            return false;
        }

        // whether there is no conflict after assigning color c to vertex index
        private boolean noConflict(int index, int k) {
            for (int i = 0; i < N; i++) {
                if (i == index) {
                    continue;
                }

                if (graph[index][i] == 1 && c[i] == k) {
                    return false;
                }
            }

            return true;
        }
    }
}
