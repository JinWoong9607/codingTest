class Solution {
    public int[] solution(int n, int s) {
        if (s/n<1) return new int[]{-1};
        else {
            int [] answer = new int [n];
            if (s%n==0) {
                for (int i = 0; i < n; i++) {
                    answer[i] = s/n;
                }
            } else {
                for (int i = 0; i < n-s%n; i++) {
                    answer[i] = s/n;
                }
                for (int i = n-s%n; i < n; i++) {
                    answer[i] = s/n+1;
                }
            }
            return answer;
        }
    }
}