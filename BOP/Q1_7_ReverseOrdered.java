public class Q1_7_ReverseOrdered {
    public static void main(String[] args) {
        int[] a = {3, 5, 2, 7, 1, 6};
        System.out.println(Arrays.toString(a));

        int count = process(a, 0, a.length - 1);
        System.out.println(count);
        System.out.println(Arrays.toString(a));
    }

    // O(NlogN)
    // inclusive [left, right]
    private static int process(int[] a, int left, int right) {
        if (left == right) {
            return 0;
        } else if (left == right - 1) {
            if (a[left] > a[right]) {
                System.out.println("(" + a[left] + ", " + a[right] + ")");

                // sort
                int tmp = a[left];
                a[left] = a[right];
                a[right] = tmp;

                return 1;
            }
        }

        int count = 0;
        int mid = (left + right) / 2;
        count += process(a, left, mid);
        count += process(a, mid + 1, right);

        // revised version of merge sort
        int[] tmp = new int[right - left + 1];
        int tmpi = 0;
        int li = left;
        int ri = mid + 1;
        while (li <= mid && ri <= right) {
            if (a[li] <= a[ri]) {
                tmp[tmpi++] = a[li++];
            } else {
                // a[li] > a[ri]

                tmp[tmpi++] = a[ri];

                // # of reverse ordered paris = (a[li], a[ri]), (a[li+1], a[ri]), ..., (a[mid], a[ri])
                count += (mid - li + 1);
                for (int k = li; k <= mid; k++) {
                    System.out.println("(" + a[k] + ", " + a[ri] + ")");
                }

                ri++;
            }
        }
        if (li > mid) {
            while (ri <= right) {
                tmp[tmpi++] = a[ri++];
            }
        }
        if (ri > right) {
            while (li <= mid) {
                tmp[tmpi++] = a[li++];
            }
        }

        for (tmpi = 0; tmpi < tmp.length; tmpi++) {
            a[left + tmpi] = tmp[tmpi];
        }

        return count;
    }
}
