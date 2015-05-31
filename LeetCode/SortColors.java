import java.util.Arrays;

public class SortColors {
    public static void main(String[] args) {
        int[] a1 = {2, 2, 0, 1, 2, 1, 2};
        sortColors(a1);
        System.out.println(Arrays.toString(a1));
        
        int[] a2 = {2, 1, 0};
        sortColors(a2);
        System.out.println(Arrays.toString(a2));
    }

    public static void sortColors(int[] nums) {
        // nums[0...border01-1] == 0
        // nums[border01...border12] == 1
        // nums[border12+1...nums.length-1] == 2
        int border01 = 0;
        int border12 = nums.length - 1;
        
        int curr = 0;
        while (curr <= border12) {
            switch (nums[curr]) {
                case 0:
                    swap(nums, border01, curr);
                    border01++;
                    curr++;
                    break;
                case 1:
                    curr++;
                    break;
                case 2:
                    swap(nums, curr, border12);
                    border12--;
                default:
                    break;
            }
        }
    }
    
    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
