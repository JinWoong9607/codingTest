import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while(!(line = br.readLine()).equals("end")) {
            boolean valid = true;
            boolean bingo = false;
            int o = 0;
            int x = 0;
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == 'O') {
                    o++;
                }
                if (line.charAt(i) == 'X') {
                    x++;
                }
            }
            if (x - o >= 2 || x - o < 0) {
                valid = false;
            }
            int start = 0;
            while (start < 9) {
                int end = start + 3;
                if (line.charAt(start) != '.' && line.charAt(start) == line.charAt(start + 1) && line.charAt(start + 1) == line.charAt(start + 2)) {
                    bingo = true;
                    while (end < 9) {
                        if (line.charAt(end) != '.' && line.charAt(end) == line.charAt(end + 1) && line.charAt(end + 1) == line.charAt(end + 2)) {
                            valid = false;
                        }
                        end+=3;
                    }
                    if (valid) {
                        if (line.charAt(start) == 'O') {
                            if (x > o) {
                                valid = false;
                            }
                        } else {
                            if (x == o) {
                                valid = false;
                            }
                        }
                    }
                }
                start += 3;
            }
            start = 0;
            while (start < 3) {
                int end = start + 1;
                if (line.charAt(start) != '.' && line.charAt(start) == line.charAt(start + 3) && line.charAt(start + 3) == line.charAt(start + 6)) {
                    bingo = true;
                    while (end < 3) {
                        if (line.charAt(end) != '.' && line.charAt(end) == line.charAt(end + 3) && line.charAt(end + 3) == line.charAt(end + 6)) {
                            valid = false;
                        }
                        end += 1;
                    }
                    if (valid) {
                        if (line.charAt(start) == 'O') {
                            if (x > o) {
                                valid = false;
                            }
                        } else {
                            if (x == o) {
                                valid = false;
                            }
                        }
                    }
                }
                start += 1;
            }
            if (line.charAt(0) != '.' && line.charAt(0)==line.charAt(4) && line.charAt(4) == line.charAt(8)) {
                bingo = true;
                if (valid) {
                    if (line.charAt(0) == 'O') {
                        if (x > o) {
                            valid = false;
                        }
                    } else {
                        if (x == o) {
                            valid = false;
                        }
                    }
                }
            }
            if (line.charAt(2) != '.' && line.charAt(2) == line.charAt(4) && line.charAt(4) == line.charAt(6)) {
                bingo = true;
                if (valid) {
                    if (line.charAt(2) == 'O') {
                        if (x > o) {
                            valid = false;
                        }
                    } else {
                        if (x == o) {
                            valid = false;
                        }
                    }
                }
            }
            if (!bingo) {
                for (char c : line.toCharArray()) {
                    if (c == '.') {
                        valid = false;
                        break;
                    }
                }
            }
            if (x < 1 || o < 1) {
                valid = false;
            }
            if (valid) {
                System.out.println("valid");
            } else {
                System.out.println("invalid");
            }
        }
    }
}
