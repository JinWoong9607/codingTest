import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] school;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        school = new int[n][n];
        
        int[][] studentLikes = new int[n*n + 1][4];
        int[] studentOrder = new int[n*n];

        for(int i = 0; i < n*n; i++) {
            st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken());
            studentOrder[i] = student;
            for (int j = 0; j < 4; j++) {
                studentLikes[student][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < n*n; i++) {
            int student = studentOrder[i];
            int[] likes = studentLikes[student];
            placeStudent(student, likes);
        }

        int totalSatisfaction = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                int student = school[i][j];
                int[] likes = studentLikes[student];
                int friendCount = countAdjacentFriends(i, j, likes);

                if(friendCount == 1) totalSatisfaction += 1;
                else if(friendCount == 2) totalSatisfaction += 10;
                else if(friendCount == 3) totalSatisfaction += 100;
                else if(friendCount == 4) totalSatisfaction += 1000;
            }
        }

        System.out.println(totalSatisfaction);
    }

    static void placeStudent(int student, int[] likes) {
        int bestX = -1, bestY = -1;
        int maxFriends = -1;
        int maxEmpty = -1;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(school[i][j] != 0) continue;

                int friendCount = countAdjacentFriends(i, j, likes);
                int emptyCount = countAdjacentEmpty(i, j);

                if(friendCount > maxFriends ||
                        (friendCount == maxFriends && emptyCount > maxEmpty) ||
                        (friendCount == maxFriends && emptyCount == maxEmpty && isBetterPosition(i, j, bestX, bestY))) {
                    bestX = i;
                    bestY = j;
                    maxFriends = friendCount;
                    maxEmpty = emptyCount;
                }
            }
        }

        school[bestX][bestY] = student;
    }

    static int countAdjacentFriends(int x, int y, int[] likes) {
        int count = 0;
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx >= 0 && nx < n && ny >= 0 && ny < n) {
                for(int j = 0; j < 4; j++) {
                    if(school[nx][ny] == likes[j]) {
                        count++;
                        break;
                    }
                }
            }
        }
        return count;
    }

    static int countAdjacentEmpty(int x, int y) {
        int count = 0;
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx >= 0 && nx < n && ny >= 0 && ny < n && school[nx][ny] == 0) {
                count++;
            }
        }
        return count;
    }

    static boolean isBetterPosition(int x1, int y1, int x2, int y2) {
        if(x2 == -1) return true; // 첫 번째 후보
        if(x1 < x2) return true;
        if(x1 == x2 && y1 < y2) return true;
        return false;
    }
}