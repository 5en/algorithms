import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AlienNumbers {
	public static void main(String[] args) throws IOException {
		//Scanner sc = new Scanner(System.in);
		//Scanner sc = new Scanner(new File("A-small-practice.in.txt"));
		Scanner sc = new Scanner(new File("A-large-practice.in.txt"));
		//PrintWriter out = new PrintWriter(System.out);
		PrintWriter out = new PrintWriter("output.txt");
		
		int N = sc.nextInt();
		sc.nextLine();
		for (int n=1; n<=N; n++) {
			String[] data = sc.nextLine().split(" ");

			out.printf("Case #%d: %s\n", n, process(data));
		}
		
		out.flush();
		out.close();
	}
	
	private static String process(String[] data) {
		String sNum = data[0];
		String source = data[1];
		String target = data[2];
		
		int sBase = source.length();
		Map<Character, Integer> sCharVal = new HashMap<Character, Integer>();
		for (int i=0; i<source.length(); i++) {
			sCharVal.put(source.charAt(i), i);
		}
		
		BigInteger sNumvalue = new BigInteger("0");
		BigInteger sMultplier = new BigInteger("1");
		BigInteger sBaseBigInt = new BigInteger(String.valueOf(sBase));
		for (int i=sNum.length()-1; i>=0; i--) {
			BigInteger digit = new BigInteger(sCharVal.get(sNum.charAt(i)).toString());
			sNumvalue = sNumvalue.add(digit.multiply(sMultplier));
			sMultplier = sMultplier.multiply(sBaseBigInt);
		}
		
		int tBase = target.length();
		Map<Integer, Character> tValChar = new HashMap<Integer, Character>();
		for (int i=0; i<target.length(); i++) {
			tValChar.put(i, target.charAt(i));
		}
		
		StringBuilder sb = new StringBuilder();
		BigInteger zero = new BigInteger("0");
		BigInteger tBaseBigInt = new BigInteger(String.valueOf(tBase));
		while (!sNumvalue.equals(zero)) {
			sb.append(tValChar.get(sNumvalue.mod(tBaseBigInt).intValue()));
			sNumvalue = sNumvalue.divide(tBaseBigInt);
		}
		
		return sb.reverse().toString();
	}
}
