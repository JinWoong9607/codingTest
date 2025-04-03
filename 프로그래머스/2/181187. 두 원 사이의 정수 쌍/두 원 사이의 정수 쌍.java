import java.util.HashMap;
class Solution {
    public long solution(int r1, int r2) {
        HashMap<Integer, Long> square = new HashMap<>();
        for (int i=0; i<=r2; i++) {
            square.put(i, (long) i*i);
        }
        long inside = 0;
        int r1count = r1;
        long r1square = (long) r1 * r1;
        for (int i = 0; i < r1; i++) {
            while (r1count >=1 && r1square - (long) i*i <= square.get(r1count)) {
                r1count --;
            }
            inside += (long) r1count;
        }
        
        long outside = 0;
        int r2count = r2;
        long r2square = (long) r2 * r2;
        for (int i = 0; i < r2; i++) {
            while (r2count >= 0 && r2square - (long) i * i < square.get(r2count)) {
                r2count --;
            }
            outside += (long) r2count;
        }
        
        long answer = 4*(outside-inside);
        return answer;
    }
}