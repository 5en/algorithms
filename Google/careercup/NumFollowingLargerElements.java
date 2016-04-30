// Given an array of elements, return an array of values pertaining to how many elements are greater than that element remaining in the array. 
// Brute force is obvious, but must be done faster than O(n^2)
//        Ex. [3,4,5,9,2,1,3]
//        Return [3, 2, 1, 0, 1, 1, 0]
//        First element is 3 because 3<4,5,9. Second element is 2 because 4< 5,9 etc

import java.util.Arrays;

public class NumFollowingLargerElements {

    public static void main(String[] args) {
        int[] result = numFollowingLargerElements(new int[] { 3, 4, 5, 9, 2, 1, 3 });
        System.out.println(Arrays.toString(result));
    }

    // O(N*logN) merge sort
    public static int[] numFollowingLargerElements(int[] nums) {
        int N = nums.length;
        Element[] elements = new Element[N];
        for (int i = 0; i < N; i++) {
            elements[i] = new Element(i, nums[i]);
        }

        mergeSort(elements, 0, N - 1);

        int[] result = new int[N];
        for (Element element : elements) {
            result[element.index] = element.count;
        }

        return result;
    }

    private static void mergeSort(Element[] elements, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = (left + right) / 2;
        mergeSort(elements, left, mid);
        mergeSort(elements, mid + 1, right);

        Element[] tmp = new Element[right - left + 1];
        int ti = 0;
        int li = left;
        int ri = mid + 1;
        while (li <= mid && ri <= right) {
            Element el = elements[li];
            Element er = elements[ri];
            if (el.num < er.num) {
                el.count += right - ri + 1;
                tmp[ti++] = el;
                li++;
            } else {
                tmp[ti++] = er;
                ri++;
            }
        }

        while (li <= mid) {
            tmp[ti++] = elements[li++];
        }
        while (ri <= right) {
            tmp[ti++] = elements[ri++];
        }

        for (int i = 0; i < tmp.length; i++) {
            elements[left + i] = tmp[i];
        }
    }

    private static class Element {
        int index;
        int num;
        int count;

        public Element(int index, int num) {
            this.index = index;
            this.num = num;
            this.count = 0;
        }
    }
}
