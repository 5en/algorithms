// http://stackoverflow.com/questions/1586858/find-the-smallest-integer-not-in-a-list

public class SmallestPositiveMissingNum {
    public static void main(String[] args) {
        System.out.println(find(new int[]{2, 4, 3})); // 1
        System.out.println(find(new int[]{2, 10, 1, 2})); // 3
        System.out.println(find(new int[]{3, 1, 2, 4})); // 5
    }
    
    // O(N), O(1) space
    // if 1 <= x <= N, place it at a[x-1]
    public static int find(int[] a) {
        int N = a.length;
        
        for (int i = 0; i < N; i++) {
            int x = a[i];
            while (x >= 1 && x <= N && a[x-1] != x) { // 1 <= result <= N+1
                int tmp = a[x-1];
                a[x-1] = x;
                x = tmp;
            }
        }
        
        for (int i = 0; i < N; i++) {
            if (a[i] != i+1) {
                return i+1;
            }
        }
        
        return N+1;
    }
}
