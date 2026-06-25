class Solution {
    public int zigZagArrays(int n, int l, int r) {
        int mod = 1_000_000_007;
        int m = r-l;
        int dp[] = new int[m+1];
        for(int v = 0; v <= m; v++){
            dp[v] = 1;
        }
        for(int i = 2; i <= n; i++){
            int[] nextdp  = new int[m+1];
            if(i % 2 == 0){
                int running = 0;
                for(int v = 0; v <= m; v++){
                    nextdp[v] = running;
                    running = (running+dp[v]) % mod;
                }
            }
            else{
                int running = 0;
                for(int v = m; v >= 0; v--){
                nextdp[v] = running;
                running = (running+dp[v]) % mod;
                }
            }
            dp = nextdp;
        }
        int totalway = 0;
        for(int v = 0; v <= m; v++){
            totalway = (totalway+ dp[v]) % mod;
        }
        return (totalway*2) % mod;
    }
}