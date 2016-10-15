import java.util.Scanner;

public class AncientExpression {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = sc.nextInt();
        }

        System.out.println(dfs(K, nums, 0, 0));
    }

    private static int dfs(int K, int[] nums, int curr, int prev) {

        if (curr == nums.length) {
            return Math.abs(K - prev);
        }

        int opt = Integer.MAX_VALUE;

        // switch nums[curr] with each of nums[curr ... (N-1)], to search all cases
        for (int i = curr; i < nums.length; i++) {
            swap(nums, curr, i);
            opt = Math.min(opt, dfs(K, nums, curr + 1, curr == 0 ? nums[curr] : prev + nums[curr]));
            opt = Math.min(opt, dfs(K, nums, curr + 1, curr == 0 ? nums[curr] : prev * nums[curr]));
            swap(nums, curr, i);
        }

        return opt;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
