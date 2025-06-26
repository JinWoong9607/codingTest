import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Integer> ans;
    static int n;
    static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        ans = new ArrayList<>();
        backtrack(1);
    }

    public static void backtrack(int startNum) {
        if (ans.size() == m) {
            for (int an : ans) {
                System.out.print(an + " ");
            }
            System.out.println();
            return;
        }

        for (int i = startNum; i <= n; i++) {
            ans.add(i);
            backtrack(i + 1);
            ans.remove(ans.size() - 1);
        }
    }
}