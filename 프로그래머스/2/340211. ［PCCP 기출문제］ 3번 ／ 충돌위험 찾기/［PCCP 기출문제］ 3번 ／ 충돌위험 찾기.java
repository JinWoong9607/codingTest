import java.util.*;
import java.util.Arrays;
class Solution {
    public int solution(int[][] points, int[][] routes) {
        int x = routes.length;
        int n = points.length;
        final int MAX = 100;  // 좌표는 1~100 범위

        // 0-based 좌표로 변환
        int[][] p = new int[n][2];
        for (int i = 0; i < n; i++) {
            p[i][0] = points[i][0] - 1;
            p[i][1] = points[i][1] - 1;
        }

        // 로봇별 현재 위치 및 다음 목표 인덱스 초기화
        int[] posX = new int[x], posY = new int[x], stage = new int[x];
        for (int i = 0; i < x; i++) {
            int start = routes[i][0] - 1;
            posX[i] = p[start][0];
            posY[i] = p[start][1];
            stage[i] = 1;
        }

        // 최대 시뮬레이션 시간 계산
        int maxTime = 0;
        for (int i = 0; i < x; i++) {
            int t = 0;
            for (int s = 1; s < routes[i].length; s++) {
                int a = routes[i][s - 1] - 1;
                int b = routes[i][s]     - 1;
                t += Math.abs(p[a][0] - p[b][0]) + Math.abs(p[a][1] - p[b][1]);
            }
            maxTime = Math.max(maxTime, t);
        }

        int ans = 0;
        int[][] visited = new int[MAX][MAX];

        // 0초(출발 지점)에서 충돌 체크
        for (int i = 0; i < x; i++) {
            visited[posX[i]][posY[i]]++;
        }
        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                if (visited[i][j] >= 2) {
                    ans += 1;
                }
            }
        }

        // 시간 경과에 따른 시뮬레이션
        for (int t = 1; t <= maxTime; t++) {
            // visited 초기화
            for (int i = 0; i < MAX; i++) {
                Arrays.fill(visited[i], 0);
            }

            // 각 로봇 이동 및 위치 집계
            for (int i = 0; i < x; i++) {
                if (stage[i] < routes[i].length) {
                    int target = routes[i][stage[i]] - 1;
                    int tx = p[target][0];
                    int ty = p[target][1];

                    // r 좌표(행) 우선 이동
                    if (posX[i] != tx) {
                        posX[i] += posX[i] < tx ? 1 : -1;
                    } else {
                        posY[i] += posY[i] < ty ? 1 : -1;
                    }

                    // 목표 지점 도달 시 다음 단계로
                    if (posX[i] == tx && posY[i] == ty) {
                        stage[i]++;
                    }

                    visited[posX[i]][posY[i]]++;
                }
            }

            // 충돌 집계 (위험 상황은 1회만)
            for (int i = 0; i < MAX; i++) {
                for (int j = 0; j < MAX; j++) {
                    if (visited[i][j] >= 2) {
                        ans += 1;
                    }
                }
            }
        }

        return ans;
    }
}
