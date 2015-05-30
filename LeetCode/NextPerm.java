// https://oj.leetcode.com/problems/next-permutation/
// https://oj.leetcode.com/discuss/8472/share-my-o-n-time-solution

import java.util.Arrays;

public class NextPerm {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4}; // 1 2 4 3
        nextPermutation(a);
        System.out.println(Arrays.toString(a));
        
        a = new int[]{2, 4, 6, 5, 3, 1}; // 2 5 1 3 4 6
        nextPermutation(a);
        System.out.println(Arrays.toString(a));
        
        a = new int[]{4, 3, 2, 1}; // 1 2 3 4
        nextPermutation(a);
        System.out.println(Arrays.toString(a));
        
        a = new int[]{2, 4, 3, 1}; // 3 1 2 4
        nextPermutation(a);
        System.out.println(Arrays.toString(a));
    }

    // O(N)
    // 1. traverse from nums[N-1] to nums[0], find nums[i-1] < nums[i] (nums[i...N-1] is reverse sorted)
    // 2. swap nums[i-1] with smallest numsber in nums[i...N-1] that is larger than nums[i-1];
    // 3. sort nums[i...N-1] (simply reverse)
    public static void nextPermutation(int[] nums) {
        int N = nums.length;
        int i = N - 1;
        for (; i >= 1; i--) {
            if (nums[i-1] < nums[i]) {
                break;
            }
        }
        
        if (i != 0) {
            int j = N - 1;
            for (; j > i; j--) {
                if (nums[j] > nums[i-1]) {
                    break;
                }
            }
            
            int tmp = nums[i-1];
            nums[i-1] = nums[j];
            nums[j] = tmp;
        }
        
        int low = i;
        int high = N-1;
        while (low < high) {
            int tmp = nums[low];
            nums[low] = nums[high];
            nums[high] = tmp;
            low++;
            high--;
        }
    }
}
