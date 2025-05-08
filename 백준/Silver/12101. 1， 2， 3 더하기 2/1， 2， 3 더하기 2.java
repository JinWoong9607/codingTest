import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int k;
    static String ans;
    static int count;
    static boolean found;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        ans = "-1";
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        backtrack(0, "");
        System.out.println(ans);
    }

    public static void backtrack(int sum, String line) {
        if (found) {
            return;
        }
        if (sum == n) {
            count ++;
            if (count == k) {
                ans = line;
                found = true;
            }
            return;
        }
        for (int i = 1; i<=3; i++) {
            if (n < sum + i) {
                continue;
            }
            if (line.isEmpty()) {
                backtrack(sum +i, line + i);
            } else {
                backtrack(sum + i, line + "+" + i);
            }
        }
    }
}
