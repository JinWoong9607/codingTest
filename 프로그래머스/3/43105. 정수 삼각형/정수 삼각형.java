class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        for (int i = 1; i< triangle.length; i++) {
            for (int j = 0; j < triangle[i].length; j++) {
                if (j == 0) {
                    triangle[i][j] = triangle[i - 1][j] + triangle[i][j];
                } else if (j < triangle[i].length - 1) {
                    int num = triangle[i][j];
                    triangle[i][j] = Math.max(triangle[i-1][j-1] + num , triangle[i-1][j] + num);
                } else {
                    triangle[i][j] = triangle[i][j] + triangle[i-1][j-1];
                }
            }
        }
        
        for (int number : triangle[triangle.length - 1]) {
            answer = answer > number ? answer : number;
        }
        
        return answer;
    }
}