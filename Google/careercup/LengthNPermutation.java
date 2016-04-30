// Given a set of values 0-9, return all permutations of that set of length n.
// Example: n=2, set ={2,3,4} Return: {2,2}, {3,3}, {4,4}, {2,3}, {3,2}, {3,4}, {4,3}, {2,4}, {4,2}

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class LengthNPermutation {
    public static void main(String[] args) {
        Set<Integer> nums = new HashSet<Integer>();
        nums.add(2);
        nums.add(3);
        nums.add(4);

        List<List<Integer>> result = perm(nums, 2);
        for (List<Integer> perm : result) {
            System.out.println(perm);
        }
    }

    public static List<List<Integer>> perm(Set<Integer> nums, int L) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        permSub(nums, L, result, new LinkedList<Integer>());
        return result;
    }

    public static void permSub(Set<Integer> nums, int L, List<List<Integer>> result, List<Integer> p) {
        if (p.size() == L) {
            result.add(new ArrayList<Integer>(p));
            return;
        }

        for (int num : nums) {
            p.add(num);
            permSub(nums, L, result, p);
            p.remove(p.size() - 1);
        }
    }
}
