import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<ArrayList<Integer>> graph;
    static Queue<int[]> path;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        char[][] map = new char[n+1][m+1];
        int red = 0;
        int blue = 0;
        int hole = 0;
        for (int j=1; j<=m; j++) {
            String mapping = br.readLine();
            for (int i = 1; i <= mapping.length(); i++) {
                map[i][j] = mapping.charAt(i-1);
                if (map[i][j]=='R') {
                    red = n*(j-1)+i;
                }
                if (map[i][j]=='B') {
                    blue = n*(j-1)+i;
                }
                if (map[i][j]=='O') {
                    hole = n*(j-1)+i;
                }
            }
        }

        graph = new ArrayList<>();
        for (int i=1; i<=n*m; i++) {
            graph.add(new ArrayList<>());
        }
        for (int j=1; j<m; j++) {
            for (int i=1; i<n; i++) {
                if (map[i][j]=='.'||map[i][j]=='B'||map[i][j]=='R'||map[i][j]=='O') {
                    if (map[i+1][j]=='.'||map[i+1][j]=='B'||map[i+1][j]=='R'||map[i+1][j]=='O') {
                        graph.get(n*(j-1)+i).add(n*(j-1)+i+1);
                        graph.get(n*(j-1)+i+1).add(n*(j-1)+i);
                    }
                    if (map[i][j+1]=='.'||map[i][j+1]=='B'||map[i][j+1]=='R'||map[i][j+1]=='O') {
                        graph.get(n*(j-1)+i).add(n*(j)+i);
                        graph.get(n*(j)+i).add(n*(j-1)+i);
                    }
                }
            }
        }
        bw.write(String.valueOf(bruteforce(n, m, red, blue ,hole)));
        bw.close();
    }
    public static int bruteforce(int n, int m, int red, int blue, int hole) {
        int trial =1;
        path = new LinkedList<>();
        int[] array = new int[2];
        array[0] = red;
        array[1] = blue;
        boolean[] visited = new boolean[2];
        for (int i=0; i<visited.length; i++) {
            visited[i] = false;
        }
        path.add(array);
        while (trial<=10) {
            Queue<int[]> next = new LinkedList<>();
            for (int[] location : path) {
                int redlocation = location[0];
                int bluelocation = location[1];
                visited[0] = false;
                visited[1] = false;

                while(graph.get(redlocation).contains(redlocation-1)) {
                    redlocation -=1;
                    if (redlocation == hole) {
                        visited[0] = true;
                    }
                }
                while(graph.get(bluelocation).contains(bluelocation-1)) {
                    bluelocation -= 1;
                    if (bluelocation == hole) {
                        visited[1] = true;
                    }
                }
                if (redlocation==bluelocation) {
                    if (location[0]>location[1]) {
                        redlocation += 1;
                    } else {
                        bluelocation += 1;
                    }
                }
                if (visited[0]&&!visited[1]) {
                    return trial;
                }
                if (!visited[0]&&!visited[1]) {
                    if (redlocation!=location[0]||bluelocation!=location[1]) {
                        int[] newlocation = new int[2];
                        newlocation[0] = redlocation;
                        newlocation[1] = bluelocation;
                        next.add(newlocation);
                    }
                }

                redlocation = location[0];
                bluelocation = location[1];
                visited[0] = false;
                visited[1] = false;
                while(graph.get(redlocation).contains(redlocation+1)) {
                    redlocation +=1;
                    if (redlocation == hole) {
                        visited[0] = true;
                    }
                }
                while(graph.get(bluelocation).contains(bluelocation+1)) {
                    bluelocation += 1;
                    if (bluelocation == hole) {
                        visited[1] = true;
                    }
                }
                if (redlocation==bluelocation) {
                    if (location[0]>location[1]) {
                        bluelocation -= 1;
                    } else {
                        redlocation -= 1;
                    }
                }
                if (visited[0]&&!visited[1]) {
                    return trial;
                }
                if (!visited[0]&&!visited[1]) {
                    if (redlocation!=location[0]||bluelocation!=location[1]) {
                        int[] newlocation = new int[2];
                        newlocation[0] = redlocation;
                        newlocation[1] = bluelocation;
                        next.add(newlocation);
                    }
                }

                redlocation = location[0];
                bluelocation = location[1];
                visited[0] = false;
                visited[1] = false;
                while(graph.get(redlocation).contains(redlocation+n)) {
                    redlocation +=n;
                    if (redlocation == hole) {
                        visited[0] = true;
                    }
                }
                while(graph.get(bluelocation).contains(bluelocation+n)) {
                    bluelocation += n;
                    if (bluelocation == hole) {
                        visited[1] = true;
                    }
                }
                if (redlocation==bluelocation) {
                    if (location[0]>location[1]) {
                        bluelocation -= n;
                    } else {
                        redlocation -= n;
                    }
                }
                if (visited[0]&&!visited[1]) {
                    return trial;
                }
                if (!visited[0]&&!visited[1]) {
                    if (redlocation!=location[0]||bluelocation!=location[1]) {
                        int[] newlocation = new int[2];
                        newlocation[0] = redlocation;
                        newlocation[1] = bluelocation;
                        next.add(newlocation);
                    }
                }

                redlocation = location[0];
                bluelocation = location[1];
                visited[0] = false;
                visited[1] = false;
                while(graph.get(redlocation).contains(redlocation-n)) {
                    redlocation -=n;
                    if (redlocation == hole) {
                        visited[0] = true;
                    }
                }
                while(graph.get(bluelocation).contains(bluelocation-n)) {
                    bluelocation -= n;
                    if (bluelocation == hole) {
                        visited[1] = true;
                    }
                }
                if (redlocation==bluelocation) {
                    if (location[0]>location[1]) {
                        redlocation += n;
                    } else {
                        bluelocation += n;
                    }
                }
                if (visited[0]&&!visited[1]) {
                    return trial;
                }
                if (!visited[0]&&!visited[1]) {
                    if (redlocation!=location[0]||bluelocation!=location[1]) {
                        int[] newlocation = new int[2];
                        newlocation[0] = redlocation;
                        newlocation[1] = bluelocation;
                        next.add(newlocation);
                    }
                }
            }
            trial++;
            path = next;
        }
        if (trial == 11) {
            return -1;
        }
        else {
            return trial;
        }
    }
}
