// https://leetcode.com/problems/top-k-frequent-elements/

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TopKFrequentElements {

    // O(N) time, O(N) space
    public List<Integer> topKFrequent(int[] nums, int k) {
        // num -> freq
        Map<Integer, Integer> num2Freq = new HashMap<Integer, Integer>();
        for (int num : nums) {
            num2Freq.put(num, num2Freq.containsKey(num) ? num2Freq.get(num) + 1 : 1);
        }
        int maxFreq = 0;
        for (int freq : num2Freq.values()) {
            maxFreq = Math.max(maxFreq, freq);
        }

        // freqBucket[freq] -> [num1, num2, ...]
        List<List<Integer>> freq2nums = new ArrayList<List<Integer>>(maxFreq + 1);
        for (int i = 0; i <= maxFreq; i++) {
            freq2nums.add(new LinkedList<Integer>());
        }
        for (int num : num2Freq.keySet()) {
            int freq = num2Freq.get(num);
            freq2nums.get(freq).add(num);
        }

        List<Integer> result = new ArrayList<Integer>();
        for (int freq = maxFreq, count = 0; count < k; freq--) {
            for (int num : freq2nums.get(freq)) {
                result.add(num);
            }
            count += freq2nums.get(freq).size();
        }

        return result;
    }
}
