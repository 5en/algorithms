import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Moist {
	public static void main(String[] args)  throws IOException {
		//Scanner sc = new Scanner(System.in);
		//Scanner sc = new Scanner(new File("C-small-practice-1.in.txt"));
		Scanner sc = new Scanner(new File("C-small-practice-2.in.txt"));
		//PrintWriter out = new PrintWriter(System.out);
		PrintWriter out = new PrintWriter("output.txt");
		
		int T = sc.nextInt();
		for (int t=1; t<=T; t++) {
			int N = sc.nextInt();
			sc.nextLine();
			
			String[] names = new String[N];
			for (int n=0; n<N; n++) {
				names[n] = sc.nextLine();
			}
			
			out.printf("Case #%d: %d\n", t, insertionSortNumMoves(names));
		}
		
		out.flush();
		out.close();
	}
	
	private static int insertionSortNumMoves(String[] names) {
		int count = 0;
		int max_idx = 0;
		
		for (int i=1; i<names.length; i++) {
			if (compare(names[max_idx], names[i]) < 0) {
				max_idx = i;
			}
			else {
				count++;
			}
		}
		
		return count;
	}
	
	private static int compare(String s1, String s2) {
		int i1 = 0;
		int i2 = 0;
		
		while (i1<s1.length() && i2<s2.length()) {
			char c1 = s1.charAt(i1);
			char c2 = s2.charAt(i2);
			
			if (c1 == c2) {
				i1++;
				i2++;
				continue;
			}
			
			if (c1 == ' ') {
				return -1;
			}
			else if (Character.isUpperCase(c1)) {
				if (c2 == ' ') {
					return 1;
				}
				else if (Character.isLowerCase(c2)) {
					return -1;
				}
				else {
					return c1-c2;
				}
			}
			else {
				if (c2==' ' || Character.isUpperCase(c2)) {
					return 1;
				}
				else {
					return c1-c2;
				}
			}
		}
		
		if (i1==s1.length()) {
			return -1;
		}
		else {
			return 1;
		}
	}
}
