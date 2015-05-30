// https://oj.leetcode.com/problems/maximum-product-subarray/

public class MaxProductContiguousSubArray {
    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{-4, -3, -2})); // (-4) * (-3) = 12
        System.out.println(maxProduct(new int[]{-1, 0, -2})); // 0
        System.out.println(maxProduct(new int[]{-1})); // -1
        System.out.println(maxProduct(new int[]{1})); // 1
    }
    
    public static int maxProduct(int[] A) {
        int max = A[0];
        int maxTailPos = A[0] > 0 ? A[0] : -1;
        int minTailNeg = A[0] < 0 ? A[0] : 1;
        
        for (int i = 1; i < A.length; i++) {
            if (A[i] > 0) {
                maxTailPos = maxTailPos != -1 ? maxTailPos*A[i] : A[i];
                minTailNeg = minTailNeg != 1 ? minTailNeg*A[i] : 1;
            } else if (A[i] < 0) {
                int oriMaxTailPos = maxTailPos;
                int oriMinTailNeg = minTailNeg;
                maxTailPos = oriMinTailNeg != 1 ? oriMinTailNeg*A[i] : -1;
                minTailNeg = oriMaxTailPos != -1 ? oriMaxTailPos*A[i] : A[i];
            } else {
                // A[i] == 0
                maxTailPos = -1;
                minTailNeg = 1;
            }
            
            if (maxTailPos != -1) {
                max = Math.max(max, maxTailPos);
            }
            max = Math.max(max, A[i]);
        }
        
        return max;
    }
}