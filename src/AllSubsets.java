import java.util.LinkedList;
import java.util.List;

public class AllSubsets {
    public static void main(String[] args) {
        System.out.println(allSubsets(new int[]{}));
        System.out.println(allSubsets(new int[]{1}));
        System.out.println(allSubsets(new int[]{1,2,3,4}));
    }

    // O(2^N * logN)
    public static List<List<Integer>> allSubsets(int[] a) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        
        for (int bits = 0; bits < Math.pow(2, a.length); bits++) {
            List<Integer> subset = new LinkedList<Integer>();
            
            for (int tmpBits = bits, ai = 0; tmpBits != 0; tmpBits /= 2, ai++) {
                if (tmpBits % 2 != 0) {
                    subset.add(a[ai]);
                }
            }
            
            result.add(subset);
        }
        
        return result;
    }
}
