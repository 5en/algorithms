
public class Q2_4_Num1s {
	public static void main(String[] args) {
		System.out.println(num1s(9));
		System.out.println(num1s(99));
		System.out.println(num1s(999));
		System.out.println(num1s(9999));
		System.out.println(num1s(99999));
		System.out.println(num1s(999999));
	}
	
	public static int num1s(int n) {
		int result = 0;
		
		//int pos = 0; // stat only. pos: ...4321
		
		int factor = 1;
		while (n / factor != 0) {
			// example: abcde position 3 (c)
			
			int higherNum = (n / factor) / 10; // ab
			int currNum = (n / factor) % 10; // c
			int lowerNum = n % factor; // de
			
			if (currNum == 0) {
				// ab0de: [0...(ab-1)][100...199]
				result += higherNum*factor;
			}
			else if (currNum == 1) {
				// ab1de: [0...(ab-1)][100...199] + [ab][100...1de]
				result += higherNum*factor + (lowerNum+1);
			}
			else {
				// ab5de: [0...ab][100...199]
				// currNum > 1
				result += (higherNum+1)*factor;
			}
			
			factor *= 10;
			
			//pos++;
			//System.out.printf("pos %d: higher %d curr %d lower %d\n", pos, higherNum, currNum, lowerNum);
		}
		
		return result;
	}
}
