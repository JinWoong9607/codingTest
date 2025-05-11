import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[][] graph;
    static int[] start = new int[2];
    static int size = 2;
    static int eatCount = 0;
    static int time = 0;
    static int[] dx = {0, -1, 1, 0};
    static int[] dy = {-1, 0, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new int[n][n];
        StringTokenizer st;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == 9) {
                    start[0] = i;
                    start[1] = j;
                    graph[i][j] = 0;
                }
            }
        }

        while (true) {
            Fish fish = findNearestFish();
            if (fish == null) break;

            time += fish.dist;
            start[0] = fish.x;
            start[1] = fish.y;
            graph[fish.x][fish.y] = 0;

            eatCount++;
            if (eatCount == size) {
                size++;
                eatCount = 0;
            }
        }

        System.out.println(time);
    }

    static Fish findNearestFish() {
        boolean[][] visited = new boolean[n][n];
        Queue<Point> queue = new LinkedList<>();
        PriorityQueue<Fish> pq = new PriorityQueue<>(
                Comparator.comparingInt((Fish f) -> f.dist)
                        .thenComparingInt(f -> f.x)
                        .thenComparingInt(f -> f.y)
        );

        queue.offer(new Point(start[0], start[1], 0));
        visited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            Point p = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                int nd = p.dist + 1;

                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if (visited[nx][ny] || graph[nx][ny] > size) continue;

                visited[nx][ny] = true;
                if (graph[nx][ny] > 0 && graph[nx][ny] < size) {
                    pq.offer(new Fish(nx, ny, nd));
                }
                queue.offer(new Point(nx, ny, nd));
            }
        }

        return pq.isEmpty() ? null : pq.poll();
    }

    static class Point {
        int x, y, dist;
        Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    static class Fish {
        int x, y, dist;
        Fish(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}