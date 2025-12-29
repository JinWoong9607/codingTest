import java.io.*;
import java.util.*;

public class Main {

    static final String GOAL = "123456780";

    static HashMap<String, Integer> visited = new HashMap<>();

    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) sb.append(st.nextToken());
        }
        String start = sb.toString();

        System.out.println(bfs(start));
    }

    static int bfs(String start) {
        Queue<String> q = new ArrayDeque<>();

        visited.clear();
        visited.put(start, 0);
        q.add(start);

        while (!q.isEmpty()) {
            String cur = q.poll();
            int dist = visited.get(cur);

            if (cur.equals(GOAL)) return dist;

            int zero = cur.indexOf('0');
            int x = zero / 3;
            int y = zero % 3;

            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (nx < 0 || nx >= 3 || ny < 0 || ny >= 3) continue;

                int nz = nx * 3 + ny;
                String next = swap(cur, zero, nz);

                if (!visited.containsKey(next)) {
                    visited.put(next, dist + 1);
                    q.add(next);
                }
            }
        }

        return -1;
    }

    static String swap(String s, int i, int j) {
        char[] a = s.toCharArray();
        char t = a[i];
        a[i] = a[j];
        a[j] = t;
        return new String(a);
    }
}

