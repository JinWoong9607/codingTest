import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        long[] a = new long[n];
        long[] b = new long[n];
        long[] c = new long[n];
        long[] d = new long[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            a[i] = Long.parseLong(st.nextToken());
            b[i] = Long.parseLong(st.nextToken());
            c[i] = Long.parseLong(st.nextToken());
            d[i] = Long.parseLong(st.nextToken());
        }
        long[] ab = new long[n*n];
        long[] cd = new long[n*n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ab[count] = a[i] + b[j];
                cd[count] = c[i] + d[j];
                count++;
            }
        }

        Arrays.sort(ab);
        Arrays.sort(cd);
        int left = 0;
        int right = n*n - 1;
        long ans = 0L;
        while ( left < n*n && right >=0) {
            long sum = ab[left] + cd[right];
            if (sum < 0) {
                left ++;
            } else if (sum > 0) {
                right --;
            } else {
                long sumAB = ab[left];
                long sumCD = cd[right];
                int countAB = 0;
                int countCD = 0;
                while (left < n*n && ab[left] == sumAB) {
                    countAB++;
                    left++;
                }
                while (right >= 0 && cd[right] == sumCD) {
                    countCD++;
                    right--;
                }
                ans += (long) countAB*countCD;
            }
        }
        System.out.println(ans);
    }
}
