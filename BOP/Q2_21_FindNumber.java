// http://www.cnblogs.com/flyinghearts/archive/2011/03/27/1997285.html

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Q2_21_FindNumber {
    public static void main(String[] args) {
        run(9);
    }

    public static void run(int n) {
        if (n <= 0 || n == 1) {
            return;
        }

        int nn = n * 2;

        // n = (2^t)*a*b
        int tmp = nn;
        int t2 = 1;
        while (tmp % 2 == 0) {
            tmp /= 2;
            t2 *= 2;
        }
        int ab = nn / t2;

        List<Integer> fs = factors(ab);
        Set<Integer> setFs = new HashSet<Integer>(fs);
        while (!setFs.isEmpty()) {
            for (int a : fs) {
                if (setFs.contains(a)) {
                    setFs.remove(a);
                    int b = ab / a;
                    setFs.remove(b);

                    // case 1:  2*n = ((2^t)*a) * b = (2*m+k-1) * k
                    int k1 = Math.min(t2 * a, b);
                    int mm = Math.max(t2 * a, b) - k1 + 1;
                    if (k1 >= 2 && mm % 2 == 0 && mm / 2 >= 1) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(n).append(" = ");
                        for (int i = 0; i <= k1 - 1; i++) {
                            sb.append(mm / 2 + i).append(' ');
                        }
                        sb.delete(sb.length() - 1, sb.length());
                        System.out.println(sb.toString());
                    }

                    // case 1:  2*n = ((2^t)*b) * a = (2*m+k-1) * k
                    int k2 = Math.min(t2 * b, a);
                    if (k2 == k1) {
                        continue;
                    }
                    mm = Math.max(t2 * b, a) - k2 + 1;
                    if (k2 >= 2 && mm % 2 == 0 && mm / 2 >= 1) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(n).append(" = ");
                        for (int i = 0; i <= k2 - 1; i++) {
                            sb.append(mm / 2 + i).append(" + ");
                        }
                        sb.delete(sb.length() - 1, sb.length());
                        System.out.println(sb.toString());
                    }
                }
            }
        }

    }

    private static List<Integer> primeFactors(int n) {
        List<Integer> pfs = new ArrayList<Integer>();

        if (n <= 0 || n == 1) {
            return pfs;
        }

        while (n % 2 == 0) {
            pfs.add(2);
            n /= 2;
        }

        for (int i = 3; i <= n / i; i += 2) {
            while (n % i == 0) {
                pfs.add(i);
                n /= i;
            }
        }

        if (n != 1) {
            pfs.add(n);
        }

        return pfs;
    }

    private static List<Integer> factors(int n) {
        List<Integer> pfs = primeFactors(n);
        List<Integer> fs = new ArrayList<Integer>();

        fs.add(1);
        int prePF = 1;
        int preF = 1;
        int len = fs.size();
        for (int pf : pfs) {
            if (pf == prePF) {
                preF *= pf;
            } else {
                prePF = pf;
                preF = pf;
                len = fs.size();
            }

            for (int i = 0; i < len; i++) {
                fs.add(preF * fs.get(i));
            }
        }

        return fs;
    }
}
