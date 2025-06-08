import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int year = 0;
        while (true) {
            meltOnce();
            year++;

            if (isSplit()) {
                System.out.println(year);
                break;
            }
            if (isAllMelted()) {
                System.out.println(0);
                break;
            }
        }
    }
    
    static void meltOnce() {
        var meltList = new ArrayList<int[]>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] > 0) {
                    int water = 0;
                    for (int d = 0; d < 4; d++) {
                        int ni = i + dx[d], nj = j + dy[d];
                        if (ni >= 0 && ni < n && nj >= 0 && nj < m && graph[ni][nj] == 0) {
                            water++;
                        }
                    }
                    if (water > 0) meltList.add(new int[]{i, j, water});
                }
            }
        }
        for (var e : meltList) {
            graph[e[0]][e[1]] = Math.max(graph[e[0]][e[1]] - e[2], 0);
        }
    }
    
    static boolean isAllMelted() {
        for (int[] row : graph) {
            for (int v : row) {
                if (v > 0) return false;
            }
        }
        return true;
    }
    
    static boolean isSplit() {
        boolean[][] visited = new boolean[n][m];
        Queue<Integer> q = new ArrayDeque<>();
        
        int sx = -1, sy = -1;
        outer:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] > 0) {
                    sx = i; sy = j;
                    break outer;
                }
            }
        }
        if (sx == -1) return false;

        q.add(sx * m + sy);
        visited[sx][sy] = true;
        while (!q.isEmpty()) {
            int loc = q.poll();
            int x = loc / m, y = loc % m;
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d], ny = y + dy[d];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m
                        && graph[nx][ny] > 0
                        && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(nx * m + ny);
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] > 0 && !visited[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
