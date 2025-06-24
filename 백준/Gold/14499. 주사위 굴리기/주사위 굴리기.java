import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int x;
    static int y;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[] cube = {0, 0, 0, 0, 0, 0};
    static int[] roll = {3, 2, 4, 1};

    static int count;
    static int[][] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        count = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int num = Integer.parseInt(st.nextToken()) - 1;
            if (countEdge(x + dx[num], y + dy[num])) {
                x += dx[num];
                y += dy[num];
                int nextTop = cube[roll[num]];
                int nextBottom = cube[5 - roll[num]];
                cube[roll[num]] = cube[5];
                cube[5 - roll[num]] = cube[0];
                cube[0] = nextTop;
                cube[5] = nextBottom;
                if (graph[x][y] == 0) {
                    graph[x][y] = cube[5];
                } else {
                    cube[5] = graph[x][y];
                    graph[x][y] = 0;
                }
                System.out.println(cube[0]);
            }
        }
    }

    public static boolean countEdge (int x, int y) {
        if (x < 0 || x >=n) {
            return false;
        }
        return y >= 0 && y < m;
    }
}
