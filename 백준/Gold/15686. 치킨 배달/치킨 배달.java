import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {
    static class node {
        int x;
        int y;

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            node node = (node) o;
            return x == node.x && y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
    static ArrayList<node> list;
    static ArrayList<node> select;
    static ArrayList<node> house;
    static int[][] graph;
    static int size;
    static int m;
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        house = new ArrayList<>();
        select = new ArrayList<>();
        graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == 1) {
                    house.add(new node(i,j));
                }
                if (graph[i][j]== 2) {
                    list.add(new node(i, j));
                }
            }
        }
        size = list.size();
        recursion(m, 0);
        System.out.println(ans);
    }

    public static void recursion(int left, int count) {
        if (left == 0) {
            calculateDistance();
            return;
        } else {
            while (count <= list.size() - left) {
                select.add(list.get(count));
                recursion(left - 1, count + 1);
                select.remove(select.size() - 1);
                count++;
            }
        }
    }

    public static void calculateDistance() {
        int total = 0;
        for (node house : house) {
            int distance = Integer.MAX_VALUE;
            for (node select : select) {
                int newDistance = Math.abs(house.getX() - select.getX()) + Math.abs(house.getY() - select.getY());
                distance = Math.min(distance, newDistance);
            }
            total += distance;
        }
        ans = Math.min(ans, total);
    }
}
