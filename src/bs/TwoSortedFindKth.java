package bs;

public class TwoSortedFindKth {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 5};
        int[] b = {2, 3, 3, 4};
        System.out.println(find(a, b, 1)); // 1
        System.out.println(find(a, b, 2)); // 2
        System.out.println(find(a, b, 3)); // 2
        System.out.println(find(a, b, 6)); // 3
        System.out.println(find(a, b, 7)); // 4
        System.out.println(find(a, b, 8)); // 5
        System.out.println();
        
        a = new int[]{1, 2, 3};
        b = new int[]{2, 4, 5, 6, 7, 8, 8, 9, 10, 10};
        System.out.println(find(a, b, 10)); // 8
        System.out.println(find(a, b, 3)); // 2
        System.out.println(find(a, b, 4)); // 3
        System.out.println();
        
        a = new int[]{1, 2, 3};
        b = new int[]{4, 4, 5, 6, 7, 8, 8, 9, 10, 10};
        System.out.println(find(a, b, 10)); // 8
        System.out.println(find(a, b, 3)); // 3
        System.out.println(find(b, a, 5)); // 4
        System.out.println();
    }
    
    // log(K)
    public static int find(int[] a, int[] b, int k) {
        if (k > a.length + b.length) {
            return -1;
        }
        
        if (k == 1) {
            return Math.min(a[0], b[0]);
        }
        
        // invariant: (ia+1) + (ib+1) == k
        int ia = k/2 - 1;
        int ib = k - k/2 - 1;
        if (ia >= a.length) {
            int delta = ia - (a.length-1);
            ia -= delta;
            ib += delta;
        } else if (ib >= b.length) {
            int delta = ib - (b.length-1);
            ib -= delta;
            ia += delta;
        }
        
        while (ia >= 0 && ib < b.length) {
            // make sure a[ia] >= b[ib]
            if (a[ia] < b[ib]) {
                int[] tmp = a;
                a = b;
                b = tmp;
                int tmpi = ia;
                ia = ib;
                ib = tmpi;
            }
            
            // if a[ia] <= b[ib+1], found
            if (ib == b.length-1 || b[ib+1] >= a[ia]) {
                return a[ia];
            }
            
            // if a[ia] > b[ib+1], decrease ia, increase ib
            int delta = Math.min(ia, ib) / 2;
            if (delta == 0) {
                delta = 1;
            }
            ia -= delta;
            ib += delta;
        }
        
        if (ia < 0) {
            return b[k - 1];
        }
        
        // ib >= b.length
        return a[k - b.length - 1];
    }
}
