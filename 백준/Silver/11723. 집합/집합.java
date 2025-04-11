import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 입력 및 출력을 빠르게 하기 위한 설정
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());
        int set = 0;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            switch (command) {
                case "add": {
                    int x = Integer.parseInt(st.nextToken());
                    set |= (1 << (x - 1));
                    break;
                }
                case "remove": {
                    int x = Integer.parseInt(st.nextToken());
                    set &= ~(1 << (x - 1));
                    break;
                }
                case "check": {
                    int x = Integer.parseInt(st.nextToken());
                    sb.append(((set & (1 << (x - 1))) != 0) ? "1\n" : "0\n");
                    break;
                }
                case "toggle": {
                    int x = Integer.parseInt(st.nextToken());
                    set ^= (1 << (x - 1));
                    break;
                }
                case "all": {
                    set = (1 << 20) - 1;
                    break;
                }
                case "empty": {
                    set = 0;
                    break;
                }
            }
        }
        System.out.print(sb);
    }
}
