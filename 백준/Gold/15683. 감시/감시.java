import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int minBlind = Integer.MAX_VALUE;
    static int[][] graph;
    static List<Cctv> cctvs = new ArrayList<>();
    
    static final int[][] DIRS = {
            {-1, 0},
            { 0, 1}, 
            { 1, 0},
            { 0,-1} 
    };
    static final int[][] CCTV_BASE = {
            {},           
            {0},        
            {0, 2},    
            {0, 1},      
            {0, 1, 2},   
            {0, 1, 2, 3} 
    };
    static final int[] ORIENT_COUNT = {0, 4, 2, 4, 4, 1};

    static class Cctv {
        int type, x, y;
        Cctv(int type, int x, int y) {
            this.type = type;
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N][M];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (1 <= graph[i][j] && graph[i][j] <= 5) {
                    cctvs.add(new Cctv(graph[i][j], i, j));
                }
            }
        }
        
        backtrack(0, graph);
        System.out.println(minBlind);
    }
    
    static void backtrack(int idx, int[][] arr) {
        if (idx == cctvs.size()) {
            int cnt = countBlind(arr);
            minBlind = Math.min(minBlind, cnt);
            return;
        }

        Cctv cam = cctvs.get(idx);
        int type = cam.type;
        int[] baseDirs = CCTV_BASE[type];
        int rotations = ORIENT_COUNT[type];
        
        for (int k = 0; k < rotations; k++) {
            int[][] copy = copyGraph(arr);
            for (int base : baseDirs) {
                int d = (base + k) % 4;
                watch(cam.x, cam.y, d, copy);
            }
            backtrack(idx + 1, copy);
        }
    }
    
    static void watch(int x, int y, int d, int[][] arr) {
        int nx = x + DIRS[d][0];
        int ny = y + DIRS[d][1];
        while (0 <= nx && nx < N && 0 <= ny && ny < M && arr[nx][ny] != 6) {
            if (arr[nx][ny] == 0) {
                arr[nx][ny] = -1; 
            }
            nx += DIRS[d][0];
            ny += DIRS[d][1];
        }
    }
    
    static int[][] copyGraph(int[][] src) {
        int[][] dst = new int[N][M];
        for (int i = 0; i < N; i++) {
            System.arraycopy(src[i], 0, dst[i], 0, M);
        }
        return dst;
    }
    
    static int countBlind(int[][] arr) {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 0) cnt++;
            }
        }
        return cnt;
    }
}
