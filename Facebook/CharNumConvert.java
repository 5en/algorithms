// A -> 1, B -> 2, ..., Z -> 26, AA -> 27, ..., AZ -> 52, BA -> 53

public class CharNumConvert {
    public static void main(String[] args) {
        System.out.println(str2num("A"));
        System.out.println(str2num("Z"));
        System.out.println(str2num("AA"));
        System.out.println(str2num("AZ"));
        System.out.println(str2num("BA"));

        System.out.println(num2str(1));
        System.out.println(num2str(26));
        System.out.println(num2str(27));
        System.out.println(num2str(52));
        System.out.println(num2str(53));
    }

    public static int str2num(String s) {
        int result = 0;
        int multiple = 1;
        for (int i = s.length()-1; i >= 0; i--) {
            result += (s.charAt(i) - 'A' + 1) * multiple;
            multiple = multiple * 26;
        }

        return result;
    }

    // n = an*26^n + ... + a2*26^2 + a1*26^1 + a0
    public static String num2str(int n) {
        StringBuilder result = new StringBuilder();

        int multiple = 1;
        while (n != 0) {
            int ai = n % 26;
            result.insert(0, (char)('A' + (ai == 0 ? 26 : ai) - 1));
            n = (n - (ai == 0 ? 26 : ai)) / 26;
        }

        return result.toString();
    }
}
