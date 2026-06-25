class Solution {
    private static final int MOD = 1_000_000_007;
    public int zigZagArrays(int n, int l, int r) {
        if(n == 1) return r - l + 1;
        int m = r - l + 1;
        int state = 2 * m;
        long[][] T = new long[state][state];
        for(int x = 0; x < m; x++){
            int down = x;
            int up = x+m;
            for(int y = x + 1; y < m; y++){
                T[y][up] = 1;
            }
            for(int y = 0; y < x; y++){
                T[y+m][down] = 1;
            }
        }
        long[][] T_power = matrixPow(T, n-1, state);
        long[] V0 = new long[state];
        for(int i = 0; i < state; i++){
            V0[i] =1;
        }
        long totalway = 0;
        for(int i = 0; i < state; i++){
            long current = 0;
            for(int j = 0; j < state; j++){
                current = (current + T_power[i][j] * V0[j]) % MOD;
            }
            totalway = (totalway + current) % MOD;
        }
        return (int) totalway;
    }
    private long[][] matrixPow(long[][] base, int exp, int size){
        long[][] res = new long[size][size];
        for(int i = 0; i < size; i++){
            res[i][i] = 1;
        }
        long[][] curr = base;
        while(exp > 0){
            if(exp % 2 == 1){
                res = matrixMul(res, curr, size);
            }
            curr = matrixMul(curr, curr, size);
            exp /= 2;
        }
        return res;
    }
    private long[][] matrixMul(long[][] A, long[][] B, int size){
        long[][] C = new long[size][size];
        for(int i = 0; i < size; i++){
            for(int k = 0; k < size; k++){
                if(A[i][k] == 0) continue;
                for(int j = 0; j < size; j++){
                    C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
                }
            }
        }
        return C;
    }
}