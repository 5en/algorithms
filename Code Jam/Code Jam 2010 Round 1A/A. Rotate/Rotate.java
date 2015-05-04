import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Rotate {
	public static void main(String[] args) throws IOException {
	    //Scanner sc = new Scanner(System.in);
        //Scanner sc = new Scanner(new File("A-small-practice.in.txt"));
        Scanner sc = new Scanner(new File("A-large-practice.in.txt"));
        //PrintWriter out = new PrintWriter(System.out);
        PrintWriter out = new PrintWriter("output.txt");
		
		int T = sc.nextInt();
		for (int t=1; t<=T; t++) {
			int N = sc.nextInt();
			int K = sc.nextInt();
			sc.nextLine();
			String[] sBoard = new String[N];
			for (int n=0; n<N; n++) {
				sBoard[n] = sc.nextLine();
			}
			
			out.printf("Case #%d: %s\n", t, process(N, K, sBoard));
		}
		
		out.flush();
		out.close();
	}
	
	private static String process(int N, int K, String[] sBoard) {
	    applyGravity(sBoard);
        
        char[][] cBoard = new char[N][];
        for (int n=0; n<N; n++) {
            cBoard[n] = sBoard[n].toCharArray();
        }
        
        boolean[] results = check(cBoard, K);
        String result = null;
        if (results[0] && results[1]) {
            result = "Both";
        }
        else if (results[0] && !results[1]) {
            result = "Red";
        }
        else if (!results[0] && results[1]) {
            result = "Blue";
        }
        else {
            result = "Neither";
        }
        
        return result;
	}
	
	private static void applyGravity(String[] strs) {
		for (int i=0; i<strs.length; i++) {
			StringBuilder sb = new StringBuilder(strs[i].replace(".", ""));
			int numDots = strs[i].length()-sb.length();
			for (int j=0; j<numDots; j++) {
				sb.insert(0, '.');
			}
			strs[i] = sb.toString();
		}
	}
	
	private static boolean[] check(char[][] c, int K) {
		// results[0] : Red
		// results[1] : Blue
		boolean[] results = {false, false};
		
		int ccRed = 0;
		int ccBlue = 0;
		int tmpI = 0;
		int tmpJ = 0;
		
		// row
		for (int i=0; i<c.length; i++) {
			if (results[0] && results[1]) {
				return results;
			}
			
			// continus count
			ccRed = 0;
			ccBlue = 0;
			
			for (int j=0; j<c[i].length; j++) {
				switch (c[i][j]) {
					case '.':
						ccRed = 0;
						ccBlue = 0;
						break;
					case 'R':
						ccRed++;
						ccBlue = 0;
						if (ccRed >= K) {
							results[0] = true;
						}
						break;
					case 'B':
						ccRed = 0;
						ccBlue++;
						if (ccBlue >= K) {
							results[1] = true;
						}
						break;
				}
			}
		}
		
		// column
		for (int j=0; j<c[0].length; j++) {
			if (results[0] && results[1]) {
				return results;
			}
			
			ccRed = 0;
			ccBlue = 0;
			
			for (int i=0; i<c.length; i++) {
				switch (c[i][j]) {
					case '.':
						ccRed = 0;
						ccBlue = 0;
						break;
					case 'R':
						ccRed++;
						ccBlue = 0;
						if (ccRed >= K) {
							results[0] = true;
						}
						break;
					case 'B':
						ccRed = 0;
						ccBlue++;
						if (ccBlue >= K) {
							results[1] = true;
						}
						break;
				}
			}
		}
		
		// diagonal
		for (int i=0; i<c.length; i++) {
			for (int j=0; j<c[0].length; j++) {
				if (i!=0 && i!=c.length-1 && j!=0 && j!=c[0].length-1) {
					continue;
				}
				
				if (results[0] && results[1]) {
					return results;
				}
				
				// lower left diagonal
				ccRed = 0;
				ccBlue = 0;
				tmpI = i;
				tmpJ = j;
				while (tmpI>=0 && tmpI<=c.length-1 && tmpJ>=0 && tmpJ<=c[0].length-1) {
					if (results[0] && results[1]) {
						return results;
					}
					
					switch (c[tmpI][tmpJ]) {
						case '.':
							ccRed = 0;
							ccBlue = 0;
							break;
						case 'R':
							ccRed++;
							ccBlue = 0;
							if (ccRed >= K) {
								results[0] = true;
							}
							break;
						case 'B':
							ccRed = 0;
							ccBlue++;
							if (ccBlue >= K) {
								results[1] = true;
							}
							break;
					}
					
					tmpI--;
					tmpJ++;
				}
				
				if (results[0] && results[1]) {
					return results;
				}
				
				// lower right diagonal
				ccRed = 0;
				ccBlue = 0;
				tmpI = i;
				tmpJ = j;
				while (tmpI>=0 && tmpI<=c.length-1 && tmpJ>=0 && tmpJ<=c[0].length-1) {
					if (results[0] && results[1]) {
						return results;
					}
					
					switch (c[tmpI][tmpJ]) {
						case '.':
							ccRed = 0;
							ccBlue = 0;
							break;
						case 'R':
							ccRed++;
							ccBlue = 0;
							if (ccRed >= K) {
								results[0] = true;
							}
							break;
						case 'B':
							ccRed = 0;
							ccBlue++;
							if (ccBlue >= K) {
								results[1] = true;
							}
							break;
					}
					
					tmpI++;
					tmpJ++;
				}
			}
		}
		
		return results;
	}
}
