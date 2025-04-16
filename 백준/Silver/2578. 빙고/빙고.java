import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        ArrayList<ArrayList<Integer>> bingo = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            bingo.add(new ArrayList<>());
        }
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                int next = Integer.parseInt(st.nextToken());
                if (i == j) {
                    bingo.get(10).add(next);
                }
                if (i + j == 4) {
                    bingo.get(11).add(next);
                }
                bingo.get(i).add(next);
                bingo.get(5+j).add(next);
            }
        }
        int answer = 0;
        int count = 0;
        outer:
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                int next = Integer.parseInt(st.nextToken());
                for (int k = 0; k < 12; k++) {
                    if (bingo.get(k).contains(next)) {
                        bingo.get(k).remove((Integer) next);
                        if (bingo.get(k).isEmpty()) {
                            count++;
                            if (count >= 3) {
                                answer = 5*i + j + 1;
                                break outer;
                            }
                        }
                    }
                }
            }
        }
        System.out.println(answer);
    }
}
