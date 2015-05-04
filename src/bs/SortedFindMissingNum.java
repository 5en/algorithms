package bs;

public class SortedFindMissingNum {
    public static void main(String[] args) {
        find(new int[]{1, 2, 2, 3, 5, 6, 7, 9, 9, 11, 15}, -5, 10);
    }

    // a[] is sorted array, find missing elements in [p,q]
    public static void find(int[] a, int p, int q) {
        findSR(a, 0, a.length - 1, p, q);
    }

    private static void findSR(int[] a, int low, int high, int p, int q) {
        if (p > q) {
            return;
        }

        if (low > high) {
            System.out.println("[" + p + "," + q + "]");
            return;
        }

        int mid = (low + high) / 2;
        if (a[mid] > q) {
            findSR(a, low, mid - 1, p, q);
        } else if (a[mid] < p) {
            findSR(a, mid + 1, high, p, q);
        } else {
            // p <= a[mid] <= q
            findSR(a, low, mid - 1, p, a[mid] - 1);
            findSR(a, mid + 1, high, a[mid] + 1, q);
        }
    }
}
