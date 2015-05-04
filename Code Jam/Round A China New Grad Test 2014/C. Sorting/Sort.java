import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Sort {
	public static void main(String[] args) throws IOException {
		//Scanner sc = new Scanner(System.in);
		//Scanner sc = new Scanner(new File("/Users/tianyanghu/Desktop/C-small-practice.in.txt"));
		Scanner sc = new Scanner(new File("/Users/tianyanghu/Desktop/C-large-practice.in.txt"));
		//PrintWriter out = new PrintWriter(System.out);
		PrintWriter out = new PrintWriter("/Users/tianyanghu/Desktop/output.txt");
		
		int T = sc.nextInt();
		for (int t=1; t<=T; t++) {
			int N = sc.nextInt();
			int[] worths = new int[N];
			for (int n=0; n<N; n++) {
				worths[n] = sc.nextInt();
			}
			
			out.printf("Case #%d: %s\n", t, run(worths));
		}
		
		out.flush();
		out.close();
	}
	
	private static String run(int[] worths) {
		List<Integer> oddIdx = new LinkedList<Integer>();
		List<Integer> oddWorths = new ArrayList<Integer>();
		List<Integer> evenIdx = new LinkedList<Integer>();
		List<Integer> evenWorths = new ArrayList<Integer>();
		
		for (int i=0; i<worths.length; i++) {
			if (Math.abs(worths[i]) % 2 == 1) {
				oddIdx.add(i);
				oddWorths.add(worths[i]);
			}
			else {
				evenIdx.add(i);
				evenWorths.add(worths[i]);
			}
		}
		
		Collections.sort(oddWorths);
		Collections.sort(evenWorths, Collections.reverseOrder());
		
		int k = 0;
		for (int i : oddIdx) {
			worths[i] = oddWorths.get(k++);
		}
		k = 0;
		for (int i : evenIdx) {
			worths[i] = evenWorths.get(k++);
		}
		
		StringBuilder sb = new StringBuilder();
		for (int x : worths) {
			sb.append(x).append(' ');
		}
		sb.delete(sb.length()-1, sb.length());
		
		return sb.toString();
	}
}