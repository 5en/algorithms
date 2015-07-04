// https://leetcode.com/problems/largest-number/

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LargestNumber {
    public static String largestNumber(int[] nums) {
        List<String> strs = new ArrayList<String>(nums.length);
        for (int num : nums) {
            strs.add(int2str(num));
        }

        Collections.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                String s1s2 = s1 + s2;
                String s2s1 = s2 + s1;
                return s1s2.compareTo(s2s1) >= 0 ? -1 : 1;
            }
        });

        StringBuilder result = new StringBuilder();
        for (String str : strs) {
            result.append(str);
        }

        while (result.length() > 1 && result.charAt(0) == '0') {
            result.deleteCharAt(0);
        }

        return result.toString();
    }

    private static String int2str(int num) {
        if (num == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            sb.insert(0, num % 10);
            num /= 10;
        }

        return sb.toString();
    }
}
