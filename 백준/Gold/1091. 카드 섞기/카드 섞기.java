import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] P = new int[n];
        int[] S = new int[n];
        int[] deck = new int[n];
        int[] shuffle = new int[n];
        for (int i = 0; i < n; i++) {
            P[i] = Integer.parseInt(st.nextToken());
            deck[i] = i%3;
            shuffle[i] = i%3;
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            S[i] = Integer.parseInt(st.nextToken());
        }
        int ans = 0;
        while (!Arrays.equals(shuffle, P)) {
            int[] tmp = new int[n];
            for (int i = 0; i < n; i++) {
                tmp[i] = shuffle[S[i]];
            }
            System.arraycopy(tmp, 0, shuffle, 0, n);
            if (Arrays.equals(shuffle, deck)) {
                ans = -1;
                break;
            }
            ans++;
        }

        System.out.println(ans);

    }
}
