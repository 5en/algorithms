import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/anagrams/

public class GroupAnagrams {
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groups = new HashMap<String, List<String>>();
        for (String str : strs) {
            // sort characters
            char[] chs = str.toCharArray();
            Arrays.sort(chs);
            String strSorted = new String(chs);

            if (!groups.containsKey(strSorted)) {
                groups.put(strSorted, new ArrayList<String>());
            }
            groups.get(strSorted).add(str);
        }

        List<List<String>> result = new ArrayList<List<String>>();
        for (List<String> group : groups.values()) {
            Collections.sort(group);
            result.add(group);
        }

        return result;
    }
}
