import java.util.*;
class Solution {
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> graph;
    public int solution(int n, int[][] edge) {
        visited = new boolean[n+1];
        graph = new ArrayList();
        for (int i = 0; i<=n; i++) {
            graph.add(new ArrayList<Integer>());
        }
        for (int i = 0; i<edge.length; i++) {
            graph.get(edge[i][0]).add(edge[i][1]);
            graph.get(edge[i][1]).add(edge[i][0]);
        }
        int answer = length();
        return answer;
    }
    public static int length() {
    Queue<Integer> q = new LinkedList<>();
    q.add(1);
    visited[1] = true;
    int answer = 0;
    while (!q.isEmpty()) {
        answer = q.size();
        for (int i = 0; i < answer; i++) {
            int node = q.poll();
            for (int next : graph.get(node)) {
                if (!visited[next]) {
                    visited[next] = true;
                    q.add(next);
                }
            }
        }
    }
    return answer;
}
}