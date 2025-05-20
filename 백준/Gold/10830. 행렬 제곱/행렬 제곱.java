import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long B;
    static int[][] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());

        A = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken()) % 1000;
            }
        }

        int[][] result = matrixPow(A, B);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(result[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    private static int[][] multiply(int[][] X, int[][] Y) {
        int[][] C = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                long sum = 0;
                for (int k = 0; k < N; k++) {
                    sum += (long) X[i][k] * Y[k][j];
                }
                C[i][j] = (int) (sum % 1000);
            }
        }
        return C;
    }

    private static int[][] matrixPow(int[][] M, long exp) {
        int[][] result = new int[N][N];
        for (int i = 0; i < N; i++) {
            result[i][i] = 1;
        }

        int[][] base = M;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = multiply(result, base);
            }
            base = multiply(base, base);
            exp /= 2;
        }
        return result;
    }
}
