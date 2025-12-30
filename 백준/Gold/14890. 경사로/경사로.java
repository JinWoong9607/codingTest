import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        boolean[] isUsed = new boolean[n];
        int next = -1;
        int count = 0;
        for (int i = 0; i < n; i++) {
            int cur = graph[i][0];
            boolean isTrue = true;
            for (int j = 1; j < n; j++) {
                next = graph[i][j];
                if (cur != next) {
                    if (cur + 1 == next) {
                        if (j >= l) {
                            boolean canSlide = true;
                            for(int k = 1; k <= l; k++) {
                                if (graph[i][j - k] != cur) {
                                    canSlide = false;
                                    break;
                                }
                                if (isUsed[j - k]) {
                                    canSlide = false;
                                    break;
                                }
                            }
                            if (canSlide) {
                                for (int k = 1; k <= l; k++) {
                                    isUsed[j-k] = true;
                                }
                                cur = next;
                            } else {
                                isTrue = false;
                                break;
                            }
                        } else {
                            isTrue = false;
                            break;
                        }
                    } else if (cur -1 == next) {
                        if (j + l <= n) {
                            boolean canSlide = true;
                            for (int k = 0; k < l; k++) {
                                if (graph[i][j+k] != next) {
                                    canSlide = false;
                                    break;
                                }
                                if (isUsed[j + k]) {
                                    canSlide = false;
                                    break;
                                }
                            }
                            if (canSlide) {
                                for (int k = 0; k < l; k++) {
                                    isUsed[j+k] = true;
                                }
                                cur = next;
                            } else {
                                isTrue = false;
                                break;
                            }
                        } else {
                            isTrue = false;
                            break;
                        }
                    } else {
                        isTrue = false;
                        break;
                    }
                }
            }
            if (isTrue) {
                count ++;
            }
            Arrays.fill(isUsed, false);
        }

        for (int i = 0; i < n; i++) {
            int cur = graph[0][i];
            boolean isTrue = true;
            for (int j = 1; j < n; j++) {
                next = graph[j][i];
                if (cur != next) {
                    if (cur + 1 == next) {
                        if (j >= l) {
                            boolean canSlide = true;
                            for(int k = 1; k <= l; k++) {
                                if (graph[j - k][i] != cur) {
                                    canSlide = false;
                                    break;
                                }
                                if (isUsed[j - k]) {
                                    canSlide = false;
                                    break;
                                }
                            }
                            if (canSlide) {
                                for (int k = 1; k <= l; k++) {
                                    isUsed[j-k] = true;
                                }
                                cur = next;
                            } else {
                                isTrue = false;
                                break;
                            }
                        } else {
                            isTrue = false;
                            break;
                        }
                    } else if (cur -1 == next) {
                        if (j + l <= n) {
                            boolean canSlide = true;
                            for (int k = 0; k < l; k++) {
                                if (graph[j+k][i] != next) {
                                    canSlide = false;
                                    break;
                                }
                                if (isUsed[j + k]) {
                                    canSlide = false;
                                    break;
                                }
                            }
                            if (canSlide) {
                                for (int k = 0; k < l; k++) {
                                    isUsed[j+k] = true;
                                }
                                cur = next;
                            } else {
                                isTrue = false;
                                break;
                            }
                        } else {
                            isTrue = false;
                            break;
                        }
                    } else {
                        isTrue = false;
                        break;
                    }
                }
            }
            if (isTrue) {
                count ++;
            }
            Arrays.fill(isUsed, false);
        }
        System.out.println(count);
    }
}
