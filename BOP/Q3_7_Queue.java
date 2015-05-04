public class Q3_7_Queue {
    public static void main(String[] args) {
        Stack stack = new Stack(10);
        stack.push(10);
        stack.push(5);
        stack.push(20);
        stack.push(1);
        System.out.println(stack.max());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.max());

        System.out.println();

        Queue queue = new Queue(10);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(10);
        queue.enqueue(4);
        queue.enqueue(5);
        System.out.println(queue.dequeue());
        queue.enqueue(6);
        System.out.println(queue.max());
        queue.enqueue(50);
        System.out.println(queue.max());
    }

    public static class Queue {
        private final int capacity;
        private final Stack s1;
        private final Stack s2;

        public Queue(int capacity) {
            this.capacity = capacity;
            s1 = new Stack(capacity);
            s2 = new Stack(capacity);
        }

        public void enqueue(int e) {
            s2.push(e);
        }

        public int dequeue() {
            if (s1.isEmpty()) {
                while (!s2.isEmpty()) {
                    s1.push(s2.pop());
                }
            }

            return s1.pop();
        }

        public int max() {
            if (s1.isEmpty() && s2.isEmpty()) {
                throw new RuntimeException("empty");
            }

            int maxV = Integer.MIN_VALUE;
            if (!s1.isEmpty() && s1.max() > maxV) {
                maxV = s1.max();
            }
            if (!s2.isEmpty() && s2.max() > maxV) {
                maxV = s2.max();
            }

            return maxV;
        }
    }

    private static class Stack {
        public final int capacity;
        private final int[] items;
        private int topI; // stack top
        private int maxI;
        private final int[] nextMax;

        public Stack(int capacity) {
            this.capacity = capacity;
            items = new int[capacity];
            topI = -1;
            maxI = -1;
            nextMax = new int[capacity];
        }

        public void push(int e) {
            topI++;

            if (topI >= capacity) {
                throw new RuntimeException("full");
            }

            items[topI] = e;

            if (maxI == -1 || e > items[maxI]) {
                nextMax[topI] = maxI;
                maxI = topI;
            } else {
                nextMax[topI] = -1;
            }
        }

        public int pop() {
            if (topI == -1) {
                throw new RuntimeException("empty");
            }

            if (maxI == topI) {
                maxI = nextMax[topI];
            }

            return items[topI--];
        }

        public int max() {
            if (maxI != -1) {
                return items[maxI];
            } else {
                throw new RuntimeException("empty");
            }
        }

        public boolean isEmpty() {
            return topI == -1;
        }
    }
}
