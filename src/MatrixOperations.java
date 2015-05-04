import java.util.LinkedList;
import java.util.Queue;

public class MatrixOperations {
    public static void main(String[] args) {
        int[][] m1 = {{1, 2, 3}, {4, 5, 6}};
        print(m1);

        System.out.println();

        print(rotateLeft(m1));

        System.out.println();

        print(rotateLeft(rotateLeft(rotateLeft(m1))));

        System.out.println();

        char[][] m2 = {{'.', 'x', '.'}, {'x', '.', 'x'}, {'.', 'x', 'x'}, {'x', '.', '.'}, {'x', '.', '.'}, {'x', 'x', '.'}};
        print(m2);

        System.out.println();

        print(gravity(m2));

        System.out.println();

        int[][] m3 = {{1, 1}, {1, 0}};

        print(multiply(m3, m3));

        System.out.println();

        print(pow(m3, 10));

        System.out.println();
    }

    //  1 2 3       ->      3 6
    //  4 5 6               2 5
    //                      1 4
    // NUM_ROW = 2  ->      NUM_ROW_NEW = 3
    // NUM_COL = 3  ->      NUM_COL_NEW = 2
    // m[row][col]      ->      m_new[NUM_COL-1-col][row]
    public static int[][] rotateLeft(int[][] m) {
        int NUM_ROW = m.length;
        int NUM_COL = m[0].length;

        int NUM_ROW_NEW = NUM_COL;
        int NUM_COL_NEW = NUM_ROW;
        int[][] m_new = new int[NUM_ROW_NEW][NUM_COL_NEW];

        for (int row = 0; row < NUM_ROW; row++) {
            for (int col = 0; col < NUM_COL; col++) {
                m_new[NUM_COL - 1 - col][row] = m[row][col];
            }
        }

        return m_new;
    }

    // . x .        ->      . . .
    // x . x                . . .
    // . x x                x . .
    // x . .                x x .
    // x . .                x x x
    // x x .                x x x
    public static char[][] gravity(char[][] m) {
        int NUM_ROW = m.length;
        int NUM_COL = m[0].length;

        char[][] m_new = new char[NUM_ROW][NUM_COL];

        for (int col = 0; col < NUM_COL; col++) {
            Queue<Integer> emptyList = new LinkedList<Integer>(); // indices of empty space from bottom up

            for (int row = NUM_ROW - 1; row >= 0; row--) {
                if (m[row][col] == '.') {
                    emptyList.add(row);
                } else if (m[row][col] == 'x') {
                    if (emptyList.isEmpty()) {
                        m_new[row][col] = 'x';
                    } else {
                        m_new[emptyList.remove()][col] = 'x';
                        emptyList.add(row);
                    }
                }
            }

            while (!emptyList.isEmpty()) {
                m_new[emptyList.remove()][col] = '.';
            }
        }

        return m_new;
    }

    // m^p
    public static int[][] pow(int[][] m, int p) {
        int M = m.length;
        int N = m[0].length;

        if (M != N) {
            throw new RuntimeException("pow() dimension error!");
        }

        // m^0 = identity matrix
        int[][] result = new int[M][M];
        for (int row = 0; row < M; row++) {
            result[row][row] = 1;
        }

        // logN times
        int[][] tmp = copy(m);
        while (p != 0) {
            if (p % 2 != 0) {
                result = MatrixOperations.multiply(result, tmp);
            }
            tmp = MatrixOperations.multiply(tmp, tmp);
            p /= 2;
        }

        return result;
    }

    // m1*m2
    public static int[][] multiply(int[][] m1, int[][] m2) {
        int M1 = m1.length;
        int N1 = m1[0].length;
        int M2 = m2.length;
        int N2 = m2[0].length;

        if (N1 != M2) {
            throw new RuntimeException("multiply() dimension error!");
        }

        int[][] m = new int[M1][N2];
        for (int row = 0; row < M1; row++) {
            for (int col = 0; col < N2; col++) {
                for (int i = 0; i < N1; i++) {
                    m[row][col] += m1[row][i] * m2[i][col];
                }
            }
        }

        return m;
    }

    public static int[][] copy(int[][] m) {
        int M = m.length;
        int N = m[0].length;

        int[][] mCopy = new int[M][N];
        for (int row = 0; row < M; row++) {
            for (int col = 0; col < N; col++) {
                mCopy[row][col] = m[row][col];
            }
        }

        return mCopy;
    }

    public static void print(char[][] m) {
        for (int row = 0; row < m.length; row++) {
            for (int col = 0; col < m[0].length; col++) {
                System.out.print(m[row][col] + " ");
            }
            System.out.println();
        }
    }

    public static void print(int[][] m) {
        for (int row = 0; row < m.length; row++) {
            for (int col = 0; col < m[0].length; col++) {
                System.out.print(m[row][col] + " ");
            }
            System.out.println();
        }
    }
}
