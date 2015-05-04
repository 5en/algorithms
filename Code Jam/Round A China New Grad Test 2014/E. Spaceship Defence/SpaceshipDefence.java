import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class SpaceshipDefence {
	public static void main(String[] args) throws IOException {
		//Scanner sc = new Scanner(System.in);
		//Scanner sc = new Scanner(new File("/Users/tianyanghu/Desktop/E-small-practice.in.txt"));
		Scanner sc = new Scanner(new File("/Users/tianyanghu/Desktop/E-large-practice.in.txt"));
		//PrintWriter out = new PrintWriter(System.out);
		PrintWriter out = new PrintWriter("/Users/tianyanghu/Desktop/output.txt");
		
		int T = sc.nextInt();
		sc.nextLine();
		for (int t=1; t<=T; t++) {
			out.printf("Case #%d:\n", t);
			run(sc, out);
		}
		out.flush();
		out.close();
	}
	
	private static void run(Scanner sc, PrintWriter out) {
		// room ID: 1, 2,..., N
		int N = sc.nextInt();
		sc.nextLine();
		int[] roomID2colorID = new int[N+1];
		
		// C is the number of colors
		// color ID: 0, 1,..., C-1
		int C = 0;
		Map<String, Integer> colorName2ID = new HashMap<String, Integer>();
		for (int n=1; n<=N; n++) {
			String color = sc.nextLine();
			if (!colorName2ID.containsKey(color)) {
				colorName2ID.put(color, C++);
			}
			roomID2colorID[n] = colorName2ID.get(color);
		}
		
		int[][] graph = new int[C][C];
		for (int i=0; i<C; i++) {
			for (int j=0; j<C; j++) {
				graph[i][j] = -1;
			}
		}
		int M = sc.nextInt();
		sc.nextLine();
		for (int m=1; m<=M; m++) {
			int ai = roomID2colorID[sc.nextInt()];
			int bi = roomID2colorID[sc.nextInt()];
			
			int newDist = sc.nextInt();
			sc.nextLine();
			if (graph[ai][bi]==-1 || newDist<graph[ai][bi]) {
				graph[ai][bi] = newDist;
			}
		}
		
		int S = sc.nextInt();
		sc.nextLine();
		for (int s=1; s<=S; s++) {
			int from = roomID2colorID[sc.nextInt()];
			int to = roomID2colorID[sc.nextInt()];
			out.printf("%d\n", shortestPath(graph, from, to));
		}
	}
	
	// from, to: 0, 1,..., C-1
	private static int shortestPath(int[][] graph, int from, int to) {
		// init
		int C = graph.length;
		
		Set<Integer> Q = new HashSet<Integer>();
		
		int[] dist = new int[C];
		dist[from] = 0;
		
		for (int i=0; i<C; i++) {
			if (i != from) {
				dist[i] = -1;
			}
			Q.add(i);
		}
		
		// main loop
		while (!Q.isEmpty()) {
			// source node in first case
			int minDist = Integer.MAX_VALUE;
			int minID = -1;
			for (int id : Q) {
				if (dist[id]!=-1 && dist[id]<minDist) {
					minDist = dist[id];
					minID = id;
				}
			}
			
			if (minID == -1) {
				break;
			}
			
			Q.remove(minID);
			
			// for each node v that there is a path from minID to v, update
			for (int v : Q) {
				if (graph[minID][v] != -1) {
					int altDist = dist[minID] + graph[minID][v];
					if (dist[v]==-1 || altDist<dist[v]) {
						dist[v] = altDist;
					}
				}
			}
		}
		
		return dist[to];
	}
}