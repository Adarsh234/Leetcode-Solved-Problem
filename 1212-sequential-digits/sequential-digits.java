import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        String master = "123456789";
        List<Integer> result = new ArrayList<>();
        int minLen = String.valueOf(low).length();
        int maxLen = String.valueOf(high).length();
        for (int length = minLen; length <= maxLen; length++) {
            for (int start = 0; start <= 9 - length; start++) {
                String sub = master.substring(start, start + length);
                int num = Integer.parseInt(sub);
                if (num >= low && num <= high) {
                    result.add(num);
                }
            }
        }
        return result;
    }
}