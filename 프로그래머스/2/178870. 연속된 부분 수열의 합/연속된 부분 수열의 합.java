class Solution {
    public int[] solution(int[] sequence, int k) {
        int start = 0;
        int end = 0;
        int sum = sequence[0];
        int[] answer = new int[2];
        answer[0] = 0;
        answer[1] = Integer.MAX_VALUE;
         while (start <= end && end < sequence.length) {
            if (sum < k) {
                end++;
                if (end < sequence.length) {
                    sum += sequence[end];
                }
            } else if (sum > k) {
                sum -= sequence[start];
                start++;
            } else {
                if (answer[1] - answer[0] > end - start) {
                    answer[0] = start;
                    answer[1] = end;
                }
                end++;
                if (end < sequence.length) {
                    sum += sequence[end];
                }
            }
        }
        return answer;
    }
}