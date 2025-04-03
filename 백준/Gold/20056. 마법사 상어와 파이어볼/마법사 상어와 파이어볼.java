import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Main {
    static public ArrayList<int[]> fireballs;
    static public int trial = 0;
    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] dy=  {-1, -1, 0, 1, 1, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        fireballs = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int[] fireBall = new int[5];
            fireBall[0] = c;
            fireBall[1] = r;
            fireBall[2] = m;
            fireBall[3] = s;
            fireBall[4] = d;
            fireballs.add(fireBall);
        }

        int answer = start(N, fireballs, K);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();

    }

    public static int start(int N, ArrayList<int[]> fireballs, int K) {
        HashMap<Integer, Integer> count = new HashMap<>();
        HashMap<Integer, Integer> mass = new HashMap<>();
        HashMap<Integer, Integer> vector = new HashMap<>();
        HashMap<Integer, Integer> speed = new HashMap<>();
        HashMap<Integer, Integer> direction = new HashMap<>();

        int trial = 0;
        while (trial<K) {

            int fireballCount = fireballs.size();
            for (int i = 0; i < fireballCount; i++) {
                int[] fireBall = fireballs.get(i);
                int c = fireBall[0];
                int r = fireBall[1];
                int m = fireBall[2];
                int s = fireBall[3];
                int d = fireBall[4];
                r += s * dy[d];
                c += s * dx[d];

                if (r < 1) {
                    while (r<1) {
                        r += N;
                    }
                }

                if (r > N) {
                    while (r>N) {
                        r -= N;
                    }
                }

                if (c < 1) {
                    while (c<1) {
                        c += N;
                    }
                }

                if (c > N) {
                    while (c>N) {
                    c -= N;
                    }
                }
                int key = (r - 1) * N + (c - 1);
                if (mass.containsKey(key)) {
                    count.put(key, count.getOrDefault(key, 1) + 1);
                    mass.put(key, mass.get(key) + m);
                    vector.put(key, vector.get(key) + d % 2);
                    speed.put(key, speed.get(key) + s);
                    direction.put(key, direction.get(key) + d);
                } else {
                    count.put(key, 1);
                    mass.put(key, m);
                    vector.put(key, d % 2);
                    speed.put(key, s);
                    direction.put(key, d);
                }
            }
            fireballs.clear();
            for (Integer key : mass.keySet()) {
                int r = key / N + 1;
                int c = key % N + 1;
                if (count.get(key) > 1) {
                    if (vector.get(key) == 0 || vector.get(key).equals(count.get(key))) {
                        for (int i = 0; i < 4; i++) {
                            if (mass.get(key) / 5 != 0) {
                            fireballs.add(new int[]{c, r, mass.get(key) / 5, speed.get(key) / count.get(key), i * 2});
                            }
                        }
                    } else {
                        for (int i = 0; i < 4; i++) {
                            if (mass.get(key) / 5 != 0) {
                                fireballs.add(new int[]{c, r, mass.get(key) / 5,
                                    speed.get(key) / count.get(key), i * 2 + 1});
                            }
                        }
                    }
                } else {
                    fireballs.add(new int[]{c, r, mass.get(key) , speed.get(key), direction.get(key)});
                }
            }
            mass.clear();
            vector.clear();
            speed.clear();
            direction.clear();
            count.clear();
            trial++;
        }
        int answer = 0;

        for (int[] fireBall : fireballs) {
            answer += fireBall[2];
        }

        return answer;
    }

}
