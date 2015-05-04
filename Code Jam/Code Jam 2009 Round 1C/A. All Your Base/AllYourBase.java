import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AllYourBase {
	public static void main(String[] args) throws IOException {
	    //Scanner sc = new Scanner(System.in);
        //Scanner sc = new Scanner(new File("C-small-practice.in.txt"));
        Scanner sc = new Scanner(new File("C-large-practice.in.txt"));
        //PrintWriter out = new PrintWriter(System.out);
        PrintWriter out = new PrintWriter("output.txt");
		
		int T = sc.nextInt();
		sc.nextLine();
		for (int t=1; t<=T; t++) {
			out.printf("Case #%d: %s\n", t, process(sc.nextLine()).toString());
		}
		
		out.flush();
		out.close();
	}
	
	private static BigInteger process(String s) {
		char[] chars = s.toCharArray();
		int[] digits = new int[chars.length];
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		
		map.put(chars[0], 1);
		digits[0] = 1;
		
		int minDigit = 0;
		for (int i=1; i<chars.length; i++) {
			if (!map.containsKey(chars[i])) {
				map.put(chars[i], minDigit);
				minDigit++;
				if (minDigit == 1) {
					minDigit = 2;
				}
			}
			digits[i] = map.get(chars[i]);
		}
		
		// base = minDigit
		// base >= 2
		int base = minDigit;
		if (base == 0 || base == 1) {
			base = 2;
		}
		
		BigInteger result = new BigInteger("0");
		BigInteger tmp = new BigInteger("1");
		BigInteger b = new BigInteger(String.valueOf(base));
		for (int i=digits.length-1; i>=0; i--) {
			result = result.add( new BigInteger(String.valueOf(digits[i])).multiply(tmp) );
			tmp = tmp.multiply(b);
		}
		
		return result;
	}
}
