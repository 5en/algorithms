import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ReadPhoneNumber {
	private static final Map<Character, String> numMap = new HashMap<Character, String>();
	private static final Map<Integer, String> countMap = new HashMap<Integer, String>();
	
	static {
		numMap.put('0', "zero");
		numMap.put('1', "one");
		numMap.put('2', "two");
		numMap.put('3', "three");
		numMap.put('4', "four");
		numMap.put('5', "five");
		numMap.put('6', "six");
		numMap.put('7', "seven");
		numMap.put('8', "eight");
		numMap.put('9', "nine");
		
		countMap.put(2, "double");
		countMap.put(3, "triple");
		countMap.put(4, "quadruple");
		countMap.put(5, "quintuple");
		countMap.put(6, "sextuple");
		countMap.put(7, "septuple");
		countMap.put(8, "octuple");
		countMap.put(9, "nonuple");
		countMap.put(10, "decuple");
	}
	
	public static void main(String[] args) throws IOException {
		//Scanner sc = new Scanner(System.in);
		//Scanner sc = new Scanner(new File("A-small-practice.in.txt"));
		Scanner sc = new Scanner(new File("A-large-practice.in.txt"));
		//PrintWriter out = new PrintWriter(System.out);
		PrintWriter out = new PrintWriter("output.txt");
		
		int T = sc.nextInt();
		for (int t=1; t<=T; t++) {
			String number = sc.next();
			String format = sc.next();
			
			out.printf("Case #%d: %s\n", t, read(number, format));
		}
		
		out.flush();
		out.close();
	}
	
	private static String read(String number, String format) {
		String[] splits_str = format.split("-");
		int[] splits = new int[splits_str.length];
		for (int i=0; i<splits_str.length; i++) {
			splits[i] = Integer.parseInt(splits_str[i]);
		}
		
		StringBuilder sb = new StringBuilder();
		
		int curIdx = 0;
		for (int split : splits) {
			
			char prev = 0;
			int prevCount = 0;
			
			for (int i=0; i<split; i++, curIdx++) {
				if (i == 0) {
					prev = number.charAt(curIdx);
					prevCount = 1;
				}
				else {
					char cur = number.charAt(curIdx);
					
					if (cur == prev) {
						prevCount++;
					}
					else {
						if (prevCount == 1) {
							sb.append(numMap.get(prev)).append(' ');
						}
						else if (prevCount <= 10) {
							sb.append(countMap.get(prevCount)).append(' ').append(numMap.get(prev)).append(' ');
						}
						else {
							for (int j=0; j<prevCount; j++) {
								sb.append(numMap.get(prev)).append(' ');
							}
						}
						
						prev = cur;
						prevCount = 1;
					}
				}
			}
			
			if (prevCount == 1) {
				sb.append(numMap.get(prev)).append(' ');
			}
			else if (prevCount <= 10) {
				sb.append(countMap.get(prevCount)).append(' ').append(numMap.get(prev)).append(' ');
			}
			else {
				for (int j=0; j<prevCount; j++) {
					sb.append(numMap.get(prev)).append(' ');
				}
			}
		}
		
		sb.delete(sb.length()-1, sb.length());
		
		return sb.toString();
	}
}
