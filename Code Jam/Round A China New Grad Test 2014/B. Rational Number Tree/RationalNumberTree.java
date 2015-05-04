import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;

public class RationalNumberTree {
	public static void main(String[] args) throws IOException {
		//Scanner sc = new Scanner(System.in);
		//Scanner sc = new Scanner(new File("B-small-practice.in.txt"));
		Scanner sc = new Scanner(new File("B-large-practice.in.txt"));
		//PrintWriter out = new PrintWriter(System.out);
		PrintWriter out = new PrintWriter("output.txt");
		
		int T = sc.nextInt();
		for (int t=1; t<=T; t++) {
			int id = sc.nextInt();
			switch (id) {
				case 1:
					BigInteger n = sc.nextBigInteger();
					Frac f = run1(n);
					out.printf("Case #%d: %s %s\n", t, f.numerator, f.denominator);
					break;
				case 2:
					BigInteger p = sc.nextBigInteger();
					BigInteger q = sc.nextBigInteger();
					out.printf("Case #%d: %s\n", t, run2(new Frac(p, q)));
					break;
			}
		}
		
		out.flush();
		out.close();
	}
	
	private static Frac run1(BigInteger n) {
		if (n.intValue() == 1) {
			return new Frac(new BigInteger("1"), new BigInteger("1"));
		}
		
		Frac p = run1(n.divide(new BigInteger("2")));
		if (n.mod(new BigInteger("2")).intValue() == 0) {
			return new Frac(p.numerator, p.numerator.add(p.denominator));
		}
		else {
			return new Frac(p.numerator.add(p.denominator), p.denominator);
		}
	}
	
	private static BigInteger run2(Frac f) {
		if (f.numerator.intValue()==1 && f.denominator.intValue()==1) {
			return new BigInteger("1");
		}
		
		if (f.numerator.compareTo(f.denominator) < 0) {
			return new BigInteger("2").multiply(run2(new Frac(f.numerator, f.denominator.subtract(f.numerator))));
		}
		else {
			return new BigInteger("2").multiply(run2(new Frac(f.numerator.subtract(f.denominator), f.denominator))).add(new BigInteger("1"));
		}
	}
	
	private static class Frac {
		public BigInteger numerator;
		public BigInteger denominator;
		
		public Frac(BigInteger numerator, BigInteger denominator) {
			this.numerator = numerator;
			this.denominator = denominator;
		}
	}
}
