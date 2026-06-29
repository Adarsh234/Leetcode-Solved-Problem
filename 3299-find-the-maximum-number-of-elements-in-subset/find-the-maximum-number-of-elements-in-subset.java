import java.util.HashMap;
import java.util.Map;
class Solution {
    public int maximumLength(int[] nums) {
        Map<Integer,Integer> count = new HashMap<>();
        for(int num : nums){
            count.put(num, count.getOrDefault(num, 0)+1);
        }
        int maxLen = 1;
        if(count.containsKey(1)){
            int ones = count.get(1);
            maxLen = Math.max(maxLen, ones % 2 == 0 ? ones-1: ones);
        }
        for(int key : count.keySet()){
            if(key==1) continue;
            if(count.get(key) < 2){
                continue;
            }
            long current = key;
            int currentLen = 0;
            while(count.containsKey((int) current) && count.get((int) current) >= 2){
                currentLen += 2;
                current *= current;
                if(current > Integer.MAX_VALUE){
                    break;
                }
            }
            if(count.containsKey((int) current)){
                currentLen += 1;
            }
            else{
                currentLen -= 1;
            }
            maxLen = Math.max(maxLen, currentLen);
        }
        return maxLen;
    }
}