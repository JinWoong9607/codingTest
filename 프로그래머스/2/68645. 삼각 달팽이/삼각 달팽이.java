class Solution {
    static int[] answer;
    public int[] solution(int n) {
        int length = (n*(n+1))/2;
        answer = new int[length];
        triangleSnail(0 , 0 ,n, 0);
        return answer;
    }
    static public void triangleSnail(int start, int m, int n, int space) {
        if (n <= 0) {
            return;
        }
        for (int i = 1; i<=n; i++) {
            answer[((i + m - 1)*(i+m))/2 +m/2] = start + i;
        }
        for (int i =1; i<n; i++) {
            answer[((n+m)*(n+m-1))/2+i + m/2] = start + n + i;
        }
        for (int i = n; i>2; i--) {
            answer[((i+m)*(i+m-1))/2 - 1 - m + m/2] = start + 3 * n - i;
        }
        triangleSnail(start+ 3 * n - 3, m + 2, n - 3, 1);
    }
}
