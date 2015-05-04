import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CaptainHammer {
	public static void main(String[] args) throws IOException {
		//Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new File("B-small-practice.in.txt"));
		//PrintWriter out = new PrintWriter(System.out);
		PrintWriter out = new PrintWriter("output.txt");
		
		int T = sc.nextInt();
		for (int t=1; t<=T; t++) {
			int V = sc.nextInt();
			int D = sc.nextInt();
			
			out.printf("Case #%d: %.7f\n", t, process(V, D));
		}
		
		out.flush();
		out.close();
	}
	
	private static double process(int V, int D) {
	    // V*SinQ*t - 0.5gt^2 = 0
        // D = V*CosQ*t
        // => Sin2Q = D*g/V^2
        
        double asin = D*9.8/(V*V);
        if (asin > 1) {
            asin = 1;
        }
        if (asin < 0) {
            asin = 0;
        }
        
        double Q = (Math.asin(asin)/2) * 180 / Math.PI;
        
        return Q;
	}
}
