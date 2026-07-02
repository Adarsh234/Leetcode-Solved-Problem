import java.util.ArrayDeque;
import java.util.List;
import java.util.Deque;
import java.util.Arrays;
class Solution {
    private final int[] DIR = {0, 1, 0, -1, 0};
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();
        int[][] MinDam = new int[m][n];
        for(int[] row: MinDam){
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        Deque<int[]> deque = new ArrayDeque<>();
        int startDam = grid.get(0).get(0);
        if(startDam >= health){
            return false;
        }
        deque.offerFirst(new int[]{0, 0, startDam});
        MinDam[0][0] = startDam;
        while(!deque.isEmpty()){
            int[] curr = deque.pollFirst();
            int r = curr[0];
            int c = curr[1];
            int d = curr[2];
            if(r == m-1 && c == n-1){
                return true;
            }
            if(d > MinDam[r][c]){
                continue;
            }
            for(int i = 0; i < 4; i++){
                int nr = r + DIR[i];
                int nc = c + DIR[i+1];
                if(nr >= 0 && nr < m && nc >= 0 && nc < n){
                    int nextDam = d + grid.get(nr).get(nc);
                    if(nextDam < health && nextDam < MinDam[nr][nc]){
                        MinDam[nr][nc] = nextDam;
                        if(grid.get(nr).get(nc) == 0){
                            deque.offerFirst(new int[]{nr, nc, nextDam});
                        }else{
                            deque.offerLast(new int[]{nr, nc, nextDam});
                        }
                    }
                }
            }
        }
        return false;
    }
}