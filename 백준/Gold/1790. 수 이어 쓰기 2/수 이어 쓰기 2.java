import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long n = Long.parseLong(st.nextToken());
        long k = Long.parseLong(st.nextToken());
        
        long totalLength = 0;
        long len = 1; 
        long count = 9;
        long tempN = n;

        while (tempN > count) {
            totalLength += len * count;
            tempN -= count;
            len++;
            count *= 10;
        }
        totalLength += tempN * len;

        if (k > totalLength) {
            System.out.println(-1);
            return;
        }
        
        long targetNum = 0;
        len = 1;
        count = 9;
        
        while (k > len * count) {
            k -= len * count;
            len++;
            count *= 10;
        }
        
        long startNum = (long) Math.pow(10, len - 1);
        
        targetNum = startNum + (k - 1) / len;
        
        int digitIndex = (int) ((k - 1) % len);

        System.out.println(String.valueOf(targetNum).charAt(digitIndex));
    }
}