import java.io.*;
import java.util.*;
import java.util.HashMap;

public class Main {
    static int[] dx = {0, 1, 0, -1}; // 우, 하, 좌, 상
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];

        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            map[x][y] = 1;
        }

        int l = Integer.parseInt(br.readLine());
        HashMap<Integer, String> directions = new HashMap<>();
        for (int i = 0; i < l; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            String direction = st.nextToken();
            directions.put(time, direction);
        }

        Deque<int[]> snake = new LinkedList<>();
        snake.offer(new int[]{0, 0});

        int time = 0;
        int direction = 0;

        while (true) {
            time++;

            int[] head = snake.peekLast();
            int nx = head[0] + dx[direction];
            int ny = head[1] + dy[direction];

            if (nx < 0 || ny < 0 || nx >= n || ny >= n) break;
            for (int[] body : snake) {
                if (body[0] == nx && body[1] == ny) {
                    bw.write(String.valueOf(time));
                    bw.flush();
                    bw.close();
                    return;
                }
            }

            snake.offerLast(new int[]{nx, ny});

            if (map[nx][ny] != 1) {
                snake.pollFirst();
            } else {
                map[nx][ny] = 0;
            }
            
            if (directions.containsKey(time)) {
                if (directions.get(time).equals("D")) {
                    direction = (direction + 1) % 4;
                } else {
                    direction = (direction + 3) % 4;
                }
            }
        }

        bw.write(String.valueOf(time));
        bw.flush();
        bw.close();
    }
}