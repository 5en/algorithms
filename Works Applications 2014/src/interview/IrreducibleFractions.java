package interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Given two numbers M and N, 
// p is from [M,N] and q is from [1,p-1], 
// find all irreducible fractions of p/q.

public class IrreducibleFractions {
    public static void main(String[] args) {
        System.out.println(findFracs(1, 4));
    }

    public static List<Frac> findFracs(int M, int N) {
        Set<Frac> fracs = new HashSet<Frac>();

        for (int p = M; p <= N; p++) {
            for (int q = 1; q <= p - 1; q++) {
                int theGcd = gcd(p, q);
                fracs.add(new Frac(p / theGcd, q / theGcd));
            }
        }

        List<Frac> sortedFracs = new ArrayList<Frac>(fracs);
        Collections.sort(sortedFracs, new Comparator<Frac>() {
            @Override
            public int compare(Frac f1, Frac f2) {
                double v1 = f1.p / f1.q;
                double v2 = f2.p / f2.q;

                return v1 < v2 ? -1 : (v1 == v2 ? 0 : 1);
            }
        });

        return sortedFracs;
    }

    private static int gcd(int x, int y) {
        while (x != 0 && y != 0) {
            if (x >= y) {
                x %= y;
            } else {
                y %= x;
            }
        }

        return x == 0 ? y : x;
    }

    private static class Frac {
        // p/q
        public final int p;
        public final int q;

        public Frac(int p, int q) {
            this.p = p;
            this.q = q;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null) {
                return false;
            }

            if (this == o) {
                return true;
            }

            if (this.getClass() != o.getClass()) {
                return false;
            }

            Frac f = (Frac) o;
            return this.p == f.p && this.q == f.q;
        }

        @Override
        public int hashCode() {
            return new Integer(p).hashCode() + new Integer(q).hashCode();
        }

        @Override
        public String toString() {
            return p + "/" + q;
        }
    }
}
