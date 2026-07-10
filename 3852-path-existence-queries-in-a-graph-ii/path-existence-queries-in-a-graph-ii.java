import java.util.Arrays;

class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i][0] = nums[i];
            pairs[i][1] = i; 
        }
        Arrays.sort(pairs, (a, b) -> Integer.compare(a[0], b[0]));
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[pairs[i][1]] = i;
        }
        int LOG = 18;
        int[][] st = new int[n][LOG];
        int r = 0;
        for (int l = 0; l < n; l++) {
            while (r + 1 < n && pairs[r + 1][0] - pairs[l][0] <= maxDiff) {
                r++;
            }
            st[l][0] = r; 
        }
        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                st[i][k] = st[st[i][k - 1]][k - 1];
            }
        }
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int u = queries[i][0];
            int v = queries[i][1];
            int a = pos[u];
            int b = pos[v];
            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }
            if (a == b) {
                ans[i] = 0;
                continue;
            }
            int curr = a;
            int steps = 0;
            for (int k = LOG - 1; k >= 0; k--) {
                if (st[curr][k] < b) {
                    curr = st[curr][k];
                    steps += (1 << k);
                }
            }
            if (st[curr][0] >= b) {
                ans[i] = steps + 1;
            } else {
                ans[i] = -1; 
            }
        }
        return ans;
    }
}