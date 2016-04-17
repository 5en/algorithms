// https://leetcode.com/problems/fraction-to-recurring-decimal/

import java.util.HashMap;
import java.util.Map;

public class FractionToRecurringDecimal {

    // store remainder -> current result length (where the "(" will be placed)
    // result.append(remainder * 10 / denominator)
    // remainder = remainder * 10 / denominator
    public String fractionToDecimal(int numerator, int denominator) {
        StringBuilder result = new StringBuilder();

        String sign = "";
        if (numerator > 0 && denominator < 0 || numerator < 0 && denominator > 0) {
            sign = "-";
        }
        result.append(sign);

        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);

        long quotient = num / den;
        result.append(quotient);

        long remainder = num % den;
        if (remainder == 0) {
            return result.toString();
        }

        result.append('.');
        Map<Long, Integer> remainder2Len = new HashMap<Long, Integer>();
        while (!remainder2Len.containsKey(remainder)) {
            remainder2Len.put(remainder, result.length());
            result.append(remainder * 10 / den);
            remainder = remainder * 10 % den;

            if (remainder == 0) {
                return result.toString();
            }
        }
        int start = remainder2Len.get(remainder);
        result.insert(start, '(');
        result.append(')');

        return result.toString();
    }
}
