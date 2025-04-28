import java.io.*;
import java.util.*;

public class Main {
    static boolean[] prime;
    static Queue<Integer> q = new LinkedList<>(List.of(2, 3, 5, 7));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int count = (int) Math.pow(10, n);
        int range = (int) Math.sqrt(count);
        prime = new boolean[range + 1];
        prime[0] = true;
        prime[1] = true;
        for (int i = 2; i < prime.length; i++) {
            if (!prime[i]) {
                int sifter = i * 2;
                while (sifter < prime.length) {
                    prime[sifter] = true;
                    sifter += i;
                }
            }
        }
        search(n);

    }

    public static void search(int n) {
        if (n == 1) {
            while (!q.isEmpty()) {
                System.out.println(q.remove());
            }
            return;
        }

        int size = q.size();
        for (int i = 0; i < size; i++) {
            int cur = q.remove();

            for (int j = 1; j < 10; j += 2) {
                if (j == 5) continue;

                int candidate = cur * 10 + j;
                boolean isPrime = true;

                int limit = (int) Math.sqrt(candidate);
                for (int k = 2; k <= limit; k++) {
                    if (candidate % k == 0) {
                        isPrime = false;
                        break;
                    }
                }

                if (isPrime) {
                    q.add(candidate);
                }
            }
        }

        search(n - 1);
    }

}
