import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static char[][] graph;
    static int[] start;
    static int[] end;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static boolean[][] visited;
    static int n;
    static int m;
    static int ans = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        start = new int[2];
        end = new int[2];

        graph = new char[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                char dot = line.charAt(j);
                graph[i][j] = dot;
                if (dot == 'D') {
                    end[0] = i;
                    end[1] = j;
                }
                if (dot == 'S') {
                    start[0] = i;
                    start[1] = j;
                }
            }
        }

        bfs();
    }

    public static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        Queue<int[]> next = new LinkedList<>();
        List<int[]> water = new ArrayList<>();
        visited[start[0]][start[1]] = true;
        q.add(start);
        while(!q.isEmpty()) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (graph[i][j] == '*') {
                        for (int k = 0; k < 4; k++) {
                            int nx = i + dx[k], ny = j + dy[k];
                            if (checkBoundary(nx, ny) && graph[nx][ny] == '.') {
                                water.add(new int[]{nx, ny});
                            }
                        }
                    }
                }
            }

            for (int[] w : water) {
                graph[w[0]][w[1]] = '*';
            }
            water.clear();

            while (!q.isEmpty()) {
                int[] loc = q.remove();
                for (int i = 0; i < 4; i++) {
                    if (checkBoundary(loc[0] + dx[i], loc[1] + dy[i])) {
                        if (graph[loc[0] + dx[i]][loc[1] + dy[i]] == '.' || graph[loc[0] + dx[i]][loc[1] + dy[i]] == 'D') {
                            int newX = loc[0] + dx[i];
                            int newY = loc[1] + dy[i];
                            int[] newLoc = {newX, newY};
                            if (!visited[newX][newY]) {
                                visited[newX][newY] = true;
                                next.add(newLoc);
                            }
                            if (graph[newX][newY] == 'D') {
                                System.out.println(ans);
                                return;
                            }
                        }
                    }
                }
            }

            q.addAll(next);
            next.clear();
            ans++;
        }
        System.out.println("KAKTUS");
    }

    public static boolean checkBoundary(int i, int j) {
        return i >= 0 && i < n && j >= 0 && j < m;
    }
}
