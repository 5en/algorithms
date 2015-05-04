package interview;

import java.util.NoSuchElementException;

/**
 * The Queue class represents an immutable first-in-first-out (FIFO) queue of objects.
 * @param <E>
 */
public class ExamImmutableQueue<E> {
    private final int length;
    private final IQElement<E> head;
    private final IQElement<E> headNext;
    private final IQElement<E> tail;

    /**
     * Immutable queue element
     */
    private static final class IQElement<E> {
        private final E e;
        private final IQElement<E> prev;

        public IQElement(E e, IQElement<E> prev) {
            this.e = e;
            this.prev = prev;
        }
    }

    /**
     * requires default constructor.
     */
    public ExamImmutableQueue() {
        this(0, null, null, null); // length zero
    }

    public ExamImmutableQueue(IQElement<E> iqe) {
        this(1, iqe, null, iqe); // length one, head and tail point to the same element
    }

    public ExamImmutableQueue(IQElement<E> head, IQElement<E> tail) {
        this(2, head, tail, tail); // length two, headNext and tail point to the same element
    }

    private ExamImmutableQueue(int length, IQElement<E> head, IQElement<E> headNext, IQElement<E> tail) {
        this.length = length;
        this.head = head;
        this.headNext = headNext;
        this.tail = tail;
    }

    // add other constructors if necessary

    /**
     * Returns the queue that adds an item into the tail of this queue without modifying this queue.
     * <pre>
     * e.g.
     * When this queue represents the queue (2, 1, 2, 2, 6) and we enqueue the value 4 into this queue,
     * this method returns a new queue (2, 1, 2, 2, 6, 4)
     * and this object still represents the queue (2, 1, 2, 2, 6) .
     * </pre>
     * If the element e is null, throws IllegalArgumentException.
     * @param e
     * @return
     * @throws IllegalArgumentException
     */
    public ExamImmutableQueue<E> enqueue(E e) {
        if (e == null) {
            throw new IllegalArgumentException("Enqueue null element");
        }

        IQElement<E> iqe = new IQElement<E>(e, this.tail); // iqe.prev points to this.tail

        switch (this.length) {
            case 0:
                return new ExamImmutableQueue<E>(iqe);
            case 1:
                return new ExamImmutableQueue<E>(this.tail, iqe);
            default:
                return new ExamImmutableQueue<E>(this.length + 1, this.head, this.headNext, iqe);
        }
    }

    /**
     * Returns the queue that removes the object at the head of this queue without modifying this queue.
     * <pre>
     * e.g.
     * When this queue represents the queue (7, 1, 3, 3, 5, 1) ,
     * this method returns a new queue (1, 3, 3, 5, 1)
     * and this object still represents the queue (7, 1, 3, 3, 5, 1) .
     * </pre>
     * If this queue is empty, throws java.util.NoSuchElementException.
     * @return
     * @throws java.util.NoSuchElementException
     */
    public ExamImmutableQueue<E> dequeue() {
        switch (this.length) {
            case 0:
                throw new NoSuchElementException("Dequeue empty queue");
            case 1:
                return new ExamImmutableQueue<E>();
            case 2:
                return new ExamImmutableQueue<E>(this.tail);
            case 3:
                return new ExamImmutableQueue<E>(this.headNext, this.tail);
            default:
                IQElement<E> headNextNext = this.tail;
                while (headNextNext.prev != this.headNext) {
                    headNextNext = headNextNext.prev;
                }
                return new ExamImmutableQueue<E>(this.length - 1, this.headNext, headNextNext, this.tail);
        }
    }

    /**
     * Looks at the object which is the head of this queue without removing it from the queue.
     * <pre>
     * e.g.
     * When this queue represents the queue (7, 1, 3, 3, 5, 1),
     * this method returns 7 and this object still represents the queue (7, 1, 3, 3, 5, 1)
     * </pre>
     * If the queue is empty, throws java.util.NoSuchElementException.
     * @return
     * @throws java.util.NoSuchElementException
     */
    public E peek() {
        if (this.length == 0) {
            throw new NoSuchElementException("Peek empty queue");
        }

        return this.head.e;
    }

    /**
     * Returns the number of objects in this queue.
     * @return
     */
    public int size() {
        return this.length;
    }

    public ExamImmutableQueue<E> append(ExamImmutableQueue<E> queue) {
        ExamImmutableQueue<E> result = this;
        ExamImmutableQueue<E> remain = queue;
        while (remain.size() != 0) {
            result = result.enqueue(remain.peek());
            remain = remain.dequeue();
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        IQElement<E> iqe = this.tail;
        for (int i = 0; i < this.length; i++) {
            sb.insert(0, iqe.e + " ");
            iqe = iqe.prev;
        }

        if (sb.length() > 0) {
            sb.delete(sb.length() - 1, sb.length());
        }

        sb.insert(0, '[');
        sb.append(']');

        return sb.toString();
    }

    public static void main(String[] args) {
        ExamImmutableQueue<Integer> queue1 = new ExamImmutableQueue<Integer>();
        queue1 = queue1.enqueue(10).enqueue(11).enqueue(12);
        ExamImmutableQueue<Integer> queue2 = new ExamImmutableQueue<Integer>();
        queue2 = queue2.enqueue(21).enqueue(22).enqueue(23).enqueue(24);
        System.out.println(queue1);
        System.out.println(queue2);

        ExamImmutableQueue<Integer> queue3 = queue1.append(queue2);
        System.out.println(queue1);
        System.out.println(queue2);
        System.out.println(queue3);
    }
}
