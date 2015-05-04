public class Q1_8_LiftOptimization {
    public static void main(String[] args) {
        int[] count = {-1, 5, 5, 5, 5, 5};
        System.out.println(process(count, 5));
    }

    // min total = sum_(i=1)^(N)(count[i]*|i-x|) subject to 1 <= x <= N and x is an integer
    // O(N)
    private static int process(int[] count, int N) {
        // suppose when x => total = Y, count[<x] = N1, count[x] = N2, count[>x] = N3
        // change to x-1 => total = Y + (N2+N3) - N1
        // change to x+1 => total = Y + (N1+N2) - N3

        // init
        int optX = 1;
        int optTotal = 0;
        int N1 = 0;
        int N2 = count[1];
        int N3 = 0;
        for (int i = 2; i <= N; i++) {
            N3 += count[i];
            optTotal += count[i] * (i - 1);
        }

        for (int x = 2; x <= N; x++) {
            if (N1 + N2 - N3 < 0) {
                optX = x;
                optTotal += N1 + N2 - N3;
                N1 += N2;
                N2 = count[x];
                N3 -= count[x];
                
            } else {
                break;
            }
        }
        
        return optX;
    }
}
