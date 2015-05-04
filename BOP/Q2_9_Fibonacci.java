import util.Matrix;

public class Q2_9_Fibonacci {
    public static void main(String[] args) {
        System.out.println(fibonacci(27));
    }

    // O(logN)
    // [F(n), F(n-1)] = [F(n-1), F(n-2)] * [ 1 1
    //                                       1 0 ]
    public static int fibonacci(int n) {
        if (n == 0) {
            return 0;
        }
        
        if (n == 1) {
            return 1;
        }

        int[][] m_f1f0 = new int[1][2];
        m_f1f0[0][0] = 1;
        m_f1f0[0][1] = 0;

        int[][] A = new int[2][2];
        A[0][0] = 1;
        A[0][1] = 1;
        A[1][0] = 1;
        A[1][1] = 0;

        int[][] result = multiply(m_f1f0, pow(A, n - 1));

        return result[0][0];
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

        int[][] tmp = copy(m);
        while (p != 0) {
            if (p % 2 == 1) {
                result = Matrix.multiply(result, tmp);
            }
            tmp = Matrix.multiply(tmp, tmp);
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
    
}
