import java.util.ArrayList;
class Solution {
    static ArrayList<Integer> answer;
    public int solution(int[][] triangle) {
        answer = new ArrayList<>();
        answer.add(triangle[0][0]);
        for (int i = 1; i < triangle.length; i++) {
            dp(triangle[i]);
        }
        
        int max= 0;
        
        for (int i = 0; i < triangle.length;i++) {
            max = answer.get(i) > max ? answer.get(i) : max;
        }
        return max;
    }
    
    public void dp(int[] nextArray) {
        ArrayList<Integer> nextAnswer = new ArrayList<>();
        for (int i = 0; i < nextArray.length; i++) {
            if (i == 0) {
                nextAnswer.add(answer.get(0) + nextArray[0]);
            } else if (i < nextArray.length - 1) {
                nextAnswer.add(answer.get(i) > answer.get(i-1) ? answer.get(i) + nextArray[i] : answer.get(i-1) + nextArray[i]);
            } else {
                nextAnswer.add(answer.get(i - 1) + nextArray[i]);
            }
            
        }
        answer = nextAnswer;
    }
    
}