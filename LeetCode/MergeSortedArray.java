// https://leetcode.com/problems/merge-sorted-array/

public class MergeSortedArray {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int t = m + n - 1;
        m--; // nums1[m] is the last element of nums1
        n--; // nums1[n] is the last element of nums2
        while (m >= 0 && n >= 0) {
            if (nums1[m] >= nums2[n]) {
                nums1[t--] = nums1[m--];
            } else {
                nums1[t--] = nums2[n--];
            }
        }
        
        if (m < 0) {
            while (n >= 0) {
                nums1[t--] = nums2[n--];
            }
        }
        
        if (n < 0) {
            while (m >= 0) {
                nums1[t--] = nums1[m--];
            }
        }
    }
}
