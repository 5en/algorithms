import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;

public class T9Spelling {
	private static int[] key = {2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,7,8,8,8,9,9,9,9};
	private static String[] press = {"2","22","222","3","33","333","4","44","444","5","55","555","6","66","666","7","77","777","7777","8","88","888","9","99","999","9999"};
	
	public static void main(String[] args)  throws IOException {
	    //Scanner sc = new Scanner(System.in);
        //Scanner sc = new Scanner(new File("C-small-practice.in.txt"));
        Scanner sc = new Scanner(new File("C-large-practice.in.txt"));
        //PrintWriter out = new PrintWriter(System.out);
        PrintWriter out = new PrintWriter("output.txt");
		
		int N = sc.nextInt();
		sc.nextLine();
		for (int n=1; n<=N; n++) {
			String line = sc.nextLine();
			
			out.printf("Case #%d: %s\n", n, process(line));
		}
		
		out.flush();
		out.close();
	}
	
	private static String process(String line) {
	    StringBuilder sb = new StringBuilder();
        
	    int prevKey = -1;
        int curKey = -1;
        for (int i=0; i<line.length(); i++) {
            char ch = line.charAt(i);
            
            if (ch == ' ') {
                curKey = -1; // for whitespace
            }
            else {
                curKey = key[ch-'a'];
            }
            
            if (curKey == prevKey) {
                sb.append(' ');
            }
            
            if (ch == ' ') {
                sb.append(0);
            }
            else {
                sb.append(press[ch-'a']);
            }
            
            prevKey = curKey;
        }
        
        return sb.toString();
	}
}
