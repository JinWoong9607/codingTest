import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int w;
    static int h;
    static int[][] point;
    static char[][] graph;
    static int[] dx = new int[] {1, 0, -1, 0};
    static int[] dy = new int[] {0, 1, 0, -1};
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        graph = new char[h][w];
        point = new int[2][2];
        for (int i = 0; i < 2; i++) {
            Arrays.fill(point[i], -1);
        }
        for (int i = 0; i < h; i++) {
            String line = br.readLine();
            for (int j = 0; j < w; j++) {
                graph[i][j] = line.charAt(j);
                if (graph[i][j] == 'C') {
                    if (point[0][0] == -1) {
                        point[0][0] = i;
                        point[0][1] = j;
                    } else {
                        point[1][0] = i;
                        point[1][1] = j;
                    }
                }
            }
        }
        bfs();
        System.out.println(count);
    }
    public static void bfs() {
        boolean[][] visited = new boolean[h][w];

        Queue<int[]> q = new LinkedList<>();
        q.add(point[0]);
        visited[point[0][0]][point[0][1]] = true;

        while (!q.isEmpty()) {
            Queue<int[]> newQ = new LinkedList<>();

            while (!q.isEmpty()) {
                int[] mirror = q.remove();

                for (int i = 0; i < 4; i++) {
                    int[] next = mirror.clone();
                    next[0] += dx[i];
                    next[1] += dy[i];

                    while (next[0] >= 0 && next[0] < h
                            && next[1] >= 0 && next[1] < w
                            && graph[next[0]][next[1]] != '*') {

                        if (Arrays.equals(next, point[1])) {
                            return;
                        }

                        if (!visited[next[0]][next[1]]) {
                            visited[next[0]][next[1]] = true;
                            newQ.add(next.clone());
                        }

                        next[0] += dx[i];
                        next[1] += dy[i];
                    }
                }
            }

            if (!newQ.isEmpty()) {
                count++;
                q = newQ;
            } else {
                return;
            }
        }
    }

}
