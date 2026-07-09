class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[] groups = new int[n];
        int connectGroup = 0;
        groups[0] = connectGroup;
        for(int i = 1; i < n; i++){
            if(nums[i] - nums[i-1] > maxDiff){
                connectGroup++;
            }
            groups[i] = connectGroup;
        }
        int m = queries.length;
            boolean[] ans = new boolean[m];
        for(int i = 0; i < m; i++){
            int v = queries[i][0];
            int u = queries[i][1];
            ans[i] = (groups[v] == groups[u]);
        }
        return ans;
    }
}