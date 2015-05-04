package tree;

public class Node<T> {
    public final T key;
    public Node<T> left = null;
    public Node<T> right = null;

    public Node<T> parent = null;
    public int height = 0; // AVL (height of empty tree = -1)

    public Node(T key) {
        this.key = key;
    }
}
