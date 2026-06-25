class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        if(nums.length == 0){
            return 0;
        }
        int n = nums.length;
        int validSub = 0;
        for(int i = 0; i < n; i++){
            int runningSum = 0;
            for(int j = i; j < n; j++){
                if(nums[j] == target){
                    runningSum++;
                }
                else{
                    runningSum--;
                }
                if(runningSum > 0){
                    validSub++;
                }
            }
        }
        return validSub;
    }
}