// https://oj.leetcode.com/problems/next-permutation/
// https://oj.leetcode.com/discuss/8472/share-my-o-n-time-solution

import java.util.Arrays;

public class NextPerm {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4};
        nextPermutation(a);
        System.out.println(Arrays.toString(a));
        
        a = new int[]{1, 2, 6, 5, 4, 3};
        nextPermutation(a);
        System.out.println(Arrays.toString(a));
        
        a = new int[]{4, 3, 2, 1};
        nextPermutation(a);
        System.out.println(Arrays.toString(a));
        
        a = new int[]{2, 4, 3, 1};
        nextPermutation(a);
        System.out.println(Arrays.toString(a));
    }

    // O(N)
    // 1. travers from num[N-1] to num[0], find num[i-1] < num[i] (num[i...N-1] is reverse sorted)
    // 2. swap num[i-1] with smallest number in num[i...N-1] that is larger than num[i-1];
    // 3. sort num[i...N-1] (simply reverse)
    public static void nextPermutation(int[] num) {
        int N = num.length;
        int i = N - 1;
        for (; i >= 1; i--) {
            if (num[i-1] < num[i]) {
                break;
            }
        }
        
        if (i != 0) {
            int j = N - 1;
            for (; j >= i+1; j--) {
                if (num[j] > num[i-1]) {
                    break;
                }
            }
            
            int tmp = num[i-1];
            num[i-1] = num[j];
            num[j] = tmp;
        }
        
        int low = i;
        int high = N-1;
        while (low < high) {
            int tmp = num[low];
            num[low] = num[high];
            num[high] = tmp;
            low++;
            high--;
        }
    }
}
