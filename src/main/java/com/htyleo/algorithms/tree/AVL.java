/*
 * for every node in the tree, the height of the left and right subtrees can differ by at most 1.
 * (height of an empty tree = -1)
 */

package com.htyleo.algorithms.tree;

public class AVL {
    public static void main(String[] args) {
        Node<Integer> root = new Node<Integer>(1);
        root = insert(root, 2);
        root = insert(root, 3);
        root = insert(root, 4);
        root = insert(root, 5);
        root = insert(root, 6);
        root = insert(root, 7);
        root = insert(root, 15);
        root = insert(root, 14);
        root = insert(root, 13);
        root = insert(root, 12);
        root = insert(root, 11);
        root = insert(root, 10);
        root = insert(root, 9);
        root = insert(root, 8);

        System.out.println("level 0");
        System.out.println(root.key);
        System.out.println("level 1");
        System.out.print(root.left.key + " ");
        System.out.println(root.right.key);
        System.out.println("level 2");
        System.out.print(root.left.left.key + " ");
        System.out.print(root.left.right.key + " ");
        System.out.print(root.right.left.key + " ");
        System.out.println(root.right.right.key);
        System.out.println("level 3");
        System.out.print(root.left.left.left.key + " ");
        System.out.print(root.left.left.right.key + " ");
        System.out.print(root.left.right.left.key + " ");
        System.out.print(root.left.right.right + " ");
        System.out.print(root.right.left.left.key + " ");
        System.out.print(root.right.left.right.key + " ");
        System.out.print(root.right.right.left.key + " ");
        System.out.println(root.right.right.right.key);
        System.out.println("level 4");
        System.out.println(root.right.left.left.left.key);
    }

    public static Node<Integer> insert(Node<Integer> root, int key) {
        if (root == null) {
            root = new Node<Integer>(key);
        } else if (root.key > key) {
            root.left = insert(root.left, key);

            if (height(root.left) - height(root.right) == 2) {
                if (root.left.key > key) {
                    // left-left
                    root = singleRotationRight(root);
                } else {
                    // left-right
                    root = doubleRotationLeftRight(root);
                }
            }
        } else if (root.key < key) {
            root.right = insert(root.right, key);

            if (height(root.right) - height(root.left) == 2) {
                if (root.right.key > key) {
                    // right-left
                    root = doubleRotationRightLeft(root);
                } else {
                    // right-right
                    root = singleRotationLeft(root);
                }
            }
        }

        // else the key is already in the tree and we do nothing

        root.height = Math.max(height(root.left), height(root.right)) + 1;

        return root;
    }

    //        k2                k1
    //    k1      Z     ->   X      k2
    // [X]   Y                     Y   Z
    //
    // k2 must have a left child
    // performed when height(k2.left)-height(k2.right) > 1
    private static Node<Integer> singleRotationRight(Node<Integer> k2) {
        Node<Integer> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;

        // pay attention to the order
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;

        return k1;
    }

    //        k2                k1
    //    k1      Z     <-   X      k2
    //  X   Y                     Y    [Z]
    //
    // k1 must have a right child
    // performed when height(k1.right)-height(k1.left) > 1
    private static Node<Integer> singleRotationLeft(Node<Integer> k1) {
        Node<Integer> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;

        // pay attention to the order
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;

        return k2;
    }

    //          k3                      k2
    //      A       k1      ->      k3      k1
    //         [k2]     D          A    B  C    D
    //        B    C
    // k3 must have a right child k1 and k1 must have a left child k2
    // performed when height(k3.right)-height(k3.left) > 1
    private static Node<Integer> doubleRotationRightLeft(Node<Integer> k3) {
        k3.right = singleRotationRight(k3.right);
        return singleRotationLeft(k3);

        /*
        // directly
        Node<Integer> k1 = k3.right;
        Node<Integer> k2 = k1.left;
        k1.left = k2.right;
        k3.right = k2.left;
        k2.left = k3;
        k2.right = k1;
        // pay attention to the order
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
        k3.height = Math.max(height(k3.left), height(k3.right)) + 1;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        return k2;
         */
    }

    //          k3                      k2
    //      k1       D      ->      k1      k3
    //   A     [k2]                A    B  C    D
    //        B    C
    // k3 must have a left child k1 and k1 must have a right child k2
    // performed when height(k3.left)-height(k3.right) > 1
    private static Node<Integer> doubleRotationLeftRight(Node<Integer> k3) {
        k3.left = singleRotationLeft(k3.left);
        return singleRotationRight(k3);

        /*
        // directly
        Node<Integer> k1 = k3.left;
        Node<Integer> k2 = k1.right;
        k1.right = k2.left;
        k3.left = k2.right;
        k2.left = k1;
        k2.right = k3;
        // pay attention to the order
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
        k3.height = Math.max(height(k3.left), height(k3.right)) + 1;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        return k2;
         */
    }

    private static int height(Node<Integer> node) {
        return (node == null) ? -1 : node.height;
    }
}
