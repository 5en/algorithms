// https://leetcode.com/problems/repeated-dna-sequences/

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RepeatedDnaSequences {
    // worst case O((N-L+1)*L*L)
    public List<String> findRepeatedDnaSequences(String s) {
        int L = 10;
        List<String> result = new LinkedList<String>();

        // str -> true / false: str occurs only once / more than once
        Map<String, Boolean> seq2once = new HashMap<String, Boolean>();
        for (int start = 0; start <= s.length() - L; start++) { // loop N - L + 1 times
            String seq = s.substring(start, start + L); // O(L)
            if (!seq2once.containsKey(seq)) { // O(L)
                seq2once.put(seq, true);
                continue;
            }

            if (seq2once.get(seq)) {
                result.add(seq);
                seq2once.put(seq, false);
            }
        }

        return result;
    }
}
