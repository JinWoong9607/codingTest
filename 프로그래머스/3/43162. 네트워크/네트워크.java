import java.util.*;
class Solution {
    static boolean[] visited;
    public int solution(int n, int[][] computers) {
        visited = new boolean[n];
        int answer = bfs(computers);
        return answer;
    }
    public static int bfs(int[][] computers) {
        Queue<Integer> q = new LinkedList<>();
        int count = 0;
        for (int i = 0; i < computers.length; i++) {
            if (!visited[i]) {
                q.add(i);
                visited[i] = true;
                while(!q.isEmpty()) {
                    int next = q.poll();
                    for (int j = 0; j<computers[next].length; j++) {
                        if (computers[next][j] == 1 && !visited[j]) {
                            q.add(j);
                            visited[j] = true;
                        }
                    }
                }
                count++;
            }
        }
        return count;
    }
}