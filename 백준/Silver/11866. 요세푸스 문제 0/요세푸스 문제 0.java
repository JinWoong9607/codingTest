import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        ArrayList<Integer> Perm = new ArrayList<>();
        for (int i = 0; i < n ; i++) {
            Perm.add(i+1);
        }
        System.out.print("<");
        int now = m - 1;
        while(!Perm.isEmpty()) {
            int rm = Perm.remove(now);
            if(!Perm.isEmpty()) {
                System.out.print(rm+", ");
            } else {
                System.out.print(rm+">");
            }
            now += m - 1;
            if (Perm.isEmpty()) {
                break;
            }
            if (now >= Perm.size()) {
                now = now % Perm.size();
            }
        }
    }
}
