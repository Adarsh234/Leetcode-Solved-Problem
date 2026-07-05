import java.util.List;
class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int MOD = 1_000_000_007;
        int[][] dpSum = new int[n+1][n+1];
        int[][] dpCount = new int[n+1][n+1];
        for(int i = 0; i <= n; i++){
            for(int j = 0; j<= n; j++){
                dpSum[i][j] = -1;
            }
        }
        dpSum[n-1][n-1] = 0;
        dpCount[n-1][n-1] = 1;

        for(int r = n-1; r >= 0; r--){
            for(int c = n-1; c >= 0; c--){
                char ch = board.get(r).charAt(c);
                if(ch == 'X' || (r == n-1 && c == n-1)){
                    continue;
                }
                int p1 = dpSum[r+1][c];
                int p2 = dpSum[r][c+1];
                int p3 = dpSum[r+1][c+1];
                int maxPrev = Math.max(p1,(Math.max(p2,p3)));
                if(maxPrev != -1){
                    int currVal = (ch == 'E')? 0: (ch - '0');
                    dpSum[r][c] = maxPrev + currVal;
                    int count = 0;
                    if(p1 == maxPrev) count = (count + dpCount[r+1][c]) % MOD;
                    if(p2 == maxPrev) count = (count + dpCount[r][c+1]) % MOD;
                    if(p3 == maxPrev) count = (count + dpCount[r+1][c+1]) % MOD;
                    dpCount[r][c] = count;
                }
            }
        }
        if(dpSum[0][0] == -1){
            return new int[]{0, 0};
        }
        return new int[]{dpSum[0][0], dpCount[0][0]};
    }
}