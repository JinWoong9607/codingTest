import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int[] ints;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ints = new int[M];
        backtrack(M);
    }
    public static void backtrack(int m) {
        if (ints[M-1] != 0) {
            StringBuilder ans = new StringBuilder();
            for(int i = 0; i < M; i++) {
                ans.append(ints[i]).append(" ");
            }
            System.out.println(ans);
            return;
        }
        for (int i = 1; i <= N; i++) {
            boolean visited = false;
            for (int j = 0; j < M; j++) {
                if (ints[j] == i) {
                    visited = true;
                    break;
                }
            }
            if (!visited) {
                ints[M - m] = i;
                backtrack(m - 1);
                ints[M - m] = 0;
            }
        }
    }
}