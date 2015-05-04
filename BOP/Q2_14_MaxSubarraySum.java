public class Q2_14_MaxSubarraySum {
    public static void main(String[] args) {
        int[] a = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7}; // 18 20 -7 12
        //int[] a = {-3, -25, -3, -16, -23, -2, -5, -22, -4}; // -2

        tri = msa(a);
        System.out.println("From index " + tri.left + ": " + a[tri.left]);
        System.out.println("To index " + tri.right + ": " + a[tri.right]);
        System.out.println("Max sum: " + tri.sum);
    }

    // O(N), O(1) space
    // ALL[n]: max sum of sub-array in range a[0...n]
    // END[n]: max sum of sub-array that ends at a[n]
    // => END[n] = max(END[n-1]+a[n], a[n])
    // => ALL[n] = max(ALL[n-1], END[n])
    public static Triple msa(int[] a) {
        int opt = a[0];
        int optLeft = 0;
        int optRight = 0;
        int end = a[0];
        int endLeft = 0;

        for (int i = 1; i < a.length; i++) {
            // update END[i] = max{a[i], END[i-1]+a[i]}
            end += a[i];
            if (a[i] > end) {
                end = a[i];
                endLeft = i;
            }

            // update ALL[i] = max{ALL[i-1], END[i]}
            if (end > opt) {
                opt = end;
                optLeft = endLeft;
                optRight = i;
            }
        }

        return new Triple(optLeft, optRight, opt);
    }

    public static class Triple {
        public final int left;
        public final int right;
        public final int sum;

        public Triple(int left, int right, int sum) {
            this.left = left;
            this.right = right;
            this.sum = sum;
        }
    }
}
