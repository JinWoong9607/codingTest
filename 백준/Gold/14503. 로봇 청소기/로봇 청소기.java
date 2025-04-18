import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] graph;
    static int count;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = { 0, 1, 0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int vector = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        for (int i = 0; i< n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        count = 0;
        cleaner(x, y, vector);
        System.out.println(count);
    }
    public static void cleaner(int x, int y, int vector) {
        boolean finished = false;
        while (!finished) {
            if (graph[x][y] == 0) {
                graph[x][y] = -1;
                count++;
            }
            if (checkCleaned(x, y)) {
                int back = (vector + 2) % 4;
                int bx = x + dx[back];
                int by = y + dy[back];
                if (graph[bx][by] == 1) {
                    finished = true;
                } else {
                    x = bx;
                    y = by;
                }
            } else {
                vector = (vector + 3) % 4;
                while (graph[x + dx[vector]][y + dy[vector]] != 0) {
                    vector = (vector + 3) % 4;
                }
                int nx =  x + dx[vector];
                int ny = y + dy[vector];
                x = nx;
                y = ny;
            }
        }
    }
    public static boolean checkCleaned(int x, int y) {
        return graph[x - 1][y] != 0
               && graph[x + 1][y] != 0
                && graph[x][y + 1] != 0
                && graph[x][y - 1] != 0;
    }
}
