import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long C = Long.parseLong(st.nextToken());

        long[] weights = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            weights[i] = Long.parseLong(st.nextToken());
        }

        int mid = N / 2;
        long[] group1 = Arrays.copyOfRange(weights, 0, mid);
        long[] group2 = Arrays.copyOfRange(weights, mid, N);

        List<Long> sums1 = new ArrayList<>();
        List<Long> sums2 = new ArrayList<>();

        generateSums(group1, 0, 0, sums1);
        generateSums(group2, 0, 0, sums2);

        Collections.sort(sums2);

        long result = 0;

        for (long sum1 : sums1) {
            if (sum1 > C) continue;

            long remaining = C - sum1;
            int count = upperBound(sums2, remaining);
            result += count;
        }

        System.out.println(result);
    }

    private static void generateSums(long[] items, int index, long currentSum, List<Long> sums) {
        if (index == items.length) {
            sums.add(currentSum);
            return;
        }

        generateSums(items, index + 1, currentSum, sums);
        generateSums(items, index + 1, currentSum + items[index], sums);
    }

    private static int upperBound(List<Long> list, long target) {
        int left = 0;
        int right = list.size();

        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}