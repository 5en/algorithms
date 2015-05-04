import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CrossTheMaze {
	public static void main(String[] args) throws IOException {
		//Scanner sc = new Scanner(System.in);
		//Scanner sc = new Scanner(new File("/Users/tianyanghu/Desktop/D-small-practice.in.txt"));
		Scanner sc = new Scanner(new File("/Users/tianyanghu/Desktop/D-large-practice.in.txt"));
		//PrintWriter out = new PrintWriter(System.out);
		PrintWriter out = new PrintWriter("/Users/tianyanghu/Desktop/output.txt");
		
		int T = sc.nextInt();
		for (int t=1; t<=T; t++) {
			int N = sc.nextInt();
			sc.nextLine();
			char[][] maze = new char[N+2][N+2];
			
			for (int row=0; row<=N+1; row++) {
				if (row==0 || row==N+1) {
					for (int col=0; col<=N+1; col++) {
						maze[row][col] = '#';
					}
					continue;
				}
				
				maze[row][0] = '#';
				maze[row][N+1] = '#';
				
				char[] input = sc.nextLine().toCharArray();
				for (int col=1; col<=N; col++) {
					maze[row][col] = input[col-1];
				}
			}
			
			int sx = sc.nextInt();
			int sy = sc.nextInt();
			int ex = sc.nextInt();
			int ey = sc.nextInt();
			
			out.printf("Case #%d: %s\n", t, new Robot(maze, sx, sy, ex, ey).solve());
		}
		
		out.flush();
		out.close();
	}
	
	private static final char[] DIRECT_CHAR = {'N', 'E', 'S', 'W'};
	
	private static class Robot {
		public char[][] maze;
		public int sx;
		public int sy;
		public int ex;
		public int ey;
		
		public int row;
		public int col;
		
		//     N-0
		// W-3     E-1
		//     S-2
		public int direct; 
		
		public Robot(char[][] maze, int sx, int sy, int ex, int ey) {
			this.maze = maze;
			this.sx = sx;
			this.sy = sy;
			this.ex = ex;
			this.ey = ey;
		}
		
		private boolean leftIsWall() {
			int left_row = 0;
			int left_col = 0;
			
			switch (direct) {
				case 0:
					left_row = row;
					left_col = col-1;
					break;
				case 1:
					left_row = row-1;
					left_col = col;
					break;
				case 2:
					left_row = row;
					left_col = col+1;
					break;
				case 3:
					left_row = row+1;
					left_col = col;
					break;
			}
			
			return maze[left_row][left_col]=='#';
		}
		
		private boolean frontIsWall() {
			int front_row = 0;
			int front_col = 0;
			
			switch (direct) {
				case 0:
					front_row = row-1;
					front_col = col;
					break;
				case 1:
					front_row = row;
					front_col = col+1;
					break;
				case 2:
					front_row = row+1;
					front_col = col;
					break;
				case 3:
					front_row = row;
					front_col = col-1;
					break;
			}
			
			return maze[front_row][front_col]=='#';
		}
		
		private boolean leftFrontIsWall() {
			int lf_row = 0;
			int lf_col = 0;
			
			switch (direct) {
				case 0:
					lf_row = row-1;
					lf_col = col-1;
					break;
				case 1:
					lf_row = row-1;
					lf_col = col+1;
					break;
				case 2:
					lf_row = row+1;
					lf_col = col+1;
					break;
				case 3:
					lf_row = row+1;
					lf_col = col-1;
					break;
			}
			
			return maze[lf_row][lf_col]=='#';
		}

		private void turnLeft() {
			direct = (direct+3) % 4;
		}
		
		private void turnRight() {
			direct = (direct+1) % 4;
		}
		
		private void proceed() {
			switch (direct) {
				case 0:
					row--;
					break;
				case 1:
					col++;
					break;
				case 2:
					row++;
					break;
				case 3:
					col--;
					break;
			}
		}
		
		public String solve() {
			// init
			row = sx;
			col = sy;
			direct = 0;
			
			if (leftIsWall() && frontIsWall()) {
				turnRight();
				turnRight();
				if (leftIsWall() && frontIsWall()) {
					return "Edison ran out of energy.";
				}
			}
			
			StringBuilder sb = new StringBuilder();
			int count = 0;
			while (count <= 10000) {
				//System.out.println(row + " " + col + " " + DIRECT_CHAR[direct]);
				
				if (row==ex && col==ey) {
					return sb.insert(0, count+"\n").toString();
				}
				
				if (!leftIsWall()) {
					turnLeft();
				}
				// leftIsWall()
				else if (frontIsWall()) {
					turnRight();
				}
				// leftIsWall() && !frontIsWall()
				else if (leftFrontIsWall()) {
					proceed();
					count++;
					sb.append(DIRECT_CHAR[direct]);
					//System.out.print(DIRECT_CHAR[direct]);
				}
				// leftIsWall() && !frontIsWall() && !leftFrontIsWall()
				else {
					proceed();
					count++;
					sb.append(DIRECT_CHAR[direct]);
					//System.out.print(DIRECT_CHAR[direct]);
					
					if (row==ex && col==ey) {
						return sb.insert(0, count+"\n").toString();
					}
					
					turnLeft();
					
					proceed();
					count++;
					sb.append(DIRECT_CHAR[direct]);
					//System.out.print(DIRECT_CHAR[direct]);
				}
			}
			
			return "Edison ran out of energy.";
		}
	}
}
