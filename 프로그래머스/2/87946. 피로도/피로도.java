class Solution {
    static boolean[] visited;
    static int answer = 0;
    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        search(k, dungeons);
        return answer;
    }
    
    public static void search(int k, int[][] dungeons) {
        for (int i = 0; i<dungeons.length; i++) {
            int count = 0;
            if (k >= dungeons[i][0] && !visited[i]) {
                visited[i] = true;
                for (int j = 0; j < dungeons.length; j++) {
                    if (visited[j]) {
                        count++;
                    }
                }
                answer = answer > count ? answer : count;
                search(k - dungeons[i][1], dungeons);
                visited[i] = false;
            }
        }
    }
}