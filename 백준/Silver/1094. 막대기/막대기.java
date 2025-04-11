import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int answer = Integer.parseInt(br.readLine());
        int count = 0;
        while(answer > 0) {
            count += (answer & 1);
            answer >>= 1;
        }
        System.out.println(count);
    }
}
