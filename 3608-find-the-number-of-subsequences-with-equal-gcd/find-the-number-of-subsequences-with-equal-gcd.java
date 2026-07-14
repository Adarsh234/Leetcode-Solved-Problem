class Solution {
    private int MOD = 1_000_000_007;
    private Integer[][][] memo;

    public int subsequencePairCount(int[] nums) {
        int n = nums.length;
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }
        memo = new Integer[n][maxVal + 1][maxVal + 1];
        return solve(0, 0, 0, nums);
    }

    private int solve(int i, int g1, int g2, int[] nums) {
        if (i == nums.length) {
            return (g1 > 0 && g1 == g2) ? 1 : 0;
        }
        if (memo[i][g1][g2] != null) {
            return memo[i][g1][g2];
        }

        long ways = 0;
        ways = (ways + solve(i + 1, g1, g2, nums)) % MOD;
        int nextG1 = (g1 == 0) ? nums[i] : gcd(g1, nums[i]);
        ways = (ways + solve(i + 1, nextG1, g2, nums)) % MOD;
        int nextG2 = (g2 == 0) ? nums[i] : gcd(g2, nums[i]);
        ways = (ways + solve(i + 1, g1, nextG2, nums)) % MOD;
        return memo[i][g1][g2] = (int) ways;
    }
    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}