class Solution {
    public int numberOfSubstrings(String s) {
        int[] lastSeen = new int[]{-1,-1,-1};
        int totalSub = 0;
        for(int i = 0; i < s.length(); i++){
            lastSeen[s.charAt(i) - 'a'] = i;
            int minLen = Math.min(lastSeen[0], Math.min(lastSeen[1], lastSeen[2]));
            if(minLen > -1){
                totalSub += (minLen + 1);
            }
        }
        return totalSub;
    }
}