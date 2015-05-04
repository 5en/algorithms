
public class Q1_2_Chess {
	public static void main(String[] args) {
		int b = 0;
		for (b=LSET(b, 1); LGET(b)<=9; b=LSET(b, LGET(b)+1)) {
			for (b=RSET(b, 1); RGET(b)<=9; b=RSET(b, RGET(b)+1)) {
				if (LGET(b)%3 != RGET(b)%3) {
					System.out.println("A = " + LGET(b) + ", B = " + RGET(b));
				}
			}
		}
	}
	
	private static int LSET(int b, int v) {
		b = b & 0x0000000F; // clear left four bits
		v = v << 4;
		b = b | v; // set left four bits
		return b;
	}
	
	private static int RSET(int b, int v) {
		b = b & 0x000000F0; // clear right four bits
		b = b | v; // set right four bits
		return b;
	}
	
	private static int LGET(int b) {
		b = b >>> 4;
		return b;
	}
	
	private static int RGET(int b) {
		b = b & 0x0000000F; // clear left four bits
		return b;
	}
}
