import java.util.Scanner;

public class Painting {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        // init memo[0][s] = 0
        double[][] memo = new double[N + 1][3];
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= 2; j++) {
                memo[i][j] = -1;
            }
        }

        for (int n = 1; n < N; n++) {
            for (int s = 1; s <= 2; s++) {
                calculate(memo, n, s);
            }
        }

        System.out.println(calculate(memo, N, 0));
    }

    /**
     *
     * @param memo N#border -> expected number of black circles after painting
     * @param N number of white circles
     * @param sideStatus 0: both sides are not black; 1: only one side is black; 2: both sides are black
     * @return expected number of black circles after painting
     */
    private static double calculate(double[][] memo, int N, int sideStatus) {
        if (memo[N][sideStatus] != -1) {
            return memo[N][sideStatus];
        }

        // iterate the case of painting circle i (if possible)
        // for sideStatus == 1, it is equivalent that either side is black, hence we assume the left side is black
        int numCases = N;
        switch (sideStatus) {
            case 0:
                numCases = N;
                break;
            case 1:
                numCases = N - 1;
                break;
            case 2:
                numCases = N - 2;
                break;
        }
        if (numCases == 0) {
            memo[N][sideStatus] = 0;
            return 0;
        }

        double result = 0;
        for (int i = 0; i < N; i++) {
            double caseResult = 0;

            switch (sideStatus) {
                case 0:
                    caseResult = 1 + calculate(memo, i, 1) + calculate(memo, N - i - 1, 1);
                    break;
                case 1:
                    if (i != 0) {
                        caseResult = 1 + calculate(memo, i, 2) + calculate(memo, N - i - 1, 1);
                    }
                    break;
                case 2:
                    if (i != 0 && i != N - 1) {
                        caseResult = 1 + calculate(memo, i, 2) + calculate(memo, N - i - 1, 2);
                    }
                    break;
            }

            result += caseResult;
        }

        memo[N][sideStatus] = result / numCases;

        return memo[N][sideStatus];
    }

}
