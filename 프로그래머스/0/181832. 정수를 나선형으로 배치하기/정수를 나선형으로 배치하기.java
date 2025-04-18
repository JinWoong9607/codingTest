class Solution {
    static int[][] answer;
    public int[][] solution(int n) {
        answer = new int[n][n];
        recursion(n, 0, 1);
        return answer;
    }
    public static void recursion(int n, int level, int count) {
        if (n == 1) {
            answer[level][level] = count++;
        } else if (n <= 0) {
            return;
        } else {
            for (int i=0; i<n; i++) {
                answer[level][level+i] = count++;
            }
            for (int i =1; i<n; i++) {
                answer[level+i][n-1+level] = count++;
            }
            for (int i = 1; i<n; i++) {
                answer[n-1+level][n-1+level-i] = count++;
            }
            for (int i = 1; i<n-1; i++) {
                answer[n-1+level-i][level] = count++;
            }
            recursion(n-2, level+1, count);
        }
    }
}