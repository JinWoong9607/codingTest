
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<ArrayList<Integer>> node;
    static boolean[] visited;
    static Queue<Integer> day = new LinkedList<>();
    static int answer = 0;
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        node = new ArrayList<>(m*n);
        for (int i =0; i<m*n; i++) {
            node.add(new ArrayList<>());
        }

        visited = new boolean[n*m];
        ArrayList<Integer> tomato = new ArrayList<>();
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                int ripe = Integer.parseInt(st.nextToken());
                tomato.add(ripe);
            }
        }
        if(!tomato.contains(0)){
            System.out.println(0);
            return;
        }
        for (int i=0; i<m*n; i++) {
            if (tomato.get(i) != -1) {
                if (i>=n) {
                    if (tomato.get(i-n)==0) {
                        node.get(i).add(i-n);
                    }
                }
                if (i>=1) {
                    if (tomato.get(i-1)==0&&i%n!=0) {
                        node.get(i).add(i-1);
                    }
                }
                if (i<n*m-n) {
                    if (tomato.get(i+n)==0) {
                        node.get(i).add(i+n);
                    }
                }
                if (i<n*m-1) {
                    if (tomato.get(i+1)==0&&(i+1)%n!=0) {
                        node.get(i).add(i+1);
                    }
                }
            }
            if (tomato.get(i)==1) {
                visited[i] = true;
                day.add(i);
            }
            if (tomato.get(i)==-1) {
                visited[i] = true;
            }
        }
        System.out.print(tomatocheck(node, visited, day));
    }

    static Integer tomatocheck(ArrayList<ArrayList<Integer>> node, boolean[] visited, Queue<Integer> day) {
        Queue<Integer> nextday = new LinkedList<>();
        while(!day.isEmpty()) {
            Integer trial = day.remove();
            for (Integer ripe: node.get(trial)) {
                if (!visited[ripe]) {
                    visited[ripe] = true;
                    nextday.add(ripe);
                }
            }
            if(day.isEmpty()) {
                answer++;
                day.addAll(nextday);
                nextday.clear();
            }
        }
        answer--;
        for (boolean all: visited) {
            if(!all) {
                answer = -1;
            }
        }
        return answer;
    }
}
