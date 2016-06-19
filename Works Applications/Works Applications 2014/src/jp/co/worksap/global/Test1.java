package jp.co.worksap.global;

public class Test1 {
    public static void main(String[] args) {
        ImmutableQueue<Integer> iq0 = new ImmutableQueue<Integer>();
        ImmutableQueue<Integer> iq1 = iq0.enqueue(1);
        ImmutableQueue<Integer> iq2 = iq1.enqueue(2);
        ImmutableQueue<Integer> iq3 = iq2.enqueue(3);
        ImmutableQueue<Integer> iq4 = iq3.enqueue(4);
        ImmutableQueue<Integer> iq5 = iq4.enqueue(5);

        ImmutableQueue<Integer> iq6 = iq5.dequeue();
        ImmutableQueue<Integer> iq7 = iq6.dequeue();
        ImmutableQueue<Integer> iq8 = iq7.dequeue();
        ImmutableQueue<Integer> iq9 = iq8.dequeue();
        ImmutableQueue<Integer> iq10 = iq9.dequeue();

        System.out.println(iq0);
        System.out.println(iq1);
        System.out.println(iq2);
        System.out.println(iq3);
        System.out.println(iq4);
        System.out.println(iq5);
        System.out.println(iq6);
        System.out.println(iq7);
        System.out.println(iq8);
        System.out.println(iq9);
        System.out.println(iq10);

        System.out.println(iq0.size());
        System.out.println(iq1.size());
        System.out.println(iq2.size());
        System.out.println(iq3.size());
        System.out.println(iq4.size());
        System.out.println(iq5.size());
        System.out.println(iq6.size());
        System.out.println(iq7.size());
        System.out.println(iq8.size());
        System.out.println(iq9.size());
        System.out.println(iq10.size());
    }
}
