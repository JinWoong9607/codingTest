import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int start;
    static int[] input;
    static int[] da = {0,1,5,7,3};
    static List<Integer> toRemove = new ArrayList<>();
    static ArrayList<Integer> line;
    static int ans;
    static int[] dr = {0,-1,1,0,0};
    static int[] dc = {0,0,0,-1,1};
    static int[] index;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        input = new int[n*n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                input[i*n + j] = Integer.parseInt(st.nextToken());
            }
        }
        line = new ArrayList<>();
        line.add(-1);
        start = input.length / 2;
        index = new int[n*n];
        fill(1);
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int vector = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());
            toRemove.clear();
            int sr = n/2, sc = n/2;
            for (int j = 1; j <= length; j++) {
                int nr = sr + dr[vector] * j;
                int nc = sc + dc[vector] * j;
                if (nr < 0 || nr >= n || nc < 0 || nc >= n) break;
                int flat = nr * n + nc;
                toRemove.add(index[flat]);
            }
            Collections.sort(toRemove, Comparator.reverseOrder());
            for (int idx : toRemove) {
                if (idx < line.size()) line.remove(idx);
            }
            popping();
        }
        System.out.println(ans);
    }

    public static void fill(int depth) {
        int point = start - depth - n * depth;
        if (depth > n/2) return;
        for (int i = 0; i < 2*depth; i++) {
            point += n;
            index[point] = line.size();
            line.add(input[point]);
        }
        for (int i = 0; i < 2*depth; i++) {
            point++;
            index[point] = line.size();
            line.add(input[point]);
        }
        for (int i = 0; i < 2*depth; i++) {
            point -= n;
            index[point] = line.size();
            line.add(input[point]);
        }
        for (int i = 0; i < 2*depth; i++) {
            point--;
            index[point] = line.size();
            line.add(input[point]);
        }
        fill(depth + 1);
    }

    public static void popping() {
        List<Integer> marbles = new ArrayList<>();
        for (int i = 1; i < line.size(); i++) {
            int v = line.get(i);
            if (v > 0) {
                marbles.add(v);
            }
        }
        boolean exploded;
        do {
            exploded = false;
            List<Integer> newMarbles = new ArrayList<>();
            int cnt = 1;
            for (int i = 1; i <= marbles.size(); i++) {
                if (i < marbles.size() && marbles.get(i).equals(marbles.get(i - 1))) {
                    cnt++;
                } else {
                    if (cnt >= 4) {
                        ans += marbles.get(i - 1) * cnt;
                        exploded = true;
                    } else {
                        for (int k = 0; k < cnt; k++) {
                            newMarbles.add(marbles.get(i - 1));
                        }
                    }
                    cnt = 1;
                }
            }
            marbles = newMarbles;
        } while (exploded);
        List<Integer> grouped = new ArrayList<>();
        int cnt = 1;
        for (int i = 1; i <= marbles.size() && grouped.size() + 2 <= n * n; i++) {
            if (i < marbles.size() && marbles.get(i).equals(marbles.get(i - 1))) {
                cnt++;
            } else {
                grouped.add(cnt);
                grouped.add(marbles.get(i - 1));
                cnt = 1;
            }
        }
        line = new ArrayList<>();
        line.add(-1);
        line.addAll(grouped);
        while (line.size() < n * n) {
            line.add(0);
        }
    }
}
