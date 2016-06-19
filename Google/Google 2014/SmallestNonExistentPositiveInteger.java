public class SmallestNonExistentPositiveInteger {
    public static void main(String[] args) {
        System.out.println(find(new int[]{2, 4, 3})); // 1
        System.out.println(find(new int[]{2, 10, 1, 3})); // 4
        System.out.println(find(new int[]{2, 1, 4, 10})); // 3
    }
    
    // O(N) time, O(1) space
    public static int find(int[] a) {
        int N = a.length;
        
        // Iteratively move x to a[x-1]
        for (int i = 0; i < N; i++) {
            int x = a[i];
            while (x >= 1 && x <= N && a[x-1] != x) {
                int tmp = a[x-1];
                a[x-1] = x;
                x = tmp;
            }
        }
        
        for (int i = 1; i <= N; i++) {
            if (a[i-1] != i) {
                return i;
            }
        }
        
        return N+1;
    }
}
