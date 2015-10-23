package tree;

// Red-Black Tree
// 1. Every node is either red or black.
// 2. The root is black.
// 3. Every leaf (NIL) is black.
// 4. If a node is red, then both its children are black.
// 5. For each node, all simple paths from the node to descendant leaves contain the same number of black nodes.

public class RBT {
    public static void main(String[] args) {
        Tree tree = new Tree();

        insert(tree, new Node(12, Color.RED));
        insert(tree, new Node(5, Color.RED));
        insert(tree, new Node(18, Color.RED));
        insert(tree, new Node(2, Color.RED));
        insert(tree, new Node(9, Color.RED));
        insert(tree, new Node(15, Color.RED));
        insert(tree, new Node(19, Color.RED));
        insert(tree, new Node(17, Color.RED));
        insert(tree, new Node(13, Color.RED));

        System.out.println(tree.root.color);
        System.out.println(tree.root.left.color + " " + tree.root.right.color);
        System.out.println(tree.root.left.left.color + " " + tree.root.left.right.color);
        System.out.println(tree.root.right.left.color + " " + tree.root.right.right.color);
        System.out
            .println(tree.root.right.left.left.color + " " + tree.root.right.left.right.color);
    }

    public static void leftRotate(Tree t, Node x) {
        Node y = x.right;

        // turn y's left subtree into x's right subtree
        x.right = y.left;
        if (y.left != Tree.NIL) {
            y.left.p = x;
        }

        // turn x's parent into y's parent
        y.p = x.p;
        if (t.root == x) {
            t.root = y;
        } else if (x == x.p.left) {
            x.p.left = y;
        } else {
            // x == x.p.right
            x.p.right = y;
        }

        // turn x into y's left subtree
        y.left = x;
        x.p = y;
    }

    public static void rightRotate(Tree t, Node y) {
        Node x = y.left;

        // turn x's right subtree into y's left subtree
        y.left = x.right;
        if (x.right != Tree.NIL) {
            x.right.p = y;
        }

        // turn y's parent into x's parent
        x.p = y.p;
        if (t.root == y) {
            // i.e. y.p == Tree.NIL
            t.root = x;
        } else if (y == y.p.left) {
            y.p.left = x;
        } else {
            // y == y.p.right
            y.p.right = x;
        }

        // turn y into x's right subtree
        x.right = y;
        y.p = x;
    }

    public static void insert(Tree t, Node z) {
        Node parent = Tree.NIL;
        Node cur = t.root;
        while (cur != Tree.NIL) {
            parent = cur;
            if (cur.key > z.key) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }

        // cur (cur == Tree.NIL) is the position to place z
        // parent is cur's parent

        z.p = parent;
        if (parent == Tree.NIL) {
            // t.root is Tree.NIL
            t.root = z;
        } else if (parent.key > z.key) {
            parent.left = z;
        } else {
            // parent.key < z.key
            parent.right = z;
        }

        // set color red
        z.color = Color.RED;
        z.left = Tree.NIL;
        z.right = Tree.NIL;
        // fix
        insertFixUp(t, z);
    }

    private static void insertFixUp(Tree t, Node z) {
        // invariant:
        // 1. Node z is red.
        // 2. If z.p is the root, then z.p is black.
        // 3. If the tree violates any of the red-black properties, then it violates at most one of them,
        //		and the violation is of either property 2 or property 4.
        // 		If the tree violates property 2, it is because z is the root and is red.
        //		If the tree violates property 4, it is because both z and z.p are red.

        while (z.p.color == Color.RED) {
            // we are done if z is red and z.p is black
            if (z.p == z.p.p.left) {
                Node uncle = z.p.p.right;
                if (uncle.color == Color.RED) {
                    // case 1
                    z.p.color = Color.BLACK;
                    uncle.color = Color.BLACK;
                    z.p.p.color = Color.RED;
                    z = z.p.p;
                    // z may be the root and z is red, fixed by the last statement
                } else {
                    // uncle is black
                    if (z == z.p.right) {
                        // case 2
                        // transform to case 3
                        z = z.p;
                        leftRotate(t, z);
                    }

                    // case 3
                    z.p.color = Color.BLACK;
                    z.p.p.color = Color.RED;
                    rightRotate(t, z.p.p);
                    // done, z.p is black
                }
            } else {
                // z.p == z.p.p.right
                Node uncle = z.p.p.left;
                if (uncle.color == Color.RED) {
                    // case 1
                    z.p.color = Color.BLACK;
                    uncle.color = Color.BLACK;
                    z.p.p.color = Color.RED;
                    z = z.p.p;
                    // z may be the root and z is red, fixed by the last statement
                } else {
                    // uncle is black
                    if (z == z.p.left) {
                        // case 3
                        // transform to case 2
                        z = z.p;
                        rightRotate(t, z);
                    }

                    // case 2
                    z.p.color = Color.BLACK;
                    z.p.p.color = Color.RED;
                    leftRotate(t, z.p.p);
                    // done, z.p is black
                }
            }
        }

        t.root.color = Color.BLACK;
    }

    public static void delete(Tree t, Node z) {
        // y: the node either removed from the tree (z) or moved within the tree (z's successor -> z's position)
        Node y = null;
        Color yOriginColor = null;

        // x: the node that moves into y's original position
        Node x = null;

        if (z.left == Tree.NIL) {
            // case a: z has only right child
            y = z;
            yOriginColor = y.color;
            x = y.right;

            transplant(t, z, z.right);
        } else if (z.right == Tree.NIL) {
            // case b: z has only left child
            y = z;
            yOriginColor = y.color;
            x = y.left;

            transplant(t, z, z.left);
        } else {
            // z has two children
            y = min(z.right);
            yOriginColor = y.color;
            x = y.right;

            if (y.p == z) {
                // case c: z has two children, z's successor is z.right
                x.p = y; // even if x is Tree.NIL
            } else {
                // case d: z has two children, z's successor is not z.right
                transplant(t, y, y.right);
                y.right = z.right;
                y.right.p = y;
            }
            // case c: z has two children, z's successor is z.right
            transplant(t, z, y);
            y.left = z.left;
            y.left.p = y;

            y.color = z.color; // set y's color as z's color
        }

        if (yOriginColor == Color.BLACK) {
            deleteFixUp(t, x);
        }
    }

    private static void deleteFixUp(Tree t, Node x) {
        // any simple path that previously contained y now have one fewer black node
        // x occupies y's (black) original position
        // we can think of y passes its blackness to x

        // x's sibling
        Node w = null;

        while (x != t.root && x.color == Color.BLACK) {
            if (x == x.p.left) {
                w = x.p.right;

                if (w.color == Color.RED) {
                    // case 1
                    w.color = Color.BLACK;
                    x.p.color = Color.RED;
                    leftRotate(t, x.p);
                    w = x.p.right; // w is now black
                }

                // w must be black now

                if (w.left.color == Color.BLACK && w.right.color == Color.BLACK) {
                    // case 2
                    x = x.p; // extra black is moved up
                    w.color = Color.RED;
                } else if (w.right.color == Color.BLACK) {
                    // case 3
                    // transform to case 4
                    w.left.color = Color.BLACK;
                    w.color = Color.RED;
                    rightRotate(t, w);
                    w = x.p.right;
                }

                // case 4
                // w.right is red
                w.color = x.p.color;
                x.p.color = Color.BLACK;
                w.right.color = Color.BLACK;
                leftRotate(t, x.p);
                x = t.root; // done
            } else {
                // x == x.p.right
                w = x.p.left;

                if (w.color == Color.RED) {
                    // case 1
                    w.color = Color.BLACK;
                    x.p.color = Color.RED;
                    rightRotate(t, x.p);
                    w = x.p.left; // w is now black
                }

                // w must be black now

                if (w.left.color == Color.BLACK && w.right.color == Color.BLACK) {
                    // case 2
                    x = x.p; // extra black is moved up
                    w.color = Color.RED;
                } else if (w.left.color == Color.BLACK) {
                    // case 3
                    // transform to case 4
                    w.right.color = Color.BLACK;
                    w.color = Color.RED;
                    leftRotate(t, w);
                    w = x.p.left;
                }

                // case 4
                // w.right is red
                w.color = x.p.color;
                x.p.color = Color.BLACK;
                w.left.color = Color.BLACK;
                rightRotate(t, x.p);
                x = t.root; // done
            }
        }

        x.color = Color.BLACK;
    }

    public static Node min(Node root) {
        while (root != Tree.NIL && root.left != Tree.NIL) {
            root = root.left;
        }

        return root;
    }

    // replace substree rooted at u by the substree rooted at v
    private static void transplant(Tree t, Node u, Node v) {
        v.p = u.p; // even if v is Tree.NIL

        if (u.p == Tree.NIL) {
            // t.root == u
            t.root = v;
        } else if (u == u.p.left) {
            u.p.left = v;
        } else {
            // u == u.p.right
            u.p.right = v;
        }
    }

    private static interface Null {
    }

    private static class Tree {
        public Node              root = Tree.NIL;
        public static final Node NIL  = new NullNode();

        private static class NullNode extends Node implements Null {
            public NullNode() {
                super(-1, Color.BLACK);
            }
        }
    }

    private static class Node {
        public int   key;
        public Color color;
        public Node  left  = Tree.NIL;
        public Node  right = Tree.NIL;
        public Node  p     = Tree.NIL;

        public Node(int key, Color color) {
            this.key = key;
            this.color = color;
        }
    }

    private static enum Color {
        RED, BLACK;
    }
}
