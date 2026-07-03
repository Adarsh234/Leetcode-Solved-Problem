import java.util.*;
class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        List<List<int[]>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++){
            adj.add(new ArrayList<>());
        }
        int maxCost = 0;
        for(int[] e: edges){
            int u = e[0];
            int v = e[1];
            int cost = e[2];
            if(online[v]){
                adj.get(u).add(new int[]{v, cost});
                maxCost = Math.max(maxCost, cost);
            }
        }
        int left = 0;
        int right = maxCost;
        int bestScore = -1;
        while(left <= right){
            int mid = left + (right - left)/2;
            if(canReach(n, adj, mid, k)){
                bestScore = mid;
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return bestScore;
    }
    private boolean canReach(int n, List<List<int[]>> adj, int minScore, long budget){
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
        pq.offer(new long[]{0, 0});
        while(!pq.isEmpty()){
            long[] curr = pq.poll();
            int u = (int) curr[0];
            long currDist = curr[1];
            if(currDist > budget) return false;
            if(u == n - 1) return true;
            if(currDist > dist[u]) continue;
            for(int[] edge: adj.get(u)){
                int v = edge[0];
                int weight = edge[1];
                if(weight < minScore) continue;
                long nextDist = currDist + weight;
                if(nextDist < dist[v]){
                    dist[v] = nextDist;
                    pq.offer(new long[]{v, nextDist});
                }
            }
        }
        return false;
    }
}