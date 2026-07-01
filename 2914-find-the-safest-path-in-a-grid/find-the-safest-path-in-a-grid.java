import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.util.PriorityQueue;
class Solution {
    private final int[] DIR = {0, 1, 0, -1, 0};
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        int [][] safe = new int[n][n];
        for(int r = 0; r < n; r++){
            for(int c= 0; c < n; c++){
                safe[r][c] = -1;
            }
        }
        Queue<int[]> bfs = new LinkedList<>();
        for(int r = 0; r < n; r++){
            for(int c = 0; c < n; c++){
                if(grid.get(r).get(c) == 1){
                    bfs.offer(new int[]{r, c});
                    safe[r][c] = 0;
                }
            }
        }
        while(!bfs.isEmpty()){
            int[] curr = bfs.poll();
            int r = curr[0];
            int c = curr[1];
            for(int i = 0; i < 4; i++){
                int nr = r + DIR[i];
                int nc = c + DIR[i + 1];
                
                if(nr >= 0 && nr < n && nc >= 0 && nc < n && safe[nr][nc] == -1){
                    safe[nr][nc] = safe[r][c] + 1;
                    bfs.offer(new int[]{nr, nc});
                }
            }
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[2] - a[2]);
        boolean[][] visited = new boolean[n][n];
        pq.offer(new int[]{0, 0, safe[0][0]});
        visited[0][0] = true;
        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int r = curr[0];
            int c = curr[1];
            int currSafe = curr[2];
            if(r == n-1 && c == n-1){
                return currSafe;
            }
            for(int i = 0; i < 4; i++){
                int nr = r + DIR[i];
                int nc = c + DIR[i+1];
                if(nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc]){
                    int path = Math.min(currSafe, safe[nr][nc]);
                    visited[nr][nc] = true;
                    pq.offer(new int[] {nr, nc, path});
                }
            }
        }
        return 0;
    }
}