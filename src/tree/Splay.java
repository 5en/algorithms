/*
 * Any M consecutive tree operations starting from an empty tree take at most O(MlogN) time. (amortized)
 *
 * The basic idea of the splay tree is that after a node is accessed,
 * it is pushed to the root by a series of AVL tree rotations.
 *
 * zig-zag case: double rotation
 * zid-zig case: single rotation
 *
 * Splaying not only moves the accessed node to the root,
 * but also has the effect of roughly halving the depth of most nodes on the access path
 * (some shallow nodes are pushed down at most two levels).
 */

package tree;

public class Splay {
    public static void main(String[] args) {
        Node<Integer> root = null;
        root = insert(root, 1);
        root = insert(root, 2);
        root = insert(root, 3);
        root = insert(root, 4);
        root = insert(root, 5);
        root = insert(root, 6);
        root = insert(root, 7);

        System.out.println("After insertion");
        System.out.print(root.key + " ");
        System.out.print(root.left.key + " ");
        System.out.print(root.left.left.key + " ");
        System.out.print(root.left.left.left.key + " ");
        System.out.print(root.left.left.left.left.key + " ");
        System.out.print(root.left.left.left.left.left.key + " ");
        System.out.println(root.left.left.left.left.left.left.key);

        root = access(root, 1);

        System.out.println("After access");
        System.out.println(root.key);
        System.out.println(root.right.key);
        System.out.println(root.right.left.key + " " + root.right.right.key);
        System.out.println(root.right.left.left.key + " " + root.right.left.right.key);
        System.out.println(root.right.left.left.right.key);

        Node<Integer> node1 = new Node<Integer>(1);
        Node<Integer> node2 = new Node<Integer>(2);
        Node<Integer> node4 = new Node<Integer>(4);
        Node<Integer> node5 = new Node<Integer>(5);
        Node<Integer> node6 = new Node<Integer>(6);
        Node<Integer> node7 = new Node<Integer>(7);
        node4.left = node2;
        node2.parent = node4;
        node4.right = node6;
        node6.parent = node4;
        node2.left = node1;
        node1.parent = node2;
        node6.left = node5;
        node5.parent = node6;
        node6.right = node7;
        node7.parent = node6;

        Node<Integer> t2 = delete(node4, 6);
        System.out.println("After deletion");
        System.out.println(t2.key);
        System.out.println(t2.left.key + " " + t2.right.key);
        System.out.println(t2.left.left.key);
        System.out.println(t2.left.left.left.key);
    }

    public static Node<Integer> insert(Node<Integer> root, int key) {
        Node<Integer> parent = null;
        Node<Integer> curr = root;
        while (curr != null) {
            parent = curr;
            if (curr.key > key) {
                curr = curr.left;
            } else if (curr.key < key) {
                curr = curr.right;
            } else {
                // found
                splay(curr);
                return curr;
            }
        }

        Node<Integer> newNode = new Node<Integer>(key);
        newNode.parent = parent;
        if (parent == null) {
            return newNode;
        } else if (parent.key > key) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        splay(newNode);

        return newNode;
    }

    public static Node<Integer> access(Node<Integer> root, int key) {
        Node<Integer> node = BST.search(root, key);
        if (node == null) {
            return root;
        } else {
            splay(node);
            return node;
        }
    }

    public static Node<Integer> delete(Node<Integer> root, int key) {
        Node<Integer> node = BST.search(root, key);
        if (node == null) {
            return root;
        } else {
            splay(node);
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                Node<Integer> leftMax = BST.max(node.left);
                splay(leftMax);
                leftMax.right = node.right;
                return leftMax;
            }
        }
    }

    private static void splay(Node<Integer> node) {
        Node<Integer> p = node.parent;
        while (p != null) {
            if (p.parent == null) {
                // rotation containing two levels
                rotationWithTwoLevels(node);
            } else {
                // rotation containing three levels
                rotationWithThreeLevels(node);
            }

            // after one rotation, node has been pushed up
            p = node.parent;
        }
    }

    private static void rotationWithTwoLevels(Node<Integer> curr) {
        Node<Integer> p = curr.parent;
        Node<Integer> pp = p.parent;

        if (curr == p.left) {
            //          pp                 pp
            //          p                 curr
            //    curr      Z     ->   X        p
            //  X      Y                     Y    Z
            //
            Node<Integer> Y = curr.right;

            curr.right = p;
            p.parent = curr;

            p.left = Y;
            if (Y != null) {
                Y.parent = p;
            }
        } else {
            // curr == p.right

            //          pp                  pp
            //         curr                 p
            //     p         Z    <-    X      curr
            //  X    Y                       Y      Z
            //
            Node<Integer> Y = curr.left;

            curr.left = p;
            p.parent = curr;

            p.right = Y;
            if (Y != null) {
                Y.parent = p;
            }
        }

        curr.parent = pp;
        if (pp != null) {
            if (pp.left == p) {
                pp.left = curr;
            } else {
                pp.right = curr;
            }
        }
    }

    private static void rotationWithThreeLevels(Node<Integer> curr) {
        Node<Integer> p = curr.parent;
        Node<Integer> pp = p.parent;
        Node<Integer> ppp = pp.parent;

        if (p == pp.left) {
            if (curr == p.left) {
                // left-left, zig-zig

                //            ppp                          ppp
                //            pp                           curr
                //        p        D                   A         p
                //  curr    C               ->                B     pp
                // A    B                                         C    D

                Node<Integer> B = curr.right;
                Node<Integer> C = p.right;

                p.right = pp;
                pp.parent = p;

                curr.right = p;
                p.parent = curr;

                pp.left = C;
                if (C != null) {
                    C.parent = pp;
                }

                p.left = B;
                if (B != null) {
                    B.parent = p;
                }
            } else {
                // left-right, zig-zag

                //            ppp                          ppp
                //            pp                           curr
                //        p        D                   p         pp
                //     A    curr               ->   A    B     C    D
                //         B    C

                Node<Integer> B = curr.left;
                Node<Integer> C = curr.right;

                curr.left = p;
                p.parent = curr;

                curr.right = pp;
                pp.parent = curr;

                p.right = B;
                if (B != null) {
                    B.parent = p;
                }

                pp.left = C;
                if (C != null) {
                    C.parent = pp;
                }
            }
        } else {
            if (curr == p.left) {
                // right-left, zag-zig

                //     ppp                           ppp
                //     pp                            curr
                //  A      p                    pp         p
                //    curr    D     ->        A    B     C   D
                //   B    C

                Node<Integer> B = curr.left;
                Node<Integer> C = curr.right;

                curr.left = pp;
                pp.parent = curr;

                curr.right = p;
                p.parent = curr;

                pp.right = B;
                if (B != null) {
                    B.parent = pp;
                }

                p.left = C;
                if (C != null) {
                    C.parent = p;
                }
            } else {
                // right-right, zag-zag

                //            ppp                          ppp
                //           curr                           pp
                //        p        D                   A         p
                //    pp    C               <-                B     curr
                // A     B                                        C     D

                Node<Integer> B = p.left;
                Node<Integer> C = curr.left;

                p.left = pp;
                pp.parent = p;

                curr.left = p;
                p.parent = curr;

                pp.right = B;
                if (B != null) {
                    B.parent = pp;
                }

                p.right = C;
                if (C != null) {
                    C.parent = p;
                }
            }
        }

        curr.parent = ppp;
        if (ppp != null) {
            if (ppp.left == pp) {
                ppp.left = curr;
            } else {
                ppp.right = curr;
            }
        }
    }
}
