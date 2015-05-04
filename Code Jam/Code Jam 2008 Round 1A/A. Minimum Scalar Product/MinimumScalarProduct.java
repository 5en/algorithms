import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MinimumScalarProduct {
	public static void main(String[] args) throws IOException {
	    //Scanner sc = new Scanner(System.in);
        //Scanner sc = new Scanner(new File("A-small-practice.in.txt"));
        Scanner sc = new Scanner(new File("A-large-practice.in.txt"));
        //PrintWriter out = new PrintWriter(System.out);
        PrintWriter out = new PrintWriter("output.txt");
		
		int T = sc.nextInt();
		for (int t=1; t<=T; t++) {
			int n = sc.nextInt();
			List<BigInteger> l1 = new ArrayList<BigInteger>(n);
			List<BigInteger> l2 = new ArrayList<BigInteger>(n);
			for (int i=0; i<n; i++) {
				l1.add(sc.nextBigInteger());
			}
			for (int i=0; i<n; i++) {
				l2.add(sc.nextBigInteger());
			}
			
			out.printf("Case #%d: %s\n", t, process(n, l1, l2));
		}
		
		out.flush();
		out.close();
	}
	
	private static String process(int n, List<BigInteger> l1, List<BigInteger> l2) {
	    Collections.sort(l1);
        Collections.sort(l2, Collections.reverseOrder());
        
        BigInteger result = new BigInteger("0");
        for (int i=0; i<n; i++) {
            result = result.add(l1.get(i).multiply(l2.get(i)));
        }
        
        return result.toString();
	}
}
