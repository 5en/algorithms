// Given an array int32 arr[] of size n, return the number of non-empty contigious subarrays whose sum lies in range [a, b]
//        Examples:
//        count([1,2,3], 0, 3) = 3 # [1], [2], [3], [1, 2]
//        count([-2,5,-1], -2, 2) = 3 # [-2], [-1], [-2, 5, -1]

public class SubarraySumInRange {

    public static void main(String[] args) {
        System.out.println(numSubarraySumInRange(new int[] { 1, 2, 3 }, 0, 3));
        System.out.println(numSubarraySumInRange(new int[] { 1, 2, 3 }, 0, 6));
        System.out.println(numSubarraySumInRange(new int[] { -2, 5, -1 }, -2, 2));
    }

    // O(N*logN)
    public static int numSubarraySumInRange(int[] nums, int a, int b) {
        // S[i] = nums[0] + nums[1] + ... + nums[i]
        int N = nums.length;
        int[] S = new int[N];
        S[0] = nums[0];
        for (int i = 1; i < N; i++) {
            S[i] = S[i - 1] + nums[i];
        }

        return numSubarraySumInRangeSub(S, 0, N - 1, a, b);
    }

    // we need to find a <= S[j] - S[i] <= b for lb <= i < j <= ub
    // and all a <= S[j] <= b for lb <= j <= ub
    //
    // similar to merge sort
    private static int numSubarraySumInRangeSub(int[] S, int lb, int ub, int a, int b) {
        if (lb == ub) {
            return (S[lb] >= a && S[lb] <= b) ? 1 : 0;
        }

        int count = 0;
        int mid = (lb + ub) / 2;
        count += numSubarraySumInRangeSub(S, lb, mid, a, b);
        count += numSubarraySumInRangeSub(S, mid + 1, ub, a, b);

        // S[lb...mid] <= S[mid+1...ub]
        // find a <= S[j] - S[i] <= b for lb <= i <= mid, mid+1 <= j <= ub
        //
        // for each lb <= i <= mid, a <= S[rlb...(rub-1)] - S[i] <= b
        int rlb = mid + 1;
        int rub = mid + 1;
        for (int i = lb; i <= mid; i++) {
            while (rlb <= ub && S[rlb] - S[i] < a) {
                rlb++;
            }
            while (rub <= ub && S[rub] - S[i] <= b) {
                rub++;
            }
            count += rub - rlb;
        }

        // sort
        int[] tmpS = new int[ub - lb + 1];
        int tmpi = 0;
        int li = lb;
        int ri = mid + 1;
        while (li <= mid && ri <= ub) {
            if (S[li] <= S[ri]) {
                tmpS[tmpi++] = S[li++];
            } else {
                tmpS[tmpi++] = S[ri++];
            }
        }
        while (li <= mid) {
            tmpS[tmpi++] = S[li++];
        }
        while (ri <= ub) {
            tmpS[tmpi++] = S[ri++];
        }
        for (tmpi = 0; tmpi < tmpS.length; tmpi++) {
            S[lb + tmpi] = tmpS[tmpi];
        }

        return count;
    }
}
