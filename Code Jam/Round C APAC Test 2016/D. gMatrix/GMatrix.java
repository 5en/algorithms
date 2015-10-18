import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class GMatrix {
    public static void main(String[] args) throws IOException {
        //                Scanner sc = new Scanner(System.in);
                Scanner sc = new Scanner(new File("D-small.in.txt"));
//        Scanner sc = new Scanner(new File("D-large.in.txt"));
        //                PrintWriter out = new PrintWriter(System.out);
        PrintWriter out = new PrintWriter("D-output.txt");

        int T = sc.nextInt();
        sc.nextLine();
        for (int t = 1; t <= T; t++) {
            System.out.println(t);

            int N = sc.nextInt();
            int K = sc.nextInt();
            int C = sc.nextInt();
            int X = sc.nextInt();
            int[] A = new int[N];
            for (int n = 0; n < N; n++) {
                A[n] = sc.nextInt();
            }
            int[] B = new int[N];
            for (int n = 0; n < N; n++) {
                B[n] = sc.nextInt();
            }

            out.printf("Case #%d: %d\n", t, process(N, K, C, X, A, B));
        }

        out.flush();
        out.close();
    }

    private static long process(int N, int K, int C, int X, int[] A, int[] B) {
        int[][] matrix = new int[N][N];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                matrix[i - 1][j - 1] = (A[i - 1] * i + B[j - 1] * j + C) % X;
            }
        }

        long sum = 0;

        //        for (int startRow = 0; startRow <= N-K; startRow++) {
        //            for (int startCol = 0; startCol <= N-K; startCol++) {
        //                // K * K
        //                int max = 0;
        //                for (int row = startRow; row < startRow + K; row++) {
        //                    for (int col = startCol; col < startCol + K; col++) {
        //                        if (matrix[row][col] > max) {
        //                            max = matrix[row][col];
        //                        }
        //                    }
        //                }
        //                sum += max;
        //            }
        //        }

        // position id = row * N + col
        PriorityQueue<Pos> maxHeap = new PriorityQueue<Pos>(
            Collections.reverseOrder(new Comparator<Pos>() {
                @Override
                public int compare(Pos p1, Pos p2) {
                    return p1.v < p2.v ? -1 : (p1.v > p2.v ? 1 : 0);
                }
            }));
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                maxHeap.add(new Pos(row, col, matrix[row][col]));
            }
        }

        Set<Integer> posIds = new HashSet<Integer>();
        while (true) {
            Pos pos = maxHeap.remove();

            for (int startRow = pos.row - K + 1; startRow <= pos.row; startRow++) {
                if (startRow < 0 || startRow + K - 1 >= N) {
                    continue;
                }
                for (int startCol = pos.col - K + 1; startCol <= pos.col; startCol++) {
                    if (startCol < 0 || startCol + K - 1 >= N) {
                        continue;
                    }

                    int posId = calPosId(startRow, startCol, N);
                    if (!posIds.contains(posId)) {
                        posIds.add(posId);
                        sum += pos.v;

                        if (posIds.size() == (N - K + 1) * (N - K + 1)) {
                            return sum;
                        }
                    }
                }
            }
        }
    }

    private static int calPosId(int row, int col, int N) {
        return row * N + col;
    }

    private static class Pos {
        public final int row;
        public final int col;
        public final int v;

        public Pos(int row, int col, int v) {
            this.row = row;
            this.col = col;
            this.v = v;
        }
    }
}
