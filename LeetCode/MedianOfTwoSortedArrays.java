// https://leetcode.com/problems/median-of-two-sorted-arrays/

public class MedianOfTwoSortedArrays {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        if ((len1 + len2) % 2 == 1) {
            return findSmallestK(nums1, 0, nums2, 0, (len1 + len2) / 2 + 1);
        } else {
            return ( ((double)findSmallestK(nums1, 0, nums2, 0, (len1 + len2) / 2)) + ((double)findSmallestK(nums1, 0, nums2, 0, (len1 + len2) / 2 + 1)) ) / 2;
        }
    }
    
    private static int findSmallestK(int[] a, int la, int[] b, int lb, int k) {
        int aLen = a.length - la;
        int bLen = b.length - lb;
        if (aLen > bLen) {
            return findSmallestK(b, lb, a, la, k);
        }
        
        if (aLen == 0) {
            return b[lb + k - 1];
        }
        
        if (k == 1) {
            return Math.min(a[la], b[lb]);
        }
        
        // aLen <= bLen
        int ak = Math.min(aLen, k / 2);
        int bk = k - ak;
        if (a[la + ak - 1] < b[lb + bk - 1]) {
            return findSmallestK(a, la+ak, b, lb, k-ak);
        } else if (a[la + ak - 1] > b[lb + bk - 1]) {
            return findSmallestK(a, la, b, lb+bk, k-bk);
        } else {
            // a[la + ak - 1] == b[lb + bk - 1]
            return a[la + ak - 1];
        }
    }
}
