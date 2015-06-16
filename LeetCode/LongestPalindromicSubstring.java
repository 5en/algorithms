// https://leetcode.com/problems/longest-palindromic-substring/

public class LongestPalindromicSubstring {
    // O(N^2) time
    public static String longestPalindrome(String s) {
        if (s.length() == 0) {
            return "";
        }
        
        int opt = 0;
        int optLeft = -1;
        int optRight = -1;
        int center1 = 0;
        int center2 = 0;
        boolean oddLen = true;
        while (center1 < s.length() && center2 < s.length()) {
            int curr = 0;
            int left = center1;
            int right = center2;
            
            while (left >= 0 && right < s.length()) {
                if (s.charAt(left) != s.charAt(right)) {
                    break;
                }
                curr += (left == right ? 1 : 2);
                left--;
                right++;
            }
            
            if (curr > opt) {
                opt = curr;
                optLeft = left + 1;
                optRight = right - 1;
            }
            
            if (oddLen) {
                center2++;
            } else {
                center1++;
            }
            oddLen = !oddLen;
        }
        
        return s.substring(optLeft, optRight+1);
    }
}
