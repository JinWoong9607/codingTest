import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static class node {
        int A;
        int B;
        int time;

        public node(int A, int B, int time) {
            this.A = A;
            this.B = B;
            this.time = time;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        ArrayList<node> edges = new ArrayList<>();
        long[] dist = new long[n+1];

        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            edges.add(new node(A, B, time));
        }

        boolean cycle = false;
        for (int i=1; i<=n; i++) {
            for (node edge : edges) {
                if (dist[edge.A] != Long.MAX_VALUE && dist[edge.B] > dist[edge.A] + edge.time) {
                    dist[edge.B] = dist[edge.A] + edge.time;
                    if (i == n) {
                        cycle = true;
                    }
                }
            }
        }

        if (cycle) {
            System.out.print("-1");
        } else {
            for (int i = 2; i<=n; i++) {
                if (dist[i] == Long.MAX_VALUE) {
                    System.out.println("-1");
                } else {
                    System.out.println(dist[i]);
                }
            }
        }
    }

}
