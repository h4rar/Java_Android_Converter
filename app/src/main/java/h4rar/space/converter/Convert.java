package h4rar.space.converter;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Convert {
    public Map<String, String> getAnswer(int sourceNum, int sourceBase, int endBase) {
        Map<String, String> answer = new HashMap<String, String>();

        BigInteger bigInteger = new BigInteger(Integer.toString(sourceNum), sourceBase);
        String ans = bigInteger.toString(endBase);
        String ans2 = bigInteger.toString(2);
        String ans3 = bigInteger.toString(3);
        String ans8 = bigInteger.toString(8);
        String ans10 = bigInteger.toString(10);
        String ans16 = bigInteger.toString(16);

        answer.put("ans", ans);
        answer.put("2", ans2);
        answer.put("3", ans3);
        answer.put("8", ans8);
        answer.put("10", ans10);
        answer.put("16", ans16);
        return answer;
    }
}
