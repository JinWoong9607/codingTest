import java.util.*;
class Edge {
    int target;
    int cost;
    
    public Edge(int target, int cost) {
        this.target = target;
        this.cost = cost;
    }
    
    public int getTarget() {
        return target;
    }
    
    public int getCost() {
        return cost;
    }
}

class Solution {
    static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
    public int solution(int n, int s, int a, int b, int[][] fares) {
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<Edge>());
        }
        for (int i = 0; i<fares.length; i++) {
            graph.get(fares[i][0]).add(new Edge(fares[i][1], fares[i][2]));
            graph.get(fares[i][1]).add(new Edge(fares[i][0], fares[i][2]));
        }
        int[] costFromS = dijkstra(s, n);
        int[] costFromA = dijkstra(a, n);
        int[] costFromB = dijkstra(b, n);
        int answer = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            if(costFromS[i] == Integer.MAX_VALUE || costFromA[i] == Integer.MAX_VALUE || costFromB[i] == Integer.MAX_VALUE) { 
                continue;
            }
            answer = Math.min(answer, costFromS[i] + costFromA[i] + costFromB[i]);
        }
        return answer;
    }
    public static int[] dijkstra(int start, int n) {
        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getCost));
        distance[start] = 0;
        pq.add(new Edge(start, 0));
        
        while(!pq.isEmpty()) {
            Edge current = pq.poll();
            int curVertex = current.getTarget();
            int curCost = current.getCost();
            
            if (curCost > distance[curVertex]) {
                continue;
            }
            
            for (Edge next : graph.get(curVertex)) {
                int nextVertex = next.getTarget();
                int newCost = curCost + next.getCost();
                
                if (newCost < distance[nextVertex]) {
                    distance[nextVertex] = newCost;
                    pq.add(new Edge(nextVertex, newCost));
                }
            }
        }
        return distance;
    }
} 