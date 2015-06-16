// https://leetcode.com/problems/3sum/

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ThreeSum {
    public static void main(String[] args) {
        int[] a = {
                7, -1, 14, -12, -8, 7, 2, -15, 8, 8, 
                -8, -14, -4, -5, 7, 9, 11, -4, -15, -6, 
                1, -14, 4, 3, 10, -5, 2, 1, 6, 11, 
                2, -2, -5, -7, -6, 2, -15, 11, -6, 8, 
                -4, 2, 1, -1, 4, -6, -15, 1, 5, -15, 
                10, 14, 9, -8, -6, 4, -6, 11, 12, -15, 
                7, -1, -9, 9, -1, 0, -4, -1, -12, -2, 14, 
                -9, 7, 0, -3, -4, 1, -2, 12, 14, -10, 
                0, 5, 14, -1, 14, 3, 8, 10, -8, 8, -5, 
                -2, 6, -11, 12, 13, -7, -12, 8, 6, -13, 
                14, -2, -5, -11, 1, 3, -6};
        System.out.println(threeSum(a));
    }
    
    public static List<List<Integer>> threeSum(int[] num) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        
        Arrays.sort(num);
        
        for (int i = 0; i < num.length; i++) {
            if (i > 0 && num[i] == num[i-1]) {
                continue;
            }
            
            // find num[low] + num[high] == -num[i], i < low < high
            int target = -num[i];
            
            int low = i + 1; // i < low < high
            int high = num.length - 1;
            while (low < high) {
                if (num[low] + num[high] > target) {
                    high--;
                    while (low < high && num[high] == num[high+1]) {
                        high--;
                    }
                } else if (num[low] + num[high] < target) {
                    low++;
                    while (low < high && num[low] == num[low-1]) {
                        low++;
                    }
                } else {
                    // num[i] + num[low] + num[high] == 0
                    List<Integer> triple = new LinkedList<Integer>();
                    triple.add(num[i]);
                    triple.add(num[low]);
                    triple.add(num[high]);
                    result.add(triple);
                    
                    low++;
                    high--;
                    while (low < high && num[low] == num[low-1]) {
                        low++;
                    }
                    while (low < high && num[high] == num[high+1]) {
                        high--;
                    }
                }
            }
        }
        
        return result;
    }
}
