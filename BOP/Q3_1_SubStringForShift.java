public class Q3_1_SubStringForShift {
	public static void main(String[] args) {
		System.out.println(run("ABCD", "BCD"));
		System.out.println(run("ABCD", "BCDA"));
		System.out.println(run("ABCD", "AC"));
		
		System.out.println(run2("ABCD", "BCD"));
		System.out.println(run2("ABCD", "BCDA"));
		System.out.println(run2("ABCD", "AC"));
	}
	
	// O(N) space
	private static boolean run(String s, String sub) {
		String srcShifted = s + s;
		return s.length()>=sub.length() && srcShifted.contains(sub);
	}
	
	// O(1) space
	private static boolean run2(String s, String sub) {
		int M = s.length();
		int N = sub.length();

		if (M < N) {
			return false;
		}
		
		int subi = 0;
		for (int si = 0; si <= (M - 1) + (N - 1); si++) {
			char c1 = s.charAt(si % M);
			char c2 = sub.charAt(subi);

			if (c1 == c2) {
				if (subi == N - 1) {
					return true;
				}
				subi++;
			} else {
				if (c1 == sub.charAt(0)) {
					subi = 1;
				} else {
					subi = 0;
				}
			}
		}
		
		return false;
	}
}
