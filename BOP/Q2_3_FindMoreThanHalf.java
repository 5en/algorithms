// find the element that its # of occurrences is more than half of the total # of elements

public class Q2_3_FindMoreThanHalf {
    public static void main(String[] args) {
        int[] a = {3, 4, 5, 2, 2, 4, 2, 2, 2};
        System.out.println(find(a));
    }

    public static int find(int[] a) {
        int candidate = 0;
        int count = 0;
        
        for (int element : a) {
            if (count == 0) {
                candidate = element;
                count = 1;
            } else {
                if (candidate == element) {
                    count++;
                } else {
                    count--;
                }
            }
        }
        
        return candidate;
    }
}
