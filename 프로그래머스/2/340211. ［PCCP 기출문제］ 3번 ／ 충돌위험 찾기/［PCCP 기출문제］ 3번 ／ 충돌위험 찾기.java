import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int solution(int[][] points, int[][] routes) {
        int x = routes.length;
        int[] rpos     = new int[x];
        int[] cpos     = new int[x];
        int[] idx      = new int[x];
        boolean[] fin  = new boolean[x];

        for (int k = 0; k < x; k++) {
            int start = routes[k][0] - 1;
            rpos[k] = points[start][0];
            cpos[k] = points[start][1];
            idx[k]  = 1;
        }

        int answer = 0;
        answer += countCollisions(rpos, cpos, fin);

        while (true) {
            for (int k = 0; k < x; k++) {
                if (!fin[k] && idx[k] >= routes[k].length) {
                    fin[k] = true;
                }
            }
            boolean allDone = true;
            for (boolean f : fin) {
                if (!f) { allDone = false; break; }
            }
            if (allDone) break;

            for (int k = 0; k < x; k++) {
                if (fin[k]) continue;
                int targetPoint = routes[k][idx[k]] - 1;
                int tr = points[targetPoint][0];
                int tc = points[targetPoint][1];
                if (rpos[k] < tr)       rpos[k]++;
                else if (rpos[k] > tr)  rpos[k]--;
                else if (cpos[k] < tc)  cpos[k]++;
                else if (cpos[k] > tc)  cpos[k]--;
                if (rpos[k] == tr && cpos[k] == tc) {
                    idx[k]++;
                }
            }

            answer += countCollisions(rpos, cpos, fin);
        }

        return answer;
    }

    private int countCollisions(int[] rpos, int[] cpos, boolean[] fin) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = rpos.length;
        for (int k = 0; k < n; k++) {
            if (fin[k]) continue;
            int key = rpos[k] * 1000 + cpos[k];
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        int cnt = 0;
        for (int v : map.values()) {
            if (v >= 2) cnt++;
        }
        return cnt;
    }
}
