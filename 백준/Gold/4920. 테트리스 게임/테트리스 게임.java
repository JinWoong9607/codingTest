import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] point;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        boolean loop = false;
        int count = 0;
        while(!loop) {
            count++;
            n = Integer.parseInt(br.readLine().trim());

            if (n == 0) {
                loop = true;
                continue;
            }

            point = new int[n][n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    point[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] baseDX = {
                    {0, 1, 2, 3},
                    {0, 1, 1, 2},
                    {0, 1, 2, 2},
                    {0, 1, 1, 2},
                    {0, 0, 1, 1}
            };
            int[][] baseDY = {
                    {0, 0, 0, 0},
                    {0, 0, -1, -1},
                    {0, 0, 0, -1},
                    {0, 0, -1, 0},
                    {0, -1, 0, -1}
            };


            int answer = Integer.MIN_VALUE;

            for (int s = 0; s < 5; s++) {
                int[] dx = Arrays.copyOf(baseDX[s], 4);
                int[] dy = Arrays.copyOf(baseDY[s], 4);

                for (int rot = 0; rot < 4; rot++) {
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            int sum = 0;
                            boolean ok = true;
                            for (int k = 0; k < 4; k++) {
                                int x = i + dx[k], y = j + dy[k];
                                if (!inBounds(x, y)) {
                                    ok = false;
                                    break;
                                }
                                sum += point[x][y];
                            }
                            if (ok && sum > answer) {
                                answer = sum;
                            }
                        }
                    }
                    int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
                    int[] ndx = new int[4], ndy = new int[4];
                    for (int k = 0; k < 4; k++) {
                        ndx[k] = dy[k];
                        ndy[k] = -dx[k];
                        minX = Math.min(minX, ndx[k]);
                        minY = Math.min(minY, ndy[k]);
                    }
                    for (int k = 0; k < 4; k++) {
                        dx[k] = ndx[k] - minX;
                        dy[k] = ndy[k] - minY;
                    }
                }
            }

            bw.write(count + ". " + answer);
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    public static boolean inBounds(int x, int y) {
        return (0 <= x && x < n && 0 <= y && y < n);
    }
}