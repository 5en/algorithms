// https://leetcode.com/contest/10/problems/k-th-smallest-in-lexicographical-order/

import org.junit.Assert;

public class KthSmallestInLexicographicalOrder {

    public static void main(String[] args) {
        Assert.assertEquals(1, calTreeNodeCount(1, 1));
        Assert.assertEquals(2, calTreeNodeCount(1, 10));
        Assert.assertEquals(55, calTreeNodeCount(1, 143));
        Assert.assertEquals(0, calTreeNodeCount(2, 1));
        Assert.assertEquals(1, calTreeNodeCount(2, 10));
        Assert.assertEquals(11, calTreeNodeCount(2, 143));
        Assert.assertEquals(55, calTreeNodeCount(8, 843));
        Assert.assertEquals(111, calTreeNodeCount(2, 843));

        Assert.assertEquals(1, findLastTreeNode(1, 1));
        Assert.assertEquals(10, findLastTreeNode(1, 10));
        Assert.assertEquals(19, findLastTreeNode(1, 100));
        Assert.assertEquals(19, findLastTreeNode(1, 143));
        Assert.assertEquals(2, findLastTreeNode(2, 10));
        Assert.assertEquals(29, findLastTreeNode(2, 143));
        Assert.assertEquals(89, findLastTreeNode(8, 843));
        Assert.assertEquals(299, findLastTreeNode(2, 843));

        Assert.assertEquals(10, new KthSmallestInLexicographicalOrder().findKthNumber(13, 2));
        Assert.assertEquals(1, new KthSmallestInLexicographicalOrder().findKthNumber(2, 1));
        Assert.assertEquals(22, new KthSmallestInLexicographicalOrder().findKthNumber(100, 16));
        System.out.println(new KthSmallestInLexicographicalOrder().findKthNumber(719885387, 209989719));
    }

    public int findKthNumber(int n, int k) {
        int skipCount = 0;
        int startRoot = 0;
        for (; startRoot < 9 && skipCount < k; startRoot++) {
            int treeNodeCount = calTreeNodeCount(startRoot + 1, n);
            if (skipCount + treeNodeCount > k) {
                break;
            }

            skipCount += treeNodeCount;
        }

        int num = 1;
        if (skipCount > 0) {
            num = findLastTreeNode(startRoot, n);
            k -= (skipCount - 1);
        }

        // if start at 1, find kth, then we have k-1 iterations
        for (int i = 1; i <= k - 1; i++) {

            //  pre-order for tree
            //
            //                       1                                      2
            //      10              11      ...      19             20      ...     299
            //  100 ... 109     110...110        190...199      200...299
            // 1000

            // left child
            // or right sibling
            // or which to another subtree
            if (num <= n / 10) {
                num *= 10;
            } else {
                while (num % 10 == 9 || num == n) {
                    num /= 10;
                }
                num++;
            }
        }

        return num;
    }

    private static int calTreeNodeCount(int root, int n) {
        if (root > n) {
            return 0;
        }

        // always have 1 on the top level
        int result = 1;
        int numLevels = calNumDigits(n);
        int numLevelNodes = 10;
        for (int i = 2; i <= numLevels - 1; i++) {
            result += numLevelNodes;
            numLevelNodes *= 10;
        }

        // bottom level
        int btmLevelHead = root * numLevelNodes;
        if (n >= btmLevelHead) {
            result += Math.min(numLevelNodes, n - btmLevelHead + 1);
        }

        return result;
    }

    private static int findLastTreeNode(int root, int n) {
        int result = root;
        int numLevels = calNumDigits(n);
        for (int i = 2; i <= numLevels - 1; i++) {
            result = result * 10 + 9;
        }

        // bottom level
        if (n >= result * 10) {
            result = Math.min(result * 10 + 9, n);
        }

        return result;
    }

    private static int calNumDigits(int n) {
        if (n == 0) {
            return 1;
        }

        int result = 0;
        while (n != 0) {
            result++;
            n /= 10;
        }

        return result;
    }

}
