package hash;

import java.util.LinkedHashMap;
import java.util.Map;

public class FirstNonRepeatedChar {
    public static void main(String[] args) {
        System.out.println(run("hhello!"));
    }

    public static char run(String s) {
        Map<Character, Integer> countMap = new LinkedHashMap<Character, Integer>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            countMap.put(ch, countMap.containsKey(ch) ? countMap.get(ch) + 1 : 1);
        }

        for (char ch : countMap.keySet()) {
            if (countMap.get(ch) == 1) {
                return ch;
            }
        }

        return 0;
    }
}
