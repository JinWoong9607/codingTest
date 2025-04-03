import java.util.ArrayList;
import java.util.Collections;
class Solution {
    public String solution(long n, String[] bans) {
        int[] banLength = new int[12];
        ArrayList<ArrayList<String>> banSize = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            banSize.add(new ArrayList<String>());
        }
        for (String ban : bans) {
            banLength[ban.length()] += 1;
            banSize.get(ban.length()).add(ban);
        } 
        int answerSize = 1;
        long locationCheck = 26;
        long correctLocation = n;
        while (answerSize < 12) {
            correctLocation += banLength[answerSize];
            if (correctLocation > locationCheck) {
                correctLocation -= locationCheck;
                locationCheck *= 26;
            } else {
                correctLocation -= banLength[answerSize];
                break;
            }
            answerSize++;
        }
        Collections.sort(banSize.get(answerSize));
        for (String corrections: banSize.get(answerSize)) {
            long corrected = 0;
            for (int i = 0; i< corrections.length(); i++) {
                char c = corrections.charAt(i);
                corrected = corrected * 26 + (c - 'a');
            }
            if (correctLocation > corrected) {
                correctLocation ++;
            } else {
                break;
            }
        }
        String answer = "";
        correctLocation --;
        if (correctLocation == 0) {
            answer = "a";
        } else {
            while (correctLocation > 0) {
            long alphabet = correctLocation % 26;
            char letter = (char)('a' + alphabet);
            answer = letter + answer;
            correctLocation = correctLocation / 26;
            }
        }
        while (answer.length() < answerSize) {
            answer = 'a' + answer;
        }
        return answer;
    }
    
    
}