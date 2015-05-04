import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class AlienLanguage {
	public static void main(String[] args) throws IOException {
	    //Scanner sc = new Scanner(System.in);
        //Scanner sc = new Scanner(new File("A-small-practice.in.txt"));
        Scanner sc = new Scanner(new File("A-large-practice.in.txt"));
        //PrintWriter out = new PrintWriter(System.out);
        PrintWriter out = new PrintWriter("output.txt");
		
		int L = sc.nextInt();
		int D = sc.nextInt();
		int N = sc.nextInt();
		sc.nextLine();
		String[] dict = new String[D];
		for (int d=0; d<D; d++) {
			dict[d] = sc.nextLine();
		}
		
		for (int n=1; n<=N; n++) {
			String patternStr = sc.nextLine();
			
			out.printf("Case #%d: %d\n", n, process(L, D, n, dict, patternStr));
		}
		
		out.flush();
		out.close();
	}
	
	private static int process(int L, int D, int N, String[] dict, String patternStr) {
	    String regex = patternStr.replace('(', '[').replace(')', ']');
        Pattern p = Pattern.compile(regex);
        
        int count = 0;
        for (int d=0; d<D; d++) {
            if (p.matcher(dict[d]).matches()) {
                count++;
            }
        }
        
        return count;
    }
}
