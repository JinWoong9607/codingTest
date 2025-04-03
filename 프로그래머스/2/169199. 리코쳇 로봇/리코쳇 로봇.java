import java.util.Queue;
import java.util.LinkedList;
import java.util.HashSet;
class Solution {
    public int solution(String[] board) {
        int[][] graph = new int[board.length][board[0].length()];
        int[] start = new int[2];
        for (int i = 0; i<board.length; i++) {
            for (int j = 0; j<board[0].length(); j++) {
                if (board[i].charAt(j) == 'D') {
                    graph[i][j] = -1;
                }
                if (board[i].charAt(j) == 'R') {
                    start[0] = i;
                    start[1] = j;
                }
                if (board[i].charAt(j) == 'G') {
                    graph[i][j] = 1;
                }
            }
        }
        
        int answer = bfs(start, graph);
        return answer;
    }
    
    public static int bfs(int[] start, int[][] graph) {
        Queue<int[]> q = new LinkedList<>();
        Queue<int[]> subq = new LinkedList<>();
        int trial = 0;
        HashSet<String> visited = new HashSet<>();
        visited.add(start[0]+","+start[1]);
        q.add(start);
        while (!q.isEmpty()) {
            int[] point = q.remove();
            int x = point[0];
            int y = point[1];
            while (x>0 && graph[x-1][y]!=-1) {
                x--;
            }
            int[] westpoint = new int[2];
            westpoint[0] = x;
            westpoint[1] = y;
            if (!visited.contains(x+","+y)) {
                visited.add(x+","+y);
                subq.add(westpoint);
                if (graph[x][y] == 1) {
                    trial ++;
                    return trial;
                }
            }
            x = point[0];
            y = point[1];
            while (x<graph.length - 1 && graph[x+1][y] != -1) {
                x++;
            }
            int[] eastpoint = new int[2];
            eastpoint[0] = x;
            eastpoint[1] = y;
            if (!visited.contains(x+","+y)) {
                visited.add(x+","+y);
                subq.add(eastpoint);
                if (graph[x][y] == 1) {
                    trial++;
                    return trial;
                }
            }
            x = point[0];
            y = point[1];
            while (y > 0 && graph[x][y-1] != -1) {
                y --;
            }
            int[] northpoint = new int[2];
            northpoint[0] = x;
            northpoint[1] = y;
            if (!visited.contains(x+","+y)) {
                visited.add(x+","+y);
                subq.add(northpoint);
                if (graph[x][y] == 1) {
                    trial++;
                    return trial;
                }
            }
            x = point[0];
            y = point[1];
            while (y < graph[0].length - 1 && graph[x][y+1] != -1) {
                y++;
            }
            int[] southpoint = new int[2];
            southpoint[0] = x;
            southpoint[1] = y;
            if (!visited.contains(x+","+y)) {
                visited.add(x+","+y);
                subq.add(southpoint);
                if (graph[x][y] == 1) {
                    trial++;
                    return trial;
                }
            }
            
            if (q.isEmpty()) {
                if (subq.isEmpty()) {
                    return -1;
                } else {
                    q.addAll(subq);
                    subq.clear();
                    trial++;
                }
            }
            
        }
        return -1;
    }
}