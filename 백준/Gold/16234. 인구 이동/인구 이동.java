import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class coord {
        int x;
        int y;
        public coord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            coord other = (coord) obj;
            return x == other.x && y == other.y;
        }
    }
    static int[] dx = new int[] {-1, 0, 1, 0};
    static int[] dy = new int[] {0, -1, 0 ,1};
    static int n;
    static int l;
    static int r;
    static int[][] graph;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int count = 0;
        while (count < 2000) {
            visited = new boolean[n][n];
            boolean isMoved = false;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j]) {
                        ArrayList<coord> union = new ArrayList<>();
                        int sum = bfs(i, j , union);

                        if (union.size() > 1) {
                            int avg = sum / union.size();
                            for (coord c : union) {
                                graph[c.x][c.y] = avg;
                            }
                            isMoved = true;
                        }
                    }
                }
            }

            if (!isMoved) {
                break;
            }
            count++;
        }
        System.out.println(count);
    }

    public static int bfs(int x, int y, ArrayList<coord> union) {
        Queue<coord> q = new LinkedList<>();
        q.add(new coord(x, y));
        visited[x][y] = true;
        union.add(new coord(x, y));
        int sum = graph[x][y];

        while (!q.isEmpty()) {
            coord current = q.poll();
            int currentX = current.x;
            int currentY = current.y;

            for (int i = 0; i < 4; i++) {
                int nextX = currentX + dx[i];
                int nextY = currentY + dy[i];

                if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n && !visited[nextX][nextY]) {
                    boolean associated = isAssociation(graph[currentX][currentY], graph[nextX][nextY]);
                    if (associated) {
                        visited[nextX][nextY] = true;
                        q.add(new coord(nextX, nextY));
                        union.add(new coord(nextX, nextY));
                        sum += graph[nextX][nextY];
                    }
                }
            }
        }
        return sum;
    }

    public static boolean isAssociation(int n , int m) {
        return ( Math.abs(n-m) >= l && Math.abs(n-m) <= r );
    }
}
