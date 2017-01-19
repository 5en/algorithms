import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by htyleo on 1/18/17.
 */
public class DecodeMorseCode {

    public static void main(String[] args){
        Map<String, String> dict = new HashMap<>();
        dict.put("..", "A");
        dict.put("._", "B");
        dict.put(".", "C");
        dict.put("_", "D");

        System.out.println(new DecodeMorseCode().decode(".._._", dict));
        System.out.println(new DecodeMorseCode().decode("._", dict));
    }

    public List<String> decode(String s, Map<String, String> dict) {
        Map<String, List<String>> memo = new HashMap<>();
        return decode(s, dict, memo);
    }

    private List<String> decode(String s, Map<String, String> dict, Map<String, List<String>> memo) {
        if (memo.containsKey(s)) {
            return memo.get(s);
        }

        List<String> result = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            String left = s.substring(0, i + 1);
            if (!dict.containsKey(left)) {
                continue;
            }

            String decodedLeft = dict.get(left);
            if (i == s.length() - 1) {
                result.add(decodedLeft);
                continue;
            }

            String right = s.substring(i + 1, s.length());
            List<String> decodedRights = decode(right, dict, memo);
            for (String decodedRight : decodedRights) {
                result.add(decodedLeft + decodedRight);
            }
        }

        memo.put(s, result);

        return result;
    }

}
