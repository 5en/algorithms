package tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class InorderTraversal {
    public static List<Integer> inorderTraversal(Node<Integer> root) {
        List<Integer> result = new LinkedList<Integer>();

        Deque<Node<Integer>> stack = new LinkedList<Node<Integer>>();
        Node<Integer> curr = root;
        while (true) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
                continue;
            }

            if (stack.isEmpty()) {
                break;
            }

            Node<Integer> currParent = stack.remove();
            result.add(currParent.key);
            curr = currParent.right;
        }

        return result;
    }

    public static List<Integer> inorderTraversalRecursive(Node<Integer> root) {
        List<Integer> result = new LinkedList<Integer>();
        inorderTraversalRecursiveSR(root, result);

        return result;
    }

    private static void inorderTraversalRecursiveSR(Node<Integer> node, List<Integer> result) {
        if (node != null) {
            inorderTraversalRecursiveSR(node.left, result);
            result.add(node.key);
            inorderTraversalRecursiveSR(node.right, result);
        }
    }
}
