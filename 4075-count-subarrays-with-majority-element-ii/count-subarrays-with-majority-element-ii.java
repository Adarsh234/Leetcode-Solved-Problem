class Solution {
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int offset = n+1;
        long[] bit = new long[2 * n + 2];
        long validSub = 0;
        int currentPre = 0;
        add(bit, currentPre + offset, 1);
        for(int i = 0; i < n; i++){
            if(nums[i] == target){
                currentPre++;
            }
            else{
                currentPre--;
            }
            validSub += query(bit, currentPre - 1 + offset);
            add(bit, currentPre + offset, 1);
        }
        return validSub;
        
    }
    private void add(long[] bit, int index, int val){
        for(; index < bit.length; index += index & -index){
            bit[index] += val;
        }
    }
    private long query(long[] bit, int index){
        long sum = 0;
        for(; index > 0; index -= index & -index){
            sum += bit[index];
        }
        return sum;
    }
}