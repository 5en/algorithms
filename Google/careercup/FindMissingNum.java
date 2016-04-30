// You are given an array of n unique integer numbers 0 <= x_i < 2 * n
// Print all integers 0 <= x < 2 * n that are not present in this array.
//        Example:
//        find_missing([0]) = [1]
//        find_missing([0, 2, 4]) = [1, 3, 5] # because all numbers are [0, 1, 2, 3, 4, 5]
//        find_missing([]) = []
//        find_missing([0, 1, 4, 5]) = [2, 3, 6, 7] # because all numbers are [0, 1, 2, 3, 4, 5, 6, 7]
//
// Quirks are about requirements:
// Time complexity O(n) -
//      BUT there should be some fixed constant C independent of size of input
//      such that every element of array is written/read < C times, so radix sorting the array is a no go.
//
// Space complexity O(1) -
//      you may modify the initial array, BUT sorted(initial_array) must equal sorted(array_after_executing_program)
//      AND you can't store integers outside range [0, 2n) in this array (imagine that it's an array of uint32_t).

public class FindMissingNum {

    public static void main(String[] args) {
        findMissingNum(new int[] { 0 });
        findMissingNum(new int[] { 0, 2, 4 });
        findMissingNum(new int[] { 0, 4, 5, 1 });
    }

    // O(N) time, O(1) space
    public static void findMissingNum(int[] nums) {
        int N = nums.length;

        // place 0 <= x < N at nums[x]
        for (int i = 0; i < N; i++) {
            while (nums[i] < N && nums[i] != i) {
                // swap nums[i] <-> nums[nums[i]]
                int tmp = nums[i];
                nums[i] = nums[tmp];
                nums[tmp] = tmp;
            }
        }

        // find missing nums in [0, N)
        for (int i = 0; i < N; i++) {
            if (nums[i] != i) {
                System.out.print(i + " ");
            }
        }

        // place N <= x < 2*N at nums[x - N]
        for (int i = 0; i < N; i++) {
            while (nums[i] >= N && nums[i] != i + N) {
                // swap nums[i] <-> nums[nums[i] - N]
                int tmp = nums[i];
                nums[i] = nums[tmp - N];
                nums[tmp - N] = tmp;
            }
        }

        // find missing nums in [N, 2N)
        for (int i = 0; i < N; i++) {
            if (nums[i] != i + N) {
                System.out.print(i + N + " ");
            }
        }

        System.out.println();
    }

}
