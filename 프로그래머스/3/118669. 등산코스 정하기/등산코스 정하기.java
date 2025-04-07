import java.util.*;

class Solution {
    static class Course implements Comparable<Course> {
        int node;
        int intensity;
        public Course(int node, int intensity) {
            this.node = node;
            this.intensity = intensity;
        }
        @Override
        public int compareTo(Course o) {
            return Integer.compare(this.intensity, o.intensity);
        }
    }
    
    static ArrayList<ArrayList<Course>> map;
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        map = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            map.add(new ArrayList<>());
        }
        for (int[] path : paths) {
            int a = path[0], b = path[1], w = path[2];
            map.get(a).add(new Course(b, w));
            map.get(b).add(new Course(a, w));
        }
        
        boolean[] isSummit = new boolean[n + 1];
        boolean[] isGate = new boolean[n + 1];
        for (int summit : summits) {
            isSummit[summit] = true;
        }
        for (int gate : gates) {
            isGate[gate] = true;
        }
        
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Course> pq = new PriorityQueue<>();
        
        for (int gate : gates) {
            dist[gate] = 0;
            pq.offer(new Course(gate, 0));
        }
        
        while (!pq.isEmpty()) {
            Course cur = pq.poll();
            int node = cur.node;
            int intensity = cur.intensity;
            
            if (intensity > dist[node]) continue;
            
            if (isSummit[node]) continue;
            
            for (Course next : map.get(node)) {
                if (isGate[next.node]) continue;
                int newIntensity = Math.max(intensity, next.intensity);
                if (newIntensity < dist[next.node]) {
                    dist[next.node] = newIntensity;
                    pq.offer(new Course(next.node, newIntensity));
                }
            }
        }
        int bestSummit = Integer.MAX_VALUE;
        int minIntensity = Integer.MAX_VALUE;
        Arrays.sort(summits); 
        for (int summit : summits) {
            if (dist[summit] < minIntensity) {
                minIntensity = dist[summit];
                bestSummit = summit;
            }
        }
        return new int[]{bestSummit, minIntensity};
    }
}

