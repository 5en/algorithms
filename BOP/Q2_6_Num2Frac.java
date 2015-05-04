// x = 0.a1a2...an
// => 10^n*x = a1a2...an
// => x = a1a2...an/10^n
//
// x = 0.a1a2...an(b1...bm)
// => 10^n*x = a1a2...an.(b1...bm)
// => 10^(n+m)*x = a1a2...anb1...bm.(b1...bm)
// => 10^(n+m)*x = a1a2...anb1...bm + 10^n*x - a1a2...an
// => 10^n(10^m-1)*x = a1a2...an(10^m-1) + b1...bm

import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Q2_6_Num2Frac {
    public static void main(String[] args) {
        Frac f = toFrac("2.625");
        System.out.println(f.n + "/" + f.d);
        f = toFrac("1.3(333)");
        System.out.println(f.n + "/" + f.d);
        f = toFrac("0.285714(285714)");
        System.out.println(f.n + "/" + f.d);
    }

    public static Frac toFrac(String num) throws NumberFormatException {
        String regexLimited = "(\\d+)\\.(\\d+)";
        Pattern pLimited = Pattern.compile(regexLimited);
        Matcher mLimited = pLimited.matcher(num);
        if (mLimited.matches()) {
            BigInteger integer = new BigInteger(mLimited.group(1));
            BigInteger fractional = new BigInteger(mLimited.group(2));

            StringBuilder pow10_n = new StringBuilder().append('1');
            for (int i = 0; i < mLimited.group(2).length(); i++) {
                pow10_n.append('0');
            }

            Frac result = new Frac(fractional, new BigInteger(pow10_n.toString()));
            result.add(integer);

            return result;
        }

        String regexUnlimited = "(\\d+)\\.(\\d+)\\((\\d+)\\)";
        Pattern pUnlimited = Pattern.compile(regexUnlimited);
        Matcher mUnlimited = pUnlimited.matcher(num);
        if (mUnlimited.matches()) {
            BigInteger integer = new BigInteger(mUnlimited.group(1));
            BigInteger fractional = new BigInteger(mUnlimited.group(2));
            BigInteger repeatFractional = new BigInteger(mUnlimited.group(3));

            StringBuilder pow10_n = new StringBuilder().append('1');
            for (int i = 0; i < mUnlimited.group(2).length(); i++) {
                pow10_n.append('0');
            }
            StringBuilder pow10_m_sub1 = new StringBuilder();
            for (int i = 0; i < mUnlimited.group(3).length(); i++) {
                pow10_m_sub1.append('9');
            }
            BigInteger int_pow10_n = new BigInteger(pow10_n.toString());
            BigInteger int_pow10_m_sub1 = new BigInteger(pow10_m_sub1.toString());

            BigInteger n = int_pow10_m_sub1.multiply(fractional).add(repeatFractional);
            BigInteger d = int_pow10_n.multiply(int_pow10_m_sub1);

            Frac result = new Frac(n, d);
            result.add(integer);

            return result;
        }

        return null;
    }

    private static class Frac {
        public BigInteger n; // n
        public BigInteger d; // d 

        public Frac(BigInteger n, BigInteger d) {
            BigInteger gcd = n.gcd(d);
            this.n = n.divide(gcd);
            this.d = d.divide(gcd);
        }

        public void add(BigInteger bigI) {
            n = n.add(d.multiply(bigI));
        }
    }
}
