import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static char[][] graph;
    static ArrayList<Integer> land;
    static Queue<Integer> q = new LinkedList<>();
    static int[] dx = {-1, 0 , 1, 0};
    static int[] dy = {0 , -1, 0, 1};
    static boolean[] visited;
    static int n;
    static int m;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new char[n][m];
        land = new ArrayList<Integer>();

        for(int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                graph[i][j] = line.charAt(j);
                if (graph[i][j] == 'L') {
                    land.add(i * m + j);
                }
            }
        }
        visited = new boolean[land.size()];

        getLength();

        System.out.println(ans);

    }
    public static void getLength() {
        for (int si = 0; si < n; si++) {
            for (int sj = 0; sj < m; sj++) {
                if (graph[si][sj] != 'L') {
                    continue;
                }
                boolean[][] visitedGrid = new boolean[n][m];
                Queue<Point> bfsQ = new LinkedList<>();
                bfsQ.add(new Point(si, sj, 0));
                visitedGrid[si][sj] = true;

                while (!bfsQ.isEmpty()) {
                    Point p = bfsQ.poll();
                    ans = Math.max(ans, p.depth);
                    for (int k = 0; k < 4; k++) {
                        int ni = p.x + dx[k], nj = p.y + dy[k];
                        if (ni<0||ni>=n||nj<0||nj>=m) continue;
                        if (!visitedGrid[ni][nj] && graph[ni][nj]=='L') {
                            visitedGrid[ni][nj] = true;
                            bfsQ.add(new Point(ni, nj, p.depth + 1));
                        }
                    }
                }
            }
        }
    }

    static class Point {
        int x, y, depth;
        Point(int x, int y, int d) { this.x = x; this.y = y; this.depth = d; }
    }
}
