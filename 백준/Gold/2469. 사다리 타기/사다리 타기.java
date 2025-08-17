import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());

        String result = br.readLine();

        String[] ladder = new String[n];
        int hiddenRow = -1;

        for (int i = 0; i < n; i++) {
            ladder[i] = br.readLine();
            if (ladder[i].contains("?")) {
                hiddenRow = i;
            }
        }

        char[] topState = new char[k];
        for (int i = 0; i < k; i++) {
            topState[i] = (char) ('A' + i);
        }

        char[] midState = Arrays.copyOf(topState, k);
        for (int i = 0; i < hiddenRow; i++) {
            midState = simulate(midState, ladder[i]);
        }

        char[] targetMidState = new char[k];
        for (int i = 0; i < k; i++) {
            targetMidState[i] = result.charAt(i);
        }

        for (int i = n - 1; i > hiddenRow; i--) {
            targetMidState = reverseSimulate(targetMidState, ladder[i]);
        }

        StringBuilder answer = new StringBuilder();
        char[] currentState = Arrays.copyOf(midState, k);

        for (int i = 0; i < k - 1; i++) {
            if (currentState[i] == targetMidState[i]) {
                answer.append('*');
            } else if (i + 1 < k && currentState[i] == targetMidState[i + 1] &&
                    currentState[i + 1] == targetMidState[i]) {
                answer.append('-');
                char temp = currentState[i];
                currentState[i] = currentState[i + 1];
                currentState[i + 1] = temp;
            } else {
                System.out.println("x".repeat(k - 1));
                return;
            }
        }

        if (Arrays.equals(currentState, targetMidState)) {
            System.out.println(answer.toString());
        } else {
            System.out.println("x".repeat(k - 1));
        }
    }

    private static char[] simulate(char[] state, String ladder) {
        char[] newState = Arrays.copyOf(state, state.length);

        for (int i = 0; i < ladder.length(); i++) {
            if (ladder.charAt(i) == '-') {
                char temp = newState[i];
                newState[i] = newState[i + 1];
                newState[i + 1] = temp;
            }
        }

        return newState;
    }

    private static char[] reverseSimulate(char[] state, String ladder) {
        char[] newState = Arrays.copyOf(state, state.length);

        for (int i = 0; i < ladder.length(); i++) {
            if (ladder.charAt(i) == '-') {
                char temp = newState[i];
                newState[i] = newState[i + 1];
                newState[i + 1] = temp;
            }
        }

        return newState;
    }
}