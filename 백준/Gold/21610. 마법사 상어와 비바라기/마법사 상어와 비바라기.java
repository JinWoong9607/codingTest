import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] dx = new int[]{0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = new int[]{0, 0, -1, -1, -1, 0, 1, 1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        ArrayList<Integer> clouds = new ArrayList<>();
        clouds.add(n*(n-2));
        clouds.add(n*(n-1));
        clouds.add(n*(n-2) + 1);
        clouds.add(n*(n-1) + 1);
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            ArrayList<Integer> newClouds = new ArrayList<>();
            int vector = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            for (int cloud: clouds) {
                int x = cloud%n;
                int y = cloud/n;
                x = check(x, vector, speed, false);
                y = check(y, vector, speed, true);
                graph[y][x] += 1;
                newClouds.add(n*y + x);
            }
            for (int cloud: newClouds) {
                int x = cloud%n;
                int y = cloud/n;
                int count = getCount(y, x, graph);
                graph[y][x] += count;
            }
            clouds.clear();
            for(int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (graph[j][k] >= 2) {
                        if (!newClouds.contains((j*n) + k)){
                            graph[j][k] -= 2;
                            clouds.add((j * n) + k);
                        }
                    }
                }
            }
        }

        int ans = 0;

        for (int[] line : graph) {
            for(int point: line) {
                ans += point;
            }
        }
        System.out.println(ans);
    }

    private static int getCount(int x, int y, int[][] graph) {
        int count = 0;
        if (x - 1 >= 0) {
            if (y - 1 >= 0) {
                if (graph[x -1][y -1] != 0) {
                    count++;
                }
            }
            if (y + 1 < n) {
                if (graph[x -1][y +1] != 0) {
                    count++;
                }
            }
        }
        if (x + 1 < n) {
            if (y - 1 >= 0) {
                if (graph[x +1][y -1] != 0) {
                    count++;
                }
            }
            if (y + 1 < n) {
                if (graph[x +1][y +1] != 0) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int check(int x, int vector, int speed, boolean vertical) {
        if (!vertical) {
            x += dx[vector] * speed;
        } else {
            x += dy[vector] * speed;
        }
        if (x < 0) {
            while(x < 0) {
                x += n;
            }
        }
        if (x >= n) {
            while(x >= n) {
                x -= n;
            }
        }
        return x;
    }
}
