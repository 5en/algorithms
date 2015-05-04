import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileFixIt {
	public static void main(String[] args) throws IOException {
	    //Scanner sc = new Scanner(System.in);
        //Scanner sc = new Scanner(new File("A-small-practice.in.txt"));
        Scanner sc = new Scanner(new File("A-large-practice.in.txt"));
        //PrintWriter out = new PrintWriter(System.out);
        PrintWriter out = new PrintWriter("output.txt");
		
		int T = sc.nextInt();
		for (int t=1; t<=T; t++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			sc.nextLine();
			
			Dir newRootDir = new Dir();
			for (int n=0; n<N; n++) {
				newRootDir.add(sc.nextLine().substring(1).split("/"));
			}
			int numOldDir = newRootDir.count;
			
			for (int m=0; m<M; m++) {
				newRootDir.add(sc.nextLine().substring(1).split("/"));
			}
			int numNewDir = newRootDir.count;
			
			out.printf("Case #%d: %d\n", t, numNewDir-numOldDir);
		}
		
		out.flush();
		out.close();
	}
	
	private static class Dir {
		public Map<String, Dir> subDirs = new HashMap<String, Dir>();
		public int count = 0;
		
		public void add(String[] path) {
			Map<String, Dir> tmpSubDirs = this.subDirs;
			for (String dName : path) {
				if (!tmpSubDirs.containsKey(dName)) {
					tmpSubDirs.put(dName, new Dir());
					this.count++;
				}
				tmpSubDirs = tmpSubDirs.get(dName).subDirs;
			}
		}
	}
}