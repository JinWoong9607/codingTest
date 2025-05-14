import java.io.*;
import java.util.*;

public class Main {
    static class Shark {
        int speed, dir, size;
        Shark(int s, int d, int z) { speed = s; dir = d; size = z; }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Shark[][] grid = new Shark[R][C];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            grid[r][c] = new Shark(s, d, z);
        }

        int ans = 0;
        int[] dr = {0, -1, 1, 0, 0};
        int[] dc = {0, 0, 0, 1, -1};

        for (int col = 0; col < C; col++) {
            for (int row = 0; row < R; row++) {
                if (grid[row][col] != null) {
                    ans += grid[row][col].size;
                    grid[row][col] = null;
                    break;
                }
            }
            Shark[][] newGrid = new Shark[R][C];
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (grid[r][c] == null) continue;
                    Shark shark = grid[r][c];
                    int s = shark.speed;
                    int d = shark.dir;
                    int z = shark.size;

                    int cycle = (d <= 2) ? (R - 1) * 2 : (C - 1) * 2;
                    int move = s % cycle;
                    int nr = r, nc = c, nd = d;

                    for (int m = 0; m < move; m++) {
                        int tr = nr + dr[nd];
                        int tc = nc + dc[nd];
                        if (tr < 0 || tr >= R || tc < 0 || tc >= C) {
                            // 방향 전환
                            if (nd == 1) nd = 2;
                            else if (nd == 2) nd = 1;
                            else if (nd == 3) nd = 4;
                            else if (nd == 4) nd = 3;
                        }
                        nr += dr[nd];
                        nc += dc[nd];
                    }
                    if (newGrid[nr][nc] == null || newGrid[nr][nc].size < z) {
                        newGrid[nr][nc] = new Shark(shark.speed, nd, z);
                    }
                }
            }
            grid = newGrid;
        }

        System.out.println(ans);
    }
}
