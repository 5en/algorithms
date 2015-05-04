import java.util.Arrays;

public class Q2_17_ShiftK {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        rightShift(a, 13);
        System.out.println(Arrays.toString(a));

        int[] b = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        rightShift2(b, 13);
        System.out.println(Arrays.toString(b));
    }

    // O(N)
    // Target: A[N-K]B[K] -> B[K]A[N-K]
    // 1. AB -> (A^r)(B^r)
    // 2. (A^r)(B^r)^r -> BA
    public static void rightShift(int[] a, int K) {
        int N = a.length;
        K = K % N;

        reverse(a, 0, N - K - 1);
        reverse(a, N - K, N - 1);
        reverse(a, 0, N - 1);
    }

    private static void reverse(int[] a, int left, int right) {
        while (left < right) {
            int tmp = a[left];
            a[left] = a[right];
            a[right] = tmp;

            left++;
            right--;
        }
    }

    // O(N)
    // is equivalent to left shit N-K%N
    public static void rightShift2(int[] a, int K) {
        // proof: for any i,j such that 0<=i<j<=gcd(N,K)-1 and for any interger x1, x2>=0, (i + x1*K)%N != (j + x2*K)%N
        int N = a.length;

        K = K % N;
        int P = gcd(N, K);

        for (int i = 0; i < P; i++) {
            int ai = a[i];

            int curr = i;
            int next = -1;
            while ((next = (curr + N - K) % N) != i) {
                a[curr] = a[next];
                curr = next;
            }
            a[curr] = ai;
        }
    }

    private static int gcd(int x, int y) {
        while (x != 0 && y != 0) {
            if (x >= y) {
                x = x % y;
            } else {
                y = y % x;
            }
        }

        return (x == 0 ? y : x);
    }
}
