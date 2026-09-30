class Solution {
    public int maxFrequencyElements(int[] nums) {
        Map<Integer, Integer> A = new HashMap<>();
        int freq = 0;
        int count = 0;
        for(int num : nums){
            A.put(num, A.getOrDefault(num, 0) + 1);
        }
        for(int val : A.values()){
            if(val == freq){
                count++;
            }
            else if(val > freq){
                freq = val;
                count = 1;
            }
        }
        return freq * count;
    }
}