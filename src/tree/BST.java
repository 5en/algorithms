package tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class BST {
    public static void main(String[] args) {
        //						12
        //				5				18
        //			2		9		15		19
        //						 13	   17

        Node<Integer> root = null;
        root = insert(root, 12);
        root = insert(root, 5);
        root = insert(root, 18);
        root = insert(root, 2);
        root = insert(root, 9);
        root = insert(root, 15);
        root = insert(root, 19);
        root = insert(root, 17);
        root = insert(root, 13);

        //inorderWalk(root);
        //inorderWalk2(root);
        //preorderWalk(root);
        //preorderWalk2(root);
        //postorderWalk(root);
        //postorderWalk2(root);

        //System.out.println(successor(root.right.left.left).key); // 13: successor = 15
        //System.out.println(successor(root.left.right).key); // 9: successor = 12
        //System.out.println(successor(root.right.right)); // 19: successor = null
        //System.out.println(predecessor(root.right.left.left).key); // 13: predecessor = 12
        //System.out.println(predecessor(root.left.right).key); // 9: predecessor = 5
        //System.out.println(predecessor(root.left.left)); // 2: predecessor = null

        System.out.println("root height = " + height(root));
        System.out.println("root height = " + height2(root));
        System.out.println("root height = " + height3(root));

        System.out.println("delete 12");
        root = delete(root, search2(root, 12));
        System.out.println("root = " + root.key); // 13
        System.out.println("root.right.left.left = " + root.right.left.left); // null
        System.out.println("root.right.left.right = " + root.right.left.right.key); // 17
    }

    // recursive
    public static void inorderWalk(Node<Integer> root) {
        if (root != null) {
            inorderWalk(root.left);
            System.out.println(root.key);
            inorderWalk(root.right);
        }
    }

    // iterative
    public static void inorderWalk2(Node<Integer> root) {
        Deque<Node<Integer>> stack = new LinkedList<Node<Integer>>();
        Node<Integer> current = root;

        while (true) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                // current is null (current.parent has no left child)
                if (stack.isEmpty()) {
                    return;
                } else {
                    current = stack.pop();
                    System.out.println(current.key);
                    current = current.right;
                }
            }
        }
    }

    // recursive
    public static void preorderWalk(Node<Integer> root) {
        if (root != null) {
            System.out.println(root.key);
            preorderWalk(root.left);
            preorderWalk(root.right);
        }
    }

    // iterative
    public static void preorderWalk2(Node<Integer> root) {
        Deque<Node<Integer>> stack = new LinkedList<Node<Integer>>();

        if (root != null) {
            stack.push(root);
            while (!stack.isEmpty()) {
                Node<Integer> current = stack.pop();
                System.out.println(current.key);
                if (current.right != null) {
                    stack.push(current.right);
                }
                if (current.left != null) {
                    stack.push(current.left);
                }
            }
        }
    }

    // recursive
    public static void postorderWalk(Node<Integer> root) {
        if (root != null) {
            postorderWalk(root.left);
            postorderWalk(root.right);
            System.out.println(root.key);
        }
    }

    // iterative
    // reversed preorder traversal
    public static void postorderWalk2(Node<Integer> root) {
        Deque<Node<Integer>> output = new LinkedList<Node<Integer>>();
        Deque<Node<Integer>> stack = new LinkedList<Node<Integer>>();

        if (root != null) {
            stack.push(root);
            while (!stack.isEmpty()) {
                Node<Integer> current = stack.pop();
                output.push(current);
                if (current.left != null) {
                    stack.push(current.left);
                }
                if (current.right != null) {
                    stack.push(current.right);
                }
            }
        }

        while (!output.isEmpty()) {
            System.out.println(output.pop().key);
        }
    }

    // recursive
    public static Node<Integer> search(Node<Integer> root, int k) {
        if (root == null || root.key == k) {
            return root;
        }

        if (root.key > k) {
            return search(root.left, k);
        } else {
            return search(root.right, k);
        }
    }

    // iterative
    public static Node<Integer> search2(Node<Integer> root, int k) {
        while (root != null && root.key != k) {
            if (root.key > k) {
                root = root.left;
            } else {
                root = root.right;
            }
        }

        return root;
    }

    public static Node<Integer> min(Node<Integer> root) {
        if (root == null) {
            return null;
        }

        while (root.left != null) {
            root = root.left;
        }

        return root;
    }

    public static Node<Integer> max(Node<Integer> root) {
        if (root == null) {
            return null;
        }

        while (root.right != null) {
            root = root.right;
        }

        return root;
    }

    // smallest node whose key is > node.key
    public static Node<Integer> successor(Node<Integer> z) {
        if (z.right != null) {
            // z has right subtree
            // the successor is the left-most node in z's right subtree
            return min(z.right);
        } else {
            // z do not have right subtree
            // the successor is the lowest ancestor of z whose left child is also an ancestor of z
            Node<Integer> child = z;
            Node<Integer> parent = z.parent;
            while (parent != null && parent.right == child) {
                child = parent;
                parent = parent.parent;
            }
            return parent;
        }
    }

    // largest node whose key is < node.key
    public static Node<Integer> predecessor(Node<Integer> z) {
        if (z.left != null) {
            // z has left subtree
            // the successor is the right-most node in z's left subtree
            return max(z.left);
        } else {
            // z do not have left subtree
            // the predecessor is the lowest ancestor of z whose right child is also an ancestor of z
            Node<Integer> child = z;
            Node<Integer> parent = z.parent;
            while (parent != null && parent.left == child) {
                child = parent;
                parent = parent.parent;
            }
            return parent;
        }

    }

    // iterative
    public static Node<Integer> insert(Node<Integer> root, int key) {
        Node<Integer> parent = null;
        Node<Integer> cur = root;
        while (cur != null) {
            parent = cur;
            if (cur.key > key) {
                cur = cur.left;
            } else if (cur.key < key) {
                cur = cur.right;
            } else {
                // found, do nothing
                return root;
            }
        }

        // curr is null
        // parent should be newNode's parent
        Node<Integer> newNode = new Node<Integer>(key);
        newNode.parent = parent;
        if (parent == null) {
            // root is null
            root = newNode;
        } else if (parent.key > key) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        return root;
    }

    public static Node<Integer> delete(Node<Integer> root, Node<Integer> z) {
        if (z.left == null) {
            // case a: z has only right child
            root = replace(root, z, z.right);
        } else if (z.right == null) {
            // case b: z has only left child
            root = replace(root, z, z.left);
        } else {
            Node<Integer> successor = min(z.right);
            if (successor.parent != z) {
                // case d: z has two children, z's successor is not z.right
                root = replace(root, successor, successor.right);
                successor.right = z.right;
                successor.right.parent = successor;
            }
            // case c: z has two children, z's successor is z.right
            root = replace(root, z, successor);
            successor.left = z.left;
            successor.left.parent = successor;
        }

        return root;
    }

    // replace substree rooted at u by the substree rooted at v
    private static Node<Integer> replace(Node<Integer> root, Node<Integer> u, Node<Integer> v) {
        if (v != null) {
            v.parent = u.parent;
        }

        if (u.parent == null) {
            // root == u
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            // u == u.p.right
            u.parent.right = v;
        }

        return root;
    }

    // recursive
    public static int height(Node<Integer> t) {
        if (t == null) {
            return -1;
        }

        int leftHeight = height(t.left);
        int rightHeight = height(t.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // iterative
    public static int height2(Node<Integer> t) {
        if (t == null) {
            return -1;
        }

        int h = 0;
        Queue<Node<Integer>> l1 = new LinkedList<Node<Integer>>(); // this level
        l1.add(t);
        Queue<Node<Integer>> l2 = new LinkedList<Node<Integer>>(); // next level

        while (true) {
            while (!l1.isEmpty()) {
                Node<Integer> tmp = l1.remove();
                if (tmp.left != null) {
                    l2.add(tmp.left);
                }
                if (tmp.right != null) {
                    l2.add(tmp.right);
                }
            }

            if (l2.isEmpty()) {
                break;
            }

            h++;
            Queue<Node<Integer>> tmp = l1;
            l1 = l2;
            l2 = tmp;
        }

        return h;
    }

    // tail recursion
    public static int height3(Node<Integer> t) {
        if (t == null) {
            return -1;
        }

        Queue<Node<Integer>> queue = new LinkedList<Node<Integer>>();
        queue.add(t);

        return height3SR(queue, 0);
    }

    private static int height3SR(Queue<Node<Integer>> queue, int prevHeight) {
        Queue<Node<Integer>> queueNextLevel = new LinkedList<Node<Integer>>();
        while (!queue.isEmpty()) {
            Node<Integer> node = queue.remove();
            if (node.left != null) {
                queueNextLevel.add(node.left);
            }
            if (node.right != null) {
                queueNextLevel.add(node.right);
            }
        }

        if (queueNextLevel.isEmpty()) {
            return prevHeight;
        } else {
            return height3SR(queueNextLevel, prevHeight + 1);
        }
    }
}
