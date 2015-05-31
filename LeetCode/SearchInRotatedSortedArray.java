// https://leetcode.com/problems/search-in-rotated-sorted-array/
// https://leetcode.com/problems/search-in-rotated-sorted-array-ii/

package bs;

public class SearchInRotatedSortedArray {
    public static void main(String[] args) {
        System.out.println(find(new int[]{6, 8, 10, 12, 14, 0, 2, 4}, 6)); // 0
        System.out.println(find(new int[]{6, 8, 10, 12, 14, 0, 2, 4}, 10)); // 2
        System.out.println(find(new int[]{6, 8, 10, 12, 14, 0, 2, 4}, 14)); // 4
        System.out.println(find(new int[]{6, 8, 10, 12, 14, 0, 2, 4}, 0)); // 5
        System.out.println(find(new int[]{6, 8, 10, 12, 14, 0, 2, 4}, 2)); // 6
        System.out.println(find(new int[]{6, 8, 10, 12, 14, 0, 2, 4}, 4)); // 7
        System.out.println(find(new int[]{6, 8, 10, 12, 14, 0, 2, 4}, 1)); // -1
        System.out.println(find(new int[]{6, 8, 10, 12, 14, 0, 2, 4}, 5)); // -1
        System.out.println(find(new int[]{6, 8, 10, 12, 14, 0, 2, 4}, 9)); // -1
        System.out.println(find(new int[]{6, 8, 10, 12, 14, 0, 2, 4}, 15)); // -1
        
        System.out.println(find(new int[]{1, 1, 1, 3, 1}, 3)); // -1 (wrong)
        System.out.println(find2(new int[]{1, 1, 1, 3, 1}, 3)); // 3
    }

    // no duplicates in a
    public static int find(int[] a, int x) {
        return find(a, 0, a.length - 1, x);
    }

    private static int find(int[] a, int low, int high, int x) {
        if (low > high) {
            return -1;
        }

        int mid = (low + high) >>> 1;
        if (a[mid] == x) {
            return mid;
        }

        if (a[mid] <= a[high]) {
            // a[low...mid] cross the boundary, a[mid...high] non-decreasing
            if (x > a[mid] && x <= a[high]) {
                return find(a, mid + 1, high, x);
            } else {
                return find(a, low, mid - 1, x);
            }
        } else {
            // a[low...mid] non-decreasing, a[mid...high] cross the boundary
            if (x >= a[low] && x < a[mid]) {
                return find(a, low, mid - 1, x);
            } else {
                return find(a, mid + 1, high, x);
            }
        }
    }
    
    // duplicates in a, worst case O(N)
    private static int find2(int[] a, int x) {
        return find2(a, 0, a.length-1, x);
    }
    
    private static int find2(int[] A, int low, int high, int x) {
        if (low > high) {
            return -1;
        }
        
        if (low == high) {
            return A[low] == x ? low : -1;
        }
        
        int mid = (low + high) >>> 1;
        if (A[low] < A[mid] || A[mid] > A[high]) {
            // cross in A[mid+1...high]
            if (x >= A[low] && x <= A[mid]) {
                return find2(A, low, mid, x);
            } else {
                return find2(A, mid+1, high, x);
            }
        } else if (A[low] > A[mid] || A[mid] < A[high]){
            // cross in A[low...mid]
            if (x >= A[mid+1] && x <= A[high]) {
                return find2(A, mid+1, high, x);
            } else {
                return find2(A, low, mid, x);
            }
        } else {
            int result = find2(A, low, mid, x);
            if (result != -1) {
                return result;
            }
            result = find2(A, mid+1, high, x);
            return result;
        }
    }
}
