// Given an array consisting of N Numbers.
// Divide it into two Equal(it is important) partitions (in size both contains N/2 elements)
// such that difference between sum of both partitions is minimum.
// If number of elements are odd difference in partition size can be at most 1.

public class BalancedPartition {

    public static void main(String[] args) {
        int[] nums = { 1, 5, 7, 8, 9, 6, 3, 11, 20, 17 }; // sum=87
        System.out.println(balancedPartition(nums));
        int[] nums2 = { 1, 5, 7 }; // sum=13
        System.out.println(balancedPartition(nums2));
    }

    public static int balancedPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int halfSum = sum % 2 == 0 ? sum / 2 : sum / 2 + 1;
        int halfN = nums.length / 2;

        // valid[n][s]: whether there are n numbers for which the sum == s
        // n = 0...halfN
        // s = 0...halfSum(+1)
        //
        // given num, valid[n+1][s] = &&{valid[n][s-num]}
        boolean[][] valid = new boolean[halfN + 1][halfSum + 1];
        valid[0][0] = true;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            for (int n = Math.min(i + 1, halfN); n >= 1; n--) {
                for (int s = halfSum; s >= 0; s--) {
                    if (s - num >= 0 && valid[n - 1][s - num]) {
                        valid[n][s] = true;
                    }
                }
            }
        }

        for (int s = halfSum; s >= 0; s--) {
            if (valid[halfN][s]) {
                return s;
            }
        }

        return 0;
    }
}
