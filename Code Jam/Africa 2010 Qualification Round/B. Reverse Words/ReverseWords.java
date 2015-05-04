import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;

public class ReverseWords {
	public static void main(String[] args) throws IOException {
	    //Scanner sc = new Scanner(System.in);
        //Scanner sc = new Scanner(new File("B-small-practice.in.txt"));
        Scanner sc = new Scanner(new File("B-large-practice.in.txt"));
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
	    String[] words = line.split(" ");
        
	    StringBuilder sb = new StringBuilder();
	    for (int i=words.length-1; i>0; i--) {
            sb.append(words[i]);
            sb.append(" ");
        }
        sb.append(words[0]);
        
        return sb.toString();
	}
}
