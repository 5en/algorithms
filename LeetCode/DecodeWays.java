// https://leetcode.com/problems/decode-ways/

public class DecodeWays {
    // O(N) time, O(N) space
    public static int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return isValid(s.substring(0, 1)) ? 1 : 0;
        }

        int[] nums = new int[s.length()]; // nums[i] is number of decoding ways for s[0...i], i = 0...s.length()-1
        nums[0] = isValid(s.substring(0, 1)) ? 1 : 0;
        nums[1] = isValid(s.substring(1, 2)) ? nums[0] : 0;
        nums[1] += isValid(s.substring(0, 2)) ? 1 : 0;
        for (int i = 2; i < s.length(); i++) {
            nums[i] = isValid(s.substring(i, i+1)) ? nums[i-1] : 0;
            nums[i] += isValid(s.substring(i-1, i+1)) ? nums[i-2] : 0;
        }

        return nums[s.length()-1];
    }

    // O(N) time, O(1) space
    public static int numDecodings2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return isValid(s.substring(0, 1)) ? 1 : 0;
        }

        int num1 = isValid(s.substring(0, 1)) ? 1 : 0; // initially nums[0]
        int num2 = isValid(s.substring(1, 2)) ? num1 : 0; // initially nums[1]
        num2 += isValid(s.substring(0, 2)) ? 1 : 0;
        for (int i = 2; i < s.length(); i++) {
            int num3 = isValid(s.substring(i, i+1)) ? num2 : 0;
            num3 += isValid(s.substring(i-1, i+1)) ? num1 : 0;
            num1 = num2;
            num2 = num3;
        }

        return num2;
    }

    private static boolean isValid(String s) {
        int n = Integer.parseInt(s);
        if (s.length() == 1) {
            return n >= 1 && n <= 9;
        }
        if (s.length() == 2) {
            return n >= 10 && n <= 26;
        }

        return false;
    }
}
