import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n, count = 0;
    static int[] col;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        col = new int[n];
        backtrack(0);
        System.out.println(count);
    }

    public static void backtrack(int row) {
        if (row == n) {
            count++;
            return;
        }
        for (int c = 0; c < n; c++) {
            if (isSafe(row, c)) {
                col[row] = c;
                backtrack(row + 1);
            }
        }
    }

    public static boolean isSafe(int row, int c) {
        for (int i = 0; i < row; i++) {
            if (col[i] == c) {
                return false;
            }                                   
            if (Math.abs(row - i) == Math.abs(c - col[i])) {
                return false;
            }
        }
        return true;
    }
}
