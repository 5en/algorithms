import java.lang.reflect.Constructor;
import java.util.List;

public class BinaryTreeToDoubleLinkedList {
    public static void main(String[] args) {
        //          10
        //      6       14
        //   4    8  12   16
        //
        // 4=6=8=10=12=14=16

        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(6);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(8);
        root.right = new TreeNode(14);
        root.right.left = new TreeNode(12);
        root.right.right = new TreeNode(16);

        ListNode node = convert(root);
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static ListNode convert(TreeNode root) {
        return convertSR(root).head;
    }

    private static ConvertResult convertSR(TreeNode root) {
        if (root == null) {
            return new ConvertResult(null, null);
        }

        ListNode rootNode = new ListNode(root.val);
        ListNode head = rootNode;
        ListNode tail = rootNode;

        ConvertResult left = convertSR(root.left);
        ConvertResult right = convertSR(root.right);

        if (left.head != null) {
            head = left.head;
            left.tail.next = rootNode;
        }
        if (right.head != null) {
            rootNode.next = right.head;
            tail = right.tail;
        }

        return new ConvertResult(head, tail);
    }

    private static class ConvertResult {
        ListNode head;
        ListNode tail;

        ConvertResult(ListNode head, ListNode tail) {
            this.head = head;
            this.tail = tail;
        }
    }

    private static class ListNode {
        int val;
        ListNode prev;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
