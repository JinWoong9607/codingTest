import java.util.PriorityQueue;
class Solution {
    static int wait;
    public int solution(int k, int n, int[][] reqs) {
        int[] queue = new int[k];
        wait = Integer.MAX_VALUE;
        for (int i = 0; i<k; i++) {
            queue[i] = 1;
        }
        permutation(n-k, k, 0, queue, reqs);
        return wait;
    }
    
    static void permutation(int remain, int counsel, int index, int[] queue, int[][] reqs) {
        if (index == counsel -1) {
            queue[index] = remain + 1;
            find(queue, reqs);
            return;
        }
        
        for(int i=0; i<=remain; i++) {
            queue[index] = i+1;
            permutation(remain - i, counsel, index +1, queue, reqs);
            
        }
    }
    
    static void find(int[] queue, int[][] reqs) {
        int totalWaitTime = 0;
        
        for (int type = 1; type <= queue.length; type++) {
            int numCounselors = queue[type -1];
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            
            for (int i = 0; i < numCounselors; i++) {
                pq.add(0);
            }
            
            for (int[] req : reqs) {
                if (req[2] == type) {
                    int startTime = req[0];
                    int duration = req[1];
                    
                    int earliestEndTime = pq.poll();
                    if (earliestEndTime > startTime) {
                        totalWaitTime += earliestEndTime - startTime;
                        pq.add(earliestEndTime + duration);
                    } else {
                        pq.add(startTime + duration);
                    }
                }
            }
        }
        
        wait = Math.min(wait, totalWaitTime);
    }
}