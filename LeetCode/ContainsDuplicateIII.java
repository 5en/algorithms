// https://leetcode.com/problems/contains-duplicate-iii/

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeSet;

public class ContainsDuplicateIII {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (new Random().nextInt() % 2 == 0) {
            return containsNearbyAlmostDuplicate1(nums, k, t);
        } else {
            return containsNearbyAlmostDuplicate2(nums, k, t);
        }
    }

    // O(N) time, O(k) space
    public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0 || k <= 0 || t < 0) {
            return false;
        }

        // Example:
        // t = 10
        // [1, 10]: bucket(1) = 1 / 11 = 0, bucket(10) = 10 / 11 = 0 (true, sample bucket)
        // [1, 11]: bucket(1) = 1 / 11 = 0, bucket(11) = 11 / 11 = 1 (true, adjacent bucket)
        // [1, 12]: bucket(1) = 1 / 11 = 0, bucket(11) = 12 / 11 = 1 (false, adjacent bucket)
        // [1, 22]: bucket(1) = 1 / 11 = 0, bucket(22) = 22 / 11 = 2 (false, not adjacent bucket)

        long lt = (long)t;
        Map<Long, Long> bucket2num = new HashMap<Long, Long>(); // bucket -> num
        for (int i = 0; i < nums.length; i++) {
            // map x to x - Integer.MIN_VALUE
            long remappedNum = ((long)nums[i]) - Integer.MIN_VALUE;
            long bucket = remappedNum / (lt + 1);
            if (bucket2num.containsKey(bucket) ||
                    (bucket2num.containsKey(bucket-1) && Math.abs(remappedNum - bucket2num.get(bucket-1)) <= t) ||
                    (bucket2num.containsKey(bucket+1) && Math.abs(remappedNum - bucket2num.get(bucket+1)) <= t)) {
                return true;
            }

            // previously contain nums[i-1], ..., nums[i-k]
            // update to nums[i], ..., nums[i-k+1]
            if (bucket2num.size() == k) {
                bucket2num.remove((((long)nums[i-k]) - Integer.MIN_VALUE) / (lt + 1));
            }
            bucket2num.put(bucket, remappedNum);
        }

        return false;
    }

    // O(N * logk) time, O(k) space
    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0 || k <= 0 || t < 0) {
            return false;
        }

        TreeSet<Integer> prevK = new TreeSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            Integer floor = prevK.floor(num); // max element smaller than num, O(logk)
            Integer ceil = prevK.ceiling(num); // min element greater than num, O(logk)
            if ((floor != null && (long)num - (long)floor <= t) || (ceil != null && (long)ceil - (long)num <= t)) {
                return true;
            }

            if (prevK.size() == k) {
                prevK.remove(nums[i-k]); // keep nums[i-k+1], ..., nums[i], O(logk)
            }
            prevK.add(num); // O(logk)
        }

        return false;
    }
}
